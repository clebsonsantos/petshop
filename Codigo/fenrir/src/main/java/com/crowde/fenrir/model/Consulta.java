package com.crowde.fenrir.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consulta")
	private Long id;

	@Column(name = "data_consulta")
	//@NotEmpty(message = "É necessário informar um data para a consulta")
	private LocalDate dataConsulta;

	@Column(name = "hora_consulta")
	private LocalTime horaConsulta;

	//@NotEmpty(message = "É necessário informar um valor para a consulta")
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "valor_adicional")
	private BigDecimal valorAdicional;

	@Column(name = "is_pago")
	private boolean isPago;

	@Column(name = "valor_pago")
	private BigDecimal valorPago;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;
	
	@Column(name = "data_pagamento")
	private LocalDateTime dataPagamento;

	@Column(name = "data_cancelamento")
	private LocalDate dataCancelamento;

	@Column(name = "data_confirmacao")
	private LocalDate dataConfirmacao;
	
	@Column(name = "data_atendimento")
	private LocalDateTime dataAtendimento;
	
	@Column(name = "data_finalizacao")
	private LocalDate dataFinalizacao;

	@Enumerated(EnumType.STRING)
	private SituacaoConsulta situacao;

	@Column(name = "is_retorno")
	private boolean isRetorno;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento")
	private FormaPagamento formaPagamento;

	@Column(name = "is_externa")
	private boolean isExterna;

	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_atendente")
	private Funcionario atendente;

	@ManyToOne
	@JoinColumn(name = "id_prontuario")
	private Prontuario prontuario;

	@ManyToOne
	@JoinColumn(name = "id_veterinario")
	private Funcionario veterinario;
	
	
	public Consulta() {
	}
	
	
	public Consulta(Long id) {
		this.id = id;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public LocalTime getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(LocalTime horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public LocalDate getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDate dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public LocalDate getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(LocalDate dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}
	
	public LocalDateTime getDataAtendimento() {
		return dataAtendimento;
	}


	public void setDataAtendimento(LocalDateTime dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}


	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDate dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public boolean getIsRetorno() {
		return isRetorno;
	}

	public void setIsRetorno(boolean isRetorno) {
		this.isRetorno = isRetorno;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Funcionario getAtendente() {
		return atendente;
	}

	public void setAtendente(Funcionario atendente) {
		this.atendente = atendente;
	}

	public Funcionario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Funcionario veterinario) {
		this.veterinario = veterinario;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public boolean getIsPago() {
		return isPago;
	}

	public void setIsPago(boolean isPago) {
		this.isPago = isPago;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getValorAdicional() {
		return valorAdicional;
	}

	public void setValorAdicional(BigDecimal valorAdicional) {
		this.valorAdicional = valorAdicional;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public SituacaoConsulta getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoConsulta situacao) {
		this.situacao = situacao;
	}

	public boolean getIsExterna() {
		return isExterna;
	}

	public void setIsExterna(boolean isExterna) {
		this.isExterna = isExterna;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	public boolean isPagamentoValido() {
		BigDecimal valorTotalPago =  valorPago == null ? new BigDecimal(0) : valorPago ;
		BigDecimal total = valor;
		if(valorDesconto != null && valorPago != null) {
			valorTotalPago = valorPago.add(valorDesconto);
		}
		
		if(valorAdicional != null) {
			total =	valor.add(valorAdicional);
		}	
		
		return (valorTotalPago.subtract(total)).compareTo(BigDecimal.ZERO) >= 0;
	}

	public boolean isNova() {
		return this.getId() == null;
	}

}
