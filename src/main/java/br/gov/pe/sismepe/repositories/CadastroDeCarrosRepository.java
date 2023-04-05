package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.CadastroDeCarros;

@Repository
public interface CadastroDeCarrosRepository extends JpaRepository<CadastroDeCarros, Long> {

	List<CadastroDeCarros> findByPlacaContaining(String placa );
	
	List<CadastroDeCarros> findBySetorContaining(String setor);
	
	List<CadastroDeCarros> findByNomeContaining( String nome);
	
	List<CadastroDeCarros> findByAtivo(String Ativo);
	
    CadastroDeCarros findByIdAndAtivo(Long id, String Ativo);
}
