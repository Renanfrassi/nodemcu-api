package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name = "permissao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permissoes implements Serializable{
    @EmbeddedId
    private PermissoesKey id;

    // @OneToMany
    // private Set<Slot> slot = new HashSet<>(); 

    public Permissoes() {
        this.id = new PermissoesKey();
    }

    public PermissoesKey getId() {
        return id;
    }

    public void setId(PermissoesKey id) {
        this.id = id;
    }

    public Permissoes(PermissoesKey id) {
        this.id = id;
    }

    
    

}
