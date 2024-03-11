package com.example.demo.service;

import com.example.demo.model.Cartao;
import com.example.demo.model.DTO.ListUsuariosDTO;
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

    public ResponseEntity findAll(){

        try{
            return ResponseEntity.ok().body(cartao.findAll());

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity addCartao(Cartao c){

        try {
            return ResponseEntity.created(URI.create("./cartao")).body(cartao.save(new Cartao(c.getId(), c.isStatusEntrada())));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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

     public Optional<Cartao> findById(String id){
        return cartao.findById(id);
     }

    public Iterable<Cartao> seachPosseId(Integer id){
        return cartao.seachPosseId(id);
    }
}