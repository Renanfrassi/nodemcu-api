package com.example.demo.controller;

import com.example.demo.model.Cartao;
import com.example.demo.service.CartaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity seachCartao(){

        return cartao.findAll();

    }

    @PostMapping
    public ResponseEntity addCartao(@RequestBody Cartao c) {

        return cartao.addCartao(c);

    }

    @PutMapping
    public ResponseEntity updateCartao(@RequestBody Cartao c){
        System.out.println(c);
        return cartao.updateCartao(c);

    }

    @DeleteMapping
    public ResponseEntity deleteCartao(@RequestParam String id){

        cartao.deleteCartao(id);
        return ResponseEntity.noContent().build();

    }
}