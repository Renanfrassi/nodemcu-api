package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.DTO.PosseCartaoDTO;
import com.example.demo.repository.CartaoRepository;
import com.example.demo.repository.PosseCartaoRepository;

import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public PosseCartao addPosseCartao(PosseCartaoDTO pDTO) throws Exception {

            if(cartaoService.findById(pDTO.getIdCartao()).stream().count() == 0){

                throw new Exception("Cartão não registrado");

            }

            if(usuarioService.findById(pDTO.getIdUsuario()).stream().count() == 0){

                throw new Exception("Usuário não registrado");

            }

            if(pDTO.getDataInicio().after(pDTO.getDataFim())){

                throw new Exception("A Data de Início não pode ser superior que a Data Fim!");

            }

            PosseCartaoKey chave = new PosseCartaoKey();
            chave.setIdUsuario(usuarioService.findById(pDTO.getIdUsuario()).get());
            chave.setIdCartao(cartaoService.findById(pDTO.getIdCartao()).get());

            PosseCartao p = new PosseCartao();
            p.setId(chave);
            p.setDataInicio(pDTO.getDataInicio());
            p.setDataFim(pDTO.getDataFim());


            posseCartao.save(p);

            return p;

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

    public PosseCartaoDTO findPosseCartaoByUsuarioCartao(String idCartao, Integer idUsuario){
        return posseCartao.findPosseCartaoByUsuarioCartao(idCartao, idUsuario);
    }

    @Transactional
<<<<<<< HEAD
    public ResponseEntity deletePosseCartaoByUsuarioCartao(String idCartao, Integer idUsuario){
        try{
            posseCartao.deletePosseCartaoByUsuarioCartao(idCartao, idUsuario);
            return ResponseEntity.ok().body(posseCartao.findAll());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
=======
    public Iterable<PosseCartao> deletePosseCartaoByUsuarioCartao(String idCartao, Integer idUsuario) throws Exception{
            posseCartao.deletePosseCartaoByUsuarioCartao(idCartao, idUsuario);
            return posseCartao.findAll();


>>>>>>> f84a61f0070484aa383bd3b3dca7c5659f386312

    }

}