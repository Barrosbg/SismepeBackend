package br.gov.pe.sismepe.services;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.TotalVeiculosNoPatio;
import br.gov.pe.sismepe.dto.estacionamento.totalVeiculosNoPatioDTO;
import br.gov.pe.sismepe.repositories.TotalVeiculosNoPatioRepository;

@Service
public class TotalVeiculosNoPatioService {

	@Autowired
	private TotalVeiculosNoPatioRepository repo;
	
	public TotalVeiculosNoPatio salvarTotal(totalVeiculosNoPatioDTO registro) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		Date dataFormatada = formato.parse(registro.getDataEntrada()); 
		TotalVeiculosNoPatio totalVeiculos = new TotalVeiculosNoPatio();
		totalVeiculos.setDataEntrada(dataFormatada);
		totalVeiculos.setTotalVeiculos(registro.getTotalVeiculos());
		return repo.save(totalVeiculos);
       
	}
	
	
}
