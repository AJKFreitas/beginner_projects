/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamentoErro;

/**
 *
 * @author simara.salgado
 */
public class ErroSQL {
    private static ErroSQL instance = new ErroSQL();

    private ErroSQL(){}
    public static ErroSQL getInstance(){
        return instance;
    }
    
    private String mensagem;
    
    public String trataErro(int cod){
        if(cod==1062){
            this.mensagem = "Código Inserido já exite! <br> Insira um novo código";
        }else if(cod==1402){
            this.mensagem = "Limite de caracteres atingido!";
        }else if(cod==1451){
            this.mensagem = "Não foi possível excluir o Departamento, pois existem alocações nele.";
        }else if(cod==1402){
            this.mensagem = "Coluna inexistente!";
        }else if(cod==1364||cod==1048){
            this.mensagem = "Campo não pode receber valor nulo!";
        }else if(cod==1054){
            this.mensagem = "Campo não encontrado!";
        }else{
            this.mensagem = "Erro não tratado! código do erro de SQL: " +cod;
        }
        return this.mensagem;
    }
    
}
