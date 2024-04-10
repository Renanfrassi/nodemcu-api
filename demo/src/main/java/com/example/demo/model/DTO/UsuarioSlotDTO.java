package com.example.demo.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import java.util.Date;

@Data
@NoArgsConstructor
public class UsuarioSlotDTO {

    private String idCartao;
    private Date dataInicio;
    private Date dataFim;
    private Integer idFechadura;
    private Integer idUsuario;
    private List<SlotDTO> listaSlot;

    public UsuarioSlotDTO(String idCartao, Date dataInicio, Date dataFim, Integer idFechadura, Integer idUsuario) {
        this.idCartao = idCartao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idFechadura = idFechadura;
        this.idUsuario = idUsuario;
    }

}
