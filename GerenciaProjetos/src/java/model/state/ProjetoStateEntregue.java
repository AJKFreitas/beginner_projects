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
public class ProjetoStateEntregue extends ProjetoState {
    
    @Override
    public int getPrazo() {
        return 5;
    }
    
    @Override
    public String getStatus() {
        return "Entregue";
    }

    @Override
    public String negociacao(Projeto projeto) {
        return "Projeto não pode ser negociado, pois foi Entregue!";
    }

    @Override
    public String desenvolvimento(Projeto projeto) {
        return "Projeto não pode ser desenvolvido, pois foi Entregue!";
    }

    @Override
    public String entregue(Projeto projeto) {
        return "Projeto foi Entregue!";
    }

    @Override
    public String cancelado(Projeto projeto) {
        return "Projeto não pode ser cancelado, pois foi Entregue!";
    }
  
}
