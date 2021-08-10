package com.crowde.fenrir.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.crowde.fenrir.model.validation.ClienteGroupSequenceProvider;
import com.crowde.fenrir.model.validation.group.CnpjGroup;
import com.crowde.fenrir.model.validation.group.CpfGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_pessoa")
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class Cliente extends Pessoa {

	private static final long serialVersionUID = 1L;

	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@NotNull(message = "Tipo cliente é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_cliente")
	private TipoCliente tipoCliente;

	@Column(name = "is_vip")
	private boolean isVip;

	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)	
	@JsonIgnore
	private List<Animal> animais;
	
	@PrePersist @PreUpdate
	private void prePersistPreUpdate() {
		this.cpfCnpj = this.cpfCnpj.replaceAll("\\.|-|/", "");
	}
	

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}

	public List<Animal> getAnimais() {
		return animais.stream().distinct().collect(Collectors.toList());
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	
	public boolean isNovo() {
		return getId() == null;
	}

}
