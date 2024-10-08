package com.example.demo.repository;

import com.example.demo.model.DTO.PermissoesDTO;
import com.example.demo.model.DTO.PosseCartaoDTO;
import com.example.demo.model.Permissoes;
import com.example.demo.model.PermissoesKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PermissoesRepository extends CrudRepository<Permissoes, PermissoesKey>{

    @Query("SELECT new com.example.demo.model.DTO.PermissoesDTO(p.id.fechadura.id, p.id.cartao.id) " +
            "FROM Permissoes p " +
            "WHERE p.id.cartao.id = :idCartao")
    Iterable<PermissoesDTO> findPosseCartaoByUsuarioCartao(@Param("idCartao") String idCartao);

    @Modifying
    @Query("DELETE Permissoes p where p.id.cartao.id = :idCartao")
    void deletePermissaoByCartao(@Param("idCartao") String idCartao);
}
