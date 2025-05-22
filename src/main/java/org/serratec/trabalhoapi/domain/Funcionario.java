package org.serratec.trabalhoapi.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

// Torna uma entidade
@Entity
public class Funcionario {
	
	// ID como chave primária
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Não aceita valores nulos
	@NotBlank(message = "nome é obrigatório")
	private String nome;

	// Escolhi relação um pra um 
	// Traz o endereco_id como chave estrangeira
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	public Funcionario() {}

	public Funcionario(Long id, String nome, Endereco endereco) {
		this.id = id;
	    this.nome = nome;
	    this.endereco = endereco;
	}

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

	public Endereco getEndereco() {
	    return endereco;
	}

	public void setEndereco(Endereco endereco) {
	    this.endereco = endereco;
	}
}
