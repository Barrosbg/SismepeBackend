package br.gov.pe.sismepe.dto;

import java.io.Serializable;

public class UrlArquivoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String url;
	private String bucket;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExtension() {
		if(bucket.equals("minio")) {
			String urlPart = url.toString().split("\\?")[0];
			return urlPart.substring(urlPart.length() - 3, urlPart.length());
		} else {
			String urlPart = url.toString().split("\\/")[url.toString().split("\\/").length - 1];
			return urlPart.substring(urlPart.length() - 3, urlPart.length());
		}
	}


	public String getFilename() {
		if(bucket.equals("minio")) {
			String urlPart = url.toString().split("\\?")[0];			
			return urlPart.split("\\/")[urlPart.split("\\/").length - 1];
		} else {
			return url.toString().split("\\/")[url.toString().split("\\/").length - 1];
		}
	}
	
	
	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	@Override
	public String toString() {
		return "UrlArquivoDTO [url=" + getUrl() + ", filename=" + getFilename() + ", extension=" + getExtension() + ", bucket="
				+ getBucket() + "]";
	}

	
	
	
	
	
	
	

}
