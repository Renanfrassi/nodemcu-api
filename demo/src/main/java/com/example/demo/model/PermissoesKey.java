package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PermissoesKey implements Serializable{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fechadura", referencedColumnName = "id")
    private Fechadura fechadura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartao", referencedColumnName = "id")
    private Cartao cartao;

    public PermissoesKey(Fechadura fechadura, Cartao cartao) {
        this.fechadura = fechadura;
        this.cartao = cartao;
    }

    public PermissoesKey() {
    }

    public Fechadura getFechadura() {
        return fechadura;
    }

    public void setFechadura(Fechadura fechadura) {
        this.fechadura = fechadura;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }


}
