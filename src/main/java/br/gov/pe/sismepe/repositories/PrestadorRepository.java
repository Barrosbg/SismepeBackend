package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.repositories.customRepository.PrestadorRespositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.Prestador;

public interface PrestadorRepository extends JpaRepository<Prestador, Long>, PrestadorRespositoryCustom {

	@Transactional(readOnly = true)
	Page<Prestador> findByNomeContainingAndNomeNotLikeAndEspecialidades_idOrderByNomeAsc(String nome, String nomeNotLike, Long idEspecialidade, Pageable pageable);

	@Transactional(readOnly = true)
	Page<Prestador> findByNomeContainingAndNomeNotLikeOrderByNomeAsc(String nome, String nomeNotLike, Pageable pageable);

	@Transactional(readOnly = true)
	Prestador findByPessoa(Pessoa pessoa);
	
}
