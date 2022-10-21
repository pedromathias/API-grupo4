package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity	
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_categoria")
	private Long id;
	
	@NotBlank(message="Preencha o nome")
	@Size(max=40,message="Máximo de 40 caracteres.")
	@Column(name="nome",nullable=false,length=40)
	private String nome;
	
	@Size(max= 150,message="Máximo de 150 caracteres")
	@Column(name = "descricao", nullable = true,length=150)
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
