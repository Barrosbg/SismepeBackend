package br.gov.pe.sismepe.services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.FichaInspecaoJms;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.ComorbidadesDTO;
import br.gov.pe.sismepe.dto.jms.FichaInspecaoJmsDTO;
import br.gov.pe.sismepe.report.service.FichaInspecaoJmsReportService;
import br.gov.pe.sismepe.repositories.FichaInspecaoJmsRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import br.gov.pe.sismepe.util.Utils;

@Service
public class JmsService {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private FichaInspecaoJmsRepository fichaInspecaoJmsRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Transactional
	public FichaInspecaoJms salvarFichaInspecaoJms(FichaInspecaoJmsDTO fichaDTO) {
				
		pessoaService.adicionarComorbidades(fichaDTO.getPessoaTitular().getId(), fichaDTO.getComorbidades());	
		
		Usuario usuarioCadastro = usuarioService.getUsuarioLogado();
		fichaDTO.setUsuarioCadastro(usuarioCadastro);
		fichaDTO.setDataCadastro(new Date());
		FichaInspecaoJms obj = fichaDTO.toModel();
		obj.setAtivo("S");
		pessoaService.atualizarComorbidades(fichaDTO.getPessoaTitular().getId(), fichaDTO.getComorbidades());	
		System.out.println(fichaDTO.getComorbidades());
		return fichaInspecaoJmsRepository.save(obj);
	}

	@Transactional
	public void atualizarFichaInspecaoJms(Integer id, FichaInspecaoJmsDTO fichaInspecaoJms) {
		
//		verifica se a ficha realmente existe
		FichaInspecaoJms f = fichaInspecaoJmsRepository.findByIdAndAtivo(id, "S")
				.orElseThrow(() -> new ObjectNotFoundException("Ficha não encontrada. ID " + id));		
		
		fichaInspecaoJms.setId(id);		
		fichaInspecaoJms.setAtivo("S");
		fichaInspecaoJms.setDataCadastro(f.getDataCadastro());
		fichaInspecaoJms.setDataAtualizacao(new Date());
		fichaInspecaoJms.setUsuarioCadastro(usuarioService.getUsuarioLogado());
		pessoaService.atualizarComorbidades(fichaInspecaoJms.getPessoaTitular().getId(), fichaInspecaoJms.getComorbidades());	
		System.out.println(fichaInspecaoJms.getComorbidades());
		fichaInspecaoJmsRepository.save(fichaInspecaoJms.toModel());
	}

	public FichaInspecaoJms findFichaInspecaoById(Integer id) {
		return fichaInspecaoJmsRepository.findByIdAndAtivo(id, "S").orElseThrow(() -> new ObjectNotFoundException("Ficha não encontrada. ID " + id));
	}

	@Transactional
	public void deletarFichaInspecaoJms(Integer id) {
//		verifica se a ficha realmente existe
		FichaInspecaoJms ficha = fichaInspecaoJmsRepository.findByIdAndAtivo(id, "S")
				.orElseThrow(() -> new ObjectNotFoundException("Ficha não encontrada. ID " + id));
		
		ficha.setAtivo("N");
		ficha.setDataAtualizacao(new Date());
		ficha.setUsuarioCadastro(usuarioService.getUsuarioLogado());
		
		fichaInspecaoJmsRepository.save(ficha);
		
	}

	public List<FichaInspecaoJms> findFichaInspecaoByTitular(Long titularId) {
		return fichaInspecaoJmsRepository.findFichasByPessoaTitular_idAndAtivo(titularId, "S");
	
	}

