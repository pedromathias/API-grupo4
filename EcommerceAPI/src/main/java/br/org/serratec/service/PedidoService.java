package br.org.serratec.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.PedidoRequestDTO;
import br.org.serratec.dto.PedidoResponseDTO;
import br.org.serratec.exception.ResourceBadRequestException;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.Pedido;
import br.org.serratec.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;

	private ModelMapper mapper = new ModelMapper();

	public List<PedidoResponseDTO> obterTodos() {
		List<Pedido> lista = repositorio.findAll();
		var novaLista = new ArrayList<PedidoResponseDTO>();
		for (Pedido pedido : lista) {
			novaLista.add(mapper.map(pedido, PedidoResponseDTO.class));
		}
		return novaLista;
	}

	public Optional<PedidoResponseDTO> obterPorId(Long id) {
		Optional<Pedido> optPedido = repositorio.findById(id);
		if (optPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o pedido com id " + id);
		}
		PedidoResponseDTO pedidoDTO = mapper.map(optPedido.get(), PedidoResponseDTO.class);
		return Optional.of(pedidoDTO);
	}

	public PedidoResponseDTO cadastrar(PedidoRequestDTO pedido) {
		validarDataPedido(pedido);
		var pedidoModel = mapper.map(pedido, Pedido.class);
		pedidoModel.setId(null);
		pedidoModel = repositorio.save(pedidoModel);
		var response = mapper.map(pedidoModel, PedidoResponseDTO.class);
		// colocar email
		return response;
	}

	public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedido) {

		obterPorId(id);
		var pedidoModel = mapper.map(pedido, Pedido.class);
		pedidoModel.setId(id);
		pedidoModel = repositorio.save(pedidoModel);
		return mapper.map(pedidoModel, PedidoResponseDTO.class);
	}

	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}

	private void validarDataPedido(PedidoRequestDTO pedido) {

		if (pedido.getDataPedido() == null) {
			throw new ResourceBadRequestException("A data deve ser informada");
		}
	}

}


