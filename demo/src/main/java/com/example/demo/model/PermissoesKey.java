package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Embeddable
@NoArgsConstructor
@Data
public class PermissoesKey implements Serializable{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fechadura", referencedColumnName = "id")
    private Fechadura fechadura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cartao", referencedColumnName = "id")
    private Cartao cartao;
    
    public PermissoesKey(Integer idFechadura, String idCartao) {
        this.fechadura = new Fechadura(idFechadura);
        this.cartao = new Cartao(idCartao);
    }
    
}
