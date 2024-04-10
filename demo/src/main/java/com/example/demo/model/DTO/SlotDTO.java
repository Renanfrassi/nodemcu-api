package com.example.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlotDTO {

    private String horaInicio;
    private String horaFim;
    private int diaSemana;

    public SlotDTO(int diaSemana, String horaInicio, String horaFim) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }
    
}
