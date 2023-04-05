package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.PesquisaPerguntaResposta;
import br.gov.pe.sismepe.dto.PesquisaPerguntaRespostaDTO;
@Repository
public interface PesquisaPerguntaRespostaRepository extends JpaRepository<PesquisaPerguntaResposta, Long>{

	

}
