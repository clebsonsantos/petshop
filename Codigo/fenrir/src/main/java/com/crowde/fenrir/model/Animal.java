package com.crowde.fenrir.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "animal")
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_animal")
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	@Size(min=2, message="O nome tem que conter pelo menos 2 letras")
	private String nome;

	@NotBlank(message = "Pelagem é obrigatório")
	private String pelagem;

	@NotNull(message = "Sexo é obrigatório")
	private String sexo;

	@NotNull(message = "Peso é obrigatório")
	@DecimalMin(value = "0.5", message = "O peso do animal deve ser maior que 0,5")
	@DecimalMax(value = "9999999.99", message = "O peso do animal deve ser menor que 9.999.999,99")
	private BigDecimal peso;

	@Column(name = "data_nascimento")
	@NotNull(message = "Data de nascimento é obrigatório")
	private LocalDate dataNascimento;

	private String imagem;
	
	@Column(name = "content_type")
	private String contentType;

	@Column(name = "status_animal")
	private boolean statusAnimal;
	
	@Transient
	private boolean novaFoto;

	@ManyToOne
	@JoinColumn(name = "id_cor")
	@NotNull(message = "Cor é obrigatório")
	private Cor cor;

	@ManyToOne(optional=true)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_raca")	
	@NotNull(message = "Raça é obrigatório")
	private Raca raca;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="id_prontuario")	
	private Prontuario prontuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPelagem() {
		return pelagem;
	}

	public void setPelagem(String pelagem) {
		this.pelagem = pelagem;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean getStatusAnimal() {
		return statusAnimal;
	}

	public void setStatusAnimal(boolean statusAnimal) {
		this.statusAnimal = statusAnimal;
	}	

	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
	
	public boolean isNovo() {
		return id == null;
	}
	
	public String getImagemOuMock() {
		return !StringUtils.isEmpty(imagem) ? imagem : "animal-pata.png";
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
		Animal other = (Animal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
