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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;
	
	@NotBlank(message = "Preencha o nome")
	@Size(max=30, message = "Tamanho máximo 30 caracteres")
	@Column(name = "nome", nullable = false, length = 30)
	private String nome;
	
	@NotBlank(message = "Preencha a descrição")
	@Size(max=100, message = "Tamanho máximo 100 caracteres")
	@Column(name = "descricao", length = 100)
	private String descricao;
	
	@NotNull
	@Column(name = "qtd_estoque", nullable = false)
	private Integer quantidadeEstoque;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@Column(name = "valor_unitario", nullable = false)
	private Double valorUnitario;
	
//	@Column
//	private Image imagemProduto;
	
	@OneToMany(mappedBy="produto")
	private List<Categoria> categoria;
	
	@ManyToOne
    @JoinColumn(name = "id_item_pedido")
    private ItemPedido itemPedido;
	
	
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
	public List<Categoria> getCategoria() {
		return categoria;
	}
	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setIdProduto(Long id) {
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
//	public Image getImagemProduto() {
//		return imagemProduto;
//	}
//	public void setImagemProduto(Image imagemProduto) {
//		this.imagemProduto = imagemProduto;
//	}

	
}
