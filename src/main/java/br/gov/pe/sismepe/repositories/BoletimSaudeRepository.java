package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.domain.BoletimSaude;
import br.gov.pe.sismepe.repositories.customRepository.BoletimSaudeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletimSaudeRepository extends JpaRepository<BoletimSaude, Long>, BoletimSaudeRepositoryCustom {
}
