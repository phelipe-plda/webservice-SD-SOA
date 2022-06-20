/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.model.repository;

import com.phelipe.marketplace.model.entity.Produto;
import com.phelipe.marketplace.model.entity.Venda;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pheli
 */
@Repository
public class VendaRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    
    
    public void save(Venda venda){
        em.persist(venda);
    }

    public Venda venda(Long id){
        return em.find(Venda.class, id);
    }
    

    
    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public void remove(Long id){
        Venda v = em.find(Venda.class, id);
        em.remove(v);
    }

    public void update(Venda venda){
        em.merge(venda);
    }
    
    public List<Venda> filtrarVendas(LocalDate data){
        Query query = em.createQuery("from Venda v where v.data = :data", Venda.class);
        query.setParameter("data",data);
        return query.getResultList();
        
        
        
    }
    
    
}
