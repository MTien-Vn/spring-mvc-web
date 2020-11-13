package com.nmt.dto;

public class UploadFileDTO {
	private String base64;
	private String nameFile;
	
	public String getBase64() {
		return base64.split(",")[1];
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getNameFile() {
		return nameFile;
	}
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
	
	
}
