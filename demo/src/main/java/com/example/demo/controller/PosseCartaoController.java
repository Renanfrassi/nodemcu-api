package com.example.demo.controller;

import com.example.demo.model.PosseCartao;
import com.example.demo.service.PosseCartaoService;

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
@RequestMapping(path = "posse_cartao")
public class PosseCartaoController {
    @Autowired
    private PosseCartaoService posseCartaoService;

    @GetMapping
    public Iterable<PosseCartao> seachPosseCartao(){
       return posseCartaoService.findAll();
    }

    @PostMapping
    public String addPosseCartao(@RequestBody PosseCartao p){
       return posseCartaoService.addPosseCartao(p);
    }

    @PutMapping
    public String updatePosseCartao(@RequestBody PosseCartao p){
      return posseCartaoService.updatePosseCartao(p);
    }

    @DeleteMapping
    public String deletePosseCartao(@RequestParam String id){
       return posseCartaoService.deletePosseCartao(id);
    }
    
}