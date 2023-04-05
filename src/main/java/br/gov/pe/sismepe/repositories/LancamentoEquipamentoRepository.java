package br.gov.pe.sismepe.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pe.sismepe.domain.Lancamento;
import br.gov.pe.sismepe.domain.LancamentoEquipamento;
import br.gov.pe.sismepe.domain.Perfil;

public interface LancamentoEquipamentoRepository extends JpaRepository<LancamentoEquipamento, Integer> {

//	@Query(value="SELECT * FROM lancamento_equipamento l  WHERE l.cd_lancamento = :cd_lancamento", nativeQuery = true)
//	List<LancamentoEquipamento> findLancamento(@Param("cd_lancamento") Integer  cd_lancamento);
	
	
	
}
