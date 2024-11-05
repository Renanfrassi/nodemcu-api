package com.example.demo.model.DTO;

import com.example.demo.model.Cartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListUsuariosDTO {
    private Integer id;

    private String nome;

    private String matricula;

    private Iterable<Cartao> cartoes;

    public ListUsuariosDTO(Integer id, String nome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }
}
