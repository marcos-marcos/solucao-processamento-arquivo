package br.com.dpsp.app.arquivo.repository;

import br.com.dpsp.MongoDBPropertiesConfig;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component public class MongoDBRepository
{

	private static final String COLLECTION_ARQUIVOS = "logUnificado";

	@Autowired private MongoDBPropertiesConfig properties;

	public Iterable<Document> find( )
	{
		return getDatabase( ).getCollection( COLLECTION_ARQUIVOS ).find( );
	}

	public Document findFirst( Document document )
	{
		return getDatabase( ).getCollection( COLLECTION_ARQUIVOS ).find( document ).first( );
	}

	private MongoDatabase getDatabase( )
	{
		return client( ).getDatabase( properties.getDataBase( ) );
	}

	private MongoClient client( )
	{
		return new MongoClient( properties.getHost( ), properties.getPort( ) );
	}

}
