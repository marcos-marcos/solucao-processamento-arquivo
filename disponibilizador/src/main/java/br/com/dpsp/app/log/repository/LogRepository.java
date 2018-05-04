package br.com.dpsp.app.log.repository;

import br.com.dpsp.app.log.dto.LogDTO;
import br.com.dpsp.app.log.mapper.LogResultSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public class LogRepository
{

	@Autowired private JdbcTemplate jdbcTemplate;

	public List<LogDTO> findAll( )
	{
		return jdbcTemplate.query( "SELECT * FROM tracing as log  order by inicio_transacao desc",
				LogResultSetExtractor.listExtractor( ) );
	}

	public LogDTO findBySequencia( String sequencia )
	{
		return jdbcTemplate.query( "SELECT * FROM tracing as log "
						+ " where sequencia_transacao = ? ",
				new Object[] { sequencia }, LogResultSetExtractor.entityExtractor( ) );
	}

	public LogDTO findBySequenciaTransacao( String sequencia, String origem )
	{
		return jdbcTemplate.query( "SELECT * FROM tracing as log "
						+ " where sequencia_transacao = ? and origem = ?",
				new Object[] { sequencia, origem }, LogResultSetExtractor.entityExtractor( ) );
	}
}
