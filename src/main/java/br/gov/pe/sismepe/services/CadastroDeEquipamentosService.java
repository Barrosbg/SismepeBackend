package br.gov.pe.sismepe.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.Equipamento;
import br.gov.pe.sismepe.domain.Lancamento;
import br.gov.pe.sismepe.domain.LancamentoEquipamento;
import br.gov.pe.sismepe.domain.Setor;
import br.gov.pe.sismepe.repositories.CadastroDeEquipamentosDescricaoRepository;
import br.gov.pe.sismepe.repositories.CadastroDeEquipamentosRepository;
import br.gov.pe.sismepe.repositories.LancamentoEquipamentoRepository;
import br.gov.pe.sismepe.repositories.SetorRepository;

@Service
public class CadastroDeEquipamentosService {

	@Autowired
	private CadastroDeEquipamentosRepository cadRepo;
	
	@Autowired
	private CadastroDeEquipamentosDescricaoRepository cadeRepoDesc;
	
	@Autowired
	private LancamentoEquipamentoRepository equipamentoLancamento;
	
	@Autowired
	private SetorRepository repoSetor;
	
	public ResponseEntity<Object> save (Lancamento equipamento){
		Lancamento cad = cadRepo.save(equipamento);
		for(LancamentoEquipamento lista : equipamento.getEquipamento()) {
			Equipamento equip = cadeRepoDesc.findById(lista.getCd_equipamento().getId()).orElse(null);
			lista.setCd_equipamento(lista.getCd_equipamento());
			lista.setCd_lancamento(cad);
			equip.setStatus(equipamento.getStatus());
			equip.setSetor_equipe(equipamento.getCd_setor_recebedor());
			equip.setCd_responsavel(equipamento.getUsuarioRecebedor());
			cadeRepoDesc.save(equip);
			equipamentoLancamento.save(lista);	
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cad.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	public Page<Setor> findByDescricao(String setor,  Integer page,
			Integer linesPerPage, String orderBy) {
		Pageable  pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.ASC, orderBy);
		return repoSetor.findSetorByDescricao(setor, pageRequest);
	}

	
	
}
