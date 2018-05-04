package br.com.dpsp.app.service;

import br.com.dpsp.app.model.NotaFiscalXml;
import br.com.dpsp.app.repository.NotaFiscalXmlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

@Service public class NotaFiscalXmlService
{

	@Autowired private NotaFiscalXmlRepository notaFiscalXmlRepository;

	public void salvar( Message<Object> message )
	{
		final MessageHeaders headers = message.getHeaders( );
		notaFiscalXmlRepository.save( new NotaFiscalXml( ( String ) headers.get( "file_name" ),
				( String ) message.getPayload( ) ) );
	}

}
