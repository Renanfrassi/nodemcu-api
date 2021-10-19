package com.example.demo.repository;

import com.example.demo.model.Cartao;

import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends CrudRepository<Cartao, String>{
    
}
