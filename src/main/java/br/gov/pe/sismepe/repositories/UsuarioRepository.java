package br.gov.pe.sismepe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Transactional(readOnly = true)
	Usuario findByLogin(String login);
	
	@Transactional(readOnly = true)
	Usuario findByEmail(String email);
	
	@Transactional(readOnly = true)
	Usuario findByPessoa(Pessoa pessoa);
	@Transactional(readOnly = true)
	Usuario findByPessoa_id(Long id);
	
	@Transactional(readOnly = true)
	Optional<Usuario> findById(Integer id);
	
	@Transactional(readOnly = true)
	List<Usuario> findUsuariosByPessoa(Pessoa pessoa);
	
	@Transactional(readOnly = true)
	List<Usuario> findUsuariosByEmail(String email);
	
	@Transactional(readOnly = true)
	@Query("SELECT u FROM Usuario u WHERE u.login = :login AND u.ativo = 'S'")
	Usuario buscarParaLogin(@Param("login") String login);
	
	//@Query("SELECT DISTINCT p FROM Produto p INNER JOIN p.categorias cat WHERE p.nome LIKE %:nome% AND cat IN :categorias ")
}