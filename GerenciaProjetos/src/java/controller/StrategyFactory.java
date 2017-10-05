/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.strategy.Funcao;

/**
 *
 * @author paulo.junior6
 */
public class StrategyFactory {
    public static Funcao create(String funcao){
        Funcao funcaoObject = null;
        String nomeClasse = "model.strategy.Funcao"+funcao;
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        if(!(objeto instanceof Funcao)) return null;
        funcaoObject = (Funcao) objeto;
        return funcaoObject;
    }
}
