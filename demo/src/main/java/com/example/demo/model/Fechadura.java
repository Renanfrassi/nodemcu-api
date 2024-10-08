package com.example.demo.model;


import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

/**
 * Fechadura
 */


@Entity
@Table(name = "fechadura")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fechadura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

}