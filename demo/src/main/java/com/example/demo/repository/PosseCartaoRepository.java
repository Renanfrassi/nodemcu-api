package com.example.demo.repository;



import com.example.demo.model.Cartao;
import com.example.demo.model.DTO.ListPosseCartaoDTO;
import com.example.demo.model.DTO.PosseCartaoDTO;
import com.example.demo.model.PosseCartao;

import com.example.demo.model.PosseCartaoKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PosseCartaoRepository extends CrudRepository<PosseCartao, PosseCartaoKey>{
    @Query("SELECT new com.example.demo.model.DTO.ListPosseCartaoDTO(u.nome, u.id, c.id, c.statusEntrada, p.dataInicio, p.dataFim) " +
            "from PosseCartao p, Cartao c, Usuario u " +
            "WHERE p.id.idCartao.id = c.id and p.id.idUsuario.id = u.id")
    Iterable<ListPosseCartaoDTO> seachAllPosses();

    @Query("SELECT new com.example.demo.model.DTO.PosseCartaoDTO(p.id.idUsuario.id, p.id.idCartao.id, p.dataInicio, p.dataFim) " +
            "FROM PosseCartao p " +
            "WHERE p.id.idUsuario.id = :idUsuario " +
            "AND p.id.idCartao.id = :idCartao")
    PosseCartaoDTO findPosseCartaoByUsuarioCartao(@Param("idCartao") String idCartao, @Param("idUsuario") Integer idUsuario);

    @Modifying
    @Query("DELETE PosseCartao p where p.id.idUsuario.id = :idUsuario " +
            "AND  p.id.idCartao.id = :idCartao ")
    void deletePosseCartaoByUsuarioCartao(@Param("idCartao") String idCartao, @Param("idUsuario") Integer idUsuario);
}
