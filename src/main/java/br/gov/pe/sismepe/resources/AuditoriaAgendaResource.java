package br.gov.pe.sismepe.resources;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.AgendaCentral;
import br.gov.pe.sismepe.domain.AgendaCentralAmb;
import br.gov.pe.sismepe.domain.AgendaCentralAudit;
import br.gov.pe.sismepe.domain.EscalaCentral;
import br.gov.pe.sismepe.domain.EscalaCentralAmb;
import br.gov.pe.sismepe.domain.Prestador;
import br.gov.pe.sismepe.repositories.AgendaCentralAuditRepository;
import br.gov.pe.sismepe.repositories.AgendaCentralRepository;
import br.gov.pe.sismepe.repositories.EscalaCentralAmbRepository;
import br.gov.pe.sismepe.repositories.EscalaCentralRepository;
import br.gov.pe.sismepe.repositories.PrestadorRepository;
import br.gov.pe.sismepe.services.AuditoriaAgendaCentralService;

@RestController
@RequestMapping(value="/auditoriaAgendaCentral")
public class AuditoriaAgendaResource {

	@Autowired
	private AgendaCentralRepository repoAgenda;
	
	@Autowired
	private EscalaCentralRepository repoEscala;
	
	@Autowired
	private AuditoriaAgendaCentralService auditeService;
	@Autowired
	private PrestadorRepository repoPrestador;
	
	@Autowired
	private EscalaCentralAmbRepository repoEscalaAmb;
	
	@Autowired
	private AgendaCentralAuditRepository agendaRepo;
	
	@GetMapping(value="/{cdAgenda}")
	private ResponseEntity<AgendaCentral> agendacentral(@PathVariable Long cdAgenda){
		  AgendaCentral agenda = repoAgenda.findById(cdAgenda).orElse(null);
		  
		  return ResponseEntity.ok(agenda);
	}
	
	@GetMapping(value="/prestador")
	private ResponseEntity<Page<AgendaCentralAmb>> buscarAgendaPorPrestador(
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer linesPerPage,
            @RequestParam(defaultValue = "escala") String orderBy,
             @RequestParam(defaultValue = "DESC") String direction,
             @RequestParam(defaultValue = "0") Long prestadorId){
		
	    Page<AgendaCentralAmb> agenda = auditeService.buscarAgendaPorCdPrestador(page,linesPerPage,orderBy,direction,prestadorId);
		
		if(agenda.isEmpty()) {
		      return ResponseEntity.noContent().build();
		}else {
			
			 return ResponseEntity.ok(agenda);
		}
		
	}
	
	@GetMapping(value="/escalaCentral/{cdEscala}")
	private ResponseEntity<EscalaCentral> escalaCentral(@PathVariable Integer cdEscala){
		EscalaCentral agenda = repoEscala.findById(cdEscala).orElse(null);
		  
		  return ResponseEntity.ok(agenda);
	}
	
	@GetMapping(value="/escalaCentralAmb/{cdEscala}")
	private ResponseEntity<EscalaCentralAmb> escalaCentralAmb(@PathVariable Long cdEscala){
		EscalaCentralAmb agenda = repoEscalaAmb.findById(cdEscala).orElse(null);
		  
		  return ResponseEntity.ok(agenda);
	}
	
	
	@GetMapping(value="/prestador/{cdPrestador}")
	private ResponseEntity<?> BuscarPrestador(@PathVariable Long cdPrestador){
		Prestador prestador = repoPrestador.findById(cdPrestador).orElse(null);
		  
		  return ResponseEntity.ok(prestador);
	}
	
	
	@GetMapping(value="/escalaCentralAmb")
	private ResponseEntity<Page<EscalaCentralAmb>> buscarTodasAsEscalas(	
			@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer linesPerPage,
            @RequestParam(defaultValue = "escala") String orderBy,
             @RequestParam(defaultValue = "DESC") String direction
          ){
		Page<EscalaCentralAmb> agenda = auditeService.buscarTodasAsEscalas(page,  linesPerPage,orderBy,direction);
		
		if(agenda.isEmpty()) {
		      return ResponseEntity.noContent().build();
		}else {
			
			  return ResponseEntity.ok(agenda);
		}
	}
	
	@GetMapping(value="/agendaModificada/{id}")
	public ResponseEntity<?> agendaModificada(@PathVariable Long id){
	    Optional<AgendaCentralAudit> agenda = agendaRepo.findById(id);
		return ResponseEntity.ok().body(agenda);
	}
	
	

	@GetMapping(value="/escalaCentral/buscarPOrData")
	private ResponseEntity<List<EscalaCentral>> buscarTodasAsEscalasPorData(@RequestParam("dataInicio") String dataInicio , @RequestParam("dataFim") String dataFim) throws ParseException{
		System.out.println(dataInicio);
		List<EscalaCentral> agenda = auditeService.buscarPorIntervaloDeDatas(dataInicio, dataFim);
		  
		if(agenda.isEmpty()) {
			return  ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(agenda);
		}
		
		  
	}
}
