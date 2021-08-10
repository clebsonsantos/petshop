package com.crowde.fenrir.repository.filter;

import java.time.LocalDate;

public class ConsultaFilter {
	
	private String clienteNome;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	
	
	
}
