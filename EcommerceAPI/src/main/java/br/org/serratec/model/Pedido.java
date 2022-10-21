package br.org.serratec.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Long id;
	
	@Column(nullable=false)
	private Date dataPedido;
	
	@Column
	private Date dataEntrega;
	
	@Column
	private Date dataEnvio;
	
	@Column(length=20)
	private String status;
	
	//@Embedded
	//private Cliente idCliente;
		
	
	
	/*public Cliente getIdCliente() {
	*	return idCliente;
	*}
	*
	*public void setIdCliente(Cliente idCliente) {
	*	this.idCliente = idCliente;
	*}
	*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
