package br.com.dpsp.app.log.service;

import br.com.dpsp.app.log.dto.LogDTO;
import br.com.dpsp.app.log.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class LogService
{

	@Autowired private LogRepository logRepository;

	public LogDTO findBySequencia( String sequencia )
	{
		return logRepository
				.findBySequencia( sequencia );
	}

	public LogDTO findBySequenciaTransacao( String sequencia,
			String origem )
	{
		return logRepository
				.findBySequenciaTransacao( sequencia, origem );
	}


	public List<LogDTO> findAll( )
	{
		return logRepository.findAll( );
	}

}
