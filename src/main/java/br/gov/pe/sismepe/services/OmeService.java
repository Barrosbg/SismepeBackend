package br.gov.pe.sismepe.services;

import br.gov.pe.sismepe.domain.Ome;
import br.gov.pe.sismepe.repositories.OmeRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OmeService {
    @Autowired
    OmeRepository repo;

    public List<Ome> findAll() {
        return repo.findAll();
    }

    public Ome findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Ome não encontrado: " + id));
    }

    public Page<Ome> filtro(String descricao, String abreviacao, Integer page, Integer linesPerPage, String orderBy,
                            String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        if (descricao != null && abreviacao == null) {
            return repo.findByDescricaoContaining(descricao, pageRequest);
        } else if (descricao == null && abreviacao != null) {
            return repo.findByAbreviacaoContaining(abreviacao, pageRequest);
        }
        return null;
    }
}
