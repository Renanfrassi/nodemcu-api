package com.example.demo.service;

import com.example.demo.model.Cartao;
import com.example.demo.model.DTO.PosseCartaoDTO;
import com.example.demo.model.PosseCartao;
import com.example.demo.model.PosseCartaoKey;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CartaoRepository;
import com.example.demo.repository.PosseCartaoRepository;

import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

/**
 * PosseCartaoService
 */
@Service
public class PosseCartaoService {
    @Autowired
    private PosseCartaoRepository posseCartao;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CartaoService cartaoService;

    public ResponseEntity findAll(){
        try{
            return ResponseEntity.ok().body(posseCartao.seachAllPosses());

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity addPosseCartao(PosseCartaoDTO pDTO){
        try {
            if(cartaoService.findById(pDTO.getIdCartao()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Cartão não registrado");

            }

            if(usuarioService.findById(pDTO.getIdUsuario()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Usuário não registrado");

            }

            PosseCartaoKey chave = new PosseCartaoKey();
            chave.setIdUsuario(usuarioService.findById(pDTO.getIdUsuario()).get());
            chave.setIdCartao(cartaoService.findById(pDTO.getIdCartao()).get());

            PosseCartao p = new PosseCartao();
            p.setId(chave);
            p.setDataInicio(pDTO.getDataInicio());
            p.setDataFim(pDTO.getDataFim());


            return ResponseEntity.created(URI.create("./posse_cartao")).body(posseCartao.save(p));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public String deletePosseCartao(PosseCartaoKey id){
        posseCartao.deleteById(id);

        return "Deletado com sucesso";
    }

    public ResponseEntity updatePosseCartao(PosseCartaoDTO pDTO){

        try{

            if(cartaoService.findById(pDTO.getIdCartao()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Cartão não registrado");

            }

            if(usuarioService.findById(pDTO.getIdUsuario()).stream().count() == 0){

                return ResponseEntity.badRequest().body("Usuário não registrado");

            }

            PosseCartaoKey chave = new PosseCartaoKey();
            chave.setIdUsuario(usuarioService.findById(pDTO.getIdUsuario()).get());
            chave.setIdCartao(cartaoService.findById(pDTO.getIdCartao()).get());

            PosseCartao p = new PosseCartao();
            p.setId(chave);
            p.setDataInicio(pDTO.getDataInicio());
            p.setDataFim(pDTO.getDataFim());


            return ResponseEntity.ok().body(posseCartao.save(p));

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    
     }


}