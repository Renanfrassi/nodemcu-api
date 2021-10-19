package com.example.demo.service;


import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService{
    @Autowired
    private UsuarioRepository usuario;
    
    public Iterable<Usuario> findAll(){
        return usuario.findAll();
    }

    public String addUsuario(Usuario u){
        usuario.save(u);

        return "Sucesso";
    }

    public String deleteUsuario(int id){
        usuario.deleteById(id);

        return "Deletado com sucesso";
    }

    public String updateUsuario(Usuario u){
        return "23423";
     }
}