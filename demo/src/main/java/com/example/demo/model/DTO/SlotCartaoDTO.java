package com.example.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlotCartaoDTO {

    private String idCartao;

    private Integer idFechadura;

    private Date dataAtual;
}
