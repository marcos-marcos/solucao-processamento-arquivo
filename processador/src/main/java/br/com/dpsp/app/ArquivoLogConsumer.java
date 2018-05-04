package br.com.dpsp.app;

import br.com.dpsp.ProcessadorAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import br.com.dpsp.app.log.service.LogTraceService;

@Component public class ArquivoLogConsumer
{

	private Logger LOGGER = LoggerFactory.getLogger( ArquivoLogConsumer.class );

	@Autowired private LogTraceService logService;

	@JmsListener( destination = ProcessadorAppConfig.LOGS_QUEUE ) public void receive( String data )
	{
		LOGGER.debug( "Iniciando processamento arquivo" );
		logService.processar( data );
		LOGGER.debug( "Finalizando processamento arquivo" );
	}

}
