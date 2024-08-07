package com.atlantic.turnstiles.domain.catraca.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantic.turnstiles.common.exception.InvalidTurnstilesParam;
import com.atlantic.turnstiles.domain.catraca.action.dto.RequestBodyCatracaLiberar;

import jakarta.validation.Valid;

@RequestMapping("/api/action")
@RestController
public class ActionController {
	
	@Autowired
	private ActionService actionService;
	
	@PostMapping("/liberar")
	public ResponseEntity<Object> liberar(@RequestBody @Valid RequestBodyCatracaLiberar body) throws InvalidTurnstilesParam, InterruptedException, IOException{
		actionService.liberarCatraca(body.ip(), body.action(), body.parameters());
		return ResponseEntity.status(200).build();
	}
}
