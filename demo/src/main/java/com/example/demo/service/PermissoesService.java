package com.example.demo.service;


import com.example.demo.model.Cartao;
import com.example.demo.model.Fechadura;
import com.example.demo.model.Permissoes;
import com.example.demo.model.PermissoesKey;
import com.example.demo.repository.PermissoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissoesService{
    @Autowired
    private PermissoesRepository permissao;
    
    public Iterable<Permissoes> findAll(){
        return permissao.findAll();
    }

    public boolean seachPermissaoPorCartaoFechadura(String idCartao, String idFechadura){
       Fechadura f = new Fechadura();
       Cartao c = new Cartao();
       c.setId(idCartao);
       f.setId(idFechadura);
       return permissao.existsById(new PermissoesKey(f, c));
    }

    public String addPermissoes(Permissoes p){
        permissao.save(p);

        return "Sucesso";
    }

    public String deletePermissoes(String id){
        // permissao.deleteById(id);

        return "Deletado com sucesso";
    }

    public String updatePermissoes(Permissoes p){
        // return permissao.findById(p.).map(mapper -> {
        //      mapper.setNome(p.getNome());
        //      permissao.save(mapper);
        //      return "Alterado com sucesso";
        //  }).orElse("Error");

        return "asda";
     }
}