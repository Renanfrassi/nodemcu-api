package com.example.demo.service;

import com.example.demo.model.PosseCartao;
import com.example.demo.repository.PosseCartaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PosseCartaoService
 */
@Service
public class PosseCartaoService {
    @Autowired
    private PosseCartaoRepository posseCartao;

    public Iterable<PosseCartao> findAll(){
        return posseCartao.findAll();
    }

    public String addPosseCartao(PosseCartao p){
        posseCartao.save(p);

        return "Sucesso";
    }

    public String deletePosseCartao(String id){
        posseCartao.deleteById(id);

        return "Deletado com sucesso";
    }

    public String updatePosseCartao(PosseCartao p){
        // return posseCartao.findById(c.getId()).map(mapper -> {
        //      mapper.setStatus_entrada(c.getStatus_entrada());
        //      posseCartao.save(mapper);
        //      return "Alterado com sucesso";
        //  }).orElse("Error");

        return "";
    
     }
}