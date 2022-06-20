/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author pheli
 */

@Entity
@Table (name = "tb_itemvenda")
public class ItemVenda implements Serializable {
    
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private double qtd;
    
    @OneToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public double ValorTotal(){
        return produto.getValor() * qtd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    
}
