package br.org.serratec.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//CREATE TABLE cliente (id_cliente SERIAL PRIMARY KEY,
//email varchar(30) NOT NULL,
//nome_usuario varchar(20) NOT NULL,
//nome_completo varchar(60) NOT NULL,
//senha varchar(255),
//cpf varchar(14) NOT NULL,
//telefone varchar(11),
//data_nasc DATE, 
//id_endereco INTEGER, FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco));

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private Long id;
	
	@NotBlank(message = "Preencha o nome")
	@Size(max=30, message = "Tamanho máximo 30 caracteres")
	@Column(nullable = false, length = 30)
	private String email;
	
	@NotBlank(message = "Preencha o nome de usuário")
	@Size(max=20, message = "Tamanho máximo 20 caracteres")
	@Column(nullable = false, length = 20)
	private String nomeUsuario;
	
	@NotBlank(message = "Preencha o nome do usuário completo")
	@Size(max=60, message = "Tamanho máximo 60 caracteres")
	@Column(nullable = false, length = 60)
	private String nomeCompleto;
	
	@Size(max=255, message = "Tamanho máximo 255 caracteres")
	@Column(nullable = true, length = 255)
	private String senha;
	
	@NotBlank(message = "Preencha o cpf")
	@Size(max=14, message = "Tamanho máximo 14 caracteres")
	@Column(nullable = false, length = 14)
	private String cpf;
	
	@Size(max=14, message = "Tamanho máximo 11 caracteres")
	@Column(nullable = true, length = 11)
	private String telefone;
	
	@Column(nullable = true)
	private Date dataNasc;
	
	@OneToMany(mappedBy="cliente")
	private List<Endereco> endereco;
	
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedido;

	
	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	
	
	
	
	
}
