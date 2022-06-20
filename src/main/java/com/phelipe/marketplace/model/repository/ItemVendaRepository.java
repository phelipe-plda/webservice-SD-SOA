/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.model.repository;

import com.phelipe.marketplace.model.entity.ItemVenda;
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
public class ItemVendaRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(ItemVenda itemVenda){
        em.persist(itemVenda);
    }
    
    public ItemVenda itemVenda(Long id){
        return em.find(ItemVenda.class, id);
    }
    
     public List<ItemVenda> itensVenda(){
        Query query = em.createQuery("from ItemVenda");
        return query.getResultList();
    }
     
     public void remove(Long id){
        ItemVenda itemVenda = em.find(ItemVenda.class, id);
        em.remove(itemVenda);
    }
     
    
     
     public void update(ItemVenda itemVenda){
        em.merge(itemVenda);
    }
    
}
