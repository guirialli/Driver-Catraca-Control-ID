package com.atlantic.turnstiles.domain.catraca;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.atlantic.turnstiles.common.http.ApiConsumerCatraca;
import com.atlantic.turnstiles.domain.catraca.vo.CatracaLogin;
import com.atlantic.turnstiles.domain.catraca.vo.CatracaSessionResponse;
import com.atlantic.turnstiles.domain.catraca.vo.SystemTimeData;

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

	public void systemTimeConfig(String ip) throws IOException, InterruptedException {
		LocalDateTime data = LocalDateTime.now();

		int day = Integer.parseInt(DateTimeFormatter.ofPattern("dd").format(data));
		int month = Integer.parseInt(DateTimeFormatter.ofPattern("MM").format(data));
		int year = Integer.parseInt(DateTimeFormatter.ofPattern("yyyy").format(data));
		int hour = Integer.parseInt(DateTimeFormatter.ofPattern("HH").format(data));
		int minute = Integer.parseInt(DateTimeFormatter.ofPattern("mm").format(data));
		int second = Integer.parseInt(DateTimeFormatter.ofPattern("ss").format(data));
		SystemTimeData timeData = new SystemTimeData(day, month, year, hour, minute, second);

		String session = this.login(ip);

		try {
			consumer.post("http://" + ip + "/set_system_time.fcgi", session, timeData);
		} finally {
			this.logout(ip, session);
		}

	}
}
