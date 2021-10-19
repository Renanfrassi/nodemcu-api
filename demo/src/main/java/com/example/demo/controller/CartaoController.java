package com.example.demo.controller;

import com.example.demo.model.Cartao;
import com.example.demo.service.CartaoService;

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
@RequestMapping(path = "cartao")
public class CartaoController {
    @Autowired
    private CartaoService cartao;

    @GetMapping
    public Iterable<Cartao> seachCartao(){
       return cartao.findAll();
    }

    @PostMapping
    public String addCartao(@RequestBody Cartao c){

        return cartao.addCartao(new Cartao(c.getId(), c.isStatusEntrada()));
    }

    @PutMapping
    public String updateCartao(@RequestBody Cartao c){
      return cartao.updateCartao(c);
    }

    @DeleteMapping
    public String deleteCartao(@RequestParam String id){
       return cartao.deleteCartao(id);
    }
}