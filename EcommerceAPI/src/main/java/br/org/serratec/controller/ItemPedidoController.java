package br.org.serratec.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/itempedidos")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService servico;

	@GetMapping
	@ApiOperation(value = "Lista todos os Itens de Pedidos", notes = "Listagem de Itens de Pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os itens de pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ItemPedido>> obterTodos() {

		List<ItemPedido> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna item de pedido por id", notes = "Retorna item de pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna item de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedido> obterPorId(@PathVariable Long id) {

		Optional<ItemPedido> optItemPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optItemPedido.get());
	}

	@PostMapping
	@ApiOperation(value = "Insere item de pedido", notes = "Insere item de pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Insere item de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedido> cadastrar(@RequestBody ItemPedido itemPedido) {
		itemPedido = servico.cadastrar(itemPedido);
		return new ResponseEntity<>(itemPedido, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza item de pedido", notes = "Atualiza item de pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza item de pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
		return ResponseEntity.ok(servico.atualizar(id, itemPedido));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta item de pedido", notes = "Deleta item de pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deleta item de pedido"),
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
