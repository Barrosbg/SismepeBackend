package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.PesquisaDeSatisfacao;

@Repository
public interface PesquisaDeSatisfacaoRepository extends JpaRepository<PesquisaDeSatisfacao, Long> {

	List<PesquisaDeSatisfacao> findPesquisaSatisfacaoByCodigoAtendimento(Long codigoAtendimento);
	

	
}
