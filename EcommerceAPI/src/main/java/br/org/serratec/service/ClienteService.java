package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.ClienteRequestDTO;
import br.org.serratec.dto.ClienteResponseDTO;
import br.org.serratec.exception.ResourceBadRequestException;
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

	public List<ClienteResponseDTO> obterTodos() {
		List<Cliente> lista = repositorio.findAll();
		var novaLista = new ArrayList<ClienteResponseDTO>();
		for (Cliente cliente : lista) {
			novaLista.add(mapper.map(cliente, ClienteResponseDTO.class));
		}
		return novaLista;
	}

	public Optional<ClienteResponseDTO> obterPorId(Long id) {
		Optional<Cliente> optCliente = repositorio.findById(id);
		if (optCliente.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o cliente com id " + id);
		}
		ClienteResponseDTO dto = mapper.map(optCliente.get(), ClienteResponseDTO.class);
		return Optional.of(dto);
	}

	public ClienteResponseDTO cadastrar(ClienteRequestDTO cliente) {
		validarEmail(cliente);
		validarNomeUsuario(cliente);
		validarNomeCompleto(cliente);
		validarCPF(cliente);
		validarTelefone(cliente);
		validarSenha(cliente);
		List<Cliente> clienteCPF = repositorio.findByCpf(cliente.getCpf());
		List<Cliente> clienteEmail = repositorio.findByEmail(cliente.getEmail());
		if (clienteCPF.size() > 0) {
			throw new ResourceBadRequestException("Cpf já cadastrado!");
		}
		if (clienteEmail.size() > 0) {
			throw new ResourceBadRequestException("Email já cadastrado");
		}
		var clienteModel = mapper.map(cliente, Cliente.class);
		clienteModel.setId(null);
		clienteModel = repositorio.save(clienteModel);
		var response = mapper.map(clienteModel, ClienteResponseDTO.class);
		var destinatarios = new ArrayList<String>();
		destinatarios.add(clienteModel.getEmail());
		String mensagem = "<h1 style=\"color:blue\"> Olá sr(a) " + clienteModel.getNomeCompleto()
				+ "! </h1> <p>Conta criada com sucesso!</p>";
		MensagemEmail email = new MensagemEmail("Nova conta criada.", mensagem, "g4serratec@gmail.com", destinatarios);
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

	private void validarEmail(ClienteRequestDTO cliente) {

		if (cliente.getEmail() == null) {
			throw new ResourceBadRequestException("O Email deve ser informado");
		} else if (cliente.getEmail().length() > 30) {
			throw new ResourceBadRequestException("Tamanho máxmimo do Email deve ser 30 caracteres.");
		} else if (!cliente.getEmail().contains("@")) {
			throw new ResourceBadRequestException("Formato de email inválido");
		}

	}

	private void validarNomeUsuario(ClienteRequestDTO cliente) {

		if (cliente.getNomeUsuario() == null) {
			throw new ResourceBadRequestException("O Nome de usuário deve ser informado");
		} else if (cliente.getNomeUsuario().length() > 20) {
			throw new ResourceBadRequestException("Tamanho máxmimo do Nome de Usuário deve ser 20 caracteres.");
		}

	}

	private void validarNomeCompleto(ClienteRequestDTO cliente) {

		if (cliente.getNomeCompleto() == null) {
			throw new ResourceBadRequestException("O Nome completo deve ser informado");
		} else if (cliente.getNomeCompleto().length() > 60) {
			throw new ResourceBadRequestException("Tamanho máxmimo do Nome completo deve ser 60 caracteres.");
		}

	}

	private void validarSenha(ClienteRequestDTO cliente) {

		if (cliente.getSenha().length() > 255) {
			throw new ResourceBadRequestException("Tamanho máxmimo da senha deve ser 255 caracteres.");
		}

	}

	private void validarCPF(ClienteRequestDTO cliente) {

		if (cliente.getCpf() == null) {
			throw new ResourceBadRequestException("O Nome completo deve ser informado");
		} else if (cliente.getCpf().length() > 14 || cliente.getCpf().length() < 14) {
			throw new ResourceBadRequestException("O Formato do cpf deve ser ex:123.456.789-10");
		}

	}

	private void validarTelefone(ClienteRequestDTO cliente) {

		if (cliente.getTelefone().length() < 10 || cliente.getTelefone().length() > 11) {
			throw new ResourceBadRequestException("O Formato do telefone deve ser ex:1140028922");
		}

	}

}
