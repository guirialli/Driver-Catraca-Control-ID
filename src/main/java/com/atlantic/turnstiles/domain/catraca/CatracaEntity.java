package com.atlantic.turnstiles.domain.catraca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "catraca")
@Table(name = "catracas")
@Getter()
@Setter()
@ToString()
@NoArgsConstructor()
@AllArgsConstructor()
public class CatracaEntity {
	public CatracaEntity(String ip) {
		this.ip = ip;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	
	@Column(name = "ip", unique = true, length = 34)
	String ip;
}
