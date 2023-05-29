package com.demo.user.integrations;

import com.demo.user.dtos.GithubRepository_ReadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GithubClient {
	private RestTemplate restTemplate;
	private String baseUrl;
	private String authToken;

	public GithubClient(@Value("${github.api.base-url}") String baseUrl, @Value("${github.api.auth-token}") String authToken) {
		this.restTemplate = new RestTemplate();
		this.baseUrl = baseUrl;
		this.authToken = authToken;
	}

	public List<GithubRepository_ReadDTO> getUserRepositories(String userId) {
		String url = baseUrl + "/users/" + userId + "/repos";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + authToken);
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<GithubRepository_ReadDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, GithubRepository_ReadDTO[].class);
		return Arrays.asList(response.getBody());
	}
}