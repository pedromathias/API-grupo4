package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository repositorio;
	
	public List<ItemPedido> obterTodos() {
		return repositorio.findAll();
	}
	
	public Optional<ItemPedido> obterPorId(Long id){
		
		Optional<ItemPedido> optItemPedido = repositorio.findById(id);
		
		if(optItemPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o itemPedido com id " + id);
		}
		
		return optItemPedido;
	}
	
	public ItemPedido cadastrar(ItemPedido itemPedido) {
		
		itemPedido.setId(null);
		return repositorio.save(itemPedido);
	}
	
	public ItemPedido atualizar(Long id, ItemPedido itemPedido) {
		
		obterPorId(id);
		
		itemPedido.setId(id);
		return repositorio.save(itemPedido);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}

}
