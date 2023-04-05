package br.gov.pe.sismepe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.Biometria;
import br.gov.pe.sismepe.dto.BiometriaDTO;
import br.gov.pe.sismepe.repositories.BiometriaRepository;
import javassist.NotFoundException;

@Service
public class BiometriaService {
	@Autowired
	BiometriaRepository repo;
	
	public BiometriaDTO getBiometriaByPessoa(Integer cd_pessoa) {
		BiometriaDTO obj = repo.getBiometriaByPessoa(cd_pessoa);
		
		return obj;
	}
	
	public Biometria getBiometria(Integer cdPessoa) {
		return repo.getBiometria(cdPessoa);
	}
	
	public Biometria save(Biometria bio) {
//		Biometria b = repo.getBiometria(bio.getCdPessoa());
//		
//		if (b == null) {
//			return repo.save(bio);
//		}
//		
//		return b;
		
		return repo.save(bio);
	}
	
	public Biometria findById(Integer id) throws NotFoundException {
		return repo.findById(id).orElseThrow(() -> new NotFoundException("Biometria não encontrada: " + id));
	}
}
