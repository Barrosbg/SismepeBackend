package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.PacienteResponsavel;
import br.gov.pe.sismepe.domain.PacienteResponsavelPK;

@Repository
public interface PacienteResponsavelRepository extends JpaRepository<PacienteResponsavel, PacienteResponsavelPK>{
			
	List<PacienteResponsavel> findByPacienteAndAtivo(Paciente paciente, String Ativo);
}
