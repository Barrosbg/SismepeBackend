package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.pe.sismepe.domain.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer>{


	@Query(value="SELECT * FROM perfil p INNER JOIN perfil_usuario pu ON pu.CD_PERFIL = p.CD_PERFIL WHERE pu.CD_USUARIO = ?1", nativeQuery = true)
	List<Perfil> listByUser(int id);
	
}
