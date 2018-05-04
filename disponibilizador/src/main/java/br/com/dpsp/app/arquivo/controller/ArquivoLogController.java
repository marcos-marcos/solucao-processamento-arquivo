package br.com.dpsp.app.arquivo.controller;

import br.com.dpsp.app.arquivo.service.ArquivoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping( path = "/arquivos" ) public class ArquivoLogController
{

	@Autowired private ArquivoLogService arquivoService;

	@RequestMapping( method = RequestMethod.GET ) public ResponseEntity findAll( )
	{
		return ResponseEntity.ok( arquivoService.findAll( ) );
	}

	@RequestMapping( method = RequestMethod.GET, value = "/{id}" ) public ResponseEntity findById(
			@PathVariable( "id" ) String id )
	{
		return ResponseEntity.ok( arquivoService.findById( id ) );
	}

}
