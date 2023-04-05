package br.gov.pe.sismepe.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.util.StringUtils;

import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.UsuarioAcessoDTO;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.resources.exceptions.FieldMessage;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.util.Utils;

public class UsuarioAcessoUpdateValidator implements ConstraintValidator<UsuarioAcessoUpdate, UsuarioAcessoDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UsuarioRepository repo;
	
	
	@Autowired
	private PessoaRepository repoPessoa;
	
	@Override
	public void initialize(UsuarioAcessoUpdate ann) {
	}

	@Override
	public boolean isValid(UsuarioAcessoDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();	
		
		
		if(request.getRequestURI().equals("/usuario/alterar-acesso")) {
			
			UserSS user = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
			Usuario u = repo.findByLogin(user.getLogin());

			switch(request.getParameter("tipo")) {
				case "senha":
					if(!u.getSenha().equals(Utils.parseToMD5(objDto.getSenhaAtual()))) {
						list.add(new FieldMessage("senhaAtual", "Senha atual incorreta!"));
					} else {
						if(u.getSenha().equals(Utils.parseToMD5(objDto.getNovaSenha()))) {
							list.add(new FieldMessage("novaSenha", "Nova senha precisa ser diferente da senha atual!"));
						}
					}
					
					break;
					
				case "login":
					if(objDto.getLogin().equals(u.getLogin())) {
						list.add(new FieldMessage("login", "Novo login não pode ser igual ao anterior!"));
					} else {
						if(repo.findByLogin(objDto.getLogin()) != null) {
							list.add(new FieldMessage("login", "Esse login já está sendo utilizado por outro usuário!"));
						}
					}
					
					break;
					
				case "email":
					if(objDto.getEmail().equals(u.getEmail())) {
						list.add(new FieldMessage("email", "Novo e-mail não pode ser igual ao anterior!"));
					} else {
						if(repo.findByEmail(objDto.getEmail()) != null) {
							list.add(new FieldMessage("email", "Esse e-mail já está sendo utilizado por outro usuário!"));
						}
					}
					
					break;
			}
		} else {
			
			if(StringUtils.isEmpty(objDto.getEmail()) && StringUtils.isEmpty(objDto.getCpf())) {
				list.add(new FieldMessage("email", "E-mail e CPF inválidos!"));
			} else {
				
				if(!StringUtils.isEmpty(objDto.getEmail())) {
					List<Usuario> usuarios = repo.findUsuariosByEmail(objDto.getEmail());
					if (usuarios.isEmpty()) {
						list.add(new FieldMessage("email", "Não foi encontrado nenhum cadastro com este e-mail!"));
					} 
				}
				
				if(!StringUtils.isEmpty(objDto.getCpf())) {
					List<Pessoa> find = repoPessoa.findByCpf(objDto.getCpf());
					if(find.isEmpty()) {
						list.add(new FieldMessage("cpf", "Não foi encontrado nenhum cadastro com este CPF!"));
					} 
				}
			}
			
			
			
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
