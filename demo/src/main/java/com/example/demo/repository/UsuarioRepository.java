package com.example.demo.repository;

import com.example.demo.model.DTO.ListUsuariosDTO;
import com.example.demo.model.DTO.SlotDTO;
import com.example.demo.model.DTO.UsuarioSlotDTO;
import com.example.demo.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    @Query("select new com.example.demo.model.DTO.ListUsuariosDTO(u.id, u.nome, u.matricula) from Usuario u")
    Iterable<ListUsuariosDTO> findAllUsuarios();

    @Query(
        "SELECT new com.example.demo.model.DTO.UsuarioSlotDTO(p.id.cartao.id, pc.dataInicio, pc.dataFim, p.id.fechadura.id, u.id) " +
        "FROM Permissoes p " +
        "JOIN PosseCartao pc ON p.id.cartao.id = pc.id.cartao.id " +
        "JOIN Usuario u ON pc.id.usuario.id = u.id " + 
        "JOIN Cartao c ON pc.id.cartao.id = c.id " +
        "WHERE u.id = :idUsuario " +
        "AND c.id = :idCartao"
    )
    UsuarioSlotDTO findUsuarioPermissaoId(@Param("idUsuario") Integer idUsuario, @Param("idCartao") String idCartao);

    @Query(
        "SELECT new com.example.demo.model.DTO.SlotDTO(s.id.diaSemana, s.horaInicio, s.horaFim) " + 
        "FROM Slot s " +
        "JOIN s.id.permissoes p " +
        "JOIN PosseCartao pc ON p.id.cartao.id = pc.id.cartao.id " +
        "JOIN pc.id.usuario u " +
        "WHERE u.id = :idUsuario " +
        "AND p.id.cartao.id = :idCartao"
    )
    List<SlotDTO> findSlotsByUsuarioAndCartao(@Param("idUsuario") Integer idUsuario, @Param("idCartao") String idCartao);
    
}