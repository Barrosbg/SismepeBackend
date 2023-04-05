package br.gov.pe.sismepe.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pe.sismepe.domain.Lancamento;
import br.gov.pe.sismepe.domain.PerfilUsuario;

@Transactional
public interface CadastroDeEquipamentosRepository extends JpaRepository<Lancamento, Integer> {


    @Query(value= "select * from lancamento as l\r\n"
    		+ "    inner join  lancamento_equipamento as le\r\n"
    		+ "     on l.cd_lancamento = le.cd_lancamento\r\n"
    		+ "    where le.cd_equipamento = :id order by l.cd_lancamento DESC  ;", nativeQuery = true)
	List<Lancamento> findByEquipamento(Integer id);
	
}
