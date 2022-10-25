package br.org.serratec.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity	
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_categoria")
	@ApiModelProperty(value="Identificador unico da categoria")
	private Long id;
	
	@Column(name="nome", nullable=false, length=40)
	@ApiModelProperty(value="Nome da categoria", required = true)
	private String nome;
	
	@Column(name = "descricao", nullable = true, length = 150)
	@ApiModelProperty(value="Descrição da categoria", required = true)
	private String descricao;

	@OneToMany(mappedBy="categoria")
	@JsonBackReference
	@ApiModelProperty(value="Id do produto")
	private List<Produto> produto;

	@OneToMany(mappedBy="categoria")
	@ApiModelProperty(value="Id do produto")
	private List<Produto> produto;

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
