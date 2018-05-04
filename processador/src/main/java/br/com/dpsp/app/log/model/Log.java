package br.com.dpsp.app.notafiscal.model;

import br.com.dpsp.app.notafiscal.xml.NfeICMSTotXml;
import br.com.dpsp.app.notafiscal.xml.NfeIdentificacaoXml;
import br.com.dpsp.app.notafiscal.xml.NfeProdutoXml;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table( name = "TRACING", uniqueConstraints = @UniqueConstraint( columnNames = {
		"id" }, name = "id_uk" ), indexes = {
		@Index( columnList = "sequencia", name = "sequencia_idx" ) } ) @NamedQueries( value = {
		@NamedQuery( name = "Log.findBySequenciaOrigem", query = "select * from Tracing log where log.sequencia = ?1 and log.origem = ?2" ) } ) public class Log
{

	@Id @GeneratedValue( strategy = GenerationType.IDENTITY ) @Getter private Long id;

	@NotNull @Column( name = "SEQUENCIA_TRANSACAO" ) @Getter private String sequencia;

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
		this.id = id this.nome = nome;
		this.sequencia = sequencia ;
		this.nome = nome ;
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

