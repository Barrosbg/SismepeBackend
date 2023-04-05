package br.gov.pe.sismepe.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.TransfusionalSolicitacao;
import br.gov.pe.sismepe.report.service.FichaCadastroTransfusionalReportService;
import br.gov.pe.sismepe.repositories.TransfusionalSolicitacaoRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class TransfusionalService {
	
	@Autowired
	private TransfusionalSolicitacaoRepository fichaTransfusionalRepository;
	
//	private PrestadorService fichaPrestador;

	public void gerarPdfFichaTransfusional(Integer id, HttpServletResponse response) throws IOException, Exception {
		
		TransfusionalSolicitacao ficha = fichaTransfusionalRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Ficha não encontrada. ID " + id));
		
		FichaCadastroTransfusionalReportService ft = new FichaCadastroTransfusionalReportService();
		
		System.out.println("Ficha "+ficha);
		
        HashMap<String, Object> parameters = new HashMap<>();
//        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        
//        //Dados Pessoais
//        parameters.put("Matricula", ficha.getMatricula());
        parameters.put("Nome", ficha.getCdPessoa().getNome());
        parameters.put("NomeMae", ficha.getCdPessoa().getNomeMae() == null? "NÃO INFORMADO" : ficha.getCdPessoa().getNomeMae());
        parameters.put("NumeroRg", ficha.getCdPessoa().getRg() == null? "00000000" : ficha.getCdPessoa().getRg());
        parameters.put("Telefone", ficha.getCdPessoa().getTelefone());
        parameters.put("NumeroSolicitacao", ficha.getNumeroSolicitacao());
        parameters.put("Cns", ficha.getCns());
        
        parameters.put("DataNasc", ficha.getCdPessoa().getDataNascimento() != null? formato.format(ficha.getCdPessoa().getDataNascimento()) : "");/////////////////
        parameters.put("Idade", ficha.getIdade());
        parameters.put("Sexo", ficha.getCdPessoa().getSexo());
        parameters.put("Peso", ficha.getPeso());
        
//  
        parameters.put("NumRegistro", ficha.getNemroRegistro());
        parameters.put("FoneSetor", ficha.getTelefoneSetor());
        parameters.put("Diagnostico", ficha.getDiagnostico());
//        parameters.put("Setor", ficha.getSetor());
//        
        parameters.put("DataReslt", ficha.getExame_data() != null? formato.format(ficha.getExame_data()):"");
		parameters.put("ExameHb", ficha.getExameHb());
		parameters.put("ExameHt", ficha.getExameHt());
		parameters.put("ExamePl", ficha.getExamePlaquetas());
		parameters.put("ExameInr", ficha.getExameInr());
        
        
//        //Antecedentes Transfusionais
//        
        parameters.put("AntTra", ficha.getAntecedenteTransf() == null? "" : true? "x" : " ");
        parameters.put("TransCh", ficha.getAntecedenteTransfCh() == false? "" : "x");
        parameters.put("TransCp", ficha.getAntecedenteTransfCp() == false? "" : "x");
        parameters.put("TransPfc", ficha.getAntecedenteTransfPfc() == false? "" : "x");
        parameters.put("TransOutros", ficha.getAntecedenteTransfOutros() == false? "" : "x");
//        
//        //Antecedentes Gestacionais
        parameters.put("AnteG", ficha.getAntecedenteGestGesta() == null? "" : true? "x" : " ");
        parameters.put("AntePara", ficha.getAntecedenteGestPara() == false? "" : "x");
        parameters.put("AnteAborto", ficha.getAntecedenteGestAborto() == false? "" : "x");
//        
//        //Reações Transfusionais
        parameters.put("ReacT", ficha.getReacaoTransf() == null? "" : true? "x" : " ");
        parameters.put("ReacTransData", ficha.getReacaoTransfData() != null? formato.format(ficha.getReacaoTransfData()):"");
        parameters.put("ReacTransTipo", ficha.getReacaoTransfTipo());
////        
//        //Usa Sangue Fenotipado
        parameters.put("UsaSF", ficha.getSangueFenotipado() == null? "" : true? "x" : " ");
        parameters.put("UsaSangueFenoTipos", ficha.getSangueFenotipadoTipos());
//       
//        //Hemocomponentes Solicitados
//        	
		parameters.put("Tipo", "sem teste");
//		parameters.put("Medida", ficha.getHemocompMedida());
//		parameters.put("Quantidade", ficha.getHemocompQuantidade());
		parameters.put("Desleucocitados", ficha.getHemocompDesleucocitado() == null? "" : true? "x" : " ");
		parameters.put("Filtrado", ficha.getHemocompFiltrado() == null? "" : true? "x" : " ");
		parameters.put("Lavado", ficha.getHemocompLavado() == null? "" : true? "x" : " ");
		parameters.put("Fenotipado", ficha.getHemocompFenotipado() == null? "" : true? "x" : " ");
		parameters.put("Irradiado", ficha.getHemocompIrradiado() == null? "" : true? "x" : " ");
//		 
		
//             
//        //Transfusão
//            
//        parameters.put("TipoTrans", ficha.getTransfusaoTipo());
        parameters.put("Programada", ficha.getTransfusaoTipo().equals("programada")? "X" : "");
        parameters.put("TransfDataP", ficha.getTransfusaoData() != null? formato.format(ficha.getTransfusaoData()):"");
        parameters.put("TransfHoraP", ficha.getTransfusaoHora());
        parameters.put("urgente", ficha.getTransfusaoTipo().equals("urgente")? "X" : "");
        parameters.put("nUrgente", ficha.getTransfusaoTipo().equals("naoUrgente")? "X" : "");
        parameters.put("eUrgencia", ficha.getTransfusaoTipo().equals("extremaUrgencia")? "X" : "");
//        parameters.put("TransfData", ficha.getDtSolicitacao()!= null? formato.format(ficha.getDtSolicitacao()):"");
//        parameters.put("Situacao", ficha.getSituacao());
//        
//        
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=fichatransfusional.pdf;");

        ft.toOutputStream("/jasper/transfusional.jrxml", parameters, response.getOutputStream());
		
	}
}
