package br.org.serratec.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Embeddable
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	@ApiModelProperty(value = "Identificador unico do Pedido")
	private Long id;

	@Column(name = "data_pedido", nullable = false)
	@ApiModelProperty(value = "Data do Pedido", required = true)
	private Date dataPedido;

	@Column(name = "data_entrega", nullable = true)
	@ApiModelProperty(value = "Data de Entrega", required = true)
	private Date dataEntrega;

	@Column(name = "data_envio", nullable = true)
	@ApiModelProperty(value = "Data de Envio", required = true)
	private Date dataEnvio;

	@Column(name = "status", length = 20)
	@ApiModelProperty(value = "Status", required = true)
	private String status;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@JsonBackReference
	@ApiModelProperty(value = "Id_Cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	@ApiModelProperty(value = "Id_ItemPedido")
	private List<ItemPedido> itemPedido;

	public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;

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
