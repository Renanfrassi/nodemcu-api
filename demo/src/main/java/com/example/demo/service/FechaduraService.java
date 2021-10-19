package com.example.demo.service;

import com.example.demo.model.Fechadura;
import com.example.demo.repository.FechaduraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FechaduraService {
    @Autowired
    private FechaduraRepository fechadura;

    public Iterable<Fechadura> findAll(){
        return fechadura.findAll();
    }

    public String addFechadura(Fechadura f){
        fechadura.save(f);

        return "Sucesso";
    }

    public String deleteFechadura(String id){
        fechadura.deleteById(id);

        return "Deletado com sucesso";
    }

    public String updateFechadura(Fechadura f){
        return "sss";
     }

}
