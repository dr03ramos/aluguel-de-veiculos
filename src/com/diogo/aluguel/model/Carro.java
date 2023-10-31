package com.diogo.aluguel.model;

import java.time.LocalDateTime;

public class Carro extends Veiculo {
    Boolean multimidia;
    String cor = "";

    public Carro(int id, String modelo, int ano, String placa, Usuario dono, LocalDateTime horario_cadastro, Boolean multimidia, String cor) {
        super(id, modelo, ano, placa, dono, horario_cadastro);
        this.multimidia = multimidia;
        this.cor = cor;
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
