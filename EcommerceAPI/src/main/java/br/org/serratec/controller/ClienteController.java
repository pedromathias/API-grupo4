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

import br.org.serratec.dto.ClienteRequestDTO;
import br.org.serratec.dto.ClienteResponseDTO;
import br.org.serratec.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService servico;
	
	@GetMapping
	@ApiOperation(value = "Lista todas os clientes", notes = "Listagem de clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<ClienteResponseDTO>> obterTodos(){
		
		List<ClienteResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna cliente por id", notes = "Retorna Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ClienteResponseDTO> obterPorId(@PathVariable Long id){
		Optional<ClienteResponseDTO> optCliente = servico.obterPorId(id);
		if (optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
			}
			return ResponseEntity.notFound().build();
			}
	
	@PostMapping 
	@ApiOperation(value = "Insere cliente", notes = "Insere cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Insere clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody ClienteRequestDTO cliente) {
		ClienteResponseDTO clienteDTO = servico.cadastrar(cliente);
		return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza cliente", notes = "Atualiza cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})

	//public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {

//	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {


	public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO cliente) {

		return ResponseEntity.ok(servico.atualizar(id, cliente));
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete cliente", notes = "Delete cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deleta cliente"),
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
