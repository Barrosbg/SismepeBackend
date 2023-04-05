package br.gov.pe.sismepe.repositories.customRepository;

import br.gov.pe.sismepe.domain.BoletimSaude;
import br.gov.pe.sismepe.dto.jms.BoletimSaudeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoletimSaudeRepositoryCustom {
    Page<BoletimSaudeDTO> findByMatriculaAndNumero(Integer matricula, Integer numero, Pageable pageable);
}
