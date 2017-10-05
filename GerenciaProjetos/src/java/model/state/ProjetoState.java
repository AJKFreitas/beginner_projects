/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.state;

import model.Projeto;

/**
 *
 * @author paulo.junior6
 */
public abstract class ProjetoState {
    
    public abstract int getPrazo();
    public abstract String getStatus();
    public abstract String negociacao(Projeto projeto);
    public abstract String desenvolvimento(Projeto projeto);
    public abstract String entregue(Projeto projeto);
    public abstract String cancelado(Projeto projeto);
    public String calculaPrazo(){
        return getPrazo()+" dias";
    }
}
