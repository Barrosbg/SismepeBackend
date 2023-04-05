package br.gov.pe.sismepe.dto.jms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LicencasPorOmeReportDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<LicencaOmeDTO> licencas = new ArrayList<LicencaOmeDTO>();
    private Integer totalPms = 0;
    private Integer totalBms = 0;

    public LicencasPorOmeReportDTO() {
    }

    public LicencasPorOmeReportDTO(List<LicencaOmeDTO> licencas) {
        this.licencas = licencas;

        for (LicencaOmeDTO lo: this.licencas) {
            if (lo.getCorporacao() != null) {
                if (lo.getCorporacao().equalsIgnoreCase("PM")) {
                    this.totalPms += 1;
                } else if (lo.getCorporacao().equalsIgnoreCase("BM")) {
                    this.totalBms += 1;
                }
            }
        }
    }

    public List<LicencaOmeDTO> getLicencas() {
        return licencas;
    }

    public void setLicencas(List<LicencaOmeDTO> licencas) {
        this.licencas = licencas;
    }

    public Integer getTotalPms() {
        return totalPms;
    }

    public void setTotalPms(Integer totalPms) {
        this.totalPms = totalPms;
    }

    public Integer getTotalBms() {
        return totalBms;
    }

    public void setTotalBms(Integer totalBms) {
        this.totalBms = totalBms;
    }
}
