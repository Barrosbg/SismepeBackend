package br.gov.pe.sismepe.services;

import br.gov.pe.sismepe.dto.jms.BoletimSaudeDTO;
import br.gov.pe.sismepe.repositories.BoletimSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoletimSaudeService {
    @Autowired
    BoletimSaudeRepository repo;

    public Page<BoletimSaudeDTO> findByMatriculaAndNumero(Integer matricula, Integer numero, Integer page, Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return repo.findByMatriculaAndNumero(matricula, numero, pageRequest);
    }
}
