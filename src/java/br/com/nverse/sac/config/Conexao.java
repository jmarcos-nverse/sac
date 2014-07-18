/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.config;

import java.util.Properties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author jmarcos
 */
public class Conexao extends DriverManagerDataSource {

    public Conexao() {

        this.setDriverClassName("com.mysql.jdbc.Driver");

//      DADOS BANCO DE DADOS LOCAL PARA TESTES 
//      this.setUrl("jdbc:mysql://localhost:3306/nversesac?zeroDateTimeBehavior=convertToNull");
//      this.setUsername("root");
//      this.setPassword("123456");   //PASSWORD ANTIGO
        
//      DADOS BANCO DE DADOS LOCAL COPIADO DO ZEUS    
        this.setUrl("jdbc:mysql://localhost:3306/saczeus_01?zeroDateTimeBehavior=convertToNull");
        this.setUsername("root");
        this.setPassword("123456");   //PASSWORD ANTIGO

//      DADOS DO NOVO SERVIDOR
//      this.setPassword("nverse");     //PASSWORD NOVO SERVIDOR
//      this.setUsername("root");
//      this.setUrl("jdbc:mysql://localhost:3306/nversesac?zeroDateTimeBehavior=convertToNull");
//       
    }
}
