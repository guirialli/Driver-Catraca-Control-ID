package com.atlantic.turnstiles.domain.catraca;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.atlantic.turnstiles.common.http.ApiConsumerCatraca;
import com.atlantic.turnstiles.domain.catraca.vo.CatracaLogin;
import com.atlantic.turnstiles.domain.catraca.vo.CatracaSessionResponse;

@Service
public class CatracaService {

	@Value("${spring.application.catraca.login}")
	String user;

	@Value("${spring.application.catraca.password}")
	String password;

	@Autowired
	ApiConsumerCatraca consumer;

	public String login(String ip) throws InterruptedException, IOException {
		String url = "http://" + ip + "/login.fcgi";
		var responseSession = consumer.post(url, new CatracaLogin(user, password), CatracaSessionResponse.class);
		return responseSession.session();
	}
	
	public void logout(String ip, String session) throws IOException, InterruptedException {
		String url = "http://" + ip + "/logout.fcgi?session=" + session;
		consumer.post(url);
	}
}
