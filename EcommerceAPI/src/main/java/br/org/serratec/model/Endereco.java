package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;

	@NotNull(message = "Preencha o cep")
//	@Max(9)
	@Column(name = "cep", nullable = false, length = 9)
	private String cep;

	@NotNull(message = "Preencha a rua")
	@Size(max = 100, message = "Tamanho máxmimo de 100 caracteres")
	@Column(name = "rua", nullable = false, length = 100)
	private String rua;

	@NotBlank(message = "Preencha o bairro")
	@Size(max = 50, message = "Tamanho máxmimo de 50 caracteres")
	@Column(name = "bairro", nullable = false, length = 50)
	private String bairro;

	@Size(max = 30, message = "Tamanho máxmimo de 30 caracteres")
	@Column(name = "cidade", nullable = true, length = 30)
	private String cidade;

	@NotNull(message = "Preencha o numero")
	@Column(name = "numero", nullable = false)
	private int numero;

	@Size(max = 20, message = "Tamanho máxmimo de 20 caracteres")
	@Column(name = "complemento", nullable = true, length = 20)
	private String complemento;

	@Column(name = "estado", nullable = true)
	private String estado;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@JsonBackReference
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
