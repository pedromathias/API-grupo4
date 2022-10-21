package br.org.serratec.dto;

import java.util.List;

import javax.persistence.OneToMany;

import br.org.serratec.model.Endereco;

public class ClienteResponseDTO {

	private Long id;
	
	private String email;
	
	private String nomeUsuario;
	
	private String nomeCompleto;
	
	private String telefone;
	
	@OneToMany(mappedBy="cliente")
	private List<Endereco> endereco;
	
	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	
}
