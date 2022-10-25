package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;



@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	@ApiModelProperty(value="Identificador unico do item de Pedido")
	private Long id;

	
	@Column(name = "quantidade")
	@ApiModelProperty(value="Quantidade")
	private int quantidade;

	@Column(name = "preco_venda")
	@ApiModelProperty(value="Preço de Venda")
	private Integer precoVenda;
		
	@Column(name="valor_bruto")
	@ApiModelProperty(value="Valor Bruto")
	private double valorBruto;
	
	@Column(name= "percentual_desconto")
	@ApiModelProperty(value="Percentual de Desconto")
	private double percentDesconto;
	
	@Column(name="valor_liquido")
	@ApiModelProperty(value="Valor Líquido")
	private double valorLiquido;

	@ManyToOne
    @JoinColumn(name = "id_pedido")
	@JsonBackReference
	@ApiModelProperty(value="Id_Pedido")
    private Pedido pedido;

	@OneToOne(mappedBy = "itemPedido")
	@ApiModelProperty(value="Id_Produto")
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Integer precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public double getPercentDesconto() {
		return percentDesconto;
	}

	public void setPercentDesconto(double percentDesconto) {
		this.percentDesconto = percentDesconto;
	}

	public double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}





}
