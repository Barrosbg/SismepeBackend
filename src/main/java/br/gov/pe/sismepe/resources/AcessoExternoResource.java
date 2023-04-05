package br.gov.pe.sismepe.resources;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.dto.externo.DependenteExternoDTO;
import br.gov.pe.sismepe.dto.externo.TitularExternoDTO;
import br.gov.pe.sismepe.services.PessoaService;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/externo")
public class AcessoExternoResource {
	
	@Autowired
	private PessoaService service;
	
	
	@GetMapping(value = "/dependente",  produces = {"application/json"})
	public ResponseEntity<Page<DependenteExternoDTO>> findDependenteByMatriculaAndSequencial(
			@ApiParam("Filtra os registros através da matrícula do dependente.")
			@RequestParam(value = "matricula", defaultValue = "") Integer matricula, 
			@ApiParam("O campo sequencial só deverá ser informado em conjunto com o campo matrícula, caso contrário ele não terá nenhum efeito na consulta.")
			@RequestParam(value = "sequencial", defaultValue = "") Integer sequencial,
			@ApiParam("Use o filtro dataConcessao para buscar registros com data de concessão maior ou igual a data informada. Use o formato yyyy-mm-dd")
			@RequestParam(value = "dataConcessao", defaultValue = "") String dataConcessao,
			@ApiParam("Use o filtro dataUltimaAtualizacao para buscar registros com data de atualização maior ou igual a data informada. Use o formato yyyy-mm-dd")
			@RequestParam(value = "dataUltimaAtualizacao", defaultValue = "") String dataUltimaAtualizacao,
			@ApiParam("Use o filtro dataCancelamento para buscar registros com data de cancelamento igual a data informada. Use o formato yyyy-mm-dd")
			@RequestParam(value = "dataCancelamento", defaultValue = "") String dataCancelamento,
			@ApiParam("Use o filtro dataBloqueio para buscar registros com data de bloqueio igual a data informada. Use o formato yyyy-mm-dd")
			@RequestParam(value = "dataBloqueio", defaultValue = "") String dataBloqueio,
			@ApiParam("Campo de número da página da consulta.")
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@ApiParam("Campo de quantidade de registros por página na consulta.")
			@RequestParam(value = "linesPerPage", defaultValue = "20") Integer linesPerPage, 
			@ApiParam("Campo para escolha da forma de ordenação da consulta.")
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@ApiParam("Campo para escolha da direção de ordenação da consulta.")
			@RequestParam(value = "direction", defaultValue = "ASC") String directions) throws ParseException {
		
		Page<PessoaDependente> list = service.findDependentesByMatriculaAndSequencial(matricula, sequencial, 
				dataConcessao, dataUltimaAtualizacao, dataCancelamento, dataBloqueio, 
				page, linesPerPage, orderBy, directions);
		Page<DependenteExternoDTO> obj = list.map(item -> new DependenteExternoDTO(item));
		return ResponseEntity.ok().body(obj);
	}
	
	
	@GetMapping(value = "/titular",  produces = {"application/json"})
	public ResponseEntity<Page<TitularExternoDTO>> findTitularByMatriculaAndDigito(
			@ApiParam("Filtra os registros através da matrícula do titular.")
			@RequestParam(value = "matricula", defaultValue = "") Integer matricula, 
			@ApiParam("Use o filtro dataInclusao para buscar registros com data de inclusão maior ou igual a data informada. Use o formato yyyy-mm-dd")
			@RequestParam(value = "dataInclusao", defaultValue = "") String dataInclusao,
			@ApiParam("Use o filtro dataUltimaAtualizacao para buscar registros com data de atualização maior ou igual a data informada. Use o formato yyyy-mm-dd")
			@RequestParam(value = "dataUltimaAtualizacao", defaultValue = "") String dataUltimaAtualizacao,
			@ApiParam("Use o filtro dataCancelamento para buscar registros com data de cancelamento igual a data informada. Use o formato yyyy-mm-dd")
			@RequestParam(value = "dataCancelamento", defaultValue = "") String dataCancelamento,
			@ApiParam("Use o filtro dataConcessao para buscar registros com data de concessão igual a data informada. Use o formato yyyy-mm-dd")
			@RequestParam(value = "dataConcessao", defaultValue = "") String dataConcessao,
			@ApiParam("Campo de número da página da consulta.")
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@ApiParam("Campo de quantidade de registros por página na consulta.")
			@RequestParam(value = "linesPerPage", defaultValue = "20") Integer linesPerPage, 
			@ApiParam("Campo para escolha da forma de ordenação da consulta.")
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@ApiParam("Campo para escolha da direção de ordenação da consulta.")
			@RequestParam(value = "direction", defaultValue = "ASC") String directions) throws ParseException {
		
		Page<PessoaTitular> list = service.findTitularAtivoByMatricula(matricula, dataInclusao, dataUltimaAtualizacao,
				dataCancelamento, dataConcessao, page, linesPerPage, orderBy, directions);
		Page<TitularExternoDTO> obj = list.map(item -> new TitularExternoDTO(item));
		return ResponseEntity.ok().body(obj);
	}

}
