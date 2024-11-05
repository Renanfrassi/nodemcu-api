package com.example.demo.repository;

import com.example.demo.model.DTO.SlotDTO;
import com.example.demo.model.Permissoes;
import com.example.demo.model.Slot;
import com.example.demo.model.SlotKey;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SlotRepository extends CrudRepository<Slot, SlotKey>{

    @Query("select new com.example.demo.model.DTO.SlotDTO(s.horaInicio, s.horaFim, s.id.diaSemana) from Slot s " +
            "where s.id.permissoes.id.fechadura.id = :idFechadura " +
            "and s.id.permissoes.id.cartao.id  = :idCartao")
    Iterable<SlotDTO> findSlotByCartaoFechadura(@Param("idCartao") String idCartao, @Param("idFechadura") Integer idFechadura);

    @Modifying
    @Query("DELETE Slot s where s.id.permissoes.id.fechadura.id = :idFechadura " +
            "AND  s.id.permissoes.id.cartao.id = :idCartao ")
    void deleteSlotByFechaduraCartao(@Param("idCartao") String idCartao, @Param("idFechadura") Integer idFechadura);

    @Query("select count(*) from Slot s, Cartao c " +
            "where :horaAtual between s.horaInicio and s.horaFim " +
            "and s.id.diaSemana = :diaAtual " +
            "and s.id.permissoes.id.cartao.id = :idCartao " +
            "and s.id.permissoes.id.fechadura.id = :idFechadura " +
            "and c.id = s.id.permissoes.id.cartao.id " +
            "and c.statusEntrada is true ")
    Integer verifySlot(@Param("idCartao") String idCartao, @Param("idFechadura") Integer idFechadura,
                       @Param("horaAtual") String horaAtual, @Param("diaAtual") Integer diaAtual);
}
