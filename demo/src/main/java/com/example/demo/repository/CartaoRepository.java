package com.example.demo.repository;

import com.example.demo.model.Cartao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CartaoRepository extends CrudRepository<Cartao, String>{
    @Query("SELECT new com.example.demo.model.Cartao(c.id, c.statusEntrada) " +
            " FROM PosseCartao p, Cartao c, Usuario u " +
            "WHERE p.id.idCartao.id = c.id and p.id.idUsuario.id = u.id " +
            "  AND p.id.idUsuario.id = :idUsuario ")
    Iterable<Cartao> seachPosseId(@Param("idUsuario") Integer idUsuario);
}
