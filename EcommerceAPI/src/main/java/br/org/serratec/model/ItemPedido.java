package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long id;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "preco_venda")
	private int precoVenda;
	
	@ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
	
	@OneToOne(mappedBy = "itemPedido")
	private Produto produto;
	
	@Column(name="valor_bruto")
	private double valorBruto;
	
	@Column(name= "percentual_desconto")
	private double percentDesconto;
	
	@Column(name="valor_liquido")
	private double valorLiquido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(int precoVenda) {
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
