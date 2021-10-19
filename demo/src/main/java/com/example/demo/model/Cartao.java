package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cartao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cartao implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "status_entrada")
    private boolean statusEntrada;

    public Cartao(String id, boolean statusEntrada) {
        this.id = id;
        this.statusEntrada = statusEntrada;
    }

    public Cartao() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatusEntrada() {
        return statusEntrada;
    }

    public void setStatusEntrada(boolean statusEntrada) {
        this.statusEntrada = statusEntrada;
    }

    


    // @OneToMany(mappedBy = "cartao")
    // private Set<Permissoes> permissao;

    // @OneToMany(mappedBy = "cartao")
    // private Set<PosseCartao> posseCartaos;

    

}
