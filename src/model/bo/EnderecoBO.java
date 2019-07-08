/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bo;

import model.vo.EnderecoServer;

/**
 *
 * @author Clony
 */
public class EnderecoBO {
    private EnderecoServer endereco;
    public EnderecoBO(EnderecoServer endereco) {
        this.endereco = endereco;
    }
    
    public boolean validarTamanhoCep(){
        return endereco.getCep().length()==8;
    }
    
    public String removeHifenCep(){
        return endereco.getCep().replace("-", "");
    }
    
    public String getEnderecoCompleto(){
        return endereco.getLocalidade()+" "+endereco.getBairro()+"/"+endereco.getUf();
    }
}