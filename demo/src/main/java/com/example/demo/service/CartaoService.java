package com.example.demo.service;

import com.example.demo.model.Cartao;
import com.example.demo.model.DTO.ListUsuariosDTO;
import com.example.demo.model.DTO.SlotCartaoDTO;
import com.example.demo.model.Fechadura;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CartaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Calendar;
import java.util.Optional;
import java.util.TimeZone;

/**
 * CartaoService
 */
@Service
public class CartaoService {
    @Autowired
    private CartaoRepository cartao;
    @Autowired
    private SlotService slot;
    @Autowired
    private PermissoesService permissao;
    @Autowired
    private PosseCartaoService posseCartao;
<<<<<<< HEAD

    @Autowired
    private FechaduraService fechadura;
=======
>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312

    @Autowired
    private FechaduraService fechadura;

    public Iterable<Cartao> findAll() throws Exception {
            return cartao.findAll();
    }

    public Cartao addCartao(Cartao c) throws Exception {
        if(c.getId() == null ){
            throw new Exception("O número do Cartão não poderá ser vazio!");
        }

        cartao.save(new Cartao(c.getId(), c.isStatusEntrada()));

        return c;

    }

    public String deleteCartao(String id){
        cartao.deleteById(id);

        return "Deletado com sucesso";
    }

    public ResponseEntity updateCartao(Cartao c){

        try{

            if(cartao.findById(c.getId()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Cartão não registrado");

            }

            return ResponseEntity.ok().body(cartao.save(c));

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
     }

     public Optional<Cartao> findById(String id) throws Exception{
        return cartao.findById(id);
     }


<<<<<<< HEAD
    public Iterable<Cartao> seachPosseId(Integer id){
        return cartao.seachPosseId(id);
    }

    public ResponseEntity deleteCartaoPossePermissao(String idCartao, Integer idUsuario){
        try{

            fechadura.getAllFechadura().forEach(item -> {
                slot.deleteSlotByFechaduraCartao(item, cartao.findById(idCartao).get());
            });

            permissao.deletePermissaoByCartao(idCartao);

            posseCartao.deletePosseCartaoByUsuarioCartao(idCartao, idUsuario);

            cartao.deleteById(idCartao);

            return ResponseEntity.ok().body(cartao.seachPosseId(idUsuario));

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
=======
    public Iterable<Cartao> seachPosseId(Integer id) throws Exception {
        return cartao.seachPosseId(id);
    }

    public Iterable<Cartao> deleteCartaoPossePermissao(String idCartao, Integer idUsuario) throws Exception{

        for (Fechadura item : fechadura.getAllFechadura()) {
            slot.deleteSlotByFechaduraCartao(item, cartao.findById(idCartao).get());
        }

        permissao.deletePermissaoByCartao(idCartao);

        posseCartao.deletePosseCartaoByUsuarioCartao(idCartao, idUsuario);

        cartao.deleteById(idCartao);

        return cartao.seachPosseId(idUsuario);

>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312
    }

    public boolean verifySlot(SlotCartaoDTO dto){

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));;
        calendar.setTime(dto.getDataAtual());

        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        String horaAtual = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));

        return slot.verifySlot(dto.getIdCartao(), dto.getIdFechadura(), horaAtual, diaSemana);
    }

}