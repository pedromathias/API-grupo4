package br.org.serratec.model;

import java.awt.Image;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@Column(name = "id_produto")
	private Long idProduto;
	
	@Column(name = "nome", nullable = false, length = 30)
	private String nome;
	
	@Column(name = "descricao", length = 100)
	private String descricao;
	
	@Column(name = "qtd_estoque", nullable = false)
	private Integer quantidadeEstoque;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@Column(name = "valor_unitario", nullable = false)
	private Double valorUnitario;
	
	@Column
	private Image imagemProduto;
	
	@Column(name = "id_categoria")
	private Integer idCategoria;

	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Image getImagemProduto() {
		return imagemProduto;
	}
	public void setImagemProduto(Image imagemProduto) {
		this.imagemProduto = imagemProduto;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
