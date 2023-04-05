package br.gov.pe.sismepe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{

	Page<Empresa> findByRazaoSocialContainingAndAtivo(String nome, String ativo, Pageable pageable);

	Page<Empresa> findByCnpjContainingAndAtivo(String cnpj, String string, Pageable pageable);

}
