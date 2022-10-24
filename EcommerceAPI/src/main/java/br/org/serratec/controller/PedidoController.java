package br.org.serratec.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import br.org.serratec.dto.PedidoRequestDTO;
import br.org.serratec.dto.PedidoResponseDTO;
import br.org.serratec.service.PedidoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService servico;

	@GetMapping
	@ApiOperation(value = "Lista todos pedidos", notes = "Listagem de pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<PedidoResponseDTO>> obterTodos() {

		List<PedidoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna pedido por Id", notes = "Retorna pedido por Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna pedido por Id"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<PedidoResponseDTO> obterPorId(@PathVariable Long id) {
		Optional<PedidoResponseDTO> optPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optPedido.get());
	}

	@PostMapping
	@ApiOperation(value = "Insere pedido", notes = "Insere pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Insere pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<PedidoResponseDTO> cadastrar(@RequestBody @Valid PedidoRequestDTO pedido) {
		PedidoResponseDTO pedidoDTO = servico.cadastrar(pedido);
		return new ResponseEntity<>(pedidoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza pedido", notes = "Atualiza pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<PedidoResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody PedidoRequestDTO pedido) {
		return ResponseEntity.ok(servico.atualizar(id, pedido));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta pedido", notes = "Deleta pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deleta pedido"),
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
