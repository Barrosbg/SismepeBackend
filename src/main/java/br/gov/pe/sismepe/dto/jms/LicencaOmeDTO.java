package br.gov.pe.sismepe.dto.jms;

import br.gov.pe.sismepe.domain.Licenca;
import br.gov.pe.sismepe.domain.enums.CorporacaoEnum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LicencaOmeDTO {
    private Integer matricula;
    private String corporacao;
    private String ome;
    private String nomePaciente;
    private String parecer;
    private String dataInicial;
    private Integer dias;
    private String dataFinal;
    private String homologacao;

    public LicencaOmeDTO() {}

    public LicencaOmeDTO(Integer matricula, String corporacao, String ome, String nomePaciente, String parecer,
                         String dataInicial, Integer dias, String dataFinal, String homologacao) {
        this.matricula = matricula;
        this.corporacao = corporacao;
        this.ome = ome;
        this.nomePaciente = nomePaciente;
        this.parecer = parecer;
        this.dataInicial = dataInicial;
        this.dias = dias;
        this.dataFinal = dataFinal;
        this.homologacao = homologacao;
    }

    public static LicencaOmeDTO fromLicenca(Licenca licenca) {
        LicencaOmeDTO lo = new LicencaOmeDTO();
        lo.setMatricula(licenca.getPessoa().getMatricula());

        CorporacaoEnum corp = licenca.getPessoa().getCorporacao() == 6 ? CorporacaoEnum.PM : CorporacaoEnum.BM;
        lo.setCorporacao(corp.getDescricao());
        lo.setOme(licenca.getOmePessoaTitular().getAbreviacao());
        lo.setNomePaciente(licenca.getPessoa().getNome());
        lo.setParecer(licenca.getParecer().getParecer());

        String di = licenca.getDataInicio() == null ? " - " : new SimpleDateFormat("dd/MM/yyyy").format(licenca.getDataInicio());
        lo.setDataInicial(di);

        lo.setDias(licenca.getQtdDias());

        // data final

        if (licenca.getQtdDias() != null && licenca.getQtdDias() >= 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(licenca.getDataInicio());
            c.add(Calendar.DATE, licenca.getQtdDias());
            Date dataFinal = c.getTime();

            String df = new SimpleDateFormat("dd/MM/yyyy").format(dataFinal);
            lo.setDataFinal(df);
        } else {
            lo.setDataFinal("-");
        }


//        String homolog = licenca.getHomologacao() == null ? " - " : licenca.getHomologacao().getDescricao();
        String homolog = licenca.getDataHomologacao() == null ? " - " : new SimpleDateFormat("dd/MM/yyyy").format(licenca.getDataHomologacao());
        lo.setHomologacao(homolog);

        return lo;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getCorporacao() {
        return corporacao;
    }

    public void setCorporacao(String corporacao) {
        this.corporacao = corporacao;
    }

    public String getOme() {
        return ome;
    }

    public void setOme(String ome) {
        this.ome = ome;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getHomologacao() {
        return homologacao;
    }

    public void setHomologacao(String homologacao) {
        this.homologacao = homologacao;
    }
}
