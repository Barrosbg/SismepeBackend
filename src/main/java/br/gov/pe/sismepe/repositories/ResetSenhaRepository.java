package br.gov.pe.sismepe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.ResetSenha;
import br.gov.pe.sismepe.domain.Usuario;

@Repository
public interface ResetSenhaRepository extends JpaRepository<ResetSenha, Integer>{
	
	
	Optional<ResetSenha> findByUsuario(Usuario usuario);
	
	Optional<ResetSenha> findByToken(String token);
	
}
