package br.gov.pe.sismepe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Especialidade;
import br.gov.pe.sismepe.repositories.EspecialidadeRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository repo;
	
	public Especialidade find(Long id) {
		Optional<Especialidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id + "Tipo: " + Especialidade.class.getName()));
	}
	
}
