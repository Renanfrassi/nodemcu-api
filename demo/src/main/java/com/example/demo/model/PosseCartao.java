package com.example.demo.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name = "posse_cartao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PosseCartao implements Serializable{
    @EmbeddedId
    private PosseCartaoKey id;

    @Column(name = "data_inicio")
    private Date dataInicio;
    
    @Column(name = "data_fim")
    private Date dataFim;

    public PosseCartao(Date dataInicio, Date dataFim) {
        this.id = new PosseCartaoKey();
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
    }
    
    public PosseCartao() {
    }

    public PosseCartaoKey getId() {
        return id;
    }

    public void setId(PosseCartaoKey id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    



}
