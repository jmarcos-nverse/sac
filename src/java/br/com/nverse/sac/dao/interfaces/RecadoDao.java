/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.interfaces;

import br.com.nverse.sac.model.Recado;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jmarcos
 */
public interface RecadoDao extends GenericDao<Recado>{

    public List<Recado> getListaRecadosValidosNaData(Date date);
    
}
