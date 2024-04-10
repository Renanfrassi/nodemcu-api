package com.example.demo.repository;

import com.example.demo.model.Cartao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CartaoRepository extends CrudRepository<Cartao, String>{
    @Query("SELECT new com.example.demo.model.Cartao(c.id, c.statusEntrada) " +
            " FROM PosseCartao pc, Cartao c, Usuario u " +
            "WHERE pc.id.cartao.id = c.id and pc.id.usuario.id = u.id " +
            "  AND pc.id.usuario.id = :idUsuario ")
    Iterable<Cartao> seachPosseId(@Param("idUsuario") Integer idUsuario);
}
