package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.AgendaCentralAudit;
import br.gov.pe.sismepe.domain.PesquisaDeSatisfacao;
import br.gov.pe.sismepe.domain.PesquisaExpiracao;
import br.gov.pe.sismepe.domain.PesquisaPerguntaResposta;
import br.gov.pe.sismepe.domain.RegistroEntradaVeiculos;
import br.gov.pe.sismepe.dto.PesquisaDeSatisfacaoDTO;
import br.gov.pe.sismepe.dto.PesquisaPerguntaRespostaDTO;
import br.gov.pe.sismepe.repositories.AgendaCentralAuditRepository;
import br.gov.pe.sismepe.repositories.AtendimentoRepository;
import br.gov.pe.sismepe.repositories.PesquisaDeSatisfacaoRepository;
import br.gov.pe.sismepe.repositories.PesquisaExpiracaoRepository;
import br.gov.pe.sismepe.repositories.PesquisaPerguntaRespostaRepository;
import br.gov.pe.sismepe.services.PesquisaDeSatisfacaoService;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/pesquisaDeSatisfacao")
public class PesquisaDeSatisfacaoResource {
	
	@Autowired
	private PesquisaDeSatisfacaoRepository repoPS;
	
	@Autowired
	private PesquisaPerguntaRespostaRepository repoPPR;
	
	@Autowired
	private AtendimentoRepository repoAtendimento;
	
	@Autowired
	private PesquisaExpiracaoRepository repoPesquisaExpiracao;
	
	@Autowired
	private PesquisaDeSatisfacaoService pesquisaService;
	
	
		
	@PostMapping
	public ResponseEntity<?> salvarPesquisa(@Valid @RequestBody PesquisaDeSatisfacaoDTO pesquisaPRDTO ){
		
		PesquisaDeSatisfacao pesquisaS2;
		try {
			pesquisaS2 = pesquisaService.salvar(pesquisaPRDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pesquisaS2.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			return ResponseEntity.status(400).body(e);
		}
//		return ResponseEntity.ok().build();
	}
	

	
	@GetMapping(value = "/enviarPesquisa/{cpf}/{cdAtendimento}")
	public ResponseEntity<?> recuperarSenha(@PathVariable String cpf, @PathVariable Long cdAtendimento) {
		boolean enviado = pesquisaService.enviarPesquisaDeSatisfacao(cpf, cdAtendimento);
		
		if (enviado)
			return ResponseEntity.ok(null);
		
		return ResponseEntity.status(400).body(null);
	}

	@GetMapping(value = "/codigo-atendimento/&{atendimento}")
	public ResponseEntity<List<PesquisaDeSatisfacao>> pesquisaDeSatisfacaoAtendimento(@PathVariable Long atendimento) {
		List<PesquisaDeSatisfacao> ps = pesquisaService.buscarCodigoAtendimento(atendimento);
		if (ps.size() > 0) {
			return ResponseEntity.ok(ps);	
		}
		
		return ResponseEntity.status(400).body(ps);
		
//		PesquisaDeSatisfacao ps = pesquisaService.buscarPorCodigoAtendimento(atendimento);
//		return ResponseEntity.ok("ok");
	}
	
	@GetMapping(value = "/pesquisaExpirada/{cdAtendimento}")
	public ResponseEntity<?>  pesquisaExpirada( @PathVariable Long cdAtendimento) {
		boolean enviado = pesquisaService.buscarPesquisaExpirada(cdAtendimento);
		
	return ResponseEntity.ok().body(enviado);
	}
	

	@GetMapping(value = "/pesquisaRespondida/{cdAtendimento}")
	public ResponseEntity<?>  buscarpesquisaRespondida( @PathVariable Long cdAtendimento) throws Exception {
		boolean enviado = pesquisaService.pesquisaRespondida(cdAtendimento);
		
	return ResponseEntity.ok().body(enviado);
	}
}
