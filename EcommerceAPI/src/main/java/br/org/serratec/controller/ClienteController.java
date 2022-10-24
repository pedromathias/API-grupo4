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


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService servico;
	
	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> obterTodos(){
		
		List<ClienteResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> obterPorId(@PathVariable Long id){
		Optional<ClienteResponseDTO> optCliente = servico.obterPorId(id);
		if (optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
			}
			return ResponseEntity.notFound().build();
			}
	
	@PostMapping 
	public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody ClienteRequestDTO cliente) {
		ClienteResponseDTO clienteDTO = servico.cadastrar(cliente);
		return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")


	//public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {

//	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {


	public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO cliente) {

		return ResponseEntity.ok(servico.atualizar(id, cliente));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
