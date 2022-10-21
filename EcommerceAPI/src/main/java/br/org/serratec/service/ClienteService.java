package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.ClienteRequestDTO;
import br.org.serratec.dto.ClienteResponseDTO;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.MensagemEmail;
import br.org.serratec.repository.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;
	
	@Autowired
	private EmailService emailService;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<ClienteResponseDTO> obterTodos(){
		List<Cliente> lista = repositorio.findAll();
		var novaLista = new ArrayList<ClienteResponseDTO>();
		for(Cliente cliente : lista) {
			novaLista.add(mapper.map(cliente, ClienteResponseDTO.class));
		}
	return novaLista;
	}
	
	public Optional<ClienteResponseDTO> obterPorId(Long id){
		Optional<Cliente> optCliente = repositorio.findById(id);
		if(optCliente.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o cliente com id " + id);
		}
		ClienteResponseDTO dto = mapper.map(optCliente.get(), ClienteResponseDTO.class);
		return Optional.of(dto);
	}
	

	public Cliente cadastrar(Cliente cliente) {
		List<Cliente> clientes = repositorio.findByCpf(cliente.getCpf());
		List<Cliente> client = repositorio.findByEmail(cliente.getEmail());
		if(clientes.size()>0) {
			throw new RuntimeException("Cpf já cadastrado!");
		}
		if(client.size()>0) {
			throw new RuntimeException("Email já cadastrado");
		}
		return repositorio.save(cliente);
	}
		

		
	public ClienteResponseDTO cadastrar(ClienteRequestDTO cliente) {
		var clienteModel = mapper.map(cliente, Cliente.class);
		clienteModel.setId(null);
		clienteModel = repositorio.save(clienteModel);
		var response = mapper.map(clienteModel, ClienteResponseDTO.class);
		var destinatarios = new ArrayList<String>();
		destinatarios.add(clienteModel.getEmail());
		String mensagem = "<h1 style=\"color:blue\"> Olá sr(a) " + clienteModel.getNomeCompleto() + "! </h1> <p>Conta criada com sucesso!</p>";
		MensagemEmail email = new MensagemEmail(
				"Nova conta criada.",
				mensagem, 
				"g4serratec@gmail.com",
				destinatarios);
		emailService.enviar(email);
		
		return response;
		
	}
  	
	public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO cliente) {
		obterPorId(id);
		var clienteModel = mapper.map(cliente, Cliente.class);
		clienteModel.setId(id);
		clienteModel = repositorio.save(clienteModel);
		return mapper.map(clienteModel, ClienteResponseDTO.class);
		
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}
	

}
