package com.atlantic.turnstiles.domain.catraca.log;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository  extends JpaRepository<Log, String>{
	
	@Query("SELECT l FROM Log l WHERE l.end = true AND l.sync = false")
	public List<Log> findAllEnded();
	
	@Query("SELECT l FROM Log l WHERE l.end = false and l.ip = :ip")
	Optional<Log> findByIp(String ip);
}
