package com.diogo.aluguel.model;

import java.time.LocalDateTime;

public class Moto extends Veiculo {
    Boolean bau;

    public Moto(int id, String modelo, int ano, String placa, Usuario dono, LocalDateTime horario_cadastro, Boolean bau) {
        super(id, modelo, ano, placa, dono, horario_cadastro);
        this.bau = bau;
    }
    
    public Boolean getBau() {
        return bau;
    }

    public void setBau(Boolean bau) {
        this.bau = bau;
    }
}
