/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.nverse.sac.controllers;

import br.com.nverse.sac.dao.interfaces.ClienteDao;
import br.com.nverse.sac.model.Cliente;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author JMarcos
 */
public class RelatorioDS {
    
    @Autowired
    ClienteDao clienteDao;
    
    public List<Cliente> obtenhaClientesStaticamente(){
        
        
        
        return clienteDao.listar();
    }
    
}
