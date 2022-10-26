package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.exception.ResourceBadRequestException;
import br.org.serratec.exception.ResourceNotFoundException;
import br.org.serratec.model.Categoria;
import br.org.serratec.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepositorio;

	public List<Categoria> obterTodos() {
		return categoriaRepositorio.findAll();
	}

	public Optional<Categoria> obterPorId(Long id) {
		Optional<Categoria> optCategoria = categoriaRepositorio.findById(id);

		if (optCategoria.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a categoria com id " + id);
		}
		return optCategoria;
	}

	public Categoria cadastrar(Categoria categoria) {
		validarNome(categoria);
		validarDescricao(categoria);
		return categoriaRepositorio.save(categoria);
	}

	public Categoria atualizar(Long id, Categoria categoria) {
		validarNome(categoria);
		validarDescricao(categoria);
		obterPorId(id);
		categoria.setId(id);
		return categoriaRepositorio.save(categoria);

	}

	public void deletar(Long id) {
		categoriaRepositorio.deleteById(id);
	}

	private void validarNome(Categoria categoria) {
		if (categoria.getNome().isBlank() || categoria.getNome() == null) {
			throw new ResourceBadRequestException("O nome deve ser informado");
		} else if (categoria.getNome().length() > 30) {
			throw new ResourceBadRequestException("Tamanho máximo de 30 caracteres no nome");
		}

	}

	private void validarDescricao(Categoria categoria) {
		if (categoria.getDescricao().length() > 150) {
			throw new ResourceBadRequestException("Tamanho máximo de 150 caracteres na descrição");
		}

	}
}
