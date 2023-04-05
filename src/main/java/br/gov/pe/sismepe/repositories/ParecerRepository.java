package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.domain.Parecer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Long> {
    List<Parecer> findByParecerContaining(String parecer);
}
