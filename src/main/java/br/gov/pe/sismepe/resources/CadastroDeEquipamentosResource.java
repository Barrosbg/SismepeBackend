package br.gov.pe.sismepe.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.Equipamento;
import br.gov.pe.sismepe.domain.Lancamento;
import br.gov.pe.sismepe.domain.Setor;
import br.gov.pe.sismepe.dto.PessoaTitularDTO;
import br.gov.pe.sismepe.repositories.CadastroDeEquipamentosDescricaoRepository;
import br.gov.pe.sismepe.repositories.CadastroDeEquipamentosRepository;
import br.gov.pe.sismepe.repositories.LancamentoEquipamentoRepository;
import br.gov.pe.sismepe.repositories.SetorRepository;
import br.gov.pe.sismepe.services.CadastroDeEquipamentosService;
import br.gov.pe.sismepe.services.EquipamentoService;



@RestController
@RequestMapping("/equipamento")
public class CadastroDeEquipamentosResource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CadastroDeEquipamentosRepository repoEquip;
	
	@Autowired
	private CadastroDeEquipamentosService equipService;
	
	@Autowired
	private LancamentoEquipamentoRepository lancamentoRepositori;
	
	@Autowired
	private SetorRepository repoSetor;
	
	@Autowired
	private EquipamentoService equipserv;
	

	@Autowired
	private CadastroDeEquipamentosDescricaoRepository repoEquipCad;
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> buscar(Integer lancamento){
		List<Lancamento> cad = repoEquip.findAll() ;
		return ResponseEntity.ok().body(cad);
	}
	
	@PostMapping("/save/lancamento")
     public ResponseEntity<Lancamento> save(@Valid @RequestBody Lancamento equipamento){
		   ResponseEntity<Object> cad = equipService.save(equipamento);		   
		   return ResponseEntity.ok().build();
	
	}
	
	@PostMapping("/save/equip")
	public void saveEquip(@Valid @RequestBody Equipamento equipamento){
	
	equipserv.saveEquipe(equipamento);
		
//		Equipamento equip = repoEquipCad.save(equipamento);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(equip.getId())
//				.toUri();
//		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Equipamento> findEquip(@PathVariable Integer id){
		Equipamento equip = repoEquipCad.findById(id).orElse(null);
		return ResponseEntity.ok().body(equip);
	}
	
	@GetMapping("/todosEquipamentos")
	public ResponseEntity<List<Equipamento>> findAllEquip(){
		List<Equipamento> equip = repoEquipCad.findAll();
		return ResponseEntity.ok().body(equip);
	}
	
	
	@GetMapping("/filtro/patrimonio/{patrimonioNumero}")
	public ResponseEntity<Equipamento> findEquipPatrimonio(@PathVariable String patrimonioNumero){
		Equipamento equip = repoEquipCad.findByPatrimonioNumero(patrimonioNumero);
		return ResponseEntity.ok().body(equip);
	}
	
	@GetMapping("/filtro/NumeroSerie/{numeroSerie}")
	public ResponseEntity<Equipamento> findEquipNumeroSerie(@PathVariable String numeroSerie){
		Equipamento equip = repoEquipCad.findByNumeroSerie(numeroSerie);
		return ResponseEntity.ok().body(equip);
	}
	
	@GetMapping("/filtro/NumeroSerieComLancamentos/{numeroSerie}")
	public ResponseEntity<Equipamento> findEquipNumeroSerieELancamentos(@PathVariable String numeroSerie){
		Equipamento equip = equipserv.EquipNumeroSerie(numeroSerie);
		return ResponseEntity.ok().body(equip);
	}
	
	@GetMapping("/filtro/usuarioRecebedor/{usuario}")
	public ResponseEntity<List<Lancamento>> findEquipUsuarioRecebedor(@PathVariable Integer usuario){
		List<Lancamento> equip = repoEquip.findByEquipamento(usuario);
		return ResponseEntity.ok().body(equip);
	}
	
	@GetMapping("/filtro/todososSetores")
	public ResponseEntity<List<Setor>> findSetor(){
		List<Setor> st = repoSetor.findAll();
		return ResponseEntity.ok().body(st);
	}
	
//	@GetMapping(value = "/filtro")
//	public ResponseEntity<Page<Setor>> findByDescricao(
//			@RequestParam(value = "setor", defaultValue = "") String Setor,  
//			@RequestParam(value = "page", defaultValue = "0") Integer page, 
//			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
//		   @RequestParam(value = "orderBy", defaultValue = "id") String orderBy
//			){ 
//		
//		Page<Setor> str = equipService.findByDescricao(Setor, page, linesPerPage, orderBy);
//		return ResponseEntity.ok().body(str);
//	}
//	
	
	
}
