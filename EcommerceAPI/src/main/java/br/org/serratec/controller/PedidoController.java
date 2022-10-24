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

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService servico;

	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> obterTodos() {

		List<PedidoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> obterPorId(@PathVariable Long id) {
		Optional<PedidoResponseDTO> optPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optPedido.get());
	}

	@PostMapping
	public ResponseEntity<PedidoResponseDTO> cadastrar(@RequestBody @Valid PedidoRequestDTO pedido) {
		PedidoResponseDTO pedidoDTO = servico.cadastrar(pedido);
		return new ResponseEntity<>(pedidoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody PedidoRequestDTO pedido) {
		return ResponseEntity.ok(servico.atualizar(id, pedido));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
