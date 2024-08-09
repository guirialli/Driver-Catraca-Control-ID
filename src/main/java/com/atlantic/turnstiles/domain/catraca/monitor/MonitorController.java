package com.atlantic.turnstiles.domain.catraca.monitor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantic.turnstiles.domain.catraca.monitor.vo.CatracaEvent;

import jakarta.servlet.http.HttpServletRequest;


@RestController()
@RequestMapping("/api/notifications")
public class MonitorController{
	
	@Autowired
	private MonitorService service;
	
	@PostMapping("catra_event")
	public ResponseEntity<Object> CatracaEvent(@RequestBody CatracaEvent body, HttpServletRequest request) {
		String  ip = request.getRemoteAddr();
		service.alterLog(ip, body);
		return ResponseEntity.ok(null);
	}
}
