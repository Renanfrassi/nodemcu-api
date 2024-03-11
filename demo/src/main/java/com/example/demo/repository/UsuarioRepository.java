package com.example.demo.repository;

import com.example.demo.model.DTO.ListUsuariosDTO;
import com.example.demo.model.DTO.UsuarioSlotDTO;
import com.example.demo.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    @Query("select new com.example.demo.model.DTO.ListUsuariosDTO(u.id, u.nome, u.matricula) from Usuario u")
    Iterable<ListUsuariosDTO> findAllUsuarios();

    @Query("select new com.example.demo.model.DTO.UsuarioSlotDTO(p.id.cartao.id, pc.dataInicio, pc.dataFim, p.id.fechadura.id, u.id)  " +
            "from Permissoes p, PosseCartao pc, Usuario u  \n" +
            "where pc.id.idUsuario.id = u.id\n" +
            "and u.id = :idUsuario \n" +
            "and pc.id.idCartao.id = p.id.cartao.id " +
            "and pc.id.idCartao.id  = :idCartao \n" )
    UsuarioSlotDTO findUsuarioPermissaoId(@Param("idUsuario") Integer idUsuario, @Param("idCartao") String idCartao);
}