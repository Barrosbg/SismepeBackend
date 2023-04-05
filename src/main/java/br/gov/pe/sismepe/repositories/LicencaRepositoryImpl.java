package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.domain.*;
import br.gov.pe.sismepe.dto.jms.*;
import br.gov.pe.sismepe.repositories.customRepository.LicencaRepositoryCustom;
import br.gov.pe.sismepe.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;
import java.sql.Timestamp;

@Repository
public class LicencaRepositoryImpl implements LicencaRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    UsuarioRepository usuarioRepo;

    @Override
    public Page<Licenca> licencaPorOme(Long cdOme, Integer tipoParecer, boolean pm, boolean bm, Integer qtdDias,
                                       boolean export, Date dataIni, Date dataFim, String opIni, String opFim,
                                       Integer matricula, boolean disp, boolean lic, Pageable pageable) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Licenca> criteria = builder.createQuery(Licenca.class);

        Root<Licenca> licenca = criteria.from(Licenca.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.isNotNull(licenca.get("pessoa").get("matricula")));

        if (cdOme != null) {
            predicates.add(builder.equal(licenca.get("omePessoaTitular").get("id"), cdOme));
        } else {
            UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());

            // Perfil - JMS - CADASTRADOR JUNTA
            boolean perfil17 = usuarioLogado.getPerfis().stream().anyMatch((perfil -> perfil.getId() == 17));

            if (perfil17) {
                Subquery<UnidadeMilitar> subsubq = criteria.subquery(UnidadeMilitar.class);
                Root<UnidadeMilitar> subSubUM = subsubq.from(UnidadeMilitar.class);
                subsubq.select(subSubUM.get("cdJms")).where(builder.equal(subSubUM.get("cdOme"), 13));

                Subquery<UnidadeMilitar> subq = criteria.subquery(UnidadeMilitar.class);
                Root<UnidadeMilitar> subUM = subq.from(UnidadeMilitar.class);
                subq.select(subUM.get("cdOme")).where(builder.equal(subUM.get("cdJms"), subsubq));

                predicates.add(licenca.get("omePessoaTitular").get("id").in(subq));
            }
        }

        if (tipoParecer != null && tipoParecer != 0) {
            predicates.add(builder.equal(licenca.get("parecer").get("tipoParecer"), tipoParecer));
        }

        if (bm && !pm) {
            predicates.add(builder.equal(licenca.get("pessoa").get("corporacao"), 40));
        }
        if (pm && !bm) {
            predicates.add(builder.equal(licenca.get("pessoa").get("corporacao"), 6));
        }

        Predicate homologNotIn = builder.not(licenca.get("homologacao").in(Arrays.asList(4, 5, 2)));
        Predicate homologNull = builder.isNull(licenca.get("homologacao"));

        predicates.add(builder.or(homologNotIn, homologNull));

        if (qtdDias != null && qtdDias != 0) {
            if (qtdDias == 30) {
                predicates.add(builder.le(licenca.get("qtdDias"), qtdDias));
            } else if (qtdDias == 365) {
                predicates.add(builder.ge(licenca.get("qtdDias"), qtdDias));
            } else if (qtdDias == 180) {
                predicates.add(builder.and(builder.ge(licenca.get("qtdDias"), qtdDias), builder.le(licenca.get("qtdDias"), qtdDias)));
            } else {
                predicates.add(builder.equal(licenca.get("qtdDias"), qtdDias));
            }
        }

        if (export) {
            predicates.add(builder.isNotNull(licenca.get("homologacao")));
            Expression<Timestamp> dataFinal = builder.function("adddate", Timestamp.class, licenca.get("dataInicio"), licenca.get("qtdDias"));
//            predicates.add(builder.and(builder.greaterThanOrEqualTo(dataFinal, builder.currentTimestamp())));
//            predicates.add(builder.and(builder.lessThanOrEqualTo(licenca.get("dataInicio"), builder.currentDate())));
        }

        if (dataIni != null) {
            if (opIni != null && opIni.equalsIgnoreCase("mai")) {
                predicates.add(builder.greaterThanOrEqualTo(licenca.get("dataInicio"), dataIni));
            } else {
                predicates.add(builder.lessThanOrEqualTo(licenca.get("dataInicio"), dataIni));
            }
        }

        if (dataFim != null) {
            Expression<Timestamp> dataFinal = builder.function("adddate", Timestamp.class, licenca.get("dataInicio"), licenca.get("qtdDias"));

            if (opFim != null && opFim.equalsIgnoreCase("mai")) {
                predicates.add(builder.greaterThanOrEqualTo(dataFinal, dataFim));
            } else {
                predicates.add(builder.lessThanOrEqualTo(dataFinal, dataFim));
            }
        }

        if (lic && !disp) {
            predicates.add(builder.le(licenca.get("parecer").get("id"), 4));
        } else if (disp && !lic) {
            predicates.add(builder.greaterThan(licenca.get("parecer").get("id"), 4));
        }

        if (matricula != null) {
            predicates.add(builder.equal(licenca.get("pessoa").get("matricula"), matricula));
        }

        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), licenca, builder));

        TypedQuery criteriaQuery = this.em.createQuery(criteria);

        List<Licenca> result = criteriaQuery.setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Count
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Licenca> licencaCount = countQuery.from(Licenca.class);
        countQuery.select(builder.count(licencaCount))
                .where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        Long count = this.em.createQuery(countQuery).getSingleResult();

        Page<Licenca> pagesLicensas = new PageImpl<>(result, pageable, count);

        return pagesLicensas;
    }

    public List<ItemRelatorioGerencialLicencaDTO> findLicencasRelatorioGerencial(Integer tipo, java.sql.Date dataIni,
                                                                                 java.sql.Date dataFim) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<ItemRelatorioGerencialLicencaDTO> criteria = builder.createQuery(ItemRelatorioGerencialLicencaDTO.class);
        Root<Licenca> licenca = criteria.from(Licenca.class);

        List<Predicate> predicates = new ArrayList<>();

        criteria.select(builder.construct(ItemRelatorioGerencialLicencaDTO.class,
                licenca.get("id"),
                licenca.get("parecer").get("parecer"),
                licenca.get("pessoa").get("sexo"),
                licenca.get("pessoa").get("dataNascimento"),
                licenca.get("dataHomologacao"),
                licenca.get("qtdDias"),
                licenca.get("prestador").get("nome"),
                licenca.get("omePessoaTitular").get("descricao"),
                licenca.get("usuarioCadastro").get("pessoa").get("nome")));

        if (dataIni != null && dataFim != null) {
            predicates.add(builder.and(builder.greaterThanOrEqualTo(licenca.get("dataCadastro"), dataIni),
                    builder.lessThanOrEqualTo(licenca.get("dataCadastro"), dataFim)));
        }

        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<ItemRelatorioGerencialLicencaDTO> query = em.createQuery(criteria);
        List<ItemRelatorioGerencialLicencaDTO> items = query.getResultList();

        return items;
    }

    public Page<LicencaMilitarPorPrestadorDTO> findMilitaresAtendidosPorPrestador(String conselho, String prestador,
                                                                                  java.sql.Date dataIni,
                                                                                  java.sql.Date dataFim,
                                                                                  Pageable pageable) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<LicencaMilitarPorPrestadorDTO> criteria = builder.createQuery(LicencaMilitarPorPrestadorDTO.class);
        Root<Licenca> licenca = criteria.from(Licenca.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(licenca.get("ativo"), "S"));

        criteria.select(builder.construct(LicencaMilitarPorPrestadorDTO.class,
                licenca.get("id"),
                licenca.get("prestador").get("numeroConselho"),
                licenca.get("prestador").get("nome"),
                licenca.get("pessoa").get("matricula"),
                licenca.get("pessoa").get("nome"),
                licenca.get("dataInicio"),
                licenca.get("qtdDias")));

        if (conselho != null && !conselho.equalsIgnoreCase(""))
            predicates.add(builder.equal(licenca.get("prestador").get("numeroConselho"), conselho));

        if (prestador != null && !prestador.equalsIgnoreCase(""))
            predicates.add(builder.like(licenca.get("prestador").get("nome"), "%" + prestador + "%"));

        if (dataIni != null)
            predicates.add(builder.greaterThanOrEqualTo(licenca.get("dataInicio"), dataIni));

        if (dataFim != null)
            predicates.add(builder.lessThanOrEqualTo(licenca.get("dataInicio"), dataFim));

        criteria.orderBy(builder.asc(licenca.get("prestador").get("nome")));

        criteria.where(builder.and(predicates.toArray(predicates.toArray(new Predicate[predicates.size()]))));

        TypedQuery<LicencaMilitarPorPrestadorDTO> query = em.createQuery(criteria);

        List<LicencaMilitarPorPrestadorDTO> result = query.setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Count
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Licenca> licencaCount = countQuery.from(Licenca.class);
        countQuery.select(builder.count(licencaCount))
                .where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        Long count = this.em.createQuery(countQuery).getSingleResult();

        Page<LicencaMilitarPorPrestadorDTO> pages = new PageImpl<>(result, pageable, count);

        return pages;
    }

    public Page<LicencasDiasIninterruptosDTO> licencasDiasIninterruptos(Pageable pageable) {
        String from = " from licenca lic" +
                " left outer join pessoa_titular pt on pt.CD_PESSOA = lic.CD_PESSOA" +
                " left outer join ome o on o.CD_OME = lic.CD_OME_PESSOA_TIT" +
                " left outer join pessoa p on p.CD_PESSOA = lic.CD_PESSOA" +
                " where lic.CD_PESSOA in (" +
                "                select l.CD_PESSOA" +
                " from licenca l" +
                " where l.DT_INICIO >= DATE_SUB(CURDATE(),INTERVAL l.QT_DIAS DAY)" +
                "                        and l.SN_ATIVO = 'S' and l.CD_HOMOLOGACAO in (1,3)" +
                "                        AND l.DT_INICIO <= CURDATE()" +
                " ) and lic.SN_ATIVO = 'S'  and lic.CD_HOMOLOGACAO in (1,3)" +
                " and lic.DT_INICIO >= DATE_SUB(CURDATE(),INTERVAL 2 YEAR)";

        String select = "select pt.NR_MATRICULA as matricula, p.NM_PESSOA as nome, " +
                " o.DS_OME as batalhao, lic.DT_INICIO as dataInicio," +
                " DATE_ADD(lic.DT_INICIO,INTERVAL lic.QT_DIAS DAY) as dataFinal, lic.QT_DIAS as qtdDias ";

        String order = " order by o.DS_OME, pt.NR_MATRICULA, dataFinal desc";

        String selectCount = "select count(lic.CD_LICENCA) ";

        Query query = em.createNativeQuery(select + from + order);
        Query queryCount = em.createNativeQuery(selectCount + from);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> result = query.getResultList();

        List<LicencasDiasIninterruptosDTO> lista = new ArrayList<>();

        Long count = Long.parseLong(queryCount.getSingleResult().toString());

        result.forEach(res -> lista.add(new LicencasDiasIninterruptosDTO((Integer) res[0], res[1].toString(),
                res[2].toString(), (Date) res[3], (Date) res[4], (Integer) res[5])));

        Page<LicencasDiasIninterruptosDTO> pages = new PageImpl<>(lista, pageable, count);

        return pages;
    }

    public Page<GenericItemRelatorioGerencialDTO> militaresAfastadosPorOme(Pageable pageable) {
        // col0: Unidade militar
        // col1: Militares homologados
        // col2: Militares lançados
        String select = "select coalesce(DS_OME, 'Não identificado') as col0, sum(homologadas) AS col1, sum(aguardando) AS col2 ";
        String selectCount = "select count(*) ";

        String from = " from (" +
                " select o.DS_OME, count(distinct l.CD_PESSOA) as homologadas, 0 as aguardando " +
                "   from licenca l " +
                "   left outer join ome o on o.CD_OME = l.CD_OME_PESSOA_TIT " +
                " where l.DT_INICIO >= DATE_SUB(CURDATE(),INTERVAL l.QT_DIAS DAY) " +
                " and l.SN_ATIVO = 'S' and l.CD_HOMOLOGACAO in (1,3) " +
                " AND l.DT_INICIO <= CURDATE() " +
                "    group by o.DS_OME " +
                " union " +
                " select o.DS_OME, 0 as homologadas, count(distinct l.CD_PESSOA) as aguardando " +
                "    from licenca l " +
                "    left outer join ome o on o.CD_OME = l.CD_OME_PESSOA_TIT " +
                " where l.DT_INICIO >= DATE_SUB(CURDATE(),INTERVAL l.QT_DIAS DAY) " +
                " and l.SN_ATIVO = 'S' and l.CD_HOMOLOGACAO is null " +
                " AND l.DT_INICIO <= CURDATE() " +
                "    group by o.DS_OME " +
                " ) mil ";

        String order = " group by DS_OME order by DS_OME";

        Query query = em.createNativeQuery(select + from + order);
        Query queryCount = em.createNativeQuery(selectCount + from);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> result = query.getResultList();

        List<GenericItemRelatorioGerencialDTO> lista = new ArrayList<>();

        Long count = Long.parseLong(queryCount.getSingleResult().toString());

        result.forEach(res -> lista.add(new GenericItemRelatorioGerencialDTO(res[0].toString(),
                Long.parseLong(res[1].toString()),
                Long.parseLong(res[2].toString()))));

        Page<GenericItemRelatorioGerencialDTO> pages = new PageImpl<>(lista, pageable, count);

        return pages;
    }

    public Page<MilitarAfastadoCidDTO> militaresAfastadosPorCid(String cid, Pageable pageable) {
        String select = "select distinct l.QT_DIAS as qtdDias, DATE_ADD(l.DT_INICIO,INTERVAL l.QT_DIAS DAY) as dataFinal," +
                " pt.NR_MATRICULA as matricula,p.NM_PESSOA as nome, o.DS_OME as batalhao, c.DS_CID_ABREV, l.CD_LICENCA";
        String selectCount = " select count(*) ";

        String from = " from licenca l " +
                " inner join licenca_cid lc on lc.CD_LICENCA = l.CD_LICENCA " +
                " inner join pessoa p on p.CD_PESSOA = l.CD_PESSOA " +
                " inner join pessoa_titular pt on pt.CD_PESSOA = l.CD_PESSOA " +
                " inner join ome o on o.CD_OME = l.CD_OME_PESSOA_TIT " +
                " inner join cid c on c.CD_CID = lc.CD_CID " +
                " where l.DT_INICIO >= DATE_SUB(CURDATE(),INTERVAL l.QT_DIAS DAY) " +
                " AND l.SN_ATIVO = 'S' AND l.CD_HOMOLOGACAO in (1,3) " +
                " AND l.DT_INICIO <= CURDATE()";

        if (cid != null && !cid.equalsIgnoreCase(""))
            from += " AND lc.CD_CID like '%"+cid+"%' ";

        String order = " order by o.DS_OME ";

        Query query = em.createNativeQuery(select + from + order);
        Query queryCount = em.createNativeQuery(selectCount + from);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> result = query.getResultList();

        List<MilitarAfastadoCidDTO> lista = new ArrayList<>();

        Long count = Long.parseLong(queryCount.getSingleResult().toString());

        result.forEach(res -> lista.add(new MilitarAfastadoCidDTO((Integer) res[0], (Date) res[1], (Integer) res[2],
                res[3].toString(), res[4].toString(), res[5].toString())));

        Page<MilitarAfastadoCidDTO> pages = new PageImpl<>(lista, pageable, count);

        return pages;
    }

    public Page<LicencaParecerDTO> relatorioLicencas(java.sql.Date dataIni, java.sql.Date dataFim, Pageable pageable) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<LicencaParecerDTO> criteria = builder.createQuery(LicencaParecerDTO.class);
        Root<Licenca> licenca = criteria.from(Licenca.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(licenca.get("ativo"), "S"));
        predicates.add(builder.equal(licenca.get("omePessoaTitular").get("id"), 95));

        criteria.select(builder.construct(LicencaParecerDTO.class,
                licenca.get("id"),
                licenca.get("pessoa").get("nome"),
                licenca.get("parecer").get("parecer"),
                licenca.get("dataInicio"),
                licenca.get("dataCadastro"),
                licenca.get("qtdDias")
                ));

        if (dataIni != null)
            predicates.add(builder.greaterThanOrEqualTo(licenca.get("dataInicio"), dataIni));

        if (dataFim != null)
            predicates.add(builder.lessThanOrEqualTo(licenca.get("dataInicio"), dataFim));

        criteria.where(builder.and(predicates.toArray(predicates.toArray(new Predicate[predicates.size()]))));

//        criteria.orderBy(QueryUtils.toOrders(pageable.getSort(), licenca, builder));
        criteria.orderBy(QueryUtils.toOrders(Sort.by("dataCadastro"), licenca, builder));

        TypedQuery<LicencaParecerDTO> query = em.createQuery(criteria);

        List<LicencaParecerDTO> result = query.setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Count
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Licenca> licencaCount = countQuery.from(Licenca.class);
        countQuery.select(builder.count(licencaCount))
                .where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        Long count = this.em.createQuery(countQuery).getSingleResult();

        Page<LicencaParecerDTO> pages = new PageImpl<>(result, pageable, count);

        return pages;
    }
}

