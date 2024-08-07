package com.atlantic.turnstiles.bootstrap;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.IllformedLocaleException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.atlantic.turnstiles.domain.catraca.CatracaEntity;
import com.atlantic.turnstiles.domain.catraca.CatracaRepository;
import com.atlantic.turnstiles.domain.catraca.monitor.MonitorService;
import com.atlantic.turnstiles.domain.catraca.monitor.vo.ConfigHost;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BootCatraca implements ApplicationRunner {

	@Autowired
	private Environment env;

	@Autowired
	private CatracaRepository repository;

	@Autowired
	private MonitorService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		String token = env.getProperty("sping.application.acess.token");
		String url = env.getProperty("spring.application.acess.url");
		String hostname = env.getProperty("spring.application.hostname");
		String port = env.getProperty("spring.application.port");


		if (url == null || token == null) {
			throw new IllegalArgumentException(
					"Token or URL not found in environment properties! url: " + url + "; token: " + token);
		} else if (hostname == null || port == null) {
			throw new IllegalArgumentException("Invalid hostname or port");
		} 

		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(url + "/device/?tipodispositivo=CATRACA"))
				.setHeader("Authorization", token).build();
		try {

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() >= 200 && response.statusCode() < 300) {
				var body = response.body();
				var objectMapper = new ObjectMapper();

				CatracaResponse[] catracaResponses = objectMapper.readValue(body, CatracaResponse[].class);

				repository.deleteAll();
				Arrays.stream(catracaResponses).forEach(c -> {
					if (c.catracaIp() != null) {
						repository.save(new CatracaEntity(c.catracaIp()));
						try {
							service.configureCatraca(c.catracaIp(), 
									new ConfigHost(hostname, "5000", port, "api/notifications"));
						} catch (Exception e) {
							System.out.println("Falha para configurar a catraca: " + c.catracaIp());
							System.out.println(e.getMessage());
						}
					}

				});

			} else if (response.statusCode() == 401) {
				throw new IllformedLocaleException("Invalid token: verify your token!");
			}
		} catch (IOException e) {
			System.err.printf("Falha ao se conectar ao servidor %s!\n%s\n", url, e.getMessage());
		}
	}

}
