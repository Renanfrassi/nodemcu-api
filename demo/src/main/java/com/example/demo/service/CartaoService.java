package com.example.demo.service;

import com.example.demo.model.Cartao;
import com.example.demo.model.DTO.ListUsuariosDTO;
import com.example.demo.model.Fechadura;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CartaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

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

    }
}