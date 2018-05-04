package br.com.dpsp.app.repository;

import br.com.dpsp.app.model.NotaFiscalXml;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotaFiscalXmlRepository extends MongoRepository<NotaFiscalXml, String>
{
}
