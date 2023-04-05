package br.gov.pe.sismepe.dto.jms;

import java.util.Date;

public class BoletimSaudeDTO {
    private Integer numBoletim;
    private Date dataCadastro;

    public BoletimSaudeDTO() {
    }

    public BoletimSaudeDTO(Integer numBoletim, Date dataCadastro) {
        this.numBoletim = numBoletim;
        this.dataCadastro = dataCadastro;
    }

    public Integer getNumBoletim() {
        return numBoletim;
    }

    public void setNumBoletim(Integer numBoletim) {
        this.numBoletim = numBoletim;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
