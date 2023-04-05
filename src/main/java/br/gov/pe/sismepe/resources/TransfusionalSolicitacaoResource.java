package br.gov.pe.sismepe.resources;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.TransfusionalSolicitacao;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.repositories.TransfusionalSolicitacaoRepository;
import br.gov.pe.sismepe.services.TransfusionalService;

@RestController
@RequestMapping(value = "/transfusional-solicitacoes")
public class TransfusionalSolicitacaoResource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TransfusionalSolicitacaoRepository transfusionalSolicitacaoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private TransfusionalService transfusionalSevice; 
	
	@GetMapping("/lista")
	public ResponseEntity<List<TransfusionalSolicitacao>> list() {
		List<TransfusionalSolicitacao> list = transfusionalSolicitacaoRepository.findAll();		
		return ResponseEntity.ok().body(list);		
	}
	
	@GetMapping("/page/{numeroPagina}/{qtdPagina}")
	public Iterable<TransfusionalSolicitacao> obterSolicitacaoPaginada(
			@PathVariable int numeroPagina, @PathVariable int qtdPagina) {
		//if(qtdPagina >= 6) qtdPagina = 6;
		Pageable page = PageRequest.of(numeroPagina, qtdPagina, Sort.by("id").descending());
		return transfusionalSolicitacaoRepository.findAll(page);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransfusionalSolicitacao> find(@PathVariable Integer id){
		Optional<TransfusionalSolicitacao>  transfusao = transfusionalSolicitacaoRepository.findById(id);
		//System.out.println(transfusao);
		if (transfusao.isPresent()) {
			return ResponseEntity.ok(transfusao.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public TransfusionalSolicitacao acicionar(@RequestBody TransfusionalSolicitacao transfusao) {
		return transfusionalSolicitacaoRepository.save(transfusao);
	}
	/*
	 *  Atualizar Ficha
	 **/
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar (@PathVariable Integer id, @RequestBody TransfusionalSolicitacao transfusao){
		Optional<TransfusionalSolicitacao> transfusaoAtual = transfusionalSolicitacaoRepository.findById(id);
	
		if (transfusaoAtual.isPresent()) {
			BeanUtils.copyProperties(transfusao, transfusaoAtual.get(), "id");
			TransfusionalSolicitacao transfusaoAtualizada = transfusionalSolicitacaoRepository.save(transfusaoAtual.get());
			System.out.println(transfusaoAtualizada);
			return ResponseEntity.ok(transfusaoAtualizada);
		}
		return ResponseEntity.notFound().build();
	}
	
	/*
	 *  Busca por número de Registro
	 **/
	@GetMapping("/trasnfusao/por-numero-registro")
	public Optional<TransfusionalSolicitacao> buscarPorNumRegistro(@RequestParam("nemroRegistro") String nemroRegistro) {
		return transfusionalSolicitacaoRepository.findBynemroRegistro(nemroRegistro);
	}
	
	/*
	 *  Busca por Pessoa pelo CD_Pessoa
	 **/
	@GetMapping("/trasnfusao/buscar-pessoa/{cdPessoaId}")
	public ResponseEntity<Pessoa> buscarPessoaId(@PathVariable Long cdPessoaId) {
		Pessoa pessoaAtual = pessoaRepository.findById(cdPessoaId).orElse(null);
		return ResponseEntity.ok().body(pessoaAtual);
	}
	
	@GetMapping(value = "/fichatransfusional/{id}/pdf")
    public void fichaTransfusionalPdf(@PathVariable Integer id, HttpServletResponse response) throws IOException, Exception {
        transfusionalSevice.gerarPdfFichaTransfusional(id, response);        
    }
	
}
