package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Entity
@Table(name = "cartao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cartao implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "status_entrada")
    private boolean statusEntrada;

    public Cartao(String id) {
        this.id = id;
    }
}
