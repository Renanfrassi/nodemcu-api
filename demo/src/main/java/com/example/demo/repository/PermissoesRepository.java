package com.example.demo.repository;


import com.example.demo.model.Permissoes;
import com.example.demo.model.PermissoesKey;

import org.springframework.data.repository.CrudRepository;

public interface PermissoesRepository extends CrudRepository<Permissoes, PermissoesKey>{
    
}
