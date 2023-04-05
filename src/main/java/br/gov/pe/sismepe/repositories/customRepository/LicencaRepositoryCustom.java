package br.gov.pe.sismepe.repositories.customRepository;

import br.gov.pe.sismepe.domain.Licenca;
import br.gov.pe.sismepe.dto.jms.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface LicencaRepositoryCustom {
    Page<Licenca> licencaPorOme(Long cdOme, Integer tipoParecer, boolean pm, boolean bm, Integer qtdDias,
                                boolean export, Date dataIni, Date dataFim, String opIni, String opFim,
                                Integer matricula, boolean disp, boolean lic, Pageable pageable);

    List<ItemRelatorioGerencialLicencaDTO> findLicencasRelatorioGerencial(Integer tipo, java.sql.Date dataIni, java.sql.Date dataFim);

    Page<LicencaMilitarPorPrestadorDTO> findMilitaresAtendidosPorPrestador(String conselho, String prestador,
                                                                                  java.sql.Date dataIni,
                                                                                  java.sql.Date dataFim,
                                                                                  Pageable pageable);

    Page<LicencasDiasIninterruptosDTO> licencasDiasIninterruptos(Pageable pageable);

    Page<GenericItemRelatorioGerencialDTO> militaresAfastadosPorOme(Pageable pageable);

    Page<MilitarAfastadoCidDTO> militaresAfastadosPorCid(String cid, Pageable pageable);

    Page<LicencaParecerDTO> relatorioLicencas(java.sql.Date dataIni, java.sql.Date dataFim, Pageable pageable);
}
