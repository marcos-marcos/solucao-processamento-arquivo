package br.com.dpsp.app.log.service;

import br.com.dpsp.app.log.repository.LogTraceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.dpsp.app.ArquivoLogSender;
import br.com.dpsp.app.log.model.Log;

import java.util.StringTokenizer;

@Service @Slf4j public class LogTraceService
{

	@Autowired private LogTraceRepository logRepository;

	@Autowired private ArquivoLogSender arquivoSender;

	public void processar( String data )
	{
		try
		{
			salvar( data );
		}
		catch (Exception ex)
		{
			enviarLogParaFilaDeErro( data );
		}
	}

	@Transactional
	public void salvar( String data )
	{
		String[] lines = data.split("\n");

		for (int i = 0 ; i < lines.length; i++ )
		{
			StringTokenizer tokenizer = new StringTokenizer( lines[ i ].trim(), "|" );
			while (tokenizer.hasMoreTokens( ))
			{
				String sequencia = tokenizer.nextToken( );
				String transacao = tokenizer.nextToken( );
				String integrador = tokenizer.nextToken( );
				String inicio = tokenizer.nextToken( );
				String fim = tokenizer.nextToken( );
				String origem = tokenizer.nextToken( );
				String destino = tokenizer.nextToken( );
				String descricao = tokenizer.nextToken( );
				String sucesso = tokenizer.nextToken( );
				String detalhes = tokenizer.nextToken( );

				final Log log = new Log( 0l,  sequencia, transacao, integrador, inicio, fim, origem, destino, descricao, sucesso, detalhes );
				logRepository.save( log );
			}
		}
	}

	private void enviarLogParaFilaDeErro( String data )
	{
		arquivoSender.send( data );
	}

}
