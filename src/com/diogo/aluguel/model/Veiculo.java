package com.diogo.aluguel.model;

import java.time.LocalDateTime;

public class Veiculo {
	private long id;
	private String modelo;
	private Usuario proprietario;
	private Usuario locatario;
	private LocalDateTime horariocadastro;

	private int rodas;
	private Boolean disponivel_para_aluguel;
	private String placa;
	private int ano;

	public Veiculo() {
		super();
		modelo = "";
	}

	public Veiculo(int id, String modelo, int ano, String placa, Usuario dono, LocalDateTime horariocadastro) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.placa = placa;
		this.proprietario = dono;
		this.horariocadastro = horariocadastro;
		this.ano = ano;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDateTime getHorariocadastro() {
		return horariocadastro;
	}

	public void setHorariocadastro(LocalDateTime horariocadastro) {
		this.horariocadastro = horariocadastro;
	}

	public int getRodas() {
		return rodas;
	}

	public void setRodas(int rodas) {
		this.rodas = rodas;
	}

	public Boolean getDisponivel_para_aluguel() {
		return disponivel_para_aluguel;
	}

	public String getDisponivel_para_aluguel_string() {
		if (locatario != null) {
			return "ALUGADO";
		} else if (disponivel_para_aluguel) {
			return "DISPONÍVEL PARA ALUGAR";
		} else {
			return "NÃO DISPONÍVEL PARA ALUGAR";
		}
	}

	public void setDisponivel_para_aluguel(Boolean disponivel_para_aluguel) {
		this.disponivel_para_aluguel = disponivel_para_aluguel;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}

	public Usuario getLocatario() {
		return locatario;
	}

	public void setLocatario(Usuario locatario) {
		this.locatario = locatario;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
