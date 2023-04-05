package br.gov.pe.sismepe.services;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.gov.pe.sismepe.domain.Atendimento;
import br.gov.pe.sismepe.domain.PesquisaDeSatisfacao;
import br.gov.pe.sismepe.domain.PesquisaExpiracao;
import br.gov.pe.sismepe.domain.PesquisaPerguntaResposta;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.dto.PesquisaDeSatisfacaoDTO;
import br.gov.pe.sismepe.dto.PesquisaPerguntaRespostaDTO;
import br.gov.pe.sismepe.repositories.AtendimentoRepository;
import br.gov.pe.sismepe.repositories.PesquisaDeSatisfacaoRepository;
import br.gov.pe.sismepe.repositories.PesquisaExpiracaoRepository;
import br.gov.pe.sismepe.repositories.PesquisaPerguntaRespostaRepository;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;

@Service
public class PesquisaDeSatisfacaoService {
	
	@Autowired
	private PesquisaDeSatisfacaoRepository repoPS;
	
	@Autowired
	private PessoaRepository repoPessoa;

	@Autowired
	private AtendimentoRepository repoAtendimento;
	
	@Autowired
	private PesquisaExpiracaoRepository repoPesquisaExpiracao;
	
//	@Autowired
//	private PesquisaPerguntaResposta repoPesquisaPesgunta;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private PesquisaPerguntaRespostaRepository repoPPR;
	
	public boolean enviarPesquisaDeSatisfacao(String cpf, Long cdAtendimento) {
		
		
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDate data = LocalDate.parse(timeStamp, parser);
		LocalDate mesSeguinte = data.plusDays(30);
		boolean soNumero = cpf.trim().chars().allMatch(c -> c >= '0' && c <= '9');
		
		
		Atendimento a = repoAtendimento
							.findById(cdAtendimento)
							.orElseThrow(() -> new ObjectNotFoundException("Atendimento não encontrado: " + cdAtendimento));
		
		if (!StringUtils.isEmpty(cpf) && soNumero && a != null) {
			Pessoa pesquisaPessoa = repoPessoa.findByCpf(cpf).get(0);
			
			
			PesquisaExpiracao pesquisa = new PesquisaExpiracao();
			pesquisa.setCdAtendimento(cdAtendimento);
			pesquisa.setCdPessoa(pesquisaPessoa.getId());
			pesquisa.setDataExpiracao(mesSeguinte);
			pesquisa.setPesquisaExpirada("N");
			
			String url = "https://www.sismepe.pe.gov.br/sn-dev/#/PesquisaSatisfacao/&" + cdAtendimento;
		
			String mailMenssage = ""
					+ "<head>"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\">"
					+ "</head>"
					+ ""
					+ "<body>"
					+ ""
					+ "    <img src=\"https://www.sismepe.pe.gov.br:4443/images/topoSite.png\"/ style=\"width: 100%;\">"
					+ ""
					+ "    <div style=\"font-family: 'Roboto', sans-serif; margin-top: 40px;\">"
					+ "Prezado(a) " + pesquisaPessoa.getNome() + ", <br><br>"
					+ "Verificamos que o senhor(a) acabou de ser atendido em uma de nossas unidades. <b>"  + "</b>.<br>"
					+ "Pedimos que por gentileza responda nossa pesquisa de satisfação no link a seguir <a href=\""+ url +"\""
					+ "\">Responder Pesquisa</a>,  suas respostas são de extrema importância para que possamos melhorar ainda mais nosso atendimento.<br><br>"
					+ "Atenciosamente.<br><br>" + "<b>SISMEPE</b>"
					+ ""
					+ "</div>"
					+ "</body>"
					+ "";
			
			if(!StringUtils.isEmpty(pesquisaPessoa.getEmail())) {
			        try {
			        	MimeMessage mimeMessage = mailSender.createMimeMessage();
			        	MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
				        message.setSubject("Pesquisa De Satisfação - SISMEPE");
				        message.setTo(pesquisaPessoa.getEmail());
				        message.setFrom("falecomsismepe@gmail.com");
				        message.setText(mailMenssage, true);
			            mailSender.send(mimeMessage);
			            repoPesquisaExpiracao.save(pesquisa);
			            return true;
			        } catch (Exception e) {
			            e.printStackTrace();
			    
			        }
			}
		}
		
		return false;
	}
		
	public List<PesquisaDeSatisfacao> buscarCodigoAtendimento(Long codigoAtendimento) {
		List<PesquisaDeSatisfacao> pesquisaDeSatisfacao = repoPS.findPesquisaSatisfacaoByCodigoAtendimento(codigoAtendimento);	
	
		return pesquisaDeSatisfacao;
	}
	
	
	
	public PesquisaDeSatisfacao salvar(PesquisaDeSatisfacaoDTO pesquisaPRDTO) throws Exception {
		PesquisaDeSatisfacao pesquisaS1 = pesquisaPRDTO.toModel();
		List<PesquisaDeSatisfacao> ps = buscarCodigoAtendimento(pesquisaPRDTO.getCodigoAtendimento());
		
		if (ps.size() > 0) {
			throw new Exception("Pesquisa já respondida");
		}
		
		PesquisaDeSatisfacao pesquisaS2 = repoPS.save(pesquisaS1);
	
		for(PesquisaPerguntaRespostaDTO lista: pesquisaPRDTO.getPesquisaPRDTO()) {
			PesquisaPerguntaResposta pesquisaPR = lista.toModel();
			pesquisaPR.setPesquisa(pesquisaS2.getId());
			repoPPR.save(pesquisaPR);
		}
		
		return pesquisaS2;
	}
	
	public boolean pesquisaRespondida(Long cdPesquisa) throws Exception {
		
		List<PesquisaDeSatisfacao> ps = buscarCodigoAtendimento(cdPesquisa);

		if (ps.size() > 0) {
			return true;
		}else {
			return false;
		}
		
		
	
		
	}
	
	
	
	public boolean buscarPesquisaExpirada(Long codigoAtendimento) {
		PesquisaExpiracao pesquisa =  repoPesquisaExpiracao.findBycdAtendimento(codigoAtendimento);
		System.out.println(codigoAtendimento);
		Date dataI = null;
     	long dataFinal = 0;
     	long dataInicial = 0;

		Date dataF = Date.from(pesquisa.getDataExpiracao().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Calendar c = Calendar.getInstance();
		dataI = c.getTime();
	
		dataFinal = dataF.getTime();
		dataInicial = dataI.getTime();
	  
		if(dataInicial > dataFinal) {
		  if(pesquisa.getPesquisaExpirada().equals("N")) {
			  pesquisa.setPesquisaExpirada("S");
			  repoPesquisaExpiracao.save(pesquisa);
		
		  }
		  
		  return true;
	      
	   }else {
		  return false;
	    }
//	
//		  Instant local
//          = Instant.parse(pesquisa.getDataExpiracao());
	}
}
