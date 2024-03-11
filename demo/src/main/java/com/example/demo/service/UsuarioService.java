package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.model.DTO.ListUsuariosDTO;
import com.example.demo.model.DTO.PermissoesDTO;
import com.example.demo.model.DTO.PosseCartaoDTO;
import com.example.demo.model.DTO.UsuarioSlotDTO;
import com.example.demo.repository.CartaoRepository;
import com.example.demo.repository.FechaduraRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class UsuarioService{
    @Autowired
    private UsuarioRepository usuario;
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
    
    public ResponseEntity findAll(){
        try{

            Iterable<ListUsuariosDTO> listUsuariosDTOS = usuario.findAllUsuarios();

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
            return ResponseEntity.created(URI.create("./usuario")).body(usuario.save(u));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public String deleteUsuario(int id){
        usuario.deleteById(id);

        return "Deletado com sucesso";
    }

    public ResponseEntity updateUsuario(Usuario u){

        try{

            if(usuario.findById(u.getId()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Usuário não registrado");

            }

            return ResponseEntity.ok().body(usuario.save(u));

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
     }

    public ResponseEntity findUsuarioById(Integer id){
        try{

            Usuario u = usuario.findById(id).get();

            return ResponseEntity.ok().body(u);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Optional<Usuario> findById(Integer id){
        return usuario.findById(id);
    }

    public ResponseEntity findUsuarioPermissaoId(Integer idUsuario, String idCartao){
        try{

            UsuarioSlotDTO u = usuario.findUsuarioPermissaoId(idUsuario, idCartao);

            return ResponseEntity.ok().body(u);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}