package br.gov.pe.sismepe.repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.domain.TransfusionalSolicitacao;
import br.gov.pe.sismepe.dto.PessoaTitularDTO;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Transactional(readOnly = true)
	List<Pessoa> findByNomeContaining(String nome);
	
	@Transactional(readOnly = true)
	List<Pessoa> findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	List<Pessoa> findByNomeContainingAndCpf(String nome, String cpf);
	
	@Transactional(readOnly = true)
	List<Pessoa> findByNomeContainingAndIdIn(String nome, List<Long> ids);
	
	@Transactional(readOnly = true)
	List<Pessoa> findByCpfAndIdIn(String cpf, List<Long> ids);
	
	@Transactional(readOnly = true)
	List<Pessoa> findByNomeContainingAndCpfAndIdIn(String nome, String cpf, List<Long> ids);
	
	@Transactional(readOnly = true)
	List<PessoaTitular> findTitularsByMatriculaAndDataCancelamentoIsNullAndNomeAndCpf(Integer matricula, String nome, String cpf);
	
	@Transactional(readOnly = true)
	List<PessoaTitular> findTitularsByMatriculaAndDataCancelamentoIsNullAndNomeContaining(Integer matricula, String nome);
	
	@Transactional(readOnly = true)
	List<PessoaTitular> findTitularsByMatriculaAndDataCancelamentoIsNull(Integer matricula);
	
	@Transactional(readOnly = true)
	List<PessoaTitular> findTitularesByDataCancelamentoIsNull();
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByTipoTabelaIn(Pageable pageable, Collection<Integer> tipoTabela);
	
	@Transactional(readOnly = true)
	List<PessoaDependente> findDependentesByMatriculaAndDataCancelamentoIsNullAndDataBloqueioIsNullAndNomeAndCpf(Integer matricula, String nome, String cpf);

	@Transactional(readOnly = true)
	List<PessoaDependente> findDependentesByMatriculaAndDataCancelamentoIsNullAndDataBloqueioIsNullAndNome(Integer matricula, String nome);

	@Transactional(readOnly = true)
	List<PessoaDependente> findDependentesByMatriculaAndDataCancelamentoIsNullAndDataBloqueioIsNull(Integer matricula);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByMatricula(Integer matricula, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByMatriculaAndSequencial(Integer matricula, Integer sequencial, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByMatriculaAndSequencialAndDataConcessaoGreaterThanEqualAndUltimaAtualizacaoGreaterThanEqual(Integer matricula, Integer sequencial, Date dataConcessao, Date ultimaAtualizacao, Pageable pageable);
		
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByMatriculaAndSequencialAndDataConcessaoGreaterThanEqual(Integer matricula, Integer sequencial, Date dataConcessao, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByMatriculaAndSequencialAndUltimaAtualizacaoGreaterThanEqual(Integer matricula, Integer sequencial, Date ultimaAtualizacao, Pageable pageable);

	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByMatriculaAndDataConcessaoGreaterThanEqual(Integer matricula, Date dataConcessao, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByMatriculaAndUltimaAtualizacaoGreaterThanEqual(Integer matricula, Date ultimaAtualizacao, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByDataConcessaoGreaterThanEqualAndUltimaAtualizacaoGreaterThanEqual(Date dataConcessao, Date ultimaAtualizacao, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByDataConcessaoGreaterThanEqual(Date dataConcessao, Pageable pageable);
		
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByUltimaAtualizacaoGreaterThanEqual(Date ultimaAtualizacao, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByDataCancelamentoBetween(Date dataCancelamento, Date fimDia,Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<PessoaDependente> findDependentesByDataBloqueioBetween(Date dataBloqueio, Date fimDia, Pageable pageable);
	
	@Transactional(readOnly = true)
	PessoaTitular findTitularByMatriculaAndCorporacao(Integer matricula, Integer corporacao);

	@Transactional(readOnly = true)
	PessoaDependente findDependenteByMatriculaAndCorporacao(Integer matricula, Integer corporacao);

	@Transactional(readOnly = true)
	PessoaTitular findTitularByMatricula(Integer matricula);

	@Transactional(readOnly = true)
	PessoaDependente findDependenteByMatricula(Integer matricula);
	
	@Transactional(readOnly = true)
	PessoaDependente findPessoaDependenteByMatriculaAndSequencial(Integer matricula, Integer sequencial);
	
	@Transactional(readOnly = true)
	PessoaDependente findPessoaDependenteByMatriculaAndSequencialAndDataCancelamentoIsNull(Integer matricula, Integer sequencial);

	@Transactional(readOnly = true)
	List<PessoaTitular> pessoaTitularPorMatriculaOmeCorporacao(Integer matricula, Integer corporacao, Long ome);

	
	@Transactional(readOnly = true)
	Page<PessoaTitularDTO> pessoaTitularPorMatriculaOmeCorporacaoPerfil(Integer matricula, Integer corporacao, Long ome, Integer perfil, Pageable pageable);

	Page<PessoaTitular> findPessoaTitularByMatricula(Integer matricula, Pageable pageable);

	Page<PessoaTitular> findPessoaTitularByDataInclusaoGreaterThanEqual(Date dataInclusao, Pageable pageable);

	Page<PessoaTitular> findPessoaTitularByDataAlteracaoGreaterThanEqual(Date dataAlteracao, Pageable pageable);

	Page<PessoaTitular> findPessoaTitularByDataInclusaoGreaterThanEqualAndDataAlteracaoGreaterThanEqual(Date dataInclusao, Date dataAlteracao, Pageable pageable);

	Page<PessoaTitular> findPessoaTitularByMatriculaAndDataInclusaoGreaterThanEqualAndDataAlteracaoGreaterThanEqual(Integer matricula, Date dataInclusao, Date dataAlteracao, Pageable pageable);
	
	Page<PessoaTitular> findPessoaTitularByMatriculaAndDataInclusaoGreaterThanEqual(Integer matricula, Date dataInclusao, Pageable pageable);

	Page<PessoaTitular> findPessoaTitularByMatriculaAndDataAlteracaoGreaterThanEqual(Integer matricula, Date dataAlteracao, Pageable pageable);
	
	Page<PessoaTitular> findPessoaTitularByMatriculaAndDataCancelamentoGreaterThanEqual(Integer matricula, Date dataCancelamento, Pageable pageable);
	
	Page<PessoaTitular> findPessoaTitularByMatriculaAndDataConcessaoGreaterThanEqual(Integer matricula, Date dataConcessao, Pageable pageable);
	
	Pessoa findPessoaByIdAndDataCadastroGreaterThanEqual(Long id, Date dataCadastro);
	
	Page<PessoaTitular> findPessoaTitularByDataCancelamentoIsNull(Pageable pageable);

	Page<PessoaTitular> findPessoaTitularByDataCancelamentoBetween(Date dataCancelamento, Date fimDia, Pageable pageable);

	Page<PessoaTitular> findPessoaTitularByDataConcessaoBetween(Date dataConcessao, Date fimDia, Pageable pageable);
}
