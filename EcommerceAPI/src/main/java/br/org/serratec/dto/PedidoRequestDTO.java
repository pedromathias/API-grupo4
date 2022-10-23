package br.org.serratec.dto;

import java.util.Date;

import br.org.serratec.model.Cliente;

public class PedidoRequestDTO {

	private Date dataPedido;
	
	private Date dataEntrega;
	
	private Date dataEnvio;
	
	private String status;
	
	private Cliente cliente;
 
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
