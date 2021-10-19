package com.example.demo.service;


import com.example.demo.model.Slot;
import com.example.demo.repository.SlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SlotService {
    @Autowired
    private SlotRepository slot;

    public Iterable<Slot> findAll(){
        return slot.findAll();
    }

    public String addSlot(Slot s){
        slot.save(s);

        return "Sucesso";
    }

    public String deleteSlot(String id){
        // slot.deleteById(id);

        return "Deletado com sucesso";
    }

    public String updateSlot(Slot c){
    //     return slot.findById(c.getHora_fim()).map(mapper -> {
    //          mapper.setHora_fim(c.getHora_inicio());
    //          slot.save(mapper);
    //          return "Alterado com sucesso";
    //      }).orElse("Error");
        return "";

    }
}