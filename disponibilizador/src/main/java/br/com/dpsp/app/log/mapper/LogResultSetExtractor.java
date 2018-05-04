package br.com.dpsp.app.log.mapper;

import br.com.dpsp.app.log.dto.EmpresaDTO;
import br.com.dpsp.app.log.dto.ItemNotaFiscalDTO;
import br.com.dpsp.app.log.dto.LogDTO;
import br.com.dpsp.app.log.dto.ProdutoDTO;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class NotaFiscalResultSetExtractor {

    public static ResultSetExtractor<List<LogDTO>> listExtractor() {
        return resultSet -> {
            final Map<Long, LogDTO> notasPorId = extractor(resultSet);
            return new ArrayList<>(notasPorId.values());
        };
    }

    public static ResultSetExtractor<LogDTO> entityExtractor() {
        return resultSet -> {
            final Map<Long, LogDTO> notasPorId = extractor(resultSet);
            return notasPorId.values().stream().findAny().orElse(null);
        };
    }

    private static Map<Long, LogDTO> extractor(ResultSet resultSet) throws SQLException {
        final Map<Long, LogDTO> notasPorId = new TreeMap<>();

        while(resultSet.next()) {
            final Long idNotaFiscal = resultSet.getLong("id_nf");
            LogDTO notaFiscal = notasPorId.get(idNotaFiscal);

            if(notaFiscal == null) {
                notaFiscal = new LogDTO(
                        resultSet.getString("numero_nf"),
                        resultSet.getBigDecimal("valor"),
                        resultSet.getBigDecimal("valor_desconto"),
                        resultSet.getBigDecimal("valor_total"),
                        resultSet.getDate("data_emissao").toLocalDate()
                );

                notaFiscal.relacionarEmitente(new EmpresaDTO(
                        resultSet.getString("cnpj_emitente"),
                        resultSet.getString("nome_emitente")
                ));

                notaFiscal.relacionarDestinatario(new EmpresaDTO(
                        resultSet.getString("cnpj_destinatario"),
                        resultSet.getString("nome_destinatario")
                ));
            }

            notaFiscal.adicionarItem(new ItemNotaFiscalDTO(
                    resultSet.getString("numero_pedido"),
                    resultSet.getInt("numero_item"),
                    resultSet.getBigDecimal("quantidade"),
                    new ProdutoDTO(
                            resultSet.getString("codigo"),
                            resultSet.getString("descricao"),
                            resultSet.getString("unidade")
                    ),
                    resultSet.getBigDecimal("valor_produto"),
                    resultSet.getBigDecimal("valor_total")
            ));

            notasPorId.put(idNotaFiscal, notaFiscal);
        }
        return notasPorId;
    }
}
