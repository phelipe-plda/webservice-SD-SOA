/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author pheli
 */
@Entity
@Table(name = "tb_cliente")
@PrimaryKeyJoinColumn(name = "id")
@Data
public class ClientePessoaFisica extends Pessoa implements Serializable {
        
        @Column(length = 11)
        @CPF
        //@Size(min=2, max=30)
        private String cpf;
        
                    
      /*@DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate datNasc;*/
                
        @OneToMany(mappedBy = "clientePessoaFisica", cascade = { CascadeType.PERSIST })
        private List<Venda> vendas;

   
    
}
