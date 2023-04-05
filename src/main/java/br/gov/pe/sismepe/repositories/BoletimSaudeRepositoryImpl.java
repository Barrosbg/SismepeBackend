package br.gov.pe.sismepe.repositories;

import br.gov.pe.sismepe.dto.jms.BoletimSaudeDTO;
import br.gov.pe.sismepe.repositories.customRepository.BoletimSaudeRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoletimSaudeRepositoryImpl implements BoletimSaudeRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    public Page<BoletimSaudeDTO> findByMatriculaAndNumero(Integer matricula, Integer numero, Pageable pageable) {
        String select = "select bs.NR_BOLETIM_SAUDE, bs.DT_CADASTRO ";
        String from = " from boletim_saude bs " +
                " join licenca l on l.CD_BOLETIM_SAUDE = bs.CD_BOLETIM_SAUDE " +
                " join pessoa_titular pt on pt.CD_PESSOA = l.CD_PESSOA " +
                " where 1=1 ";
        String where = "";
        String group = " group by bs.NR_BOLETIM_SAUDE, bs.DT_CADASTRO ";

        String selectCount = " select count(*) ";

        if (matricula != null && matricula != 0) {
            where += " and pt.NR_MATRICULA = " + matricula.toString() + " " ;
        }

        if (numero != null && numero != 0L) {
            where += " and bs.NR_BOLETIM_SAUDE = " + numero.toString() + " ";
        }

        String qCount = "select count(*) from (" + selectCount + from + where + group + ") t ";

        Query query = em.createNativeQuery(select + from + where + group);
        Query queryCount = em.createNativeQuery(qCount);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> result = query.getResultList();

        List<BoletimSaudeDTO> lista = new ArrayList<>();
        Long count = Long.parseLong(queryCount.getSingleResult().toString());
//        Long count = 0L;

        result.forEach(res -> lista.add(new BoletimSaudeDTO((Integer) res[0], (Date) res[1])));

        Page<BoletimSaudeDTO> pages = new PageImpl<>(lista, pageable, count);

        return pages;
    }
}
