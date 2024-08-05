package com.atlantic.turnstiles.domain.monitor;


import com.atlantic.turnstiles.domain.monitor.vo.CatracaEvent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/api/notifications")
public class MonitorController{
	@PostMapping("catra_event")
	public ResponseEntity<Object> CatracaEvent(@RequestBody CatracaEvent body) {
		System.out.println(body);
		return ResponseEntity.ok(null);
	}
}
