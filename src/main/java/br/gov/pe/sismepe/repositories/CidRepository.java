package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.domain.Cid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CidRepository extends JpaRepository<Cid, String> {
    @Transactional(readOnly = true)
    List<Cid> findCidByIdLikeOrDescricaoLike(String id, String descricao);

    @Transactional(readOnly = true)
    Page<Cid> findByIdContaining(String id, Pageable pageable);
    
    @Transactional(readOnly = true)
    Page<Cid> findByDescricaoContaining(String descricao, Pageable pageable);
}
