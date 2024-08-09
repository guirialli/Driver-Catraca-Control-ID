package com.atlantic.turnstiles.domain.catraca.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.atlantic.turnstiles.domain.catraca.action.dto.MovimentacaoData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Log")
@Table(name = "logs")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Log {

	public Log(String ip, MovimentacaoData movimentacao) {
		this.id = UUID.randomUUID().toString();
		this.ip = ip;

		this.uidQrcode = movimentacao.uidQrcode();
		this.idCracha = movimentacao.idCracha();
		this.codigo = movimentacao.codigo();
		this.data = movimentacao.data();
		this.status = movimentacao.status();
		this.idDevice = movimentacao.idDevice();
		this.sentido = movimentacao.sentido();

	}

	@Id
	@Column(nullable = false)
	private String id;

	@Column(nullable = false)
	private String ip;

	@Column(nullable = false)
	private String uidQrcode;

	@Column(nullable = false)
	private String idCracha;

	@Column(nullable = false)
	private String codigo;

	@Column(nullable = false)
	private String data;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private Integer idDevice;

	@Column(nullable = false)
	private String sentido;

	@Column(nullable = false)
	private Boolean end = false;
	
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean sync = false;

	@Column
	private String dt_acesso;

	@Column
	private Boolean st_acesso;

	public void concluiu(long timestamp, Boolean desistiu) {
		this.end = true;
		this.st_acesso = desistiu;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		this.dt_acesso = sdf.format(new Date());
	}

	public MovimentacaoData parserMovimentacao() {
		return new MovimentacaoData(uidQrcode, idCracha, codigo, data, status, idDevice, sentido, dt_acesso, st_acesso);
	}

}
