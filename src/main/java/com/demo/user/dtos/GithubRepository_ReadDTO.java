package com.demo.user.dtos;

public class GithubRepository_ReadDTO {
	private String name;
	private String url;
	private String language;

	public GithubRepository_ReadDTO() {
	}

	public GithubRepository_ReadDTO(String name, String url, String language) {
		this.name = name;
		this.url = url;
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
