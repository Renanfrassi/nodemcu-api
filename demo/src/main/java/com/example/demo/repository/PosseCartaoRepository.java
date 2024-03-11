package com.example.demo.repository;



import com.example.demo.model.Cartao;
import com.example.demo.model.DTO.ListPosseCartaoDTO;
import com.example.demo.model.PosseCartao;

import com.example.demo.model.PosseCartaoKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PosseCartaoRepository extends CrudRepository<PosseCartao, PosseCartaoKey>{
    @Query("SELECT new com.example.demo.model.DTO.ListPosseCartaoDTO(u.nome, u.id, c.id, c.statusEntrada, p.dataInicio, p.dataFim) " +
            "from PosseCartao p, Cartao c, Usuario u " +
            "WHERE p.id.idCartao.id = c.id and p.id.idUsuario.id = u.id")
    Iterable<ListPosseCartaoDTO> seachAllPosses();

}
