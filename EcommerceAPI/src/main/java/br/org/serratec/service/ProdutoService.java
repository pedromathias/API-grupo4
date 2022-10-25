package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.org.serratec.dto.ClienteResponseDTO;
import br.org.serratec.dto.PedidoRequestDTO;
import br.org.serratec.dto.PedidoResponseDTO;
import br.org.serratec.dto.ProdutoRequestDTO;
import br.org.serratec.dto.ProdutoResponseDTO;
import br.org.serratec.exception.ResourceBadRequestException;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.model.MensagemEmail;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositorio;
	
	@Autowired
	private ItemPedidoService ipService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;

	private ModelMapper mapper = new ModelMapper();

	public List<ProdutoResponseDTO> obterTodos() {
		List<Produto> lista = repositorio.findAll();
		var novaLista = new ArrayList<ProdutoResponseDTO>();
		for (Produto produto : lista) {
			novaLista.add(mapper.map(produto, ProdutoResponseDTO.class));
		}
		return novaLista;
	}

	public Optional<ProdutoResponseDTO> obterPorId(Long id) {
		Optional<Produto> optProduto = repositorio.findById(id);
		if (optProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o produto com id " + id);
		}
		ProdutoResponseDTO dto = mapper.map(optProduto.get(), ProdutoResponseDTO.class);
		return Optional.of(dto);
	}

	public ProdutoResponseDTO cadastrar(ProdutoRequestDTO produto) {
		validarNomeProduto(produto);
		validarDescricao(produto);
		validarQuantidadeEstoque(produto);
		validarValorUnitario(produto);
		validarCategoria(produto);
		
		List<Produto> produtoDescricao = repositorio.findByDescricao(produto.getDescricao());
		if (produtoDescricao.size() > 0) {
			throw new ResourceBadRequestException("Descrição já cadastrada!");
		}
		var produtoModel = new Produto();
		produtoModel.setNome(produto.getNome());
		produtoModel.setDescricao(produto.getDescricao());
		produtoModel.setQuantidadeEstoque(produto.getQuantidadeEstoque());
		produtoModel.setDataCadastro(produto.getDataCadastro());
		produtoModel.setValorUnitario(produto.getValorUnitario());
		produtoModel.setImagemProduto(converterImagemBase64(produto.getImagemProduto()));
		produtoModel.setCategoria(produto.getCategoria());
		produtoModel.setItemPedido(produto.getItemPedido());
		produtoModel = repositorio.save(produtoModel);
		Long itemPedidoId = produto.getItemPedido().getId();
		Optional<ItemPedido> itemPedido = ipService.obterPorId(itemPedidoId);
		Long pedidoID = itemPedido.get().getPedido().getId();
		Optional<PedidoResponseDTO> pedido = pedidoService.obterPorId(pedidoID);
		Optional<PedidoRequestDTO> pedido2 = pedidoService.obterPorId2(pedidoID);
		Long clienteId = pedido2.get().getCliente().getId();
		Optional<ClienteResponseDTO> cliente = clienteService.obterPorId(clienteId);
		var destinatarios = new ArrayList<String>();
		destinatarios.add(cliente.get().getEmail());
		String mensagem = "<h1 style=\"color:blue\">Olá Sr(a)" + cliente.get().getNomeUsuario()
				+ "!</h1> <p> Seu pedido foi cadastrado com sucesso!</p>" + "<ul>Dados do Pedido:"
					+ "<li>Data Pedido:"+pedido.get().getDataPedido()+"</li>"
					+ "<li>Status Pedido:"+pedido.get().getStatus()+"</li>"
					+ "<li>Valor do Pedido"+itemPedido.get().getValorLiquido()+"</li>"
				+ "</ul>"
				+"<ul>Dados do Cliente: "
					+"<li>Cpf do Cliente:"+cliente.get().getCpf()+"</li>"
					+"<li>Nome do Cliente:"+cliente.get().getNomeCompleto()+"</li>"
				+ "</ul>"
				+"<ul>Dados do Produto: "
					+"<li>Nome do Produto "+produtoModel.getNome()+"</li>"
					+"<li>Descrição do Produto"+produtoModel.getDescricao()+"</li>"
				+"</ul>";
		MensagemEmail email = new MensagemEmail("Nova conta criada.", mensagem, "g4serratec@gmail.com", destinatarios);
		emailService.enviar(email);
		var response = mapper.map(produtoModel, ProdutoResponseDTO.class);
		return response;
	}
	
	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produto) {
		obterPorId(id);
		validarNomeProduto(produto);
		validarDescricao(produto);
		validarQuantidadeEstoque(produto);
		validarValorUnitario(produto);
		validarCategoria(produto);
		var produtoModel = new Produto();
		produtoModel.setId(id);
		produtoModel.setNome(produto.getNome());
		produtoModel.setDescricao(produto.getDescricao());
		produtoModel.setQuantidadeEstoque(produto.getQuantidadeEstoque());
		produtoModel.setDataCadastro(produto.getDataCadastro());
		produtoModel.setValorUnitario(produto.getValorUnitario());
		produtoModel.setImagemProduto(converterImagemBase64(produto.getImagemProduto()));
    produtoModel.setCategoria(produto.getCategoria());
		produtoModel = repositorio.save(produtoModel);
		return mapper.map(produtoModel, ProdutoResponseDTO.class);
	}

	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}

	private void validarNomeProduto(ProdutoRequestDTO produto) {
		if (produto.getNome() == null) {
			throw new ResourceBadRequestException("O nome deve ser informado");
		} else if (produto.getNome().length() > 30)
		{
			throw new ResourceBadRequestException("Tamanho máximo de 30 caracteres no nome");
		}

	}

	private void validarDescricao(ProdutoRequestDTO produto) {
		if (produto.getNome().length() > 100)
		{
			throw new ResourceBadRequestException("Tamanho máximo de 100 caracteres na descrição");
		}

	}

	private void validarQuantidadeEstoque(ProdutoRequestDTO produto) {
		if (produto.getQuantidadeEstoque() == null) {
			throw new ResourceBadRequestException("A quantidade de estoque deve ser informada");
		}

	}

	private String converterImagemBase64(MultipartFile imagem) {
		try {
			byte[] imageByteArray = Base64.encodeBase64(imagem.getBytes());
			String result = new String(imageByteArray);
			return result;
		} catch (Exception e) {
			throw new ResourceBadRequestException("O valor unitário deve ser informado");
		}

	}
	
	private void validarValorUnitario(ProdutoRequestDTO produto) {
		if (produto.getValorUnitario() == null) {
			throw new ResourceBadRequestException("O valor unitário deve ser informado");
		}

	}

	private void validarCategoria(ProdutoRequestDTO produto) {
		if(produto.getCategoria() == null){
			throw new ResourceBadRequestException("A categoria deve ser informado");
		}
	}

}
