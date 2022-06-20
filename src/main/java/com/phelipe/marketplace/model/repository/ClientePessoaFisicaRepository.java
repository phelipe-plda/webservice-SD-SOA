/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.model.repository;


import com.phelipe.marketplace.model.entity.ClientePessoaFisica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pheli
 */

@Repository
@Transactional
public class ClientePessoaFisicaRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(ClientePessoaFisica clientePessoaFisica){     
        em.persist(clientePessoaFisica);
    }
    
    public void update(ClientePessoaFisica clientePessoaFisica){
        em.merge(clientePessoaFisica);
    }
    
     public ClientePessoaFisica clientePessoaFisica(Long id){
        return em.find(ClientePessoaFisica.class, id);
    }

    public List<ClientePessoaFisica> clientesPessoaFisica(){
        Query query = em.createQuery("from ClientePessoaFisica");
        return query.getResultList();
    }
    
  
    
    public List<ClientePessoaFisica> filtrarClientesPessoaFisica(String nome){
        Query query = em.createQuery("from ClientePessoaFisica c where c.nome like :nome", ClientePessoaFisica.class);
        query.setParameter("nome","%" + nome + "%");
        return query.getResultList();
        
        
        
    }

    public void remove(Long id){
        ClientePessoaFisica c = em.find(ClientePessoaFisica.class, id);
        em.remove(c);
    }

  
}
