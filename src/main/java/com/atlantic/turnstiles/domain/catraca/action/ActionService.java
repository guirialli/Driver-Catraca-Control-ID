package com.atlantic.turnstiles.domain.catraca.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantic.turnstiles.common.exception.InvalidTurnstilesParam;
import com.atlantic.turnstiles.common.http.ApiConsumerCatraca;
import com.atlantic.turnstiles.domain.catraca.CatracaService;
import com.atlantic.turnstiles.domain.catraca.action.dto.ActionData;
import com.atlantic.turnstiles.domain.catraca.action.dto.ActionsData;

@Service
public class ActionService {

	@Autowired
	private ApiConsumerCatraca consumerCatraca;

	@Autowired
	private CatracaService catracaService;

	public void liberarCatraca(String ip, String action, String parameters) throws InterruptedException, IOException, InvalidTurnstilesParam {
		parameters = parameters.toLowerCase();
		if (!parameters.equals("clockwise") && !parameters.equals("both") && !parameters.equals("anticlockwise"))
			throw new InvalidTurnstilesParam("O parametro da catraca precisa ser clockwise, both, ou, anticlockwise");
		parameters = "allow=" + parameters;

		List<ActionData> actionData = new ArrayList<>();
		actionData.add( new ActionData(action, parameters));
		var actions = new ActionsData(actionData);

		var session = catracaService.login(ip);
		try {

			var url = "http://" + ip + "/execute_actions.fcgi";
			consumerCatraca.post(url, session, actions);

		} catch (Exception e) {
			throw new InvalidTurnstilesParam("Algum parametro informado está invlaido, verique apartir do erro:\n" + e.getMessage());
		} finally {
			catracaService.logout(ip, session);
		}

	}
}
