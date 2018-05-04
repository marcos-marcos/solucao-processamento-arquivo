package br.com.dpsp.app.log.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Entity @Table( name = "TRACING", uniqueConstraints = @UniqueConstraint( columnNames = {
		"id" }, name = "id_uk" ), indexes = {
		@Index( columnList = "SEQUENCIA_TRANSACAO", name = "sequencia_idx" ) } )
//@NamedQueries( value = {
//		@NamedQuery( name = "Log.findBySequenciaOrigem", query = "select log.* from Log log where log.SEQUENCIA_TRANSACAO = ?1 and log.origem = ?2" ) } )
//
public class Log
{

	public Long getId( )
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getSequencia_transacao( )
	{
		return sequencia_transacao;
	}

	public void setSequencia_transacao( String sequencia_transacao )
	{
		this.sequencia_transacao = sequencia_transacao;
	}

	public String getNome( )
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	public String getIntegrador( )
	{
		return integrador;
	}

	public void setIntegrador( String integrador )
	{
		this.integrador = integrador;
	}

	public LocalDate getInicioTransacao( )
	{
		return inicioTransacao;
	}

	public void setInicioTransacao( LocalDate inicioTransacao )
	{
		this.inicioTransacao = inicioTransacao;
	}

	public LocalDate getFimTransacao( )
	{
		return fimTransacao;
	}

	public void setFimTransacao( LocalDate fimTransacao )
	{
		this.fimTransacao = fimTransacao;
	}

	public String getOrigem( )
	{
		return origem;
	}

	public void setOrigem( String origem )
	{
		this.origem = origem;
	}

	public String getDestino( )
	{
		return destino;
	}

	public void setDestino( String destino )
	{
		this.destino = destino;
	}

	public String getDescricao( )
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public String getStatus( )
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}

	public String getDetalhes( )
	{
		return detalhes;
	}

	public void setDetalhes( String detalhes )
	{
		this.detalhes = detalhes;
	}

	@Id @GeneratedValue( strategy = GenerationType.IDENTITY ) @Getter private Long id;

	@NotNull @Column( name = "SEQUENCIA_TRANSACAO" ) @Getter private String sequencia_transacao;

	@NotNull @Column( name = "NOME_TRANSACAO" ) @Getter private String nome;

	@NotNull @Column( name = "INTEGRADOR" ) @Getter private String integrador;

	@NotNull @Column( name = "INICIO_TRANSACAO" ) @Getter private LocalDate inicioTransacao;

	@NotNull @Column( name = "FIM_TRANSACAO" ) @Getter private LocalDate fimTransacao;

	@NotNull @Column( name = "ORIGEM" ) @Getter private String origem;

	@NotNull @Column( name = "DESTINO" ) @Getter private String destino;

	@NotNull @Column( name = "DESCRICAO" ) @Getter private String descricao;

	@NotNull @Column( name = "STATUS" ) @Getter private String status;

	@NotNull @Column( name = "DETALHES" ) @Getter private String detalhes;

	protected Log( )
	{
	}

	public Log( Long id, String sequencia, String nome, String integrador,
			LocalDate inicioTransacao, LocalDate fimTransacao, String origem, String destino,
			String descricao, String status, String detalhes )
	{
		this.id = id;
		this.nome = nome;
		this.sequencia_transacao = sequencia;
		this.nome = nome;
		this.integrador = integrador;
		this.inicioTransacao = inicioTransacao;
		this.fimTransacao = fimTransacao;
		this.origem = origem;
		this.destino = destino;
		this.descricao = descricao;
		this.status = status;
		this.detalhes = detalhes;
	}

	public Log(Long id, String sequencia, String nome, String integrador,
			String inicioTransacao, String fimTransacao, String origem, String destino,
			String descricao, String status, String detalhes )
	{
		try
		{
			/// 2018-04-25 16:36:12
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			this.id = id;

			this.sequencia_transacao = sequencia.trim();
			this.nome = nome.trim();
			this.integrador = integrador.trim();
			this.inicioTransacao =  LocalDate.parse(inicioTransacao.trim().substring( 0, 19 ).replace( '_', ' ' ), formatter);
			this.fimTransacao = LocalDate.parse(fimTransacao.trim().substring( 0, 19 ).replace( '_', ' ' ), formatter);
			this.origem = origem.trim();
			this.destino = destino.trim();
			this.descricao = descricao;
			this.status = status.trim();
			this.detalhes = detalhes;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}