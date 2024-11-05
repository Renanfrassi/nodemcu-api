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

    public Permissoes addPermissoes(PermissoesDTO pDTO) throws Exception {

            if(cartaoService.findById(pDTO.getIdCartao()).stream().count() == 0){

                throw new Exception("Cartão não registrado");

            }

            if(fechaduraService.findById(pDTO.getIdFechadura()).stream().count() == 0){

                throw new Exception("Fechadura não registrada");

            }

            PermissoesKey chave = new PermissoesKey();
            chave.setFechadura(fechaduraService.findById(pDTO.getIdFechadura()).get());
            chave.setCartao(cartaoService.findById(pDTO.getIdCartao()).get());

            Permissoes p = new Permissoes();
            p.setId(chave);

            permissao.save(p);

            return p;

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
<<<<<<< HEAD
    public ResponseEntity deletePermissaoByCartao(String idCartao){
        try{
            permissao.deletePermissaoByCartao(idCartao);
            return ResponseEntity.ok().body(permissao.findAll());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
=======
    public Iterable<Permissoes> deletePermissaoByCartao(String idCartao) throws Exception {
        permissao.deletePermissaoByCartao(idCartao);
        return permissao.findAll();
>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312

    }

}