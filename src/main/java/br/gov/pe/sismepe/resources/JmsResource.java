package br.gov.pe.sismepe.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.FichaInspecaoJms;
import br.gov.pe.sismepe.dto.jms.FichaInspecaoJmsDTO;
import br.gov.pe.sismepe.repositories.FichaInspecaoJmsRepository;
import br.gov.pe.sismepe.services.JmsService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/jms")
public class JmsResource {
	
	@Autowired	
	private FichaInspecaoJmsRepository fichaInspecaoJmsRepository;
	
	@Autowired
	private JmsService jmsService;
	
	@GetMapping(value = "/ficha-inspecao")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_FICHA')")
	public ResponseEntity<List<FichaInspecaoJms>> listarFichasInspecaoJms(){
		return ResponseEntity.ok(fichaInspecaoJmsRepository.findByAtivo("S"));
	}
	
	@GetMapping(value = "/ficha-inspecao/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_FICHA')")
	public ResponseEntity<FichaInspecaoJms> fichaInspecaoJmsPorId(@PathVariable Integer id){
		return ResponseEntity.ok(jmsService.findFichaInspecaoById(id));
	}
	
	@GetMapping(value = "/ficha-inspecao/titular/{titularId}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_FICHA')")
	public ResponseEntity < List<FichaInspecaoJms>> fichaInspecaoJmsPorTitular(@PathVariable Long titularId){
		return ResponseEntity.ok(jmsService.findFichaInspecaoByTitular(titularId));
	}
	
	@PostMapping(value = "/ficha-inspecao")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_FICHA')")
	public ResponseEntity<Void> salvarFichaInspecaoJms(@RequestBody FichaInspecaoJmsDTO fichaInspecaoJms){
		FichaInspecaoJms ficha = jmsService.salvarFichaInspecaoJms(fichaInspecaoJms);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ficha.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/ficha-inspecao/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_FICHA')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarFichaInspecaoJms(@PathVariable Integer id, @RequestBody FichaInspecaoJmsDTO fichaInspecaoJms){		
		jmsService.atualizarFichaInspecaoJms(id, fichaInspecaoJms);
	}
	
	@DeleteMapping(value = "/ficha-inspecao/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_FICHA')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarFichaInspecaoJms(@PathVariable Integer id){		
		jmsService.deletarFichaInspecaoJms(id);
	}
	
	@GetMapping(value = "/ficha-inspecao/{id}/pdf")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_CADASTRO_FICHA')")
    public void fichaInspecaoJmsPorIdPdf(@PathVariable Integer id, HttpServletResponse response) throws IOException, Exception {
        jmsService.gerarPdfFichaInspecao(id, response);        
    }
    
	
	
}
