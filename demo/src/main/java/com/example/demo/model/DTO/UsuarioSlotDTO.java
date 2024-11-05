package com.example.demo.model.DTO;

import com.example.demo.model.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioSlotDTO {

    private String idCartao;

    private Date dataInicio;

    private Date dataFim;

    private Integer idFechadura;

    private Integer idUsuario;

    private Iterable<SlotDTO> listaSlot;

    public UsuarioSlotDTO(String idCartao, Date dataInicio, Date dataFim, Integer idFechadura, Integer idUsuario) {
        this.idCartao = idCartao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idFechadura = idFechadura;
        this.idUsuario = idUsuario;
    }
}
