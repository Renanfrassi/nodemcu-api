package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Entity
@Table(name = "permissoes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Permissoes implements Serializable{
    @EmbeddedId
    private PermissoesKey id;

}
