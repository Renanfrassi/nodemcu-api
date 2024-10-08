package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.model.DTO.PermissoesDTO;
import com.example.demo.repository.CartaoRepository;
import com.example.demo.repository.FechaduraRepository;
import com.example.demo.repository.PermissoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Optional;

@Service
public class PermissoesService{
    @Autowired
    private PermissoesRepository permissao;
    @Autowired
    private CartaoService cartaoService;
    @Autowired
    private FechaduraService fechaduraService;
    
    public ResponseEntity findAll(){

        try{
            return ResponseEntity.ok().body(permissao.findAll());

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity addPermissoes(PermissoesDTO pDTO){
        try {

            if(cartaoService.findById(pDTO.getIdCartao()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Cartão não registrado");

            }

            if(fechaduraService.findById(pDTO.getIdFechadura()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Fechadura não registrada");

            }

            PermissoesKey chave = new PermissoesKey();
            chave.setFechadura(fechaduraService.findById(pDTO.getIdFechadura()).get());
            chave.setCartao(cartaoService.findById(pDTO.getIdCartao()).get());

            Permissoes p = new Permissoes();
            p.setId(chave);

            return ResponseEntity.created(URI.create("./permissao")).body(permissao.save(p));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public String deletePermissoes(String id){
        // permissao.deleteById(id);

        return "Deletado com sucesso";
    }

    public String updatePermissoes(Permissoes p){
        // return permissao.findById(p.).map(mapper -> {
        //      mapper.setNome(p.getNome());
        //      permissao.save(mapper);
        //      return "Alterado com sucesso";
        //  }).orElse("Error");

        return "asda";
     }

    @Transactional
    public ResponseEntity deletePermissaoByCartao(String idCartao){
        try{
            permissao.deletePermissaoByCartao(idCartao);
            return ResponseEntity.ok().body(permissao.findAll());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}