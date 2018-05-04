package br.com.dpsp.app;

import br.com.dpsp.ProcessadorAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import br.com.dpsp.app.log.service.NotaFiscalService;

@Component
public class ArquivoNotaFiscalConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(ArquivoNotaFiscalConsumer.class);

    @Autowired
    private NotaFiscalService notaFiscalService;

    @JmsListener(destination = ProcessadorAppConfig.NOTA_FISCALQUEUE)
    public void receive(String xml) {
        LOGGER.debug("Iniciando processamento arquivo");
        notaFiscalService.processar(xml);
        LOGGER.debug("Finalizando processamento arquivo");
    }

}
