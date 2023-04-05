package br.gov.pe.sismepe.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.CadastroDeCarros;
import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.dto.PacienteDTO;
import br.gov.pe.sismepe.dto.estacionamento.CadastroDeCarrosEstacionamentoDTO;
import br.gov.pe.sismepe.repositories.CadastroDeCarrosRepository;
import br.gov.pe.sismepe.services.CadastroDeCarrosService;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@RestController
@RequestMapping("/veiculos")
public class CadastroDecarrosResource {

	@Autowired
	private CadastroDeCarrosRepository cadastroDeCarrosRepository;
	
	@Autowired
	private CadastroDeCarrosService cadService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CadastroDeCarros>> buscarTodos(){
		List<CadastroDeCarros> carros =  cadastroDeCarrosRepository.findAll();
		return ResponseEntity.ok().body(carros);
	}
	
	@GetMapping("/ativos")
	public ResponseEntity<List<CadastroDeCarros>> buscarTodosAtivos(){
		List<CadastroDeCarros> carrosAtivos =  cadastroDeCarrosRepository.findByAtivo("S");
		return ResponseEntity.ok().body(carrosAtivos);
	}
	@GetMapping(value="/listaSetor/{setor}")
	public ResponseEntity<List<CadastroDeCarros>> buscarPorSetor(@PathVariable String setor){
		List<CadastroDeCarros> CadSetor = cadastroDeCarrosRepository.findBySetorContaining(setor);
		return !CadSetor.isEmpty() ?  ResponseEntity.ok(CadSetor) : ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/listarSetor")
	public ResponseEntity<List<String>> listarSetor(){
		List<String> CadSetor = cadService.filtrarPorSetor();
		return !CadSetor.isEmpty() ?  ResponseEntity.ok(CadSetor) : ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/listaSetor/{setor}/pdf")
    public void listarCadastrosPorSetorPdf(@PathVariable String setor, HttpServletResponse response) throws IOException, Exception {
		cadService.gerarPdfcartaoEstacionamento(setor, response);
    }
	
	@GetMapping(value = "/listaPorNome/{nome}/pdf")
    public void listarCadastrosPornomePdf(@PathVariable String nome, HttpServletResponse response) throws IOException, Exception {
		cadService.gerarPdfcartaoEstacionamentoPorPessoa(nome, response);
    }
	
	@RequestMapping(value = "/filtro", method = RequestMethod.GET)
	public ResponseEntity<List<CadastroDeCarros>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome){
		    List<CadastroDeCarros> list = cadastroDeCarrosRepository.findByNomeContaining(nome);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/cadastro/{id}")
	public ResponseEntity<?> buscarVeiculoPorId(@PathVariable Long id){
		 CadastroDeCarros carrosAtivos =  cadastroDeCarrosRepository.findById(id).orElse(null);
		return ResponseEntity.ok().body(carrosAtivos);
	}
	@PostMapping
	public  ResponseEntity<Object> salvar(@Valid @RequestBody CadastroDeCarros carro){
	      
		  CadastroDeCarros cadCarro = cadastroDeCarrosRepository.save(carro);
		  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cadCarro.getId()).toUri();
		  System.out.println(uri);
		  return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value="/inativar/{id}")
	public void inativarCadastro(@PathVariable Long id, CadastroDeCarros cadCarros) {
		cadService.inativarCad(id, cadCarros);
	}
	
	@PutMapping(value="/atualizar/{id}")
	public void atualizarCadastrodeCarros(@PathVariable Long id, @RequestBody CadastroDeCarrosEstacionamentoDTO cadCarros) {
		cadService.atualizarcadastro(id, cadCarros);
	}
	
	
	@GetMapping(value="/{placa}")
	public ResponseEntity<?> buscarPorPlaca( @PathVariable String placa){
		    List<CadastroDeCarros> carro = cadastroDeCarrosRepository.findByPlacaContaining(placa);
		  
		    return !carro.isEmpty() ?  ResponseEntity.ok(carro) : ResponseEntity.noContent().build();
	}
	
	
}
