package br.com.dpsp.app.log.repository;

import br.com.dpsp.app.log.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaFiscalRepository extends JpaRepository<Log, Long> {

    Log findByEmitenteENumero(String numeroNf, Long idEmpresaEmitente);

}
