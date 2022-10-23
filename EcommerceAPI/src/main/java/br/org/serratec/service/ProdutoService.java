package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.ProdutoRequestDTO;
import br.org.serratec.dto.ProdutoResponseDTO;
import br.org.serratec.exception.ResourceBadRequestException;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositorio;

	private ModelMapper mapper = new ModelMapper();

//	public List<Produto> obterTodos() {
//		return repositorio.findAll();
//	}

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
		List<Produto> produtoDescricao = repositorio.findByDescricao(produto.getDescricao());
		if (produtoDescricao.size() > 0) {
			throw new ResourceBadRequestException("Descrição já cadastrada!");
		}
		var produtoModel = mapper.map(produto, Produto.class);
		produtoModel.setId(null);
		produtoModel = repositorio.save(produtoModel);
		var response = mapper.map(produtoModel, ProdutoResponseDTO.class);
		return response;
	}

	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produto) {
		obterPorId(id);
		var produtoModel = mapper.map(produto, Produto.class);
		produtoModel.setId(id);
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
			;
		{
			throw new ResourceBadRequestException("Tamanho máximo de 30 caracteres no nome");
		}

	}

	private void validarDescricao(ProdutoRequestDTO produto) {
		if (produto.getNome().length() > 100)
			;
		{
			throw new ResourceBadRequestException("Tamanho máximo de 100 caracteres na descrição");
		}

	}

	private void validarQuantidadeEstoque(ProdutoRequestDTO produto) {
		if (produto.getQuantidadeEstoque() == null) {
			throw new ResourceBadRequestException("A quantidade de estoque deve ser informada");
		}

	}

	private void validarValorUnitario(ProdutoRequestDTO produto) {
		if (produto.getValorUnitario() == null) {
			throw new ResourceBadRequestException("O valor unitário deve ser informado");
		}

	}

}
