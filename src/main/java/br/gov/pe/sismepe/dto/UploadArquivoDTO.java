package br.gov.pe.sismepe.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.util.StringUtils;

public class UploadArquivoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String base64;
   
	@NotBlank
    private String extensao;
	
    public UploadArquivoDTO() {
    }

    public UploadArquivoDTO(String base64, String extensao) {
        this.base64 = base64;
        this.extensao = extensao;
    }

	public String getBase64() {
        String[] s = this.base64.split(",");

        if (StringUtils.isEmpty(s[1]))
            throw new ArrayIndexOutOfBoundsException("Não existe base64 na string");

        return s[1];
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }
    
    
}
