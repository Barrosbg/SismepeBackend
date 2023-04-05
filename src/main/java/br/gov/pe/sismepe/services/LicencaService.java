package br.gov.pe.sismepe.services;

import br.gov.pe.sismepe.domain.Licenca;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.dto.LicencaDTO;
import br.gov.pe.sismepe.dto.jms.*;
import br.gov.pe.sismepe.repositories.LicencaRepository;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class LicencaService {
    @Autowired
    LicencaRepository repo;

    @Autowired
    PessoaRepository pessoaRepo;

    @Autowired
    UsuarioRepository usuarioRepo;

    public List<Licenca> findByMatriculaAndCorporacao(Integer matricula, Integer corporacao) {
        Pessoa pessoa = pessoaRepo.findTitularByMatriculaAndCorporacao(matricula, corporacao);

        if (pessoa == null) {
            pessoa = pessoaRepo.findDependenteByMatriculaAndCorporacao(matricula, corporacao);
        }

        return repo.findByPessoa(pessoa);
    }

    public Page<Licenca> findByMatriculaAndCorporacaoPage(Integer matricula, Integer corporacao, Integer page,
                                                          Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Pessoa pessoa;

        if (corporacao != null) {
            pessoa = pessoaRepo.findTitularByMatriculaAndCorporacao(matricula, corporacao);

            if (pessoa == null) {
                pessoa = pessoaRepo.findDependenteByMatriculaAndCorporacao(matricula, corporacao);
            }
        } else {
            pessoa = pessoaRepo.findTitularByMatricula(matricula);

            if (pessoa == null) {
                pessoa = pessoaRepo.findDependenteByMatricula(matricula);
            }
        }

        return repo.findByPessoa(pessoa, pageRequest);
    }

    public List<Licenca> findByMatricula(Integer matricula) {
        Pessoa pessoa = pessoaRepo.findTitularByMatricula(matricula);

        if (pessoa == null) {
            pessoa = pessoaRepo.findDependenteByMatricula(matricula);
        }

        return repo.findByPessoa(pessoa);
    }

    public Licenca save(LicencaDTO licenca) {
        Licenca lic = licenca.toLicenca();

        UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());

        lic.setDataCadastro(new java.util.Date(System.currentTimeMillis()));
        lic.setAtivo("S");
        lic.setUsuarioCadastro(usuarioLogado);
//        lic.setDataHomologacao();
//        lic.setHomologacao();
//        lic.setObservacaoHomologacao();


        return repo.save(lic);
    }

    public Licenca update(Long id, LicencaDTO licencaDTO) {
        Optional<Licenca> l = repo.findById(id);
        Licenca lic = l.orElseThrow(() -> new ObjectNotFoundException("Licenca não encontrada: " + id));

        if (licencaDTO.getAtivo() != null) {
            lic.setAtivo(licencaDTO.getAtivo());
        }
        if (licencaDTO.getDataHomologacao() != null) {
            lic.setDataHomologacao(licencaDTO.getDataHomologacao());
        }
        if (licencaDTO.getObservacaoHomologacao() != null) {
            lic.setObservacaoHomologacao(licencaDTO.getObservacaoHomologacao());
        }
        if (licencaDTO.getOmeCadastro() != null) {
            lic.setOmeCadastro(licencaDTO.getOmeCadastro());
        }

        if (licencaDTO.getQtdDias() != null) {
            lic.setQtdDias(licencaDTO.getQtdDias());
        }
        if (licencaDTO.getDataInicio() != null) {
            lic.setDataInicio(licencaDTO.getDataInicio());
        }
        if (licencaDTO.getPrestador() != null) {
            lic.setPrestador(licencaDTO.getPrestador());
        }
        if (licencaDTO.getParecer() != null) {
            lic.setParecer(licencaDTO.getParecer());
        }
        if (licencaDTO.getUsuarioCadastro() != null) {
            lic.setUsuarioCadastro(licencaDTO.getUsuarioCadastro());
        }
        if (licencaDTO.getCids() != null) {
            lic.setCids(licencaDTO.getCids());
        }

        repo.save(lic);

        return lic;
    }

    public Licenca findById(Long id) {
        Optional<Licenca> l = repo.findById(id);

        return l.orElseThrow(() -> new ObjectNotFoundException("Licenca não encontrada: " + id));
    }

    public Page<Licenca> licencaPorOme(Long cdOme, Integer tipoParecer, boolean pm, boolean bm, Integer qtdDias,
                                       boolean export, Date dataIni, java.sql.Date dataFim, String opIni, String opFim,
                                       Integer matricula, boolean disp, boolean lic, Integer page, Integer linesPerPage,
                                       String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return repo.licencaPorOme(cdOme, tipoParecer, pm, bm, qtdDias, export, dataIni, dataFim,
                opIni, opFim, matricula, disp, lic, pageRequest);
    }

    public LicencasPorOmeReportDTO gerarRelatorioLicencasPorOmeDTO(Long cdOme, Integer tipoParecer, boolean pm,
                                                                   boolean bm, Integer qtdDias, boolean export,
                                                                   Date dataIni, Date dataFim, String opIni,
                                                                   String opFim, Integer matricula,
                                                                   boolean disp, boolean lic) {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.valueOf("ASC"), "pessoa.nome");

        Page<Licenca> licencaPage = repo.licencaPorOme(cdOme, tipoParecer, pm, bm, qtdDias, export, dataIni, dataFim,
                opIni, opFim, matricula, disp, lic, pageRequest);
        List<Licenca> licencas = licencaPage.getContent();

        List<LicencaOmeDTO> licencasOmeDto;

        licencasOmeDto = licencas
                .stream()
                .map((licenca -> LicencaOmeDTO.fromLicenca(licenca)))
                .collect(Collectors.toList());

        return new LicencasPorOmeReportDTO(licencasOmeDto);
    }

    public List<LicencaMilitarPorPrestadorDTO> findMilitaresAtendidosPorPrestador(String conselho, String prestador,
                                                                                  java.sql.Date dataIni,
                                                                                  java.sql.Date dataFim) {
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        return repo.findMilitaresAtendidosPorPrestador(conselho, prestador, dataIni, dataFim, pageRequest).getContent();
    }

    public Page<LicencaMilitarPorPrestadorDTO> findMilitaresAtendidosPorPrestador(String conselho, String prestador,
                                                                                  java.sql.Date dataIni,
                                                                                  java.sql.Date dataFim,
                                                                                  Integer page, Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return repo.findMilitaresAtendidosPorPrestador(conselho, prestador, dataIni, dataFim, pageRequest);
    }

    public RelatorioGerencialLicencaDTO getRelatorioGerencial(Integer tipo, java.sql.Date dataIni, java.sql.Date dataFim) {
        List<ItemRelatorioGerencialLicencaDTO> licencas = repo.findLicencasRelatorioGerencial(tipo, dataIni, dataFim);

        Long fem = licencas.stream().filter(item -> item.getSexoPessoa().equalsIgnoreCase("F")).count();
        Long mas = licencas.stream().filter(item -> item.getSexoPessoa().equalsIgnoreCase("M")).count();

        Collector agrupadoPor;
        Map<String, Long> qtdPorParecer;
        Map<String, Long> qtdPorParecerHomolog;

        String labelColunaDescricao;

        if (tipo == 2) {
            // Por médico
            qtdPorParecer = licencas.stream()
                    .collect(groupingBy(ItemRelatorioGerencialLicencaDTO::getNomeMedico, counting()));
            qtdPorParecerHomolog = licencas.stream().filter(item -> item.getDataHomologacao() != null)
                    .collect(groupingBy(ItemRelatorioGerencialLicencaDTO::getNomeMedico, counting()));

            labelColunaDescricao = "Médico emissor";
        } else if (tipo == 3) {
            // Por OME
            qtdPorParecer = licencas.stream()
                    .collect(groupingBy(ItemRelatorioGerencialLicencaDTO::getOmeTitular, counting()));
            qtdPorParecerHomolog = licencas.stream().filter(item -> item.getDataHomologacao() != null)
                    .collect(groupingBy(ItemRelatorioGerencialLicencaDTO::getOmeTitular, counting()));

            labelColunaDescricao = "Unidade militar";
        }
        else if (tipo == 4) {
            // Por digitador
            qtdPorParecer = licencas.stream()
                    .collect(groupingBy(ItemRelatorioGerencialLicencaDTO::getNomeUsuarioCadastro, counting()));
            qtdPorParecerHomolog = licencas.stream().filter(item -> item.getDataHomologacao() != null)
                    .collect(groupingBy(ItemRelatorioGerencialLicencaDTO::getNomeUsuarioCadastro, counting()));

            labelColunaDescricao = "Digitador";
        }
        else {
            // Por parecer/todos
            qtdPorParecer = licencas.stream()
                    .collect(groupingBy(ItemRelatorioGerencialLicencaDTO::getParecer, counting()));
            qtdPorParecerHomolog = licencas.stream().filter(item -> item.getDataHomologacao() != null)
                    .collect(groupingBy(ItemRelatorioGerencialLicencaDTO::getParecer, counting()));

            labelColunaDescricao = "Tipo";
        }

        HashMap<String, List<Long>> tudo = new HashMap<>();
        qtdPorParecer.forEach((k, v) -> tudo.put(k, new ArrayList<>(Arrays.asList((v)))));

        // Junta os maps de licencas totais e licencas homologadas
        for (Map.Entry<String, Long> homolog : qtdPorParecerHomolog.entrySet()) {
            Long val = homolog.getValue();
            String key = homolog.getKey();

            if (tudo.containsKey(key)) {
                tudo.get(key).add(val);
            } else {
                tudo.put(key, new ArrayList<>(Arrays.asList(val)));
            }
        }

        List<GenericItemRelatorioGerencialDTO> lista = new ArrayList<>();
        // Transforma o map geral (licencas totais + homologadas) em uma lista de objetos para ser renderizado pela Jasper
        for (Map.Entry<String, List<Long>> lic : tudo.entrySet()) {
            List<Long> val = lic.getValue();
            String key = lic.getKey();

            if (val.size() == 2)
                lista.add(new GenericItemRelatorioGerencialDTO(key, val.get(0), val.get(1)));
            else if (val.size() == 1)
                lista.add(new GenericItemRelatorioGerencialDTO(key, val.get(0), 0L));
            else
                lista.add(new GenericItemRelatorioGerencialDTO(key, 0L, 0L));
        }

        Long abaixo19 = licencas.stream().filter(item -> item.getIdadeAnos() < 19).count();
        Long entre19e30 = licencas.stream().filter(item -> item.getIdadeAnos() >= 19 && item.getIdadeAnos() < 30).count();
        Long entre31e40 = licencas.stream().filter(item -> item.getIdadeAnos() >= 31 && item.getIdadeAnos() < 40).count();
        Long entre41e50 = licencas.stream().filter(item -> item.getIdadeAnos() >= 41 && item.getIdadeAnos() < 50).count();
        Long acimaDe50 = licencas.stream().filter(item -> item.getIdadeAnos() > 50).count();
        Long naoInformado = 0L;

        Long total = licencas.stream().count();

        RelatorioGerencialLicencaDTO rg = new RelatorioGerencialLicencaDTO();

        rg.setLabelColunaDescricao(labelColunaDescricao);

        rg.setMasculino(mas);
        rg.setFeminino(fem);

        rg.setAgrupado(tudo);
        rg.setLista(lista);

        rg.setabaixoDe19(abaixo19);
        rg.setEntre19e30(entre19e30);
        rg.setentre31e40(entre31e40);
        rg.setEntre41e50(entre41e50);
        rg.setAcimaDe50(acimaDe50);
        rg.setNaoInformado(naoInformado);

        rg.setTotalLicencas(total);

        return rg;
    }

    public RelatorioGerencialLicencaDTO getQtdLicencasPorDia(java.sql.Date dataIni, java.sql.Date dataFim) {
        RelatorioGerencialLicencaDTO rg = new RelatorioGerencialLicencaDTO();
        List<GenericItemRelatorioGerencialDTO> items = new ArrayList<>();
        List<Tuple> tps = new ArrayList<>();

        if (dataIni != null && dataFim != null) {
            tps = repo.getQtdLicencasPorDia(dataIni, dataFim);
        } else {
            tps = repo.getQtdLicencasPorDia();
        }

        tps.stream().forEach(t -> items.add(new GenericItemRelatorioGerencialDTO(t.get(0).toString(), (Long) t.get(1), (Long) t.get(2))));

        rg.setLista(items);
        rg.setLabelColunaDescricao("Dias afastados");

        return rg;
    }

    public RelatorioGerencialLicencaDTO getSomatorioDiasLicenca(java.sql.Date dataIni, java.sql.Date dataFim) {
        RelatorioGerencialLicencaDTO rg = new RelatorioGerencialLicencaDTO();
        List<GenericItemRelatorioGerencialDTO> items = new ArrayList<>();
        List<Tuple> tps = new ArrayList<>();

        if (dataIni != null && dataFim != null) {
            tps = repo.getSomatorioDiasLicenca(dataIni, dataFim);
        } else {
            tps = repo.getSomatorioDiasLicenca();
        }

        tps.stream().forEach(t -> items.add(new GenericItemRelatorioGerencialDTO(t.get(0).toString(), (Long) t.get(1), 0L)));

        rg.setLista(items);
        rg.setLabelColunaDescricao("Parecer");
        rg.setLabelCol1("Somatório dos dias afastados");

        return rg;
    }

    public RelatorioGerencialLicencaDTO getQtdLicencasPorMilitar(Integer matricula, java.sql.Date dataIni, java.sql.Date dataFim) {
        RelatorioGerencialLicencaDTO rg = new RelatorioGerencialLicencaDTO();
        List<GenericItemRelatorioGerencialDTO> items = new ArrayList<>();
        List<Tuple> tps = new ArrayList<>();

        if (matricula != null) {
            if (dataIni != null && dataFim != null) {
                tps = repo.getQtdLicencasPorMilitar(matricula, dataIni, dataFim);
            } else {
                tps = repo.getQtdLicencasPorMilitar(matricula);
            }
        } else {
            if (dataIni != null && dataFim != null) {
                tps = repo.getQtdLicencasPorMilitar(dataIni, dataFim);
            } else {
                tps = repo.getQtdLicencasPorMilitar();
            }
        }

        tps.stream().forEach(t -> items.add(new GenericItemRelatorioGerencialDTO(t.get(0).toString(), Long.parseLong(t.get(1).toString()), (Long) t.get(2))));

        rg.setLista(items);
        rg.setLabelColunaDescricao("Nome");
        rg.setLabelCol1("Matricula");
        rg.setLabelCol2("Quantidade total");

        return rg;
    }

    public RelatorioGerencialLicencaDTO getQtdLicencaPorCid(String cid, java.sql.Date dataIni, java.sql.Date dataFim) {
        RelatorioGerencialLicencaDTO rg = new RelatorioGerencialLicencaDTO();
        List<GenericItemRelatorioGerencialDTO> items = new ArrayList<>();
        List<Tuple> tps = new ArrayList<>();

        if (!cid.equalsIgnoreCase("")) {
            if (dataIni != null && dataFim != null) {
                tps = repo.getQtdLicencaPorCid(cid, dataIni, dataFim);
            } else {
                tps = repo.getQtdLicencaPorCid(cid);
            }
        } else {
            if (dataIni != null && dataFim != null) {
                tps = repo.getQtdLicencaPorCid(dataIni, dataFim);
            } else {
                tps = repo.getQtdLicencaPorCid();

            }
        }

        tps.stream().forEach(t -> items.add(new GenericItemRelatorioGerencialDTO(t.get(0).toString(), (Long) t.get(1), 0L)));

        rg.setLista(items);
        rg.setLabelColunaDescricao("Cid");

        return rg;
    }

    public RelatorioGerencialLicencaDTO getQtdLicencasPorGrupo(java.sql.Date dataIni, java.sql.Date dataFim) {
        RelatorioGerencialLicencaDTO rg = new RelatorioGerencialLicencaDTO();
        List<GenericItemRelatorioGerencialDTO> items = new ArrayList<>();
        List<Tuple> tps = new ArrayList<>();

        if (dataIni != null && dataFim != null) {
            tps = repo.getQtdLicencasPorGrupo(dataIni, dataFim);
        } else {
            tps = repo.getQtdLicencasPorGrupo();
        }

        tps.stream().forEach(t -> items.add(new GenericItemRelatorioGerencialDTO(t.get(0).toString(), (Long) t.get(1), (Long) t.get(2))));

        rg.setLista(items);
        rg.setLabelColunaDescricao("Parecer");

        return rg;
    }

    public RelatorioGerencialLicencaDTO getRelatorio(Integer tipo, java.sql.Date dataIni, java.sql.Date dataFim, Integer matricula, String cid) {
        switch (tipo) {
            case 1:
                return getQtdLicencasPorDia(dataIni, dataFim);
            case 5:
                return getSomatorioDiasLicenca(dataIni, dataFim);
            case 6:
                return getQtdLicencaPorCid(cid, dataIni, dataFim);
            case 7:
                return getQtdLicencasPorMilitar(matricula, dataIni, dataFim);
            case 8:
                return getQtdLicencasPorGrupo(dataIni, dataFim);
            default:
                return getRelatorioGerencial(tipo, dataIni, dataFim);
        }
    }

    public Page<LicencasDiasIninterruptosDTO> licencasDiasIninterruptos(Integer page, Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return repo.licencasDiasIninterruptos(pageRequest);
    }

    public Page<GenericItemRelatorioGerencialDTO> militaresAfastadosPorOme(Integer page, Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return repo.militaresAfastadosPorOme(pageRequest);
    }

    public Page<MilitarAfastadoCidDTO> militaresAfastadosPorCid(String cid, Integer page, Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return repo.militaresAfastadosPorCid(cid, pageRequest);
    }

    public Page<LicencaParecerDTO> relatorioLicencas(java.sql.Date dataIni, java.sql.Date dataFim, Integer page,
                                                     Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);

        return repo.relatorioLicencas(dataIni, dataFim, pageRequest);
    }
}
