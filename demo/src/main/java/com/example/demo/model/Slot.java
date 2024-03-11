package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;


@Entity
@Table(name = "slot")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Slot implements Serializable{

    @EmbeddedId
    private SlotKey id;

    @Column(name = "hora_inicio")
    private String horaInicio;

    @Column(name = "hora_fim")
    private String horaFim;
    
}



