package br.gov.pe.sismepe.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.PerfilUsuario;
@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {

	@Query(value= "select u.cd_usuario,u.cd_perfil from perfil_usuario u where u.cd_usuario = :id", nativeQuery = true)
	List<PerfilUsuario> findByUsuario_id(Integer id);
	
	@Transactional
	@Modifying
	@Query(value= "insert into perfil_usuario (cd_perfil, cd_usuario) values (:cd_perfil,:cd_usuario)", nativeQuery = true)
	void savePerfilUSer(@Param("cd_perfil") Integer cd_perfil,@Param("cd_usuario") Integer cd_usuario);
	
	
}
