package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.model.DTO.ListUsuariosDTO;
import com.example.demo.model.DTO.PermissoesDTO;
import com.example.demo.model.DTO.PosseCartaoDTO;
import com.example.demo.model.DTO.SlotDTO;
import com.example.demo.model.DTO.UsuarioSlotDTO;
import com.example.demo.repository.CartaoRepository;
import com.example.demo.repository.FechaduraRepository;
import com.example.demo.repository.SlotRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private FechaduraRepository fechaduraRepository;
    @Autowired
    private CartaoService cartaoService;
    @Autowired
    private PosseCartaoService posseCartaoService;
    @Autowired
    private PermissoesService permissoesService;
    @Autowired
    private FechaduraService fechaduraService;
    @Autowired
    private SlotService slotService;
    @PersistenceContext
    private EntityManager entityManager;
    
    public ResponseEntity findAll(){
        try{

            Iterable<ListUsuariosDTO> listUsuariosDTOS = usuarioRepository.findAllUsuarios();

            listUsuariosDTOS.forEach(item -> { item.setCartoes(cartaoService.seachPosseId(item.getId())); });
            return ResponseEntity.ok().body(listUsuariosDTOS);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity addSlot(UsuarioSlotDTO usuarioSlotDTO){
        try {

            cartaoService.addCartao(new Cartao(usuarioSlotDTO.getIdCartao(), true));

            posseCartaoService.addPosseCartao(new PosseCartaoDTO(usuarioSlotDTO.getIdUsuario(), usuarioSlotDTO.getIdCartao(), usuarioSlotDTO.getDataInicio(), usuarioSlotDTO.getDataFim()));

            permissoesService.addPermissoes(new PermissoesDTO(usuarioSlotDTO.getIdFechadura(), usuarioSlotDTO.getIdCartao()));

            usuarioSlotDTO.getListaSlot().forEach(
                    item -> {
                        PermissoesKey chave = new PermissoesKey();
                        chave.setFechadura(fechaduraService.findById(usuarioSlotDTO.getIdFechadura()).get());
                        chave.setCartao(cartaoService.findById(usuarioSlotDTO.getIdCartao()).get());
                        Permissoes p = new Permissoes();
                        p.setId(chave);

                        SlotKey slotKey = new SlotKey(p,item.getDiaSemana()); // Suponha que você tenha uma instância de SlotKey ou adapte isso conforme necessário

                        Slot slot = new Slot();
                        slot.setId(slotKey); // Atribua a chave primária composta // Atribua um valor para o atributo diaSemana
                        slot.setHoraInicio(item.getHoraInicio()); // Atribua um valor para o atributo horaInicio
                        slot.setHoraFim(item.getHoraFim()); // Atribua um valor para o atributo horaFim

                        slotService.addSlot(slot);
                    }
            );

            return ResponseEntity.created(URI.create("./usuario/slot")).body(usuarioSlotDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity addUsuario(Usuario u){
        try {
            return ResponseEntity.created(URI.create("./usuario")).body(usuarioRepository.save(u));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public String deleteUsuario(int id){
        usuarioRepository.deleteById(id);

        return "Deletado com sucesso";
    }

    public ResponseEntity updateUsuario(Usuario u){

        try{

            if(usuarioRepository.findById(u.getId()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Usuário não registrado");

            }

            return ResponseEntity.ok().body(usuarioRepository.save(u));

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
     }

    public ResponseEntity findUsuarioById(Integer id){
        try{

            Usuario u = usuarioRepository.findById(id).get();

            return ResponseEntity.ok().body(u);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Optional<Usuario> findById(Integer id){
        return usuarioRepository.findById(id);
    }

    public ResponseEntity findUsuarioPermissaoId(Integer idUsuario, String idCartao){
        try{

            UsuarioSlotDTO usuarioSlotDTO = usuarioRepository.findUsuarioPermissaoId(idUsuario, idCartao);
            List<SlotDTO> slots = usuarioRepository.findSlotsByUsuarioAndCartao(idUsuario, idCartao);
            System.out.println(slots);

            usuarioSlotDTO.setListaSlot(slots);

            return ResponseEntity.ok().body(usuarioSlotDTO);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional 
    public ResponseEntity updateSlotUsuario(Integer idUsuario, UsuarioSlotDTO usuarioSlotDTO){
    try{
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if(!usuarioOptional.isPresent()){
            return ResponseEntity.badRequest().body("Usuário não registrado");
        }

        Optional<Cartao> cartaoOptional = cartaoRepository.findById(usuarioSlotDTO.getIdCartao());
        if(!cartaoOptional.isPresent()){
            return ResponseEntity.badRequest().body("Cartão não registrado");
        }

        Optional<Fechadura> fechaduraOptional = fechaduraRepository.findById(usuarioSlotDTO.getIdFechadura());
        if(!fechaduraOptional.isPresent()){
            return ResponseEntity.badRequest().body("Fechadura não registrada");
        }
        // Excluir todos os slots associados ao idCartao e idFechadura fornecidos
        slotRepository.deleteAllByPermissoesIdCartaoAndPermissoesIdFechadura(usuarioSlotDTO.getIdCartao(), usuarioSlotDTO.getIdFechadura());

        // Criar novos slots com os dados fornecidos
        for (SlotDTO slotDTO : usuarioSlotDTO.getListaSlot()) {
            Slot slot = new Slot();
            slot.setId(new SlotKey(usuarioSlotDTO.getIdFechadura(), usuarioSlotDTO.getIdCartao(), slotDTO.getDiaSemana()));
            slot.setHoraInicio(slotDTO.getHoraInicio());
            slot.setHoraFim(slotDTO.getHoraFim());
            slotRepository.save(slot);
        }

        return ResponseEntity.ok().body(usuarioSlotDTO);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
    
}

