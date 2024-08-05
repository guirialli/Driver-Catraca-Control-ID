package com.atlantic.turnstiles.common.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiConsumerCatraca {
	private ObjectMapper mapper = new ObjectMapper();

	public HttpResponse<String> post(String url, Object body) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var jsonString = mapper.writeValueAsString(body);
		var request = HttpRequest.newBuilder(URI.create(url)).POST(BodyPublishers.ofString(jsonString))
				.setHeader("Content-Type", "application/json").build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		if(response.statusCode() < 200 || response.statusCode() >= 300)
			throw new RuntimeException(response.body() + "\n" + response);
		return response;
	}

	public HttpResponse<String> post(String url) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(url)).POST(BodyPublishers.noBody())
				.setHeader("Content-Type", "application/json").build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		if(response.statusCode() < 200 || response.statusCode() >= 300)
			throw new RuntimeException(response.body() + "\n" + response);

		return response;

	}

	public HttpResponse<String> post(String url, String session, Object body) throws IOException, InterruptedException {
		return this.post(url + "?session=" + session, body);
	}

	public <T> T post(String url, String session, Class<T> dest) throws IOException, InterruptedException {
		var response = this.post(url + "?session=" + session);
		return mapper.readValue(response.body(), dest);
	}

	public <T> T post(String url, String session, Object body, Class<T> dest) throws IOException, InterruptedException {
		HttpResponse<String> response = this.post(url + "?session=" + session , session, body);
		return mapper.readValue(response.body(), dest);
	}

	public <T> T post(String url, Object body, Class<T> dest) throws IOException, InterruptedException {
		HttpResponse<String> response = this.post(url, body);
		return mapper.readValue(response.body(), dest);
	}
}
