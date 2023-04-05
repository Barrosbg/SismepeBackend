package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.domain.Licenca;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.dto.jms.GenericItemRelatorioGerencialDTO;
import br.gov.pe.sismepe.repositories.customRepository.LicencaRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

@Repository
public interface LicencaRepository extends JpaRepository<Licenca, Long>, LicencaRepositoryCustom {
    @Transactional(readOnly = true)
    List<Licenca> findByPessoa(Pessoa pessoa);

    @Transactional(readOnly = true)
    Page<Licenca> findByPessoa(Pessoa pessoa, Pageable pageable);

    @Query(value = "select l.qtdDias, count(l.id), count(l.dataHomologacao) from Licenca l group by l.qtdDias order by count(l.id) desc")
    List<Tuple> getQtdLicencasPorDia();

    @Query(value = "select l.qtdDias, count(l.id), count(l.dataHomologacao) from Licenca l " +
            "where l.dataCadastro >= :dataIni and l.dataCadastro <= :dataFim " +
            "group by l.qtdDias order by count(l.id) desc")
    List<Tuple> getQtdLicencasPorDia(Date dataIni, Date dataFim);

    @Query(value = "select p.parecer, sum(l.qtdDias) from Licenca l join l.parecer p group by p.parecer order by sum(l.qtdDias) desc")
    List<Tuple> getSomatorioDiasLicenca();

    @Query(value = "select p.parecer, sum(l.qtdDias) " +
            "from Licenca l join l.parecer p " +
            "where l.dataCadastro >= :dataIni and l.dataCadastro <= :dataFim " +
            "group by p.parecer order by sum(l.qtdDias) desc")
    List<Tuple> getSomatorioDiasLicenca(Date dataIni, Date dataFim);

    @Query(value = "select p.nome, p.matricula, count(l.id) from Licenca l join l.pessoa p where p.matricula = :matricula group by p.matricula, p.nome order by count(l.id) desc")
    List<Tuple> getQtdLicencasPorMilitar(Integer matricula);

    @Query(value = "select p.nome, p.matricula, count(l.id) from Licenca l join l.pessoa p group by p.matricula, p.nome order by count(l.id) desc")
    List<Tuple> getQtdLicencasPorMilitar();

    @Query(value = "select p.nome, p.matricula, count(l.id) from Licenca l join l.pessoa p where p.matricula = :matricula and l.dataCadastro >= :dataIni and l.dataCadastro <= :dataFim group by p.matricula, p.nome order by count(l.id) desc")
    List<Tuple> getQtdLicencasPorMilitar(Integer matricula, Date dataIni, Date dataFim);

    @Query(value = "select p.nome, p.matricula, count(l.id) from Licenca l join l.pessoa p where l.dataCadastro >= :dataIni and l.dataCadastro <= :dataFim group by p.matricula, p.nome order by count(l.id) desc")
    List<Tuple> getQtdLicencasPorMilitar(Date dataIni, Date dataFim);

    @Query(value = "select c.abreviacao, count(l.id) from Licenca l join l.cids c group by c.abreviacao order by count(l.id) desc")
    List<Tuple> getQtdLicencaPorCid();

    @Query(value = "select c.abreviacao, count(l.id) from Licenca l join l.cids c where c.abreviacao like concat('%',:cid,'%') group by c.abreviacao order by count(l.id) desc")
    List<Tuple> getQtdLicencaPorCid(String cid);

    @Query(value = "select c.abreviacao, count(l.id) from Licenca l join l.cids c where l.dataCadastro >= :dataIni and l.dataCadastro <= :dataFim group by c.abreviacao order by count(l.id) desc")
    List<Tuple> getQtdLicencaPorCid(Date dataIni, Date dataFim);

    @Query(value = "select c.abreviacao, count(l.id) from Licenca l join l.cids c where c.abreviacao like concat('%',:cid,'%') and l.dataCadastro >= :dataIni and l.dataCadastro <= :dataFim group by c.abreviacao order by count(l.id) desc")
    List<Tuple> getQtdLicencaPorCid(String cid, Date dataIni, Date dataFim);

    @Query(value = "select g.descricao, count(l.id), count(l.dataHomologacao) from Licenca l join l.omePessoaTitular o join o.grupoUnidadeMilitar g group by g.descricao order by count(l.id) desc")
    List<Tuple> getQtdLicencasPorGrupo();

    @Query(value = "select g.descricao, count(l.id), count(l.dataHomologacao) from Licenca l join l.omePessoaTitular o join o.grupoUnidadeMilitar g where l.dataCadastro >= :dataIni and l.dataCadastro <= :dataFim group by g.descricao order by count(l.id) desc")
    List<Tuple> getQtdLicencasPorGrupo(Date dataIni, Date dataFim);
}
