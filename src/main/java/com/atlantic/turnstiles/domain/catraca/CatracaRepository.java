package com.atlantic.turnstiles.domain.catraca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface CatracaRepository extends JpaRepository<CatracaEntity, Long>{
	CatracaEntity findByIp(String ip);
}
