package br.gov.pe.sismepe.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.AgendaCentral;
import br.gov.pe.sismepe.domain.AgendaCentralAmb;
import br.gov.pe.sismepe.domain.EscalaCentral;
import br.gov.pe.sismepe.domain.EscalaCentralAmb;
import br.gov.pe.sismepe.repositories.AgendaCentralRepository;
import br.gov.pe.sismepe.repositories.EscalaCentralAmbRepository;
import br.gov.pe.sismepe.repositories.EscalaCentralRepository;

@Service
public class AuditoriaAgendaCentralService {

	
	
	@Autowired
	private EscalaCentralRepository repoEscala;
	
	
	@Autowired
	private EscalaCentralAmbRepository repoEscalaAmb;
	
	@Autowired
	private AgendaCentralRepository repoAgenda;
	
	public Page<EscalaCentralAmb> buscarTodasAsEscalas( Integer page, Integer linesPerPage,String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by("escala").descending());
		return repoEscalaAmb.findAll(pageRequest);
	}
	
	public Page<AgendaCentralAmb> buscarAgendaPorCdPrestador( Integer page, Integer linesPerPage,String orderBy, String direction, Long cdPrestador) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by("dataAgendamento").descending());
		return repoAgenda.findByPrestador_id(cdPrestador , pageRequest);
	}
	
	
	
	
	public List<EscalaCentral> buscarPorIntervaloDeDatas(String dataInicio ,String dataFim) throws ParseException{
		
	
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date dataIni = formato.parse(dataInicio);
		Date dataF = formato.parse(dataFim);
       System.out.println(dataIni);
       System.out.println("Entrei");
		  List<EscalaCentral> agenda = repoEscala.findByHrInicialBetween(dataIni, dataF);
		  
		  return  agenda;
	}
	
	
	
}
