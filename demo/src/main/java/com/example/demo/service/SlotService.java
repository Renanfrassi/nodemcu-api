package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.model.DTO.SlotDTO;
import com.example.demo.repository.SlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;


@Service
public class SlotService {
    @Autowired
    private SlotRepository slot;

    @Autowired
    private CartaoService cartaoService;
    @Autowired
    private PosseCartaoService posseCartaoService;
    @Autowired
    private PermissoesService permissoesService;

    public Iterable<Slot> findAll(){
        return slot.findAll();
    }

    public ResponseEntity addSlot(Slot s){

        try {

            return ResponseEntity.created(URI.create("./slot")).body(slot.save(s));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    public Iterable<SlotDTO> findSlotByCartaoFechadura(String idCartao, Integer idFechadura){
        return slot.findSlotByCartaoFechadura(idCartao, idFechadura);
    }
}