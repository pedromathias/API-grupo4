package br.org.serratec.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;
	
	@NotBlank(message="Preencha o nome")
	@Size(max=30,message="Tamanho máximo de 30 caracteres")
	@Column(name = "nome",nullable=false,length=30)
	private String nome;
	
	@Size(max=100,message="Tamanho máximo de 100 caracteres")
	@Column(name = "descricao",nullable=true,length=100)
	private String descricao;
	
	@NotNull(message="Preencha a quantidade de estoque")
	@Column(name = "qtd_estoque",nullable=false)
	private Integer quantidadeEstoque;
	
	@Column(name = "data_cadastro",nullable=true)
	private Date dataCadastro;
	
	@NotNull(message="Preencha o Valor unitário")
	@Column(name = "valor_unitario",nullable=false)
	private Double valorUnitario;
	
	@ManyToOne
	@JoinColumn(name = "id_item_pedido")
	@JsonBackReference
	private ItemPedido itemPedido;
	
	@OneToMany(mappedBy="produto")
    private List<Categoria> categoria;
	
//	@JoinColumn(name = "idCategoria", referencedColumnName = "id_categoria")
//	private Categoria categoria;
//	@Column
//	private Image imagemProduto;
	
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<Categoria> getCategoria() {
		return categoria;
	}
	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	
//	public Image getImagemProduto() {
//		return imagemProduto;
//	}
//	public void setImagemProduto(Image imagemProduto) {
//		this.imagemProduto = imagemProduto;
//	}

}
