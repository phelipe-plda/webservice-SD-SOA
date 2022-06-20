/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phelipe.marketplace.controller;

import com.phelipe.marketplace.model.entity.ClientePessoaFisica;
import com.phelipe.marketplace.model.entity.ItemVenda;
import com.phelipe.marketplace.model.repository.ClientePessoaFisicaRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pheli
 */
@Transactional
@Controller
@RequestMapping("clientespessoafisica")
public class ClientePessoaFisicaController {
    
    @Autowired
    ClientePessoaFisicaRepository repository;
    
    @GetMapping("/form")
    public String form(ClientePessoaFisica clientePessoaFisica){
        return "/clientespessoafisica/form";
    }
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("clientespessoafisica", repository.clientesPessoaFisica());
        return new ModelAndView("/clientespessoafisica/list", model);
    }
    

    
    @GetMapping("/filtrar")
    public ModelAndView filtrarNome(@RequestParam("nomefiltrar") String nome, ModelMap model) {
        model.addAttribute("clientespessoafisica", repository.filtrarClientesPessoaFisica(nome));
        return new ModelAndView("/clientespessoafisica/list", model);
    }
    
   
    
    
    
    @PostMapping("/save")
    public ModelAndView save(@Valid ClientePessoaFisica clientePessoaFisica, BindingResult result){ 
        
        if(result.hasErrors()){
            return new ModelAndView("/clientespessoafisica/form");
        }
        else{
            repository.save(clientePessoaFisica);
            return new ModelAndView("redirect:/clientespessoafisica/list");   
        }  
        
        
        
    }
    
    @PostMapping("/update")
    public ModelAndView update(@Valid ClientePessoaFisica clientePessoaFisica, BindingResult result) {   
         if(result.hasErrors()){
            return new ModelAndView("/clientespessoafisica/form");
        }
        else{
            repository.update(clientePessoaFisica);
            return new ModelAndView("redirect:/clientespessoafisica/list");   
        }  
        
        
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        System.out.println("id " +  id);
        model.addAttribute("clientePessoaFisica", repository.clientePessoaFisica(id));
        return new ModelAndView("/clientespessoafisica/form", model);
    }
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        
        repository.remove(id);
        return new ModelAndView("redirect:/clientespessoafisica/list");
    }
    
    @GetMapping("/login")
    public String login(ClientePessoaFisica clientePessoaFisica){
        return "/clientespessoafisica/login";
    }
    
    
}
