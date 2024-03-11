package com.example.demo.model.DTO;

import com.example.demo.model.Cartao;
import com.example.demo.model.Fechadura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissoesDTO {

    private Integer idFechadura;

    private String idCartao;
}
