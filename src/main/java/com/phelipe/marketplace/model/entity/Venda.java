/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author pheli
 */
@Scope("session")
@Component
@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable{
    
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private LocalDate data;

    @OneToMany(mappedBy = "venda", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<ItemVenda> itensVendas = new ArrayList();
    
    @ManyToOne
    @JoinColumn(name = "id_clientefisico")
    @NotNull(message = "Selecione um cliente")
    private ClientePessoaFisica clientePessoaFisica;
  
    public double ValorTotal(){
        double valorTotal = 0;
        for(ItemVenda v : itensVendas) {
            valorTotal = valorTotal + v.ValorTotal();
       }
        return valorTotal;
    
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVenda> getItensVendas() {
        return itensVendas;
    }

    public void setItensVendas(List<ItemVenda> itensVendas) {
        this.itensVendas = itensVendas;
    }

    public ClientePessoaFisica getClientePessoaFisica() {
        return clientePessoaFisica;
    }

    public void setClientePessoaFisica(ClientePessoaFisica clientePessoaFisica) {
        this.clientePessoaFisica = clientePessoaFisica;
    }

 
       
   
      
}
