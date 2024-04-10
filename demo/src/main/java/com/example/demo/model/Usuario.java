package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;
    
    @Column(name = "telefone")
    private String telefone;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "data_nascimento")
    private Date dataNasc;

    @Column(name = "senha")
    private String senha;

    @Column(name = "email")
    private String email;
    
}