package br.gov.pe.sismepe.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.repositories.PacienteRepository;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {
	
	@Autowired
	PacienteRepository repo;
	
	@Autowired
	PessoaRepository repoPessoa;
	
	public Paciente find(Long id) {
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrato! ID: " + id));
	}

	public List<Paciente> findAll() {
		return repo.findAll();
	}
	
	public Page<Paciente> findByPessoaNomeMatricula(String nome, Integer matricula, Integer page, Integer linesPerPage, String orderBy, String directions){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		if(matricula!=null) {
			List<PessoaTitular> listaPessoaTitular = repoPessoa.findTitularsByMatriculaAndDataCancelamentoIsNull(matricula);
			List<PessoaDependente> listaPessoaDependente = repoPessoa.findDependentesByMatriculaAndDataCancelamentoIsNullAndDataBloqueioIsNull(matricula);
			List<Pessoa> listaPessoa;
			if(listaPessoaDependente.isEmpty()) {
				listaPessoa = listaPessoaTitular.stream().map(obj -> new Pessoa(obj.getId())).collect(Collectors.toList());
			}else {
				listaPessoa = listaPessoaDependente.stream().map(obj -> new Pessoa(obj.getId())).collect(Collectors.toList());
				listaPessoa.add(new Pessoa(listaPessoaTitular.get(0).getId()));
			}
			return repo.findByPessoaIn(listaPessoa, pageRequest);
		}		
		return repo.findByPessoa_nomeContaining(nome, pageRequest);
	}
	
	public Paciente save(Paciente paciente) {
		return repo.save(paciente);
	}
}
