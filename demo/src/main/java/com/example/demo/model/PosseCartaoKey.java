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
public class PosseCartaoKey implements Serializable{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartao", referencedColumnName = "id")
    private Cartao cartao;

    public PosseCartaoKey() {
    }

    public PosseCartaoKey(Usuario idUsuario, Cartao cartao) {
        this.idUsuario = idUsuario;
        this.cartao = cartao;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    

}
