package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Endereco;
import br.org.serratec.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repositorio;
	
	public List<Endereco>obterTodos(){
		return repositorio.findAll();
	}
	
	public Optional<Endereco> obterPorId(Long id_endereco){
		return repositorio.findById(id_endereco);
	}
	
	public Endereco cadastrar(Endereco endereco) {
		endereco.setIdEndereco(null);
		return repositorio.save(endereco);
	}
	
	public Endereco atualizar(Long idEndereco, Endereco endereco) {
		obterPorId(idEndereco);
		endereco.setIdEndereco(idEndereco);
		return repositorio.save(endereco);
	}
	
	public void deletar (Long idEndereco) {
		obterPorId(idEndereco);
		repositorio.deleteById(idEndereco);
		
	}
}


