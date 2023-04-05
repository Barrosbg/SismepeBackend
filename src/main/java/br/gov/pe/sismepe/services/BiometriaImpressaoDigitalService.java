package br.gov.pe.sismepe.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.domain.BiometriaImpressaoDigital;
import br.gov.pe.sismepe.dto.BiometriaImpressaoDigitalDTO;
import br.gov.pe.sismepe.repositories.BiometriaImpressaoDigitalRepository;

@Service
public class BiometriaImpressaoDigitalService {
	@Autowired
	BiometriaImpressaoDigitalRepository repo;
	
//	BiometriaImpressaoDigitalDTO getBiometriaImpressaoDigitalDTO(Integer cdBiometria, Integer cdIdentificadorDedo) {
//		BiometriaImpressaoDigitalDTO obj = repo.getBiometriaImpressaoDigitalDTO(cdBiometria, cdIdentificadorDedo);
//		
//		return obj;
//	}
////	
//	public List<BiometriaImpressaoDigital> getImpressoesDigitais(Integer cdBiometria) {
//		List<BiometriaImpressaoDigital> impressoes = repo.getBiometriaImpressoesDigitais(cdBiometria);
//		
//		return impressoes;
//	}
//	
//	public List<BiometriaImpressaoDigital> saveAll(List<BiometriaImpressaoDigital> impressoes) {		
//		return (List<BiometriaImpressaoDigital>) repo.saveAll(impressoes);
//	}
//	
//	public BiometriaImpressaoDigital save(BiometriaImpressaoDigital bio) {
//		return repo.save(bio);
//	}
}
