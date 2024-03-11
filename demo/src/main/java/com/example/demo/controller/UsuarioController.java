package com.example.demo.controller;

import com.example.demo.model.DTO.UsuarioSlotDTO;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

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
@RequestMapping(path = "usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity seachUsuario(){
       return usuarioService.findAll();
    }

    @GetMapping("find")
    public ResponseEntity findUsuarioById(@RequestParam Integer id){
        return usuarioService.findUsuarioById(id);
    }

    @PostMapping
    public ResponseEntity addUsuario(@RequestBody Usuario u){
       return usuarioService.addUsuario(u);
    }

    @PostMapping("slot")
    public ResponseEntity addSlot(@RequestBody UsuarioSlotDTO usuarioSlotDTO){
        return usuarioService.addSlot(usuarioSlotDTO);
    }
    @PutMapping
    public ResponseEntity updateUsuario(@RequestBody Usuario u){
      return usuarioService.updateUsuario(u);
    }

    @DeleteMapping
    public String deleteUsuario(@RequestParam int id){
       return usuarioService.deleteUsuario(id);
    }

    @GetMapping("slot")
    public ResponseEntity findUsuarioPermissaoId(@RequestParam Integer idUsuario,@RequestParam String idCartao){
        return usuarioService.findUsuarioPermissaoId(idUsuario, idCartao);
    }

}