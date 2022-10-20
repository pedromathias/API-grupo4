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

import br.org.serratec.model.Endereco;
import br.org.serratec.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService servico;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> obterTodos(){
		List<Endereco> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> obterPorId(@PathVariable Long id){
		Optional<Endereco> optEndereco = servico.obterPorId(id);
		return ResponseEntity.ok(optEndereco.get());
	}
	
	@PostMapping 
	public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco) {
		endereco = servico.cadastrar(endereco);
		return new ResponseEntity<>(endereco, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
		return ResponseEntity.ok(servico.atualizar(id, endereco));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
