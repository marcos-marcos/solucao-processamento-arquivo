package br.com.dpsp.app.log.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter @EqualsAndHashCode( of = { "id" } ) public class LogDTO
{

	private static final String DATE_FORMAT = "dd/MM/yyyy";

	private BigDecimal id;
	private String sequencia;
	private String integrador;
	private String nomeTransacao;
	private LocalDate inicioTransacao;
	private LocalDate fimTransacao;
	private String origem;
	private String destino;
	private String descricao;
	private String status;
	private String detalhes;

	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT ) private LocalDate dataEmissao;

	public LogDTO( BigDecimal id, String sequencia, String nomeTransacao, String integrador,
			LocalDate inicioTransacao, LocalDate fimTransacao, String origem, String destino,
			String descricao, String status, String detalhes )
	{
		this.id = id;
		this.sequencia = sequencia;
		this.nomeTransacao = nomeTransacao;
		this.integrador = integrador;
		this.inicioTransacao = inicioTransacao;
		this.fimTransacao = fimTransacao;
		this.origem = origem;
		this.destino = destino;
		this.descricao = descricao;
		this.status = status;
		this.detalhes = detalhes;

	}

}
