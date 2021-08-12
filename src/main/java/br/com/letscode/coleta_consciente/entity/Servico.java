package br.com.letscode.coleta_consciente.entity;

import lombok.Getter;

@Getter
public class Servico {

    private final float totalPagar;

    public Servico (PontoColeta pontoColeta, Cliente cliente){
        this.totalPagar = pontoColeta.getPreco() * cliente.getQuantidade();
    }
}
