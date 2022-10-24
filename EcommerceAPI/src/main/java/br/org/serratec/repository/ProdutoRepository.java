package br.org.serratec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByDescricao(String descricao);

}