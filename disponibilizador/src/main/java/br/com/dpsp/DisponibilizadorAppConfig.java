package br.com.dpsp;

import br.com.dpsp.app.arquivo.controller.ArquivoLogController;
import br.com.dpsp.app.log.controller.LogController;
import br.com.dpsp.app.log.repository.LogRepository;
import br.com.dpsp.app.log.service.LogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication( exclude = MongoAutoConfiguration.class ) @ComponentScan( basePackageClasses = {
		ArquivoLogController.class, MongoDBPropertiesConfig.class, LogRepository.class,
		LogService.class, LogController.class } ) public class DisponibilizadorAppConfig
{

	public static void main( String[] args )
	{
		SpringApplication.run( DisponibilizadorAppConfig.class, args );
	}
}
