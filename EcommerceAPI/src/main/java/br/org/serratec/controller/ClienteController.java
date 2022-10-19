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

import br.org.serratec.model.Cliente;
import br.org.serratec.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteServico;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> obterTodos(){
		
		List<Cliente> lista = clienteServico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	@GetMapping("/{idCliente}")
	public ResponseEntity<Cliente> obterPorId(@PathVariable Long id){
		
		Optional<Cliente> optCliente = clienteServico.obterPorId(id);
		return ResponseEntity.ok(optCliente.get());
	}
	
	@PostMapping 
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
		cliente = clienteServico.cadastrar(cliente);
		return new ResponseEntity<>(cliente, HttpStatus.CREATED);
	}
	@PutMapping("/{idCliente}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteServico.atualizar(id, cliente));
	}
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		clienteServico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
