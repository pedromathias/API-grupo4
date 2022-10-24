package br.org.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.dto.ProdutoRequestDTO;
import br.org.serratec.dto.ProdutoResponseDTO;
import br.org.serratec.service.ProdutoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pokemons")

public class ProdutoController {

	@Autowired
	private ProdutoService servico;

//	@GetMapping
//	public ResponseEntity<List<Produto>> obterTodos() {
//
//		List<Produto> lista = servico.obterTodos();
//		return ResponseEntity.ok(lista);
//	}
	
	@GetMapping
	@ApiOperation(value = "Lista todos os produtos", notes = "Listagem dos produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os produtos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodos() {
		List<ProdutoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna produto por Id", notes = "Retorna produto por Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna produto por Id"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id) {
		Optional<ProdutoResponseDTO> optProduto = servico.obterPorId(id);
		if(optProduto.isPresent()){
			return ResponseEntity.ok(optProduto.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@ApiOperation(value = "Insere produto", notes = "Insere produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Insere produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ProdutoResponseDTO> cadastrar(@ModelAttribute ProdutoRequestDTO produto) {
		ProdutoResponseDTO produtoDTO = servico.cadastrar(produto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza produto", notes = "Atualiza produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produto) {
		return ResponseEntity.ok(servico.atualizar(id, produto));
	public ResponseEntity<ProdutoResponseDTO> cadastrar(@ModelAttribute ProdutoRequestDTO pokemon) {
		ProdutoResponseDTO pokemonDTO = servico.cadastrar(pokemon);
		return new ResponseEntity<>(pokemonDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO pokemon) {
		return ResponseEntity.ok(servico.atualizar(id, pokemon));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta produto", notes = "Deleta produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deleta produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
