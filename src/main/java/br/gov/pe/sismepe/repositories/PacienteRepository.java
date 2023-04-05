package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.Pessoa;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
		
	@Transactional(readOnly = true)
	Page<Paciente> findByPessoa_nomeContaining(String pessoaNome, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<Paciente> findByPessoaIn(List<Pessoa> pessoa, Pageable pageable);
	
}
