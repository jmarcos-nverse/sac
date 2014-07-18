/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.nverse.sac.controllers;

import br.com.nverse.sac.dao.interfaces.UsuarioDao;
import br.com.nverse.sac.model.security.Perfil;
import br.com.nverse.sac.model.security.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author JMarcos
 */
@Controller(value = "usuarioCtrl")
@Scope("view")
public class UsuarioCtrl {
    
    private Usuario usuario;
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    private List<Usuario> listaUsuarios;
    
    private List<Perfil> listaPerfis;

    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Perfil> getListaPerfis() {
        return listaPerfis;
    }

    public void setListaPerfis(List<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }
    
    
    
    
}
