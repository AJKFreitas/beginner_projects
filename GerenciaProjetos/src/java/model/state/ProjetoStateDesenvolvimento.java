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
public class ProjetoStateDesenvolvimento extends ProjetoState {
    
    @Override
    public int getPrazo() {
        return 60;
    }
   
    @Override
    public String getStatus() {
        return "Desenvolvimento";
    }

    @Override
    public String negociacao(Projeto projeto) {
        return "Projeto está em desenvolvimento!";
    }

    @Override
    public String desenvolvimento(Projeto projeto) {
        return "Projeto está em desenvolvimento!";
    }

    @Override
    public String entregue(Projeto projeto) {
        projeto.setStatus(new ProjetoStateEntregue());
        return "Projeto pode ser entregue!";
    }

    @Override
    public String cancelado(Projeto projeto) {
        projeto.setStatus(new ProjetoStateCancelado());
        return "Projeto pode ser cancelado!";
    }
 
}
