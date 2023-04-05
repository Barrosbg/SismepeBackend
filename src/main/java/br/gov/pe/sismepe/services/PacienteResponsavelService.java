package br.gov.pe.sismepe.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.PacienteResponsavel;
import br.gov.pe.sismepe.domain.PacienteResponsavelPK;
import br.gov.pe.sismepe.domain.Parentesco;
import br.gov.pe.sismepe.dto.PacienteResponsavelDTO;
import br.gov.pe.sismepe.repositories.PacienteResponsavelRepository;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteResponsavelService {
	
	@Autowired
	PacienteResponsavelRepository repo;
	
	@Autowired
	PessoaRepository repoPessoa;
	
	public PacienteResponsavel find(Long pacienteId, Long pessoaId) {
		PacienteResponsavelPK pk =  new PacienteResponsavelPK();
		pk.setPaciente(pacienteId);
		pk.setResponsavel(pessoaId);
		Optional<PacienteResponsavel> obj = repo.findById(pk);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Responsável pelo paciente não encontrato! paciente ID: " + pacienteId + ", pessoaId:" + pessoaId));
	}

	public List<PacienteResponsavel> findAll() {
		return repo.findAll();
	}
	
	public List<PacienteResponsavel> findByPaciente(Long pacienteId) {
		return repo.findByPacienteAndAtivo(new Paciente(pacienteId),"S");
	}
	
	public PacienteResponsavel insert(PacienteResponsavel pacienteResponsavel) {
		pacienteResponsavel.setDataInc(new Date());
		pacienteResponsavel.setAtivo("S");
		return repo.save(pacienteResponsavel);
	}

	public PacienteResponsavel fromDTO(PacienteResponsavelDTO objDTO) {
		return new PacienteResponsavel(new PacienteResponsavelPK(objDTO.getPacienteId(), 
				objDTO.getResponsavelId()), new Parentesco(objDTO.getParentescoId()), 
				objDTO.getTelefone(), objDTO.getDataInc(), objDTO.getAtivo());
	}

	public void update(PacienteResponsavelDTO entity) {
		PacienteResponsavelPK pk =  new PacienteResponsavelPK();
		pk.setPaciente(entity.getPacienteId());
		pk.setResponsavel(entity.getResponsavelId());
		Optional<PacienteResponsavel> obj = repo.findById(pk);
		if(entity.getAtivo()!=null) obj.get().setAtivo(entity.getAtivo());		
		if(entity.getTelefone()!=null) obj.get().setTelefone(entity.getTelefone());
		if(entity.getParentescoId()!=null) obj.get().setParentesco(new Parentesco(entity.getParentescoId()));
		repo.save(obj.get());
	}
}
