/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.state.ProjetoState;

/**
 *
 * @author paulo.junior6
 */
public class StateFactory {
    public static ProjetoState create(String state){
        ProjetoState stateObject = null;
        String nomeClasse = "model.state.ProjetoState"+state;
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        if(!(objeto instanceof ProjetoState)) return null;
        stateObject = (ProjetoState) objeto;
        return stateObject;
    }
}
