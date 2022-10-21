package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.EnderecoResponseDTO;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;
import br.org.serratec.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<EnderecoResponseDTO>obterTodos(){
		List<Endereco> lista = repositorio.findAll();
		var novaLista = new ArrayList<EnderecoResponseDTO>();
		for(Endereco endereco : lista) {
			novaLista.add(mapper.map(endereco, EnderecoResponseDTO.class));
		}
		return novaLista;
	}
	
	public Optional<Endereco> obterPorId(Long id){
		Optional<Endereco> optEndereco = repositorio.findById(id);
		if(optEndereco.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o endereço com id " + id);
		}
		return repositorio.findById(id);
	}
	
	public Endereco cadastrar(Endereco endereco) {
		endereco.setId(null);
		return repositorio.save(endereco);
	}
	
	public Endereco atualizar(Long id, Endereco endereco) {
		obterPorId(id);
		endereco.setId(id);
		return repositorio.save(endereco);
	}
	
	public void deletar (Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
		
	}
}


