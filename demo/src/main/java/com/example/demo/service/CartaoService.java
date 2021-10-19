package com.example.demo.service;

import com.example.demo.model.Cartao;
import com.example.demo.repository.CartaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CartaoService
 */
@Service
public class CartaoService {
    @Autowired
    private CartaoRepository cartao;

    public Iterable<Cartao> findAll(){
        return cartao.findAll();
    }

    public String addCartao(Cartao c){
        cartao.save(c);

        return "Sucesso";
    }

    public String deleteCartao(String id){
        cartao.deleteById(id);

        return "Deletado com sucesso";
    }

    public String updateCartao(Cartao c){
        return "asdsadas";
     }
}