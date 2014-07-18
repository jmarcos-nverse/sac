
package br.com.nverse.sac.dao.interfaces;

import br.com.nverse.sac.model.DetalheContato;
import java.util.List;

/**
 *
 * @author jmarcos
 */
public interface DetalheContatoDao extends GenericDao<DetalheContato>{

    public List<DetalheContato> acheFilhosDe(DetalheContato pai);

    public int obtenhaUltimoCodigo(DetalheContato pai);
    
}
