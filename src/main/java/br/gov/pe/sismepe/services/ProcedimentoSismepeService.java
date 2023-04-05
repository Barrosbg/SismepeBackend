package br.gov.pe.sismepe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.ProcedimentoSismepe;
import br.gov.pe.sismepe.repositories.ProcedimentoSismepeRepository;

@Service
public class ProcedimentoSismepeService {
	
	@Autowired
	private ProcedimentoSismepeRepository procedimentoSismepeRepository;

	public Page<ProcedimentoSismepe> find(String descricao, String especificacao, Integer page, Integer linesPerPage, String orderBy,
			String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);

		
		if(especificacao != null && !especificacao.equals("")) {
			return procedimentoSismepeRepository.findByEspecificacaoContainingAndValorIsNotNull(especificacao, pageRequest);
		} else {
			
			return procedimentoSismepeRepository.findByDescricaoContainingAndValorIsNotNull(descricao, pageRequest);
		}
		
	}
	
	
}
