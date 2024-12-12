package com.example.demo.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArquivoCartaoDTO {

    private String fileName;

    private List<String> listaHorario;
}
