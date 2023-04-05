package br.gov.pe.sismepe.services;

import br.gov.pe.sismepe.domain.Cid;
import br.gov.pe.sismepe.repositories.CidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CidService {
    @Autowired
    private CidRepository repo;

    public Page<Cid> findCidByIdLikeOrDescricaoContaining(String id, String descricao, Integer page, Integer linesPerPage,
                                             String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        
        if(id != null && !id.equals("")) {        	
        	return repo.findByIdContaining(id, pageRequest);
        } else {
        	return repo.findByDescricaoContaining(descricao, pageRequest);        	
        }
        
    }
}
