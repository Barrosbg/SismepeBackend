package br.gov.pe.sismepe.services;

import br.gov.pe.sismepe.domain.Cid;
import br.gov.pe.sismepe.domain.Conselho;
import br.gov.pe.sismepe.repositories.ConselhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ConselhoService {
    @Autowired
    private ConselhoRepository repo;

    public Page<Conselho> findByDescricaoOrSiglaOrUf(String descricao, String sigla, String uf, Integer page, Integer linesPerPage,
                                                               String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return repo.findBySiglaOrUfOrNumero(sigla, uf, descricao, pageRequest);
    }
}
