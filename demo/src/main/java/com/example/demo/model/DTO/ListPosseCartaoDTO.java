package com.example.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListPosseCartaoDTO {

    private String nomeUsuario;

    private Integer idUsuario;

    private String idCartao;

    private boolean statusEntrada;

    private Date dataInicio;

    private Date dataFim;
}
