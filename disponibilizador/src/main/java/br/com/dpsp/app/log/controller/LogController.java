package br.com.dpsp.app.log.controller;

import br.com.dpsp.app.log.dto.LogDTO;
import br.com.dpsp.app.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping( path = "log" ) public class LogController
{

	@Autowired private LogService logService;

	@RequestMapping( method = RequestMethod.GET, value = "/{sequencia}" ) public ResponseEntity findByCnpjEmitenteENumeroNotaFiscal(
			@PathVariable( "sequencia" ) String sequencia )
	{
		final LogDTO logDTO = logService
				.findBySequencia( sequencia );
		return logDTO != null ?
				ResponseEntity.ok( logDTO ) :
				ResponseEntity.badRequest( ).body( "log não encontrada" );
	}

	@RequestMapping( method = RequestMethod.GET, value = "/{sequencia}/{origem}" ) public ResponseEntity findByCnpjEmitenteENumeroNotaFiscal(
			@PathVariable( "sequencia" ) String sequencia,
			@PathVariable( "origem" ) String origem )
	{
		final LogDTO logDTO = logService
				.findBySequenciaTransacao( sequencia, origem );
		return logDTO != null ?
				ResponseEntity.ok( logDTO ) :
				ResponseEntity.badRequest( ).body( "log não encontrada" );
	}


	@RequestMapping( method = RequestMethod.GET ) public ResponseEntity findAll( )
	{
		return ResponseEntity.ok( logService.findAll( ) );
	}

}
