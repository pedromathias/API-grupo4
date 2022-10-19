package br.org.serratec.model.error;

public class MensagemError {

	private String dataHora;
	
	private Integer status;
	
	private String titulo;
	
	private String mensagem;
	
	public MensagemError(String dataHora, Integer status, String titulo, String mensagem) {
		this.dataHora = dataHora;
		this.status = status;
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}	
	
	
}
