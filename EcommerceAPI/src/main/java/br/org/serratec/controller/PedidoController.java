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

import br.org.serratec.model.Pedido;
import br.org.serratec.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService servico;

	@GetMapping
	public ResponseEntity<List<Pedido>> obterTodos() {

		List<Pedido> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id_pedido}")
	public ResponseEntity<Pedido> obterPorId(@Valid @PathVariable Long id) {

		Optional<Pedido> optPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optPedido.get());
	}

	@PostMapping
	public ResponseEntity<Pedido> cadastrar(@Valid @RequestBody Pedido pedido) {
		pedido = servico.cadastrar(pedido);
		return new ResponseEntity<>(pedido, HttpStatus.CREATED);
	}

	@PutMapping("/{id_pedido}")
	public ResponseEntity<Pedido> atualizar(@Valid @PathVariable Long id, @RequestBody Pedido pedido) {
		return ResponseEntity.ok(servico.atualizar(id, pedido));
	}

	@DeleteMapping("/{id_pedido}")
	public ResponseEntity<?> deletar(@Valid @PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
