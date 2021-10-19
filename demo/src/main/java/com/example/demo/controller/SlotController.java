package com.example.demo.controller;


import com.example.demo.model.Slot;
import com.example.demo.service.SlotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "slot")
public class SlotController {
    @Autowired
    private SlotService slotService;

    @GetMapping
    public Iterable<Slot> seachSlot(){
       return slotService.findAll();
    }

    @PostMapping
    public String addSlot(@RequestBody Slot s){
       return slotService.addSlot(s);
    }

    @PutMapping
    public String updateSlot(@RequestBody Slot s){
      return slotService.updateSlot(s);
    }

    @DeleteMapping
    public String deleteSlot(@RequestParam String id){
       return slotService.deleteSlot(id);
    }
    
}