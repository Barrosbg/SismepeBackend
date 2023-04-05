package br.gov.pe.sismepe.services;

import java.util.List;
import java.util.Optional;

import br.gov.pe.sismepe.repositories.PrestadorRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Prestador;
import br.gov.pe.sismepe.repositories.PrestadorRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class PrestadorService {
	
	@Autowired
	PrestadorRepository repo;

	@Autowired
	PrestadorRepositoryImpl repo2;
	
	public Prestador find(Long id) {
		Optional<Prestador> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Prestador não encontrato! ID: " + id));
	}

	public List<Prestador> findAll() {
		return repo.findAll();
	}
	
	public Page<Prestador> findByNomeAndEspecialidade(String nome, Long idEspecialidade, String numeroConselho, Integer page, Integer linesPerPage, String orderBy, String directions){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		return idEspecialidade != null ? 
				repo.findByNomeContainingAndNomeNotLikeAndEspecialidades_idOrderByNomeAsc(nome, "", idEspecialidade, pageRequest) : 
					repo.findByNomeContainingAndNomeNotLikeOrderByNomeAsc(nome, "", pageRequest);
	}

	public Page<Prestador> findPrestador(String nome, Long idEspecialidade, String numeroConselho, Integer page, Integer linesPerPage, String orderBy, String directions){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		return repo.findPrestador(nome, idEspecialidade, numeroConselho, pageRequest);
	}
}
