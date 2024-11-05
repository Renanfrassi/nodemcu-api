package com.example.demo.controller;

import com.example.demo.model.Cartao;
import com.example.demo.model.DTO.SlotCartaoDTO;
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

import java.net.URI;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "cartao")
public class CartaoController {
    @Autowired
    private CartaoService cartao;


    @DeleteMapping
<<<<<<< HEAD
    public ResponseEntity deleteCartao(@RequestParam String idCartao, @RequestParam Integer idUsuario){
        return cartao.deleteCartaoPossePermissao(idCartao, idUsuario);
=======
    public ResponseEntity deleteCartao(@RequestParam String idCartao, @RequestParam Integer idUsuario)  {
        try {
            return ResponseEntity.ok().body(cartao.deleteCartaoPossePermissao(idCartao, idUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312
    }

    @PostMapping("valida-acesso")
    public ResponseEntity verifySlot(@RequestBody SlotCartaoDTO dto)  {
        try {
            return ResponseEntity.created(URI.create("./cartao/valida-acesso'")).body(cartao.verifySlot(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}