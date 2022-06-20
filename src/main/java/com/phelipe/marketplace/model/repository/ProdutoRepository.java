/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.model.repository;

import com.phelipe.marketplace.model.entity.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pheli
 */
@Repository
public class ProdutoRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(Produto produto){
        em.persist(produto);
    }
    
    public Produto produto(Long id){
        return em.find(Produto.class, id);
    }
    
     public List<Produto> produtos(){
        Query query = em.createQuery("from Produto");
        return query.getResultList();
    }
     
     public void remove(Long id){
        Produto produto = em.find(Produto.class, id);
        em.remove(produto);
    }
     
     public void update(Produto produto){
        em.merge(produto);
    }
}
