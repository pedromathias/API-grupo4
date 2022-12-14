package br.org.serratec.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.serratec.dto.ClienteResponseDTO;
import br.org.serratec.dto.PedidoRequestDTO;
import br.org.serratec.dto.PedidoResponseDTO;
import br.org.serratec.dto.ProdutoResponseDTO;
import br.org.serratec.exception.ResourceBadRequestException;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.model.MensagemEmail;
import br.org.serratec.model.Pedido;
import br.org.serratec.repository.ItemPedidoRepository;
import br.org.serratec.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;

	@Autowired
	private ItemPedidoService servicoItemPedido;

	@Autowired
	private ItemPedidoRepository itemPedidoRepositorio;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ProdutoService produtoService;

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

	public Optional<PedidoRequestDTO> obterPorId2(Long id) {
		Optional<Pedido> optPedido = repositorio.findById(id);
		if (optPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o pedido com id " + id);
		}
		PedidoRequestDTO pedidoDTO = mapper.map(optPedido.get(), PedidoRequestDTO.class);
		return Optional.of(pedidoDTO);
	}

	public PedidoResponseDTO cadastrar(PedidoRequestDTO pedido) {
		validarDataPedido(pedido);
		validarStatus(pedido);
		var pedidoModel = mapper.map(pedido, Pedido.class);
		pedidoModel.setId(null);
		pedidoModel = repositorio.save(pedidoModel);
		var response = mapper.map(pedidoModel, PedidoResponseDTO.class);
		List<ItemPedido> itensResponse = new ArrayList<ItemPedido>();
		for (ItemPedido itemPedido : pedido.getItemPedido()) {
			var itemPedidoModel = mapper.map(itemPedido, ItemPedido.class);
			itemPedidoModel.setPedido(pedidoModel);
			itemPedidoRepositorio.save(itemPedidoModel);
			itensResponse.add(mapper.map(itemPedidoModel, ItemPedido.class));
			Long clienteId = pedido.getCliente().getId();
			var destinatarios = new ArrayList<String>();
			Optional<ClienteResponseDTO> cliente = clienteService.obterPorId(clienteId);
			destinatarios.add(cliente.get().getEmail());
			Long produtoID = itemPedido.getProduto().getId();
			Optional<ProdutoResponseDTO> produto = produtoService.obterPorId(produtoID);
			servicoItemPedido.calcularValorBruto(itemPedidoModel, produto.get().getValorUnitario());
			servicoItemPedido.calcularValorLiquido(itemPedidoModel);
			String mensagem = "<h1 style=\"color:blue\">Olá Sr(a)" + cliente.get().getNomeUsuario()
					+ "!</h1> <p> Seu pedido foi cadastrado com sucesso!</p>" + "<ul><strong>Dados do Pedido: </strong>"
					+ "<li>Data Pedido:" + pedidoModel.getDataPedido() + "</li>" + "<li>Status Pedido:"
					+ pedidoModel.getStatus() + "</li>" + "<li>Valor bruto do Pedido: "
					+ itemPedidoModel.getValorBruto() + "</li>" + "<li>Porcentagem de desconto do Pedido: "
					+ itemPedidoModel.getPercentDesconto() + "</li>" + "<li>Valor final do Pedido: "
					+ itemPedidoModel.getValorLiquido() + "</li>" + "</ul>" + "<ul><strong>Dados do Produto: </strong>"
					+ "<li>Nome do Produto: " + produto.get().getNome() + "</li>" + "<li>Descrição do Produto: "
					+ produto.get().getDescricao() + "</li>" + "<li>Código do Produto: " + produto.get().getId()
					+ "<li>" + "</ul>" + "<ul><strong>Dados do Cliente: </strong>" + "<li>Cpf do Cliente:"
					+ cliente.get().getCpf() + "</li>" + "<li>Nome do Cliente:" + cliente.get().getNomeCompleto()
					+ "</li>" + "</ul>";
			MensagemEmail email = new MensagemEmail("Pedido Cadastrado.", mensagem, "g4serratec@gmail.com",
					destinatarios);
			emailService.enviar(email);
			return response;

		}
		return response;
	}

	public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedido) {

		obterPorId(id);
		validarDataPedido(pedido);
		validarStatus(pedido);
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
		Date data = new Date();
		if (pedido.getDataPedido() == null) {
			throw new ResourceBadRequestException("A data deve ser informada");
		} else if (pedido.getDataPedido().before(data)) {
			throw new ResourceBadRequestException("A data não pode ser retroativa");
		}

	}

	private void validarStatus(PedidoRequestDTO pedido) {
		if (pedido.getStatus().length() > 20) {
			throw new ResourceBadRequestException("Tamanho máximo de 20 caracteres no status");
		}
	}

}
