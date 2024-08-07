package com.atlantic.turnstiles.domain.catraca.monitor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantic.turnstiles.common.http.ApiConsumerCatraca;
import com.atlantic.turnstiles.domain.catraca.CatracaService;
import com.atlantic.turnstiles.domain.catraca.monitor.vo.ConfigHost;
import com.atlantic.turnstiles.domain.catraca.monitor.vo.ConfigMonitor;

@Service()
public class MonitorService {

	@Autowired
	ApiConsumerCatraca consumer;

	@Autowired
	CatracaService catracaService;

	public void configureCatraca(String catracasIp, ConfigHost cfg) throws IOException, InterruptedException {
		String session = catracaService.login(catracasIp);

		try {
			String url = "http://" + catracasIp + "/set_configuration.fcgi";
			consumer.post(url, session, new ConfigMonitor(cfg));

		} finally {
			catracaService.logout(catracasIp, session);
		}
	}

}
