package br.com.dpsp.app.log.service;

import br.com.dpsp.app.log.repository.NotaFiscalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.dpsp.app.ArquivoLogSender;
import br.com.dpsp.app.exception.BusinessException;
import br.com.dpsp.app.exception.FileException;
import br.com.dpsp.app.log.model.Log;
import br.com.dpsp.app.util.XmlRead;
import br.com.dpsp.app.log.xml.NfeDetXml;
import br.com.dpsp.app.log.xml.NfeICMSTotXml;
import br.com.dpsp.app.log.xml.NfeIdentificacaoXml;
import br.com.dpsp.app.log.xml.NfeInfoXml;
import br.com.dpsp.app.log.xml.NfeProcXml;
import br.com.dpsp.app.log.xml.NfeProdutoXml;

import java.util.Map;

@Service
@Slf4j
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ArquivoLogSender arquivoSender;

    public void processar(String arquivoNotaFiscal) {
        try {
            final NfeProcXml xmlNotaFiscal = XmlRead.read(arquivoNotaFiscal);
            salvar(xmlNotaFiscal);
        } catch (FileException bex) {
            log.error("Erro no parser do arquivo", bex);
        } catch (BusinessException bex) {
            log.warn("Nota Fiscal já existente", bex);
        } catch (Exception ex) {
            enviarNotaFiscalParaFilaDeErro(arquivoNotaFiscal);
            log.error("Erro processar notaFisca", ex);
        }
    }

    @Transactional
    public void salvar(NfeProcXml xml) {
        final NfeInfoXml info = xml.getInfo();
        final NfeIdentificacaoXml identificacao = info.getIdentificacao();
        final NfeICMSTotXml total = info.getIcmsTotXml();

        final Empresa emitente = empresaService.buscaEmitenteSenaoCria(info);
        final Empresa destinatario = empresaService.buscaDestinatarioSenaoCria(info);

        validaNotaExistente(identificacao.getnNF(), emitente);

        final Log log = new Log(identificacao, total, emitente, destinatario);

        final Map<String, Produto> produtosExistentesPorCodigo = produtoService.findProdutoPorCodigoByfornecedor(info.getDets(), emitente);

        for(NfeDetXml det : info.getDets()) {
            for(NfeProdutoXml produtoXml : det.getProdutos()) {
                final Produto produto = produtoService.getProdutoSenaoCria(produtosExistentesPorCodigo, produtoXml, emitente);
                log.addItem(produtoXml, produto);
            }
        }

        notaFiscalRepository.save( log );
    }

    private void validaNotaExistente(String numeroNf, Empresa emitente) {
        final Log log = notaFiscalRepository.findByEmitenteENumero(numeroNf, emitente.getId());
        if(log != null) {
            throw new BusinessException(String.format("O Número %s de Nota fiscal já existente para o emitente %s (%s)",
                    numeroNf, emitente.getNome(), emitente.getCnpj()));
        }
    }

    private void enviarNotaFiscalParaFilaDeErro(String xml) {
        arquivoSender.send(xml);
    }

}
