package br.org.serratec.dto;

import java.util.Date;
import java.util.List;

import br.org.serratec.model.Endereco;

public class ClienteRequestDTO {

	private String email;
	
	private String nomeUsuario;
	
	private String nomeCompleto;
	
	private String senha;
	
	private String cpf;
	
	private String telefone;
	
	private Date dataNasc;
	
//	private List<Endereco> endereço;
//
//	
//	public List<Endereco> getEndereço() {
//		return endereço;
//	}
//
//	public void setEndereço(List<Endereco> endereço) {
//		this.endereço = endereço;
//	}

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
