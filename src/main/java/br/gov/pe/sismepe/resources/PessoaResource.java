package br.gov.pe.sismepe.resources;

import java.io.IOException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.Comorbidade;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.dto.GrauParentescoDTO;
import br.gov.pe.sismepe.dto.PessoaDTO;
import br.gov.pe.sismepe.dto.PessoaNewDTO;
import br.gov.pe.sismepe.dto.PessoaTitularDTO;
import br.gov.pe.sismepe.dto.RecadastramentoTitularDTO;
import br.gov.pe.sismepe.dto.TelefoneDTO;
import br.gov.pe.sismepe.dto.UploadArquivoDTO;
import br.gov.pe.sismepe.dto.UploadArquivoInfoDTO;
import br.gov.pe.sismepe.services.MinioStorageService;
import br.gov.pe.sismepe.services.PessoaService;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidExpiresRangeException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService service;
	
	@Autowired
	private MinioStorageService minioStorageService;
	
	/**
	 * Pesquisar um paciente pelo Id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Long id){
		Pessoa obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/dto")
	public ResponseEntity<?> findDto(@PathVariable Long id){
		Pessoa obj = service.find(id);
		
		PessoaDTO dto = new PessoaDTO(obj);
		
		if (obj.getBucketMinio() != null && obj.getFilenameFotoMinio() != null) {
			UploadArquivoInfoDTO info = minioStorageService.getFile(obj.getBucketMinio(), obj.getFilenameFotoMinio());
			dto.setUrlFotoRecadastramento(info.getUrl());
		}
				
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/dependente")
	public ResponseEntity<?> findDependenteByMatriculaAndSequencial(
			@RequestParam Integer matricula, 
			@RequestParam Integer sequencial) {
		PessoaDependente obj = service.findDependenteByMatriculaAndSequencial(matricula, sequencial);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody GrauParentescoDTO dto, @RequestParam boolean basico) {
		try {
			service.save(dto, basico);
			return ResponseEntity.ok().body("ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/titular")
	public ResponseEntity<?> update(@RequestBody RecadastramentoTitularDTO dto) {
		try {
			service.saveTitular(dto);
			return ResponseEntity.status(200).body("");
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
		
	}
	
	@PutMapping(value = "/telefone")
	public ResponseEntity<Pessoa> update(@RequestBody TelefoneDTO dto) {
		Pessoa p = service.save(dto);
		
		return ResponseEntity.ok(p);
	}
	
	/**
	 * Pesquisar todos os pacientes
	 */
	@GetMapping
	public ResponseEntity<List<PessoaDTO>> findAll(){
		List<Pessoa> list = service.findAll();
		List<PessoaDTO> listDTO = list.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	/**
	 * Pesquisar todos os pacientes paginado e com filtro
	 */
	@GetMapping(value = "/filtro")
	public ResponseEntity<Page<PessoaDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome, 
			@RequestParam(value = "matricula", defaultValue = "") Integer matricula, 
			@RequestParam(value = "cpf", defaultValue = "") String cpf, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "pessoa.nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions){
		
		Page<Pessoa> list = service.findByPessoaNomeMatriculaCpf(nome, matricula, cpf, page, linesPerPage, orderBy, directions);
		
		Page<PessoaDTO> listDTO = list.map(obj -> {
				PessoaDTO dto = new PessoaDTO(obj);
				
				if (obj.getBucketMinio() != null && obj.getFilenameFotoMinio() != null) {
					UploadArquivoInfoDTO info = minioStorageService.getFile(obj.getBucketMinio(), obj.getFilenameFotoMinio());
					dto.setUrlFotoRecadastramento(info.getUrl());
				}
				
				return dto;
			});
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody PessoaNewDTO objDTO){
		Pessoa obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping("/{id}/adicionar-comorbidades")
	public ResponseEntity<Pessoa> adicionarComorbidades(@PathVariable Long id, @RequestBody List<Comorbidade> comorbidades) {
		Pessoa pessoa = service.adicionarComorbidades(id, comorbidades);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(pessoa);
	}
	
	@PutMapping("/{id}/remover-comorbidades")
	public ResponseEntity<Pessoa> removerComorbidades(@PathVariable Long id, @RequestBody List<Comorbidade> comorbidades) {
		Pessoa pessoa = service.removerComorbidades(id, comorbidades);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(pessoa);
	}
	
	@PostMapping("/{id}/foto/upload")
	public ResponseEntity<String> upload(@Valid @RequestBody UploadArquivoDTO uploadArquivoDTO, @PathVariable Long id) throws InvalidKeyException, ErrorResponseException, IllegalArgumentException,
			InsufficientDataException, InternalException, InvalidBucketNameException, InvalidResponseException,
			NoSuchAlgorithmException, ServerException, XmlParserException, InvalidExpiresRangeException, IOException {
		UploadArquivoInfoDTO info = service.save(uploadArquivoDTO, id);
		
		return ResponseEntity.ok(info.getUrl());
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_JUNTA')")
	@GetMapping(value = "/titulares/filtro")
	public ResponseEntity<Page<PessoaTitularDTO>> findTitularesByMatriculaCorporacaoOme(
			@RequestParam(value = "matricula", defaultValue = "") Integer matricula, 
			@RequestParam(value = "corporacao", defaultValue = "") Integer corporacao, 
			@RequestParam(value = "ome", defaultValue = "") Long ome, 
			@RequestParam(value = "perfil", defaultValue = "") Integer perfil, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions){ 
		
		Page<PessoaTitularDTO> pageDTO = service.findByPessoaTitularByMatriculaCorporacaoOme(matricula, corporacao, ome, perfil, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok().body(pageDTO);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_JUNTA')")
	@PutMapping(value = "/titular/atualizar-ome")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarOme(@RequestBody PessoaTitularDTO dto) throws ObjectNotFoundException {
		service.atualizarOme(dto);
	}
}
