package br.gov.pe.sismepe.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.Equipamento;
import br.gov.pe.sismepe.domain.Lancamento;
import br.gov.pe.sismepe.repositories.CadastroDeEquipamentosDescricaoRepository;
import br.gov.pe.sismepe.repositories.CadastroDeEquipamentosRepository;

@Service
public class EquipamentoService {

	@Autowired
	private CadastroDeEquipamentosDescricaoRepository repoEquipCad;
	
	@Autowired
	private CadastroDeEquipamentosRepository repoLancamento;
	
//	@Autowired
//	private CadastroDeEquipamentosDescricaoRepository cadEquipamento;
//	
//    public Page<Equipamento> filtro(String numeroSerie, Integer page, Integer linesPerPage,
//            String orderBy, String direction) {
//	PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
//	    	
//	return (Page<Equipamento>) cadEquipamento.findEquipamentoByNumeroSerie(numeroSerie, pageRequest);
	

//}
	

	public Equipamento EquipNumeroSerie( String numeroSerie){
		
		Equipamento equip = repoEquipCad.findByNumeroSerie(numeroSerie);
        if(equip != null) {
        	  List<Lancamento> lanc = repoLancamento.findByEquipamento(equip.getId());
        	   equip.setLancamento(lanc);
        	   return equip;
        	  
        }else {
        	System.out.println(equip);
        	return equip;
        }
        
       
	}
	
	
	public ResponseEntity<Equipamento> saveEquipe(Equipamento equipamento)  {
		
		Equipamento equip = repoEquipCad.findByNumeroSerie(equipamento.getNumeroSerie());
         if(equip != null) {
     
        	  throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Máquina já cadastrada");
         }
         
         Equipamento equipa = repoEquipCad.save(equipamento);
  		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(equipa.getId())
  				.toUri();
  		return ResponseEntity.created(uri).build();
		
	}
	
	
}
