package br.org.serratec.model;

import java.util.List;

public class MensagemEmail {

	private String assunto;

	private String mensagem;

	private String remetente;

	private List<String> destinatarios;

	public MensagemEmail(String assunto, String mensagem, String remetente, List<String> destinatarios) {
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.remetente = remetente;
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public List<String> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}

}
