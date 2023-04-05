package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.AutorizacaoProcedimentoExterno;
import br.gov.pe.sismepe.domain.RegistroProcedimentoInterno;
import br.gov.pe.sismepe.domain.Usuario;

@Repository
public interface RegistroProcedimentoInternoRepository extends JpaRepository<RegistroProcedimentoInterno, Long>{

	List<RegistroProcedimentoInterno> findByAutorizacaoIn(List<AutorizacaoProcedimentoExterno> auths);

	Page<RegistroProcedimentoInterno> findByUsuarioCadastro(Usuario usuario, Pageable pageable);


}
