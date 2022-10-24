package br.org.serratec.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long id;

	@NotNull(message = "Preencha a quantidade")
	@Column(name = "quantidade")
	private int quantidade;

	@NotNull(message = "Preencha o preço da venda")
	@Column(name = "preco_venda")
	private double precoVenda;
	
	@Column(name = "valor_bruto")
	private double valorBruto;
	
	@NotNull(message = "Preencha o preço da venda")
	@Column(name = "percentual_desconto")
	private double percentDesconto;

	@Column(name = "valor_líquido")
	private double valorLiquido;

	@ManyToOne(mappedBy = "itemPedido")
	@JsonBackReference
	private List<Pedido> pedido;

	@OneToMany(mappedBy = "itemPedido")
	private List<Produto> produto;

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

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	public Double getValorBruto() {
	    return valorBruto;
	}
	
	public void setValorBruto(Double valorBruto) {
	    this.valorBruto = valorBruto;
	    
	}
	
	public Double getPercentDesconto() {
        return percentDesconto;
    }
    
    public void setPercentDesconto(double percentDesconto) {
        this.percentDesconto = percentDesconto;
        
    }
	
    public Double getValorLiquido() {
        return valorLiquido;
    }
    
    public void setValorLiquido(Double valorLiquido) {
        this.valorLiquido = valorLiquido;
        
    }
	

}
