package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Pedido;
import br.org.serratec.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepositorio;
	
	public List<Pedido> obterTodos(){
		return pedidoRepositorio.findAll();
	}
	
	public Optional<Pedido> obterPorId(Long id){
		Optional<Pedido> optPedido = pedidoRepositorio.findById(id);
		
		return optPedido;
	}
	
	public Pedido cadastrar(Pedido pedido) {
		
		return pedidoRepositorio.save(pedido);
	}
	
	public Pedido atualizar(Long id, Pedido pedido) {
		
		pedido.setId(id);
		return pedidoRepositorio.save(pedido);
		
	}
	
	public void deletar(Long id) {
		pedidoRepositorio.deleteById(id);
	}
}
