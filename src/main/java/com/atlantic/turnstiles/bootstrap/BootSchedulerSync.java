package com.atlantic.turnstiles.bootstrap;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.atlantic.turnstiles.domain.catraca.log.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BootSchedulerSync implements ApplicationRunner {

	@Autowired
	private LogRepository repository;

	@Value("${spring.application.acess.url}")
	private String sigacess;

	@Value("${sping.application.acess.token}")
	String token;

	private class Scheduler extends Thread {
		private final LogRepository logRepository;
		private final String sigacess;
		private final String token;
		private final ObjectMapper mapper = new ObjectMapper();

		public Scheduler(LogRepository logRepository, String sigacess, String token) {
			super();
			this.logRepository = logRepository;
			this.sigacess = sigacess;
			this.token = token;
		}

		@Override
		public void run() {
			try {
				while (true) {
					var logs = logRepository.findAllEnded();
					logs.forEach(l -> {
						try {
							var jsonString = mapper.writeValueAsString(l.parserMovimentacao());
							var client = HttpClient.newHttpClient();
							var request = HttpRequest.newBuilder(URI.create(sigacess + "/acess/movimentacao"))
									.setHeader("Content-Type", "application/json")
									.setHeader("Authorization", token).POST(BodyPublishers.ofString(jsonString))
									.build();
							var response = client.send(request, HttpResponse.BodyHandlers.ofString());
							
							if(response.statusCode() < 200 || response.statusCode() >= 300)
								throw new RuntimeException("Erro ao salvar no sigacess: " + response.body());
							l.setSync(true);
							repository.save(l);
							

						} catch (Exception e) {
							System.out.println("Falha ao completar a transação do log:  " + l.getId() + "!");
							System.out.println(e.getMessage());
						}
					});
					sleep(1000 * 60);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		var scheduler = new Scheduler(repository, sigacess, token);
		scheduler.run();

	}

}
