/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.interfaces;

import java.util.List;
import br.com.nverse.sac.model.Entidade;
import org.hibernate.Session;

/**
 *
 * @author jmarcos
 */
public interface GenericDao<T extends Entidade>{
    
    public abstract void salvar(T entidade) throws Exception;
    
    public abstract void salvarTodos(List<T> lista) throws Exception;

    public abstract void atualizar(T entidade) throws Exception;

    public abstract void remover(T entidade) throws Exception;

    public abstract T obterPor(String propriedade, String valor);
    
    public abstract T obterPorId(Long id);

    public abstract List<T> listarPor(String propriedade, String valor);

    public abstract List<T> listar();
    
    
    
}
