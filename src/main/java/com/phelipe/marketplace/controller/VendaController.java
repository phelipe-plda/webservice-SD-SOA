/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.controller;

import com.phelipe.marketplace.model.entity.ClientePessoaFisica;
import com.phelipe.marketplace.model.entity.ItemVenda;
import com.phelipe.marketplace.model.entity.Produto;
import com.phelipe.marketplace.model.entity.Venda;
import com.phelipe.marketplace.model.repository.ClientePessoaFisicaRepository;
import com.phelipe.marketplace.model.repository.ProdutoRepository;
import com.phelipe.marketplace.model.repository.VendaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author pheli
 */
@Scope("request")
@Transactional
@Controller
@RequestMapping("vendas")
public class VendaController {
    
    @Autowired
    VendaRepository repository;
    
    @Autowired
    ProdutoRepository produtoRepository;
    
    @Autowired
    ClientePessoaFisicaRepository clienteRepository;
        
    @Autowired
    Venda venda;
    

    
       
    @GetMapping("/form")
    public String form(ItemVenda itemVenda, ModelMap model){
        model.addAttribute("produtos", produtoRepository.produtos()); 
        
        
        return "/vendas/form";
    }
 
    
     @GetMapping("/cart")
        public String cart(ItemVenda itemVenda, ModelMap model){
        model.addAttribute("produtos", produtoRepository.produtos());
        model.addAttribute("clientespessoafisica", clienteRepository.clientesPessoaFisica());
        return "/vendas/cart";
    }
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", repository.vendas());
        return new ModelAndView("/vendas/list", model);
    }
    
    @GetMapping("/save")
    public ModelAndView save(@RequestParam(value="idCliente") Long id, RedirectAttributes attributes){    
        if(venda.getItensVendas().isEmpty()){
            attributes.addFlashAttribute("erroteste", "A cesta de produtos esta vazia!" );
            return new ModelAndView("redirect:/vendas/cart");
           
        
        } 
        
        venda.setId(0); 
        venda.setData(LocalDate.now());  
        
  
       ClientePessoaFisica c = clienteRepository.clientePessoaFisica(id);
       venda.setClientePessoaFisica(c);

        
        repository.save(venda);
        venda.getItensVendas().clear();
        return new ModelAndView("redirect:/vendas/list");
        
   
        
    } 
    
    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        repository.update(venda);
        return new ModelAndView("redirect:/vendas/list");
    }
    
    
    /*
    @PostMapping("/save")
    public ModelAndView save(Venda venda){    
        this.venda.setId(0);
        this.venda.setData(LocalDate.now());
        repository.save(this.venda);
        this.venda.getItensVendas().clear();
        return new ModelAndView("redirect:/vendas/list");
    } */
    
    
    
    
 
    
    
    
              
    @PostMapping("/additem")
    public ModelAndView additem(@Valid ItemVenda itemVenda){
        Produto p = produtoRepository.produto(itemVenda.getProduto().getId());
        itemVenda.setProduto(p);
        itemVenda.setVenda(venda);
        venda.getItensVendas().add(itemVenda);
        return new ModelAndView("redirect:/vendas/form");    
    }
    
    @GetMapping("/removeitem/{id}")
    public ModelAndView removeitem(@PathVariable("id") Long id){
        venda.getItensVendas().remove(id);
        return new ModelAndView("redirect:/vendas/list");
    }

    
    
    
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/vendas/list");
    }
    
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(ModelMap model, @PathVariable("id") Long id){
        model.addAttribute("vendas", repository.venda(id));
        return new ModelAndView("/vendas/detalhes");
    }
    
    
    @GetMapping("/removeitemcart/{index}")
    public ModelAndView removeitemcart(@PathVariable("index") int index){
        venda.getItensVendas().remove(index);
        return new ModelAndView("redirect:/vendas/cart");
    }
    
    @GetMapping("/filtrar")
    public ModelAndView filtrarData(@RequestParam("datafiltrar") String data, ModelMap model) {
        
        LocalDate date = LocalDate.parse(data);
        System.out.println("localDate " + date);
        model.addAttribute("vendas", repository.filtrarVendas(date));
        return new ModelAndView("/vendas/list", model);
    }
    
    
   
    
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/vendas/form", model);
    }
    
   
       
    
    
  
}