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
public class ProjetoStateNegociacao extends ProjetoState{
    
    @Override
    public int getPrazo() {
        return 30;
    }
   
    @Override
    public String getStatus() {
        return "Negociacao";
    }

    @Override
    public String negociacao(Projeto projeto) {
        return "Projeto está em negociação!";
    }

    @Override
    public String desenvolvimento(Projeto projeto) {
        projeto.setStatus(new ProjetoStateDesenvolvimento());
        return "Projeto pode ser desenvolvido!";
    }

    @Override
    public String entregue(Projeto projeto) {
        return "Projeto não pode ser entrgue, pois ainda está em negociação!";
    }

    @Override
    public String cancelado(Projeto projeto) {
        projeto.setStatus(new ProjetoStateCancelado());
        return "Projeto pode ser cancelado!";
    }
    
}
