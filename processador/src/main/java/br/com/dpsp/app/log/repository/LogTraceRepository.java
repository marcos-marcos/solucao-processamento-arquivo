package br.com.dpsp.app.log.repository;

import br.com.dpsp.app.log.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogTraceRepository extends JpaRepository<Log, Long>
{

//	Log findBySequencia_Transacao( String sequencia, Long origem );

}
