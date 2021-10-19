package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SlotKey implements Serializable{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="id_fechadura", referencedColumnName = "id_fechadura"),
        @JoinColumn(name="id_cartao", referencedColumnName = "id_cartao"),
    })
    private Permissoes permissoes;

    public SlotKey(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    public SlotKey() {
    }

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    
}

