package br.org.serratec.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.serratec.exception.ResourceBadRequestException;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repositorio;

	ItemPedido valorBruto = new ItemPedido();
	ItemPedido valorLiquido = new ItemPedido();

	public List<ItemPedido> obterTodos() {
		return repositorio.findAll();
	}

	public Optional<ItemPedido> obterPorId(Long id) {

		Optional<ItemPedido> optItemPedido = repositorio.findById(id);

		if (optItemPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o itemPedido com id " + id);
		}

		return optItemPedido;
	}

	public ItemPedido cadastrar(ItemPedido itemPedido) {
		itemPedido.setId(null);
		validarQuantidade(itemPedido);
		validarPrecoVenda(itemPedido);
		return repositorio.save(itemPedido);
	}

	public ItemPedido atualizar(Long id, ItemPedido itemPedido) {

		obterPorId(id);
		itemPedido.setId(id);
		calcularValorBruto2(itemPedido);
		calcularValorLiquido(itemPedido);
		validarQuantidade(itemPedido);
		validarPrecoVenda(itemPedido);
		return repositorio.save(itemPedido);
	}

	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}
	
	public ItemPedido calcularValorBruto2(ItemPedido itemPedido) {
		itemPedido.setValorBruto(itemPedido.getQuantidade() * itemPedido.getPrecoVenda());
		return repositorio.save(itemPedido);
	}


	public ItemPedido calcularValorBruto(ItemPedido itemPedido, double produto) {
		itemPedido.setValorBruto(itemPedido.getQuantidade() * produto);
		return repositorio.save(itemPedido);
	}

	public ItemPedido calcularValorLiquido(ItemPedido itemPedido) {
		itemPedido.setValorLiquido(
				itemPedido.getValorBruto() - itemPedido.getValorBruto() * itemPedido.getPercentDesconto());
		return repositorio.save(itemPedido);
	}

	private void validarQuantidade(ItemPedido itemPedido) {
		if (itemPedido.getQuantidade() == null) {
			throw new ResourceBadRequestException("A quantidade deve ser informado");
		} else if (itemPedido.getQuantidade() < 1) {
			throw new ResourceBadRequestException("O valor da quantidade deve ser maior que 1");
		}

	}

	private void validarPrecoVenda(ItemPedido itemPedido) {
		if (itemPedido.getPrecoVenda() == null) {
			throw new ResourceBadRequestException("O preço da venda não pode ser nulo");
		} else if (itemPedido.getPrecoVenda() <= 0) {
			throw new ResourceBadRequestException("O valor do Preco de venda deve ser maior que 0.00");
		}

	}

}
