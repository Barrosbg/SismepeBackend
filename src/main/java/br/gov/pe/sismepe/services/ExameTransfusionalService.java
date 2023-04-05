  package br.gov.pe.sismepe.services;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.gov.pe.sismepe.domain.ExameTransfusional;
import br.gov.pe.sismepe.dto.ExameTransfusionalDTO;
import br.gov.pe.sismepe.report.service.ResultadoExamesExternosReportSevice;
import br.gov.pe.sismepe.repositories.ExameTransfusionalRepository;
import br.gov.pe.sismepe.repositories.PacienteRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
@Transactional
public class ExameTransfusionalService {

	@Autowired
	private ExameTransfusionalRepository repo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PacienteRepository paciente;
	
	public List<ExameTransfusional> buscarExame(Long id) {
		
		List<ExameTransfusional> exame = repo.findExameTransfusionalByPessoa_IdAndAtivo(id, "S");
		return exame;
		
		
	}
	@Transactional
	public void deletarExame(Long id) {
		
		ExameTransfusional exame = repo.findByIdAndAtivo(id, "S").orElseThrow(() -> new ObjectNotFoundException("Exame não encontrada. ID " + id));
		
		exame.setAtivo("N");
		exame.setDataAtualizacao(new Date());
		exame.setUsuarioAlteracao(usuarioService.getUsuarioLogado());
		
		repo.save(exame);
		
	}
	@Transactional
	public void atualizarExames(Long id, ExameTransfusionalDTO examesAgencia) {
		
		ExameTransfusional exame = repo.findByIdAndAtivo(id, "S").orElseThrow(() -> new ObjectNotFoundException("Exame não encontrada. ID " + id));
		exame.setId(id);
		exame.setGrupoSanguineo(examesAgencia.getGrupoSanguineo());
		exame.setRhd(examesAgencia.getRhd());
		exame.setComboDireto(examesAgencia.getComboDireto());
		exame.setComboIndireto(examesAgencia.getComboIndireto());
		exame.setDataAtualizacao(new Date());
		exame.setUsuarioAlteracao(usuarioService.getUsuarioLogado());
		repo.save(exame);
		
	}
	@Transactional
	public ExameTransfusional buscarExamePorId(Long id) {
		
		ExameTransfusional exame = repo.findExameTransfusionalById(id);
		return exame;
		
		
	}
	@Transactional
	public void imprimirExame(Long id, HttpServletResponse response) throws IOException, Exception{
		ExameTransfusional  exame = repo.findExameTransfusionalById(id);
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(exame.getDataCadastro());
		ResultadoExamesExternosReportSevice rs = new ResultadoExamesExternosReportSevice();

	    HashMap<String,  Object> parameters = new HashMap<>();
	       URL imagem = ResourceUtils.getURL("classpath:imagens/img/"+exame.getUsuarioCadastro().getId()+".jpeg");
	       URL imagemDt = ResourceUtils.getURL("classpath:imagens/img/"+exame.getUsuarioCadastro().getId()+"dt.jpeg");
	       parameters.put("imagem", imagem);
	       parameters.put("imagemDt", imagemDt);
//	    parameters.put("TITULO", "FICHA DE INSPEÇÃO PARA QUADRO DE ACESSO JMS");
        parameters.put("pessoa", exame.getPessoa().getNome());
        parameters.put("prestador", exame.getPrestador().getNome());
        parameters.put("grupoSanguineo", exame.getGrupoSanguineo() == null ? "SEM INFORMAÇÃO" : exame.getGrupoSanguineo());
        parameters.put("rhd", exame.getRhd() == null ? "SEM INFORMAÇÃO" : exame.getRhd().contains("P") ? "POSITIVO" : "NEGATIVO");
        parameters.put("comboIndireto", exame.getComboIndireto() == null ? "SEM INFORMAÇÃO" : exame.getComboIndireto().contains("P") ?  "POSITIVO" : "NEGATIVO");
        parameters.put("comboDireto", exame.getComboDireto() == null ? "SEM INFORMAÇÃO" : exame.getComboDireto().contains("P") ? "POSITIVO" : "NEGATIVO");
        parameters.put("dataCadastro",data );
        parameters.put("matricula", exame.getPessoa().getMatricula());
	    response.setContentType("application/pdf");
	    response.addHeader("Content-Disposition", "inline; filename=ResultadoExamesExternos.pdf;");

	    rs.toOutputStream("/jasper/ResultadoExamesExternos.jrxml",parameters, response.getOutputStream());
	}
	
	
	
}
