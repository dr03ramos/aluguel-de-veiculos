package com.diogo.aluguel.model;

import java.time.LocalDateTime;

public class Furgao extends Carro {
    int espaco_interno;
    Boolean multimidia;
    String cor = "";

    public Furgao(int id, String modelo, int ano, String placa, Usuario dono, LocalDateTime horario_cadastro, Boolean multimidia, String cor, int espaco_interno) {
        super(id, modelo, ano, placa, dono, horario_cadastro, multimidia, cor);
        this.espaco_interno = espaco_interno;
    }

    public int getEspaco_interno() {
        return espaco_interno;
    }

    public void setEspaco_interno(int espaco_interno) {
        this.espaco_interno = espaco_interno;
    }    

    public Boolean getMultimidia() {
        return multimidia;
    }

    public void setMultimidia(Boolean multimidia) {
        this.multimidia = multimidia;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
