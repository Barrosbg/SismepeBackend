package br.gov.pe.sismepe.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pe.sismepe.domain.Equipamento;
import br.gov.pe.sismepe.domain.FichaInspecaoJms;
import br.gov.pe.sismepe.domain.Lancamento;
@Transactional
public interface CadastroDeEquipamentosDescricaoRepository extends JpaRepository<Equipamento, Integer> {
	

//	@Query(value="select * from equipamento_cad ec inner join lancamento_equipamento l inner join lancamento l2 on l.cd_lancamento = l2.cd_lancamento and ec.cd_equipamento = l.cd_equipamento where ec.patrimonio_numero=:patrimonio_numero", nativeQuery = true)
//	Equipamento buscarPorPatrimonio(@Param("patrimonio_numero") String patrimonioNumero);
	
	Equipamento findByPatrimonioNumero(String patrimonioNumero);
	Equipamento findByNumeroSerie(String numeroSerie);

}
