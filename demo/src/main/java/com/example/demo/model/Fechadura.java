package com.example.demo.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
/**
 * Fechadura
 */

@Getter 
@Setter
@Entity
@Table(name = "fechadura")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Fechadura implements Serializable{
    @Id
    private String id;

    @Column(name = "descricao")
    private String descricao;

    public Fechadura(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Fechadura() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

    
    // @OneToMany(mappedBy = "fechadura")
    // private Set<Permissoes> permissao;
}