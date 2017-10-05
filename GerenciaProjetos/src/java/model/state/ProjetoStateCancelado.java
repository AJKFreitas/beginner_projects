/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.state;

import model.Projeto;

/**
 *
 * @author Donizeth
 */
public class ProjetoStateCancelado extends ProjetoState {
    
    @Override
    public int getPrazo() {
        return 7;
    }
   
    @Override
    public String getStatus() {
        return "Cancelado";
    }

    @Override
    public String negociacao(Projeto projeto) {
        return "Projeto foi cancelado!";
    }

    @Override
    public String desenvolvimento(Projeto projeto) {
        return "Projeto não pode ser desenvolvido, pois foi cancelado!";
    }

    @Override
    public String entregue(Projeto projeto) {
        return "Projeto não pode ser entregue, pois foi cancelado!";
    }

    @Override
    public String cancelado(Projeto projeto) {
        return "Projeto foi cancelado!";
    }
 
}
