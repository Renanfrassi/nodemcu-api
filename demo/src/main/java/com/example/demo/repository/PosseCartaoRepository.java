package com.example.demo.repository;



import com.example.demo.model.Cartao;
import com.example.demo.model.DTO.ListPosseCartaoDTO;
import com.example.demo.model.PosseCartao;

import com.example.demo.model.PosseCartaoKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PosseCartaoRepository extends CrudRepository<PosseCartao, PosseCartaoKey>{
    @Query("SELECT new com.example.demo.model.DTO.ListPosseCartaoDTO(u.nome, u.id, c.id, c.statusEntrada, pc.dataInicio, pc.dataFim) " +
            "from PosseCartao pc, Cartao c, Usuario u " +
            "WHERE pc.id.cartao.id = c.id and pc.id.usuario.id = u.id")
    Iterable<ListPosseCartaoDTO> seachAllPosses();

}
