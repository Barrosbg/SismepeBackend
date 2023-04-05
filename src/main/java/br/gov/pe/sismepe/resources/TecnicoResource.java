package br.gov.pe.sismepe.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.tecnico;
import br.gov.pe.sismepe.repositories.TecnicoRepository;

@RestController
@RequestMapping("/tecnico")
public class TecnicoResource {

		
		@Autowired
		private TecnicoRepository  repoTecnico;
		
		@GetMapping
		public List<tecnico> buscarPessoa(){
			System.out.println("TEste");
			System.out.println("TEste");
			System.out.println("TEste");
			
			return repoTecnico.findAll();
		}	
	
}
