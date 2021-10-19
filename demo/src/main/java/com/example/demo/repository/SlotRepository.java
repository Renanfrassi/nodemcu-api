package com.example.demo.repository;

import com.example.demo.model.Permissoes;
import com.example.demo.model.Slot;
import com.example.demo.model.SlotKey;

import org.springframework.data.repository.CrudRepository;

public interface SlotRepository extends CrudRepository<Slot, SlotKey>{
    
}