	public void gerarPdfFichaInspecao(Integer id, HttpServletResponse response) throws IOException, Exception {
		
		FichaInspecaoJms ficha = fichaInspecaoJmsRepository.findByIdAndAtivo(id, "S")
				.orElseThrow(() -> new ObjectNotFoundException("Ficha não encontrada. ID " + id));
	
		ComorbidadesDTO comorbidadesDTO = new ComorbidadesDTO(ficha.getPessoaTitular().getComorbidades());
		
		
		FichaInspecaoJmsReportService rs = new FichaInspecaoJmsReportService();
		
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("TITULO", "FICHA DE INSPEÇÃO PARA QUADRO DE ACESSO JMS");
         parameters.put("identificacaoTitular", ficha.getNumeroRegistro() == null 
        		 ? "Não informado" 
        				 : ficha.getNumeroRegistro());
    	 parameters.put("nomeTitular", ficha.getPessoaTitular().getNome());
    	 parameters.put("usuarioCadastro", ficha.getUsuarioCadastro().getPessoa().getNome());
    	 parameters.put("dtCadastro", ficha.getDataCadastro());
    	 parameters.put("telefoneTitular", ficha.getPessoaTitular().getTelefone() == null 
    			 ? "Não informado" 
    					 : ficha.getPessoaTitular().getTelefone());
    	 parameters.put("postoTitular", ficha.getPessoaTitular().getPosto().getNome());
    	 parameters.put("matriculaTitular", ficha.getPessoaTitular().getMatricula() + "-" + ficha.getPessoaTitular().getDigito());
    	 parameters.put("omeTitular", ficha.getPessoaTitular().getOme() == null ? "Não informado" : ficha.getPessoaTitular().getOme().getAbreviacao());
    	 parameters.put("idadeTitular", Utils.idadePorDataDeNascimento(ficha.getPessoaTitular().getDataNascimento()));
//    	 parameters.put("estadoCivilTitular", ficha.getPessoaTitular().getEstadoCivil() == null 
//    			 ? "Não informado" 
//    					 : ficha.getPessoaTitular().getEstadoCivil());
    	 parameters.put("naturalidadeTitular", ficha.getPessoaTitular().getNaturalidade() == null 
    			 ? "Não informado" 
    					 : ficha.getPessoaTitular().getNaturalidade());
    	 parameters.put("filiacaoTitular", (ficha.getPessoaTitular().getNomePai() == null 
    			 ? "Não informado" 
    					 : ficha.getPessoaTitular().getNomePai()) + " / " + (ficha.getPessoaTitular().getNomeMae() == null 
    					 ? "Não informado" 
    							 : ficha.getPessoaTitular().getNomeMae()));
    	 parameters.put("ufTitular", ficha.getPessoaTitular().getEndereco() == null 
    			 ? "Não informado" 
    					 : ficha.getPessoaTitular().getEndereco().getUf());
    	 parameters.put("pesoTitular", ficha.getPeso());
    	 parameters.put("alturaTitular", ficha.getAltura());
    	 parameters.put("imcTitular", ficha.getImc());
    	 parameters.put("corTitular", "Não informado");
    	 parameters.put("snHip", comorbidadesDTO.getTemHipertensao());
    	 parameters.put("snDbt", comorbidadesDTO.getTemDiabetes());
    	 parameters.put("snFuma", comorbidadesDTO.getFuma());
    	 parameters.put("snPneum", comorbidadesDTO.getTemPneumopatias());
    	 parameters.put("snInsufCard", comorbidadesDTO.getTemInsuficiencia());
    	 parameters.put("snCereb", comorbidadesDTO.getTemDoencaCerebro());
    	 parameters.put("snRenCron", comorbidadesDTO.getTemDoencaRenal());
    	 parameters.put("snImun", comorbidadesDTO.getImunossuprimidos());
    	 parameters.put("snAnem", comorbidadesDTO.getTemAnemia());
    	 parameters.put("snObes", comorbidadesDTO.getTemObesidade());
    	 parameters.put("fezExameDeSangueNosUltimos6meses", ficha.getFezExameDeSangueNosUltimos6mesesFormat());
    	 parameters.put("fezCirurgiaNosUltimos12meses", ficha.getFezCirurgiaNosUltimos12mesesFormat());
		 parameters.put("esteveInternadoNosUltimos12meses", ficha.getEsteveInternadoNosUltimos12mesesFormat());
    	 parameters.put("fezExameDoCoracaoNosUltimos12meses", ficha.getFezExameDoCoracaoNosUltimos12mesesFormat());
    	 parameters.put("fazUsoDeMedicacaoContinua", ficha.getFazUsoDeMedicacaoContinuaFormat());
    	 parameters.put("descricaoMedicacaoContinua", ficha.getDescricaoMedicacaoContinuaFormat());
    	 parameters.put("possuiLicencaOudispensaAtualmente", ficha.getPossuiLicencaOudispensaAtualmenteFormat());
    	 parameters.put("descricaoTempoLicencaDispensa", ficha.getDescricaoTempoLicencaDispensaFormat());
    	 parameters.put("possuiOutroProblemaDeSaude", ficha.getPossuiOutroProblemaDeSaudeFormat());
    	 parameters.put("descricaoOutroProblemaSaude", ficha.getDescricaoOutroProblemaSaudeFormat());
    	 parameters.put("praticaAtividadeFisica", ficha.getPraticaAtividadeFisicaFormat());
    	 parameters.put("aptoParaIngresso", ficha.getAptoParaIngressoFormat());
    	 
         System.out.println(comorbidadesDTO.getTemHipertensao());
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=ficha-inspecao-jms.pdf;");

        rs.toOutputStream("/jasper/relFichaInspecaoQuadroAcessoJMSretrato.jrxml", ficha.getPessoaTitular().getComorbidades(), parameters, response.getOutputStream());
		
	}
}
