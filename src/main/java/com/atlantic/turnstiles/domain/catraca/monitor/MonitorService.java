package com.atlantic.turnstiles.domain.catraca.monitor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantic.turnstiles.common.http.ApiConsumerCatraca;
import com.atlantic.turnstiles.domain.catraca.CatracaService;
import com.atlantic.turnstiles.domain.catraca.log.LogService;
import com.atlantic.turnstiles.domain.catraca.monitor.vo.CatracaEvent;
import com.atlantic.turnstiles.domain.catraca.monitor.vo.ConfigHost;
import com.atlantic.turnstiles.domain.catraca.monitor.vo.ConfigMonitor;

@Service()
public class MonitorService {

	@Autowired
	private ApiConsumerCatraca consumer;

	@Autowired
	private CatracaService catracaService;

	@Autowired
	private LogService logService;

	public void configureCatraca(String catracasIp, ConfigHost cfg) throws IOException, InterruptedException {
		String session = catracaService.login(catracasIp);

		try {
			String url = "http://" + catracasIp + "/set_configuration.fcgi";
			consumer.post(url, session, new ConfigMonitor(cfg));

		} finally {
			catracaService.logout(catracasIp, session);
		}
	}

	public void alterLog(String ip, CatracaEvent catraca) {
		var log = logService.findByIp(ip);
		log.concluiu(catraca.event().time(), catraca.event().name().equals("GIVE UP"));
		logService.update(log);
	}

}
