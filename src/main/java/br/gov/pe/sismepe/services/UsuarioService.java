package br.gov.pe.sismepe.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.gov.pe.sismepe.domain.Perfil;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.domain.ResetSenha;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.UsuarioAcessoDTO;
import br.gov.pe.sismepe.dto.UsuarioDTO;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.repositories.PrestadorRepository;
import br.gov.pe.sismepe.repositories.ResetSenhaRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.NegocioException;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import br.gov.pe.sismepe.util.Utils;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private PrestadorRepository repoPrestador;

	@Autowired
	private ResetSenhaRepository repoResetSenha;

	@Autowired
	private PessoaRepository repoPessoa;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Environment env;

	public UsuarioDTO getUsuarioDTOLogado() {
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = repo.findByLogin(user.getLogin());

		UsuarioDTO u = new UsuarioDTO();

		u.setId(usuario.getId());
		u.setAtivo(usuario.getAtivo());
		u.setEmail(usuario.getEmail());
		u.setLogin(usuario.getLogin());
		u.setNivelAcesso(usuario.getNivelAcesso());
		u.setPerfis(usuario.getPerfis());
		u.setPessoa(usuario.getPessoa());
		u.setPrestador(repoPrestador.findByPessoa(usuario.getPessoa()));
		if (usuario.getPessoa().getClass().getSimpleName().equals("PessoaTitular")) {
			PessoaTitular pessoa = (PessoaTitular) usuario.getPessoa();
			u.setOme(pessoa.getOme());
		}
		return u;
	}
	
	public Usuario getUsuarioLogado() {
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return repo.findByLogin(user.getLogin());		
	}

	public void alterarAcessoUsuario(UsuarioAcessoDTO usuarioAcessoDTO, String tipo) {
		UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = repo.findByLogin(user.getLogin());

		switch (tipo) {
		case "senha":
			usuario.setSenha(Utils.parseToMD5(usuarioAcessoDTO.getNovaSenha()));
			break;
		case "login":
			usuario.setLogin(usuarioAcessoDTO.getLogin());
			break;
		case "email":
			usuario.setEmail(usuarioAcessoDTO.getEmail());
			break;
		default:
			break;
		}
		repo.save(usuario);
	}

	public void recuperarSenha(UsuarioAcessoDTO dto) throws MessagingException {
		Usuario usuario = null;

		if (!StringUtils.isEmpty(dto.getEmail())) {
			usuario = repo.findUsuariosByEmail(dto.getEmail()).get(0);
		}

		if (!StringUtils.isEmpty(dto.getCpf())) {
			Pessoa pessoa = repoPessoa.findByCpf(dto.getCpf()).get(0);
			usuario = repo.findUsuariosByPessoa(pessoa).get(0);
		}

		ResetSenha resetSenha = new ResetSenha();
		resetSenha.setUsuario(usuario);
		resetSenha.setToken(UUID.randomUUID().toString());
		resetSenha.setAtivo("S");

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 1);
		resetSenha.setDataExpiracao(c.getTime());

		Optional<ResetSenha> resetSenhaOptional = repoResetSenha.findByUsuario(usuario);

		if (resetSenhaOptional.isPresent()) {
			resetSenha.setId(resetSenhaOptional.get().getId());
		}

		repoResetSenha.save(resetSenha);

		MimeMessage mail = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mail);
		helper.setTo(usuario.getEmail());
		helper.setSubject("Alteração de Senha - SISMEPE");
		helper.setText(""
				+ "<head>"
				+ "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\">"
				+ "</head>"
				+ ""
				+ "<body>"
				+ ""
				+ "    <img src=\"https://www.sismepe.pe.gov.br:4443/images/topoSite.png\"/ style=\"width: 100%;\">"
				+ ""
				+ "    <div style=\"font-family: 'Roboto', sans-serif; margin-top: 40px;\">"
				+ "Prezado(a) " + usuario.getPessoa().getNome() + ", <br><br>"
				+ "Recebemos uma solicitação de redefinição de senha da sua conta do SISMEPE, com o login <b>" + usuario.getLogin() + "</b>.<br>"
				+ "Você poderá alterar sua senha clicando no link <a href=\"https://www.sismepe.pe.gov.br/" + env.getProperty("path-recovery-password") + "/#/alterar-senha/"
				+ resetSenha.getToken()
				+ "\">redefinição de senha</a>, e informando sua nova senha. O link terá validade de 24 horas ou até o momento em que a senha for alterada.<br><br>"
				+ "Caso a solicitação não tenha sido feita por você, por favor, ignore essa mensagem. <br>"
				+ "Atenciosamente.<br><br>" + "<b>DTI DASIS</b>"
				+ ""
				+ "</div>"
				+ "</body>"
				+ "", true);

		mailSender.send(mail);

	}

	public void validarToken(String token) {
		Optional<ResetSenha> object = repoResetSenha.findByToken(token);
		if (!object.isPresent()) {
			throw new NegocioException("Token inválido!");
		} else {
			ResetSenha resetSenha = object.get();
			if (!resetSenha.isAtivo() || resetSenha.getDataExpiracao().compareTo(new Date()) < 0) {
				throw new NegocioException("Token expirado!");
			}
		}
	}

	public void resetarSenha(UsuarioAcessoDTO dto) {
		Optional<ResetSenha> object = repoResetSenha.findByToken(dto.getToken());

		if (!object.isPresent()) {
			throw new NegocioException("Token inválido!");
		} else {
			ResetSenha resetSenha = object.get();

			if (!resetSenha.isAtivo() || resetSenha.getDataExpiracao().compareTo(new Date()) < 0) {
				throw new NegocioException("Token expirado!");
			}

			Usuario usuario = resetSenha.getUsuario();
			usuario.setSenha(Utils.parseToMD5(dto.getNovaSenha()));
			repo.save(usuario);

			resetSenha.setAtivo("N");
			repoResetSenha.save(resetSenha);
		}

	}

	public void removerPerfilUsuario(Integer usuarioId, Perfil perfil) {
		Usuario usuario = repo.findById(usuarioId)
				.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! ID: " + usuarioId));

		if (usuario.getPerfis().remove(perfil)) {
			repo.save(usuario);
		} else {
			throw new ObjectNotFoundException("Perfil não encontrado no usuário informado! ID: " + perfil.getId());
		}

	}
	
	public void adicionarPerfilUsuario(Integer usuarioId, Perfil perfil) {
		Usuario usuario = repo.findById(usuarioId)
				.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! ID: " + usuarioId));

		if (usuario.getPerfis().contains(perfil)) {
			throw new NegocioException("Usuário já possui esse perfil!");
		} else {
			usuario.getPerfis().add(perfil);
			repo.save(usuario);
		}

	}
}
