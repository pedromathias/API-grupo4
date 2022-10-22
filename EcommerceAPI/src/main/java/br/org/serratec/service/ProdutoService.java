package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.exception.ResourceBadRequestException;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repositorio;
	
	public List<Produto> obterTodos() {
		return repositorio.findAll();
	}
	
	public Optional<Produto> obterPorId(Long id){
		
		Optional<Produto> optProduto = repositorio.findById(id);
		
		if(optProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o produto com id " + id);
		}
		
		return optProduto;
	}
	
	public Produto cadastrar(Produto produto) {
		List<Produto> produtoDescricao = repositorio.findByDescricao(produto.getDescricao());
		if(produtoDescricao.size() > 0) {
			throw new ResourceBadRequestException("Descrição já cadastrada!");
		}
		produto.setIdProduto(null);
		return repositorio.save(produto);
	}
	
	public Produto atualizar(Long id, Produto produto) {
		
		obterPorId(id);
	
		produto.setIdProduto(id);
		return repositorio.save(produto);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}

}
