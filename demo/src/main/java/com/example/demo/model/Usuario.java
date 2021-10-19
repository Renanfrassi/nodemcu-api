package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;
    
    @Column(name = "telefone")
    private int telefone;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "data_nascimento")
    private Date dataNasc;

    @Column(name = "senha")
    private String senha;

    @Column(name = "email")
    private String email;
    // @OneToMany(mappedBy = "usuario")
    // private Set<PosseCartao> posseCartaos;
    
    public Usuario() {
    }

    public Usuario(String nome, int idade, int telefone, String matricula, Date dataNasc, String senha,
            String email) {
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.matricula = matricula;
        this.dataNasc = dataNasc;
        this.senha = senha;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
}