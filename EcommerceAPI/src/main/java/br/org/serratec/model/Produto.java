package br.org.serratec.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	@ApiModelProperty(value = "Identificador unico do Produto")
	private Long id;

	@Column(name = "nome", nullable = false, length = 30)
	@ApiModelProperty(value = "Nome do Produto", required = true)
	private String nome;

	@Column(name = "descricao", length = 100)
	@ApiModelProperty(value = "Descrição do Pedido", required = true)
	private String descricao;

	@Column(name = "qtd_estoque", nullable = false)
	@ApiModelProperty(value = "Quantidade em Estoque", required = true)
	private Integer quantidadeEstoque;

	@Column(name = "data_cadastro")
	@ApiModelProperty(value = "Data de Cadastro")
	private Date dataCadastro;

	@Column(name = "valor_unitario", nullable = false)
	@ApiModelProperty(value = "Valor Unitário", required = true)
	private Double valorUnitario;

	@Column(name = "imagem_produto", nullable = true, columnDefinition = "TEXT")
	@ApiModelProperty(value = "Imagem do Produto", required = true)
	private String imagemProduto;
<<<<<<< HEAD

=======
			
>>>>>>> tabela-pedidos
	@ManyToOne
	@ApiModelProperty(value = "Categoria do Produto")
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@OneToOne
	@JoinColumn(name = "id_item_pedido")
	@ApiModelProperty(value = "Id Item Pedido")
	@JsonBackReference
	private ItemPedido itemPedido;

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

<<<<<<< HEAD
=======
	
>>>>>>> tabela-pedidos
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
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

	public String getImagemProduto() {
		return imagemProduto;
	}

	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}

}
