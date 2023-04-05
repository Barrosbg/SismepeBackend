package br.gov.pe.sismepe.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.CadastroDeCarros;
import br.gov.pe.sismepe.dto.ComorbidadesDTO;
import br.gov.pe.sismepe.dto.covid.boletim.CasoCovidDTO;
import br.gov.pe.sismepe.dto.estacionamento.CadastroDeCarrosEstacionamentoDTO;
import br.gov.pe.sismepe.report.service.CadastroDeCarrosReportService;
import br.gov.pe.sismepe.repositories.CadastroDeCarrosRepository;
import br.gov.pe.sismepe.util.Utils;

@Service
public class CadastroDeCarrosService {

	@Autowired
	private CadastroDeCarrosRepository repo;

	public List<CadastroDeCarros> CadastroDeCarrosfindByPlaca(String placa) {
		return repo.findByPlacaContaining(placa);
	}
	
	public List<String> filtrarPorSetor(){
		List<CadastroDeCarros> setor = repo.findAll();
		List<String> setorList = new ArrayList<String>();
		for (CadastroDeCarros item : setor) {
			 
             if(setorList.contains(item.getSetor())) {
            	
             }else {
            	 setorList.add(item.getSetor());
            	
             }
		}
		return setorList;
	}
	
	
	
	@Transactional
	public void inativarCad(Long id,CadastroDeCarros cadCarros) {
		CadastroDeCarros carro = repo.findByIdAndAtivo(id,"S");
	    cadCarros.setId(carro.getId());
	    cadCarros.setNome(carro.getNome());
	    cadCarros.setSetor(carro.getSetor());
	    cadCarros.setMarca(carro.getMarca());
	    cadCarros.setCor(carro.getCor());
	    cadCarros.setPlaca(carro.getPlaca());
	    cadCarros.setModelo(carro.getModelo());
	    cadCarros.setTelefone(carro.getTelefone());
	    cadCarros.setAtivo("N");
	    repo.save(cadCarros.toModel());
	}

	@Transactional
	public void atualizarcadastro(Long id,CadastroDeCarrosEstacionamentoDTO cadCarros) {
		
		CadastroDeCarros carro = repo.findByIdAndAtivo(id,"S");
		System.out.println(carro);
		carro.setId(id);
		carro.setNome(cadCarros.getNome());
		carro.setSetor(cadCarros.getSetor());
		carro.setMarca(cadCarros.getMarca());
		carro.setCor(cadCarros.getCor());
		carro.setPlaca(cadCarros.getPlaca());
		carro.setModelo(cadCarros.getModelo());
		carro.setTelefone(cadCarros.getTelefone());
		carro.setAtivo("S");
	    repo.save(carro.toModel());
	}
	

public void gerarPdfcartaoEstacionamento(String setor, HttpServletResponse response) throws IOException, Exception {
		
		List<CadastroDeCarros> listaSetor = repo.findBySetorContaining(setor);
				
		CadastroDeCarrosReportService rs = new CadastroDeCarrosReportService();
		
        HashMap<String,  Object> parameters = new HashMap<>();
      
 
//        for(CadastroDeCarros item : listaSetor) {
//        	
//       	 parameters.put("nome", item.getNome());
//     	 parameters.put("setor", item.getSetor());
//  	     parameters.put("placa", item.getPlaca());
//
//       }
    
        response.setContentType("application/pdf");

        response.addHeader("Content-Disposition", "inline; filename=listaPorSetor.pdf;");

        rs.toOutputStream("/jasper/FichaEstacionamento.jrxml", listaSetor , parameters, response.getOutputStream());
		
	
}

public void gerarPdfcartaoEstacionamentoPorPessoa(String nome, HttpServletResponse response) throws IOException, Exception {
	
	List<CadastroDeCarros> listaSetor = repo.findByNomeContaining(nome);
	System.out.println(listaSetor);
			
	CadastroDeCarrosReportService rs = new CadastroDeCarrosReportService();
	
    HashMap<String,  Object> parameters = new HashMap<>();
  

//    for(CadastroDeCarros item : listaSetor) {
//    	
//   	 parameters.put("nome", item.getNome());
// 	 parameters.put("setor", item.getSetor());
//	     parameters.put("placa", item.getPlaca());
//
//   }

    response.setContentType("application/pdf");
    response.addHeader("Content-Disposition", "inline; filename=listaPorSetor.pdf;");

    rs.toOutputStream("/jasper/FichaEstacionamento.jrxml", listaSetor , parameters, response.getOutputStream());
	

}


	
}
