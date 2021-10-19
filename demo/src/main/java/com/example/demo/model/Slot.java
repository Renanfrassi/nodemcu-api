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
@Table(name = "slot")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Slot implements Serializable{
    @EmbeddedId
    private SlotKey id;
    
    @Column(name = "dia_semana")
    private int diaSemana;

    @Column(name = "hora_inicio")
    private String horaInicio;

    @Column(name = "hora_fim")
    private String horaFim;

    public Slot() {
    }

    public Slot(Permissoes permissoes, int diaSemana, String horaInicio, String horaFim) {
        this.id = new SlotKey();
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public SlotKey getId() {
        return id;
    }

    public void setId(SlotKey id) {
        this.id = id;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    
}



