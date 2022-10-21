package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.exception.ResourceBadRequestException;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.Cliente;
import br.org.serratec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;
	
	public List<Cliente> obterTodos(){
		return repositorio.findAll();
	}
	
	public Optional<Cliente> obterPorId(Long id){
		Optional<Cliente> optCliente = repositorio.findById(id);
		
		if(optCliente.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o cliente com id " + id);
		}
		return optCliente;
	}
	
	public Cliente cadastrar(Cliente cliente) {
		
		return repositorio.save(cliente);
	}
	
	public Cliente atualizar(Long id, Cliente cliente) {
		
		obterPorId(id);
		cliente.setId(id);
		return repositorio.save(cliente);
		
	}
	
	public void deletar(Long id) {
		repositorio.deleteById(id);
	}
	
}
