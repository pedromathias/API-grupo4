package br.org.serratec.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.org.serratec.model.Endereco;
import br.org.serratec.model.Pedido;

public class ClienteRequestDTO {

	@NotBlank(message = "Preencha o nome")
	@Size(max=30, message = "Tamanho máximo 30 caracteres")
	private String email;
	
	@NotBlank(message = "Preencha o nome de usuário")
	@Size(max=20, message = "Tamanho máximo 20 caracteres")
	private String nomeUsuario;
	
	@NotBlank(message = "Preencha o nome do usuário completo")
	@Size(max=60, message = "Tamanho máximo 60 caracteres")
	private String nomeCompleto;
	
	@Size(max=255, message = "Tamanho máximo 255 caracteres")
	private String senha;
	
	@NotBlank(message = "Preencha o cpf")
	@Size(max=14, message = "Tamanho máximo 14 caracteres")
	private String cpf;
	
	@Size(max=14, message = "Tamanho máximo 11 caracteres")
	private String telefone;

	private Date dataNasc;
	
	private List<Endereco> endereço;
	
	private List<Pedido> pedido;
	
	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public List<Endereco> getEndereço() {
		return endereço;
	}

	public void setEndereço(List<Endereco> endereço) {
		this.endereço = endereço;
	}

	public String getEmail() {
		return email;
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

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
