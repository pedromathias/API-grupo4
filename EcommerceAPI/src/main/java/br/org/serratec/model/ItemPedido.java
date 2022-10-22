package br.org.serratec.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private int precoVenda;
	
	@Column(name = "valor_bruto")
	private double valorBruto;
	
	@NotNull(message = "Preencha o preço da venda")
	@Column(name = "percentual_desconto")
	private double percentDesconto;

	@Column(name = "valor_líquido")
	private double valorLiquido;

	@OneToMany(mappedBy = "itemPedido")
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

	public int getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(int precoVenda) {
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
