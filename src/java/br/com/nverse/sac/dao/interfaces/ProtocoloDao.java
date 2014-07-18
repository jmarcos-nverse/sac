/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.interfaces;

import br.com.nverse.sac.model.Cliente;
import br.com.nverse.sac.model.Protocolo;
import java.util.List;

/**
 *
 * @author jmarcos
 */
public interface ProtocoloDao extends GenericDao<Protocolo>{

    public long obtenhaUltimoNumero();

    public List<Protocolo> listarPorNomes(String numPesqProtocolo, String contrClientPesqProtocolo, String fantasiaPesqProtocolo);

    public List<Protocolo> listar(int i);

    public boolean clientePossuiLigacoes(Cliente cliente);
    
}
