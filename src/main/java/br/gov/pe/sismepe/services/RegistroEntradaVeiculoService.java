package br.gov.pe.sismepe.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.gov.pe.sismepe.domain.CadastroDeCarros;
import br.gov.pe.sismepe.domain.RegistroEntradaVeiculos;
import br.gov.pe.sismepe.domain.TotalVeiculosNoPatio;
import br.gov.pe.sismepe.dto.estacionamento.RegistroEntradaVeiculoDTO;
import br.gov.pe.sismepe.repositories.CadastroDeCarrosRepository;
import br.gov.pe.sismepe.repositories.RegistroEntradaVeiculosRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import br.gov.pe.sismepe.util.Utils;

@Service
public class RegistroEntradaVeiculoService {

	

	@Autowired
	private RegistroEntradaVeiculosRepository repo;
	
	@Autowired
	private CadastroDeCarrosRepository cadastroDeCarrosRepository;
	

	
   public List<RegistroEntradaVeiculos> buscarPorSituacao(){
	    List<RegistroEntradaVeiculos> registroEntrada = null;
		List<RegistroEntradaVeiculos> veiculos =  repo.findBySituacaoEntrada("E");		
		
		return veiculos;

   }
   
//	public RegistroEntradaVeiculos totalVeiculosEstacionados() {
//		Date dataInicio = Utils.trim(new Date(), 0, 0, 0, 0);
//		Date dataFim = Utils.trim(new Date(), 23, 59, 59, 59);
//
//		return total !=null ?  ResponseEntity.ok().body(total) : ResponseEntity.noContent().build();
//	}
//
//   
   public RegistroEntradaVeiculos registrarSaidaVeiculo(Long id, RegistroEntradaVeiculoDTO registro) {
	   RegistroEntradaVeiculos f = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado. ID " + id));
	   
	   registro.setId(id);
	   registro.setDataEntrada(f.getDataEntrada());
	   registro.setDataSaida(new Date());
	   registro.setSituacao_entrada("S");
	   registro.setCadastro_carros(f.getCadastroCarros());
	   return repo.save(registro.toModel());
   }
   
   
//   public CadastroDeCarros buscarPorIdEntrada(@PathVariable String placa) {
//	   CadastroDeCarros carro = cadastroDeCarrosRepository.findByPlacaContaining(placa);
//       RegistroEntradaVeiculos registro = repo.findRegistroEntradaVeiculosByCadastroCarrosAndSituacaoEntrada(carro, "E") ; 
//      
//     
//       if(registro != null ) {
//    	   Date data = new Date();
//           SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
//           String Atual = formatador.format(data);
//           String banco = formatador.format(registro.getDataEntrada());
//    	   if(registro != null && Atual.equals(banco)) {
//    		   return null;
//    	   }else {
//    		   return carro;
//    	   }
//      }else {
//    	  return carro;
//      }
//    	  
        
       
         
	   
	 
	
	   
//   }
	
	
}
