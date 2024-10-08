package com.example.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilterDTO {
    private String idCartao;

    private Integer idFechadura;

    private Integer idUsuario;
}
