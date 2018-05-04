package br.com.dpsp.app.log.mapper;

import br.com.dpsp.app.log.dto.LogDTO;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class LogResultSetExtractor
{

	public static ResultSetExtractor<List<LogDTO>> listExtractor( )
	{
		return resultSet ->
		{
			final Map<Long, LogDTO> logsPorId = extractor( resultSet );
			return new ArrayList<>( logsPorId.values( ) );
		};
	}

	public static ResultSetExtractor<LogDTO> entityExtractor( )
	{
		return resultSet ->
		{
			final Map<Long, LogDTO> logsPorId = extractor( resultSet );
			return logsPorId.values( ).stream( ).findAny( ).orElse( null );
		};
	}

	private static Map<Long, LogDTO> extractor( ResultSet resultSet ) throws SQLException
	{
		final Map<Long, LogDTO> logsPorId = new TreeMap<>( );

		while (resultSet.next( ))
		{
			final Long idLog = resultSet.getLong( "sequencia_transacao" );
			LogDTO log = logsPorId.get( idLog );

			if (log == null)
			{
				log = new LogDTO( resultSet.getBigDecimal( "id" ),
						resultSet.getString( "sequencia_transacao" ),
						resultSet.getString( "nome_transacao" ),
						resultSet.getString( "integrador" ),
						resultSet.getDate( "inicio_transacao" ).toLocalDate( ),
						resultSet.getDate( "fim_transacao" ).toLocalDate( ),
						resultSet.getString( "origem" ), resultSet.getString( "destino" ),
						resultSet.getString( "descricao" ), resultSet.getString( "status" ),
						resultSet.getString( "detalhes" ) );

				logsPorId.put( idLog, log );
			}
		}
		return logsPorId;
	}
}
