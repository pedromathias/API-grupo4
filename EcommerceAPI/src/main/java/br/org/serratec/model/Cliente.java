package br.org.serratec.model;


import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	@ApiModelProperty(value="Identificador unico de cliente")
	private Long id;
	
//	@Email
	@Column(unique = true)
	@ApiModelProperty(value="Email do cliente", required = true)
	private String email;
	
	@Column(nullable = false, length = 20)
	@ApiModelProperty(value="Nome do usuario", required = true)
	private String nomeUsuario;
	
	@Column(nullable = false, length = 60)
	@ApiModelProperty(value="Nome completo do cliente", required = true)
	private String nomeCompleto;
	
	@Column(nullable = true, length = 255)
	@ApiModelProperty(value="Senha cliente", required=true)
	private String senha;
	
	@Column(unique = true)
	@ApiModelProperty(value="Cpf do cliente", required = true)
	private String cpf;
	
	@Column(nullable = true, length = 11)
	@ApiModelProperty(value="Telefone do cliente", required = true)
	private String telefone;
	
	@Column(nullable = true)
	@ApiModelProperty(value="Data de Nascimento do cliente", required = true)
	private Date dataNasc;
	
	@OneToMany(mappedBy="cliente")
	@ApiModelProperty(value="Endereço")
	private List<Endereco> endereco;
	
	@OneToMany(mappedBy="cliente")
	@ApiModelProperty(value="Pedido do Cliente")
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
