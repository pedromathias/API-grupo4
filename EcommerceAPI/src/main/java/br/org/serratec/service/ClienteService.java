package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Cliente;
import br.org.serratec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepositorio;
	
	public List<Cliente> obterTodos(){
		return clienteRepositorio.findAll();
	}
	
	public Optional<Cliente> obterPorId(Long idCliente){
		Optional<Cliente> optCliente = clienteRepositorio.findById(idCliente);
		
		return optCliente;
	}
	
	public Cliente cadastrar(Cliente cliente) {
		
		return clienteRepositorio.save(cliente);
	}
	
	public Cliente atualizar(Long id, Cliente cliente) {
		
		cliente.setIdCliente(id);
		return clienteRepositorio.save(cliente);
		
	}
	
	public void deletar(Long id) {
		clienteRepositorio.deleteById(id);
	}
}
