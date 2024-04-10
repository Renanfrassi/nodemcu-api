package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlotKey implements Serializable{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="id_fechadura", referencedColumnName = "id_fechadura"),
        @JoinColumn(name="id_cartao", referencedColumnName = "id_cartao"),
    })
    private Permissoes permissoes;
    @Column(name = "dia_semana")
    private int diaSemana;

    public SlotKey(Integer idFechadura, String idCartao, int diaSemana) {
        this.permissoes = new Permissoes(new PermissoesKey(idFechadura, idCartao));
        this.diaSemana = diaSemana;
    }
}

