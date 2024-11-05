package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.model.DTO.*;
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

    public Iterable<ListUsuariosDTO> findAll() throws Exception{

        Iterable<ListUsuariosDTO> listUsuariosDTOS = usuario.findAllUsuarios();

        for (ListUsuariosDTO item : listUsuariosDTOS) {
            item.setCartoes(cartaoService.seachPosseId(item.getId()));
        }
        return listUsuariosDTOS;

    }

    public UsuarioSlotDTO addSlot(UsuarioSlotDTO usuarioSlotDTO) throws Exception {

            if(usuarioSlotDTO.getIdCartao() == null){
                throw new Exception("O número do Cartão não pode ser vazio!");
            }

            if(usuarioSlotDTO.getIdFechadura() == null){
                throw new Exception("A fechadura não pode ser vazia!");
            }

            if(usuarioSlotDTO.getDataInicio() == null){
                throw new Exception("A Data de Início não pode ser vazia!");
            }

            if(usuarioSlotDTO.getDataFim() == null){
                throw new Exception("A Data Final não pode ser vazia!");
            }

            if(usuarioSlotDTO.getDataInicio().after(usuarioSlotDTO.getDataFim())){
                throw new Exception("A Data de Início não pode ser superior que a Data Fim!");
            }

            cartaoService.addCartao(new Cartao(usuarioSlotDTO.getIdCartao(), true));

            posseCartaoService.addPosseCartao(new PosseCartaoDTO(usuarioSlotDTO.getIdUsuario(), usuarioSlotDTO.getIdCartao(), usuarioSlotDTO.getDataInicio(), usuarioSlotDTO.getDataFim()));

            permissoesService.addPermissoes(new PermissoesDTO(usuarioSlotDTO.getIdFechadura(), usuarioSlotDTO.getIdCartao()));


<<<<<<< HEAD
                        SlotKey slotKey = new SlotKey(p,item.getDiaSemana()); // Suponha que você tenha uma instância de SlotKey ou adapte isso conforme necessário
                        Slot slot = new Slot();
                        slot.setId(slotKey); // Atribua a chave primária composta // Atribua um valor para o atributo diaSemana
                        slot.setHoraInicio(item.getHoraInicio()); // Atribua um valor para o atributo horaInicio
                        slot.setHoraFim(item.getHoraFim()); // Atribua um valor para o atributo horaFim
=======
        for (SlotDTO item : usuarioSlotDTO.getListaSlot()) {
            PermissoesKey chave = new PermissoesKey();
            chave.setFechadura(fechaduraService.findById(usuarioSlotDTO.getIdFechadura()).get());
            chave.setCartao(cartaoService.findById(usuarioSlotDTO.getIdCartao()).get());
            Permissoes p = new Permissoes();
            p.setId(chave);

            SlotKey slotKey = new SlotKey(p, item.getDiaSemana()); // Suponha que você tenha uma instância de SlotKey ou adapte isso conforme necessário
            Slot slot = new Slot();
            slot.setId(slotKey); // Atribua a chave primária composta // Atribua um valor para o atributo diaSemana
            slot.setHoraInicio(item.getHoraInicio()); // Atribua um valor para o atributo horaInicio
            slot.setHoraFim(item.getHoraFim()); // Atribua um valor para o atributo horaFim
>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312

            slotService.addSlot(slot);
        }

        return usuarioSlotDTO;

    }

    public Usuario addUsuario(Usuario u) throws Exception {

            if(u.getNome() == null){
                throw new Exception("O nome não pode ser vazio!");
            }

            if(u.getMatricula() == null){
                throw new Exception("A matrícula não pode ser vazia!");
            }

            if(u.getDataNasc() == null){
                throw new Exception("A data de nascimento não pode ser vazia!");
            }
            usuario.save(u);
            return u;

    }

    public String deleteUsuario(int id){
        usuario.deleteById(id);

        return "Deletado com sucesso";
    }

    public Usuario updateUsuario(Usuario u) throws Exception {

            if(u.getNome() == null){
                throw new Exception("O nome não pode ser vazio!");
            }

            if(u.getMatricula() == null){
                throw new Exception("A matrícula não pode ser vazia!");
            }

            if(u.getDataNasc() == null){
                throw new Exception("A data de nascimento não pode ser vazia!");
            }

            if(usuario.findById(u.getId()).stream().count() == 0){

                throw new Exception("Usuário não registrado");

            }
            usuario.save(u);
            return u;

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

<<<<<<< HEAD
    public ResponseEntity findUsuarioSlot(FilterDTO filter){
        try {
=======
    public UsuarioSlotDTO findUsuarioSlot(FilterDTO filter) throws Exception {
>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312
            UsuarioSlotDTO usuarioSlotDTO = new UsuarioSlotDTO();

            PosseCartaoDTO posseCartaoDTO = posseCartaoService.findPosseCartaoByUsuarioCartao(filter.getIdCartao(), filter.getIdUsuario());

            usuarioSlotDTO.setIdUsuario(filter.getIdUsuario());
            usuarioSlotDTO.setIdCartao(filter.getIdCartao());

            usuarioSlotDTO.setDataInicio(posseCartaoDTO.getDataInicio());
            usuarioSlotDTO.setDataFim(posseCartaoDTO.getDataFim());

            usuarioSlotDTO.setListaSlot(slotService.findSlotByCartaoFechadura(filter.getIdCartao(), filter.getIdFechadura()));

<<<<<<< HEAD
            return ResponseEntity.ok().body(usuarioSlotDTO);
        } catch (java.lang.Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public ResponseEntity updateSlot(UsuarioSlotDTO usuarioSlotDTO){
        try {
=======
            return usuarioSlotDTO;

    }

    public UsuarioSlotDTO updateSlot(UsuarioSlotDTO usuarioSlotDTO) throws Exception {

            if(usuarioSlotDTO.getIdCartao() == null){
                throw new Exception("O número do Cartão não pode ser vazio!");
            }

            if(usuarioSlotDTO.getDataInicio() == null){
                throw new Exception("A Data de Início não pode ser vazia!");
            }

            if(usuarioSlotDTO.getDataFim() == null){
                throw new Exception("A Data Final não pode ser vazia!");
            }

            if(usuarioSlotDTO.getDataInicio().after(usuarioSlotDTO.getDataFim())){
                throw new Exception("A Data de Início não pode ser superior que a Data Fim!");
            }

            if(usuarioSlotDTO.getIdFechadura() == null){
                throw new Exception("A fechadura não pode ser vazia!");
            }

>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312

            cartaoService.addCartao(new Cartao(usuarioSlotDTO.getIdCartao(), true));

            posseCartaoService.addPosseCartao(new PosseCartaoDTO(usuarioSlotDTO.getIdUsuario(), usuarioSlotDTO.getIdCartao(), usuarioSlotDTO.getDataInicio(), usuarioSlotDTO.getDataFim()));

            permissoesService.addPermissoes(new PermissoesDTO(usuarioSlotDTO.getIdFechadura(), usuarioSlotDTO.getIdCartao()));

            slotService.deleteSlotByFechaduraCartao(fechaduraService.findById(usuarioSlotDTO.getIdFechadura()).get(), cartaoService.findById(usuarioSlotDTO.getIdCartao()).get());

<<<<<<< HEAD
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

    public ResponseEntity deleteUsuarioPosse(Integer idUsuario){

        try{
            Iterable<Cartao> cartaos = cartaoService.seachPosseId(idUsuario);

            cartaos.forEach(cartao -> {
                cartaoService.deleteCartaoPossePermissao(cartao.getId(), idUsuario);
            });

            usuario.deleteById(idUsuario);

            return ResponseEntity.ok().body(usuario.findAll());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

=======
        for (SlotDTO item : usuarioSlotDTO.getListaSlot()) {
            PermissoesKey chave = new PermissoesKey();
            chave.setFechadura(fechaduraService.findById(usuarioSlotDTO.getIdFechadura()).get());
            chave.setCartao(cartaoService.findById(usuarioSlotDTO.getIdCartao()).get());
            Permissoes p = new Permissoes();
            p.setId(chave);

            SlotKey slotKey = new SlotKey(p, item.getDiaSemana()); // Suponha que você tenha uma instância de SlotKey ou adapte isso conforme necessário
            Slot slot = new Slot();
            slot.setId(slotKey); // Atribua a chave primária composta // Atribua um valor para o atributo diaSemana
            slot.setHoraInicio(item.getHoraInicio()); // Atribua um valor para o atributo horaInicio
            slot.setHoraFim(item.getHoraFim()); // Atribua um valor para o atributo horaFim

            slotService.addSlot(slot);
        }

        return usuarioSlotDTO;

    }

    public Iterable<Usuario> deleteUsuarioPosse(Integer idUsuario) throws Exception{

            Iterable<Cartao> cartaos = cartaoService.seachPosseId(idUsuario);

        for (Cartao cartao : cartaos) {
            cartaoService.deleteCartaoPossePermissao(cartao.getId(), idUsuario);
        }

        usuario.deleteById(idUsuario);

            return usuario.findAll();


>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312
    }

}