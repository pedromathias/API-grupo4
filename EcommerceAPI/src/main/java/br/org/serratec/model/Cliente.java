package br.org.serratec.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//CREATE TABLE cliente (id_cliente SERIAL PRIMARY KEY,
//email varchar(30) NOT NULL,
//nome_usuario varchar(20) NOT NULL,
//nome_completo varchar(60) NOT NULL,
//senha varchar(255),
//cpf varchar(14) NOT NULL,
//telefone varchar(11),
//data_nasc DATE, 
//id_endereco INTEGER, FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco));

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	@Column(nullable = false, length = 30)
	private String email;
	
	@Column(nullable = false, length = 20)
	private String nomeUsuario;
	
	@Column(nullable = false, length = 60)
	private String nomeCompleto;
	
	@Column(nullable = true, length = 255)
	private String senha;
	
	@Column(nullable = false, length = 14)
	private String cpf;
	
	@Column(nullable = true, length = 11)
	private String telefone;
	
	@Column(nullable = true)
	private Date dataNasc;
	
	//Fazer idEndereco
	
	
	
}
