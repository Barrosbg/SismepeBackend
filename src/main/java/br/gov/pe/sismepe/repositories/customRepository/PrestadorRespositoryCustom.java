package br.gov.pe.sismepe.repositories.customRepository;

import br.gov.pe.sismepe.domain.Prestador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrestadorRespositoryCustom {
    Page<Prestador> findPrestador(String nome, Long especialidade, String numeroConselho, Pageable pageable);
}
