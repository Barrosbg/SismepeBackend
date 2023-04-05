package br.gov.pe.sismepe.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.AgendaCentral;
import br.gov.pe.sismepe.domain.AgendaCentralAmb;

@Repository
public interface AgendaCentralRepository extends JpaRepository<AgendaCentral,Long>{

	@Transactional(readOnly = true)
	List<AgendaCentralAmb> findAmbsByEspecialidade_id(Long especialidadeId);
	
	@Transactional(readOnly = true)
	List<AgendaCentralAmb> findAmbsByAndPrestador_id(Long prestadorId);

//	@Transactional(readOnly = true)
//	Page<AgendaCentralAmb> findAmbsByAndPrestador_id(Long prestadorId, Pageable pageable);
	
	
	Page<AgendaCentralAmb> findByPrestador_id(Long prestadorId,Pageable pageable) ;
		// TODO Auto-generated method stub
		
	
	
	@Transactional(readOnly = true)
	List<AgendaCentralAmb> findAmbsByEspecialidade_idAndPrestador_id(Long especialidadeId, Long prestadorId);
	
}
