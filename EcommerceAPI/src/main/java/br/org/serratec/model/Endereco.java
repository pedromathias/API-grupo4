package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Embeddable
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	@ApiModelProperty(value = "Identificador unico do endereço")
	private Long id;

	@Column(name = "cep", nullable = false, length = 9)
	@ApiModelProperty(value = "CEP", required = true)
	private String cep;

	@Column(name = "rua", nullable = false, length = 100)
	@ApiModelProperty(value = "Logradouro", required = true)
	private String logradouro;

	@Column(name = "complemento", nullable = true, length = 20)
	@ApiModelProperty(value = "Complemento", required = true)
	private String complemento;

	@Column(name = "numero", nullable = false)
	@ApiModelProperty(value = "Numero", required = true)
	private String numero;

	@Column(name = "bairro", nullable = false, length = 50)
	@ApiModelProperty(value = "Bairro", required = true)
	private String bairro;

	@Column(name = "cidade", nullable = true, length = 30)
	@ApiModelProperty(value = "Localidade", required = true)
	private String localidade;

	@Column(name = "estado", nullable = false, length = 2)
	@ApiModelProperty(value = "UF", required = true)
	private String uf;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@JsonBackReference
	@ApiModelProperty(value = "Cliente")
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public Long getEndereco() {
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
