package br.org.serratec.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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

import com.google.gson.Gson;

import br.org.serratec.dto.EnderecoRequestDTO;
import br.org.serratec.dto.EnderecoResponseDTO;
import br.org.serratec.model.Endereco;
import br.org.serratec.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService servico;
	
	@GetMapping
	@ApiOperation(value = "Lista todos os endereços dos clientes", notes = "Listagem de endereços dos clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os endereços dos clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<List<EnderecoResponseDTO>> obterTodos(){
		List<EnderecoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna o endereço de cliente por Id", notes = "Retorna endereço de cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna endereço de cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<EnderecoResponseDTO> obterPorId(@PathVariable Long id){
		Optional<EnderecoResponseDTO> optEndereco = servico.obterPorId(id);
		if (optEndereco.isPresent()) {
			return ResponseEntity.ok(optEndereco.get());
			}
			return ResponseEntity.notFound().build();
			}
		
	
	@PostMapping 
	@ApiOperation(value = "Insere o endereço", notes = "Insere endereço")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Insere endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	public ResponseEntity<EnderecoResponseDTO> cadastrar(@RequestBody EnderecoRequestDTO endereco) throws Exception {
		URL url=new URL ("https://viacep.com.br/ws/"+endereco.getCep()+"/json/");
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String cep = "";
		StringBuilder jsonCep=new StringBuilder();
		while((cep=br.readLine())!=null) {
			jsonCep.append(cep);	
		}
		Endereco userAux = new Gson().fromJson(jsonCep.toString(), Endereco.class);
		endereco.setCep(userAux.getCep());
		endereco.setLogradouro(userAux.getLogradouro());
		endereco.setBairro(userAux.getBairro());
		endereco.setLocalidade(userAux.getLocalidade());
		endereco.setUf(userAux.getUf());
		
		EnderecoResponseDTO enderecoDTO = servico.cadastrar(endereco);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza o endereço por Id", notes = "Atualiza o endereço")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza o endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	
	})
	public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable Long id, @RequestBody EnderecoRequestDTO endereco) throws Exception {
			URL url=new URL ("https://viacep.com.br/ws/"+endereco.getCep()+"/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String cep = "";
			StringBuilder jsonCep=new StringBuilder();
			while((cep=br.readLine())!=null) {
				jsonCep.append(cep);	
			}
			Endereco userAux = new Gson().fromJson(jsonCep.toString(), Endereco.class);
			endereco.setCep(userAux.getCep());
			endereco.setLogradouro(userAux.getLogradouro());
			endereco.setBairro(userAux.getBairro());
			endereco.setLocalidade(userAux.getLocalidade());
			endereco.setUf(userAux.getUf());

		return ResponseEntity.ok(servico.atualizar(id, endereco));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta o endereço por Id", notes = "Deleta endereço")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deleta endereço"),
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
