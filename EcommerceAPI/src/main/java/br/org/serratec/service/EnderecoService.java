package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.EnderecoRequestDTO;
import br.org.serratec.dto.EnderecoResponseDTO;
import br.org.serratec.exception.ResourceNotFoundException;
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
	
	public Optional<EnderecoResponseDTO> obterPorId(Long id){
		Optional<Endereco> optEndereco = repositorio.findById(id);
		if(optEndereco.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o endereço com id " + id);
	}
		EnderecoResponseDTO dto = mapper.map(optEndereco.get(), EnderecoResponseDTO.class);
	return Optional.of(dto);
	}
	
	public EnderecoResponseDTO cadastrar(EnderecoRequestDTO endereco) {
		var enderecoModel = mapper.map(endereco, Endereco.class);
		enderecoModel.setId(null);
		enderecoModel = repositorio.save(enderecoModel);
		var response = mapper.map(enderecoModel, EnderecoResponseDTO.class);
		return response;
	}

	public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO endereco) {
		obterPorId(id);
		var enderecoModel = mapper.map(endereco, Endereco.class);
		enderecoModel.setId(id);
		enderecoModel = repositorio.save(enderecoModel);
		return mapper.map(enderecoModel, EnderecoResponseDTO.class);
	}
	
	public void deletar (Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
		
	}
}


