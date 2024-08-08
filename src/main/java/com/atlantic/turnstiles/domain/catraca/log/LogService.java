package com.atlantic.turnstiles.domain.catraca.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantic.turnstiles.domain.catraca.action.dto.MovimentacaoData;

@Service
public class LogService {

	@Autowired
	private LogRepository repository;

	public void saveLog(String ip, MovimentacaoData data) {
		Log log = new Log(ip, data);
		repository.save(log);
	}
	
	public void update(Log log) {
		repository.save(log);
	}
	
	public Log findByIp(String ip){
		return this.repository.findByIp(ip).orElseThrow(() -> new RuntimeException("Erro ao buscar o log!"));
		
	}
}
