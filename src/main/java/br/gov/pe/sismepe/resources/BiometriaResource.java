package br.gov.pe.sismepe.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Biometria;
import br.gov.pe.sismepe.domain.BiometriaImpressaoDigital;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.BiometriaDTO;
import br.gov.pe.sismepe.dto.BiometriaImpressaoDigitalDTO;
import br.gov.pe.sismepe.dto.covid.PessoaDTO;
import br.gov.pe.sismepe.repositories.BiometriaImpressaoDigitalRepository;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.BiometriaImpressaoDigitalService;
import br.gov.pe.sismepe.services.BiometriaService;
import br.gov.pe.sismepe.services.PessoaService;
import javassist.NotFoundException;
import springfox.documentation.annotations.ApiIgnore;

class CodigoPessoa {
	private Integer cdPessoa;
	
	public CodigoPessoa() {
	}
	
	void setCdPessoa(Integer cdPessoa) {
		this.cdPessoa = cdPessoa;
	}
	
	Integer getCdPessoa() {
		return this.cdPessoa;
	}
}

@ApiIgnore
@RestController
@RequestMapping(value = "/biometria")
@Transactional
public class BiometriaResource {
	@Autowired
	private BiometriaService service;
	
	@Autowired
	BiometriaImpressaoDigitalRepository bidRepo;
	
	@Autowired
	PessoaService pessoaService;
	
    @Autowired
    UsuarioRepository usuarioRepo;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/nova", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Biometria> nova(@RequestBody BiometriaDTO bio) {
		Biometria b = service.getBiometria(bio.getCdPessoa());
		List<BiometriaImpressaoDigital> digitais;
		
		Pessoa p = pessoaService.find(new Long(bio.getCdPessoa()));
		p.setRecadastramento("S");
		
		if (b == null) {
			b = new Biometria();
			b.setCdPessoa(bio.getCdPessoa());
			b.setDataCadastro(new Date(System.currentTimeMillis()));
			
			digitais = new ArrayList<BiometriaImpressaoDigital>();
			
			b = service.save(b);
			
			for (BiometriaImpressaoDigitalDTO digital : bio.getDigitais()) {
				UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());

				BiometriaImpressaoDigital bid = new BiometriaImpressaoDigital(
						Integer.parseInt(digital.getIndex()), 
						digital.getHash(), 
						new Date(System.currentTimeMillis()), 
						usuarioLogado.getId(), "S", b);
				bid = bidRepo.save(bid);
				digitais.add(bid);
			}
			
			b.setDigitais(digitais);
		} else {
			// if b.getDigitais is not empty
			for (BiometriaImpressaoDigitalDTO digital : bio.getDigitais()) {
				for (BiometriaImpressaoDigital bid : b.getDigitais()) {
					// Se a digital já está cadastrada e os hashs são diferentes, atualiza o hash
					if (Integer.parseInt(digital.getIndex()) == bid.getCdIdentificadorDedo()) {
						if (!digital.getHash().equals(bid.getDsBiometriaHash())) {
							UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
							Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());
							
							bid.setDsBiometriaHash(digital.getHash());
							bid.setDtCadastro(new Date(System.currentTimeMillis()));
							bid.setCdUsuario(usuarioLogado.getId());
							bidRepo.save(bid);
						}
					}
				}
			}
			// else, cria
		}
		b = service.save(b);
		pessoaService.save(p);
		
		return ResponseEntity.ok(b);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/digitais", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> digitais(@RequestBody CodigoPessoa cdPessoa) {
		Biometria b = service.getBiometria(cdPessoa.getCdPessoa());
		
		if (b == null || b.getDigitais().isEmpty()) {
			return ResponseEntity.ok(Arrays.asList());			
		}
		
		return ResponseEntity.ok(b.getDigitais());
	}
}
