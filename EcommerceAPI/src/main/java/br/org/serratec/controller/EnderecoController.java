package br.org.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService servico;
	
	@GetMapping
	public List<Endereco> obterTodos(){
		return servico.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Endereco> obterPorId(@PathVariable Long id_endereco){
		return servico.obterPorId(id_endereco);
	}
	
	@PostMapping 
	public Endereco cadastrar(@RequestBody Endereco endereco) {
		return servico.cadastrar(endereco);
	}
	
	@PutMapping("/{id}")
	public Endereco atualizar(@PathVariable Long id_endereco, @RequestBody Endereco endereco) {
		return servico.atualizar(id_endereco, endereco);
	}
	
	@DeleteMapping ("/id}")
	public void deletar (@PathVariable Long id_endereco) {
		servico.deletar(id_endereco);
	}

}
