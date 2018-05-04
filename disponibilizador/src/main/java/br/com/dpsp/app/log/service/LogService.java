package br.com.dpsp.app.log.service;

import br.com.dpsp.app.log.dto.NotaFiscalDTO;
import br.com.dpsp.app.log.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalDTO findByCnpjEmitenteENumeroNotaFiscal(String cnpjEmitente, String numeroNotaFiscal) {
        return notaFiscalRepository.findByCnpjEmitenteENumeroNotaFiscal(cnpjEmitente, numeroNotaFiscal);
    }

    public List<NotaFiscalDTO> findAll() {
        return notaFiscalRepository.findAll();
    }

}
