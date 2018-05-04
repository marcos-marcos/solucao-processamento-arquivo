package br.com.dpsp;

import br.com.dpsp.app.log.repository.LogTraceRepository;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import br.com.dpsp.app.ArquivoLogConsumer;
import br.com.dpsp.app.log.model.Log;

import javax.jms.Queue;

@SpringBootApplication @ComponentScan( basePackageClasses = {
		ArquivoLogConsumer.class } ) @EntityScan( basePackageClasses = { Log.class,
		Jsr310JpaConverters.class } ) @EnableJpaRepositories( basePackageClasses = {
		LogTraceRepository.class } ) @EnableJms public class ProcessadorAppConfig
{

	public static final String LOGS_QUEUE = "logs.queue";
	public static final String LOGS_ERRO_QUEUE = "logs.erro.queue";

	public static void main( String[] args )
	{
		SpringApplication.run( ProcessadorAppConfig.class, args );
	}

	@Bean public Queue queue( )
	{
		return new ActiveMQQueue( LOGS_ERRO_QUEUE );
	}
}
