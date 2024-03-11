package com.example.demo.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Entity
@Table(name = "posse_cartao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PosseCartao implements Serializable{
    @EmbeddedId
    private PosseCartaoKey id;

    @Column(name = "data_inicio")
    private Date dataInicio;
    
    @Column(name = "data_fim")
    private Date dataFim;

}
