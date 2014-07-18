/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.interfaces;

import br.com.nverse.sac.model.Cliente;
import br.com.nverse.sac.model.enums.UF;
import java.util.List;

/**
 *
 * @author jmarcos
 */
public interface ClienteDao extends GenericDao<Cliente>{
    
    public String obterUltimoCodigo();
    
    public abstract List<Cliente> listarPorNomes(String nome);

    public List<Cliente> listarPorNomes(String codigoPequisa,
            String nomePequisa, String placaPequisa, String ufPequisa, String situacao);

    public int obtenhaQuantidadePorUF(UF estado);
    
}
