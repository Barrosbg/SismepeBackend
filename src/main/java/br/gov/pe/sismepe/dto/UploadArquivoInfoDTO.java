package br.gov.pe.sismepe.dto;

public class UploadArquivoInfoDTO {
	private String bucket;
	private String filename;
	private String url;
	
	public UploadArquivoInfoDTO() {
		super();
	}
	
	public UploadArquivoInfoDTO(String bucket, String filename, String url) {
		super();
		this.bucket = bucket;
		this.filename = filename;
		this.url = url;
	}
	
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "UploadArquivoInfoDTO [bucket=" + bucket + ", filename=" + filename + ", url=" + url + "]";
	}
	
}
