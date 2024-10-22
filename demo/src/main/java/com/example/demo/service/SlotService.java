package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.model.DTO.SlotDTO;
import com.example.demo.repository.SlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Slot addSlot(Slot s) throws Exception {
            slot.save(s);
            return s;
    }

    public String deleteSlot(String id){
//        slot.deleteById(id);

        return "Deletado com sucesso";
    }

    @Transactional
    public Iterable<Slot> deleteSlotByFechaduraCartao(Fechadura f, Cartao c) throws Exception{

            slot.deleteSlotByFechaduraCartao(c.getId(), f.getId());
            return slot.findAll();

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