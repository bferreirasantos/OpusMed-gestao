package com.example.oracleapi;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MovimentoService {
    @Autowired
    private DataSource dataSource;

    public Map<String, Object> updateMovimento(MovimentoDTO dto) throws SQLException {
        Map<String, Object> response = new HashMap<>();

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call T09E_MOVIMENTO_POST(?, ?, ?, ?, ?)}")) {

            stmt.setString(1, dto.nomeColaborador);
            stmt.setString(2, dto.codigoBarras);
            stmt.setDate(3, java.sql.Date.valueOf(dto.data));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(dto.horarioSaida));
            stmt.setInt(5, dto.quantidadeSaida);

            stmt.execute();

            response.put("success", true);
            response.put("message", ",Movimento inserido com sucesso");
        } catch (SQLException e) {
            response.put("success", false);
            response.put("message", "Erro ao inserir movimento: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> inserirMovimento(MovimentoDTO dto) throws SQLException {
        Map<String, Object> response = new HashMap<>();

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call T09E_MOVIMENTO_POST(?, ?, ?, ?, ?)}")) {

            stmt.setString(1, dto.nomeColaborador);
            stmt.setString(2, dto.codigoBarras);
            stmt.setDate(3, java.sql.Date.valueOf(dto.data));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(dto.horarioSaida));
            stmt.setInt(5, dto.quantidadeSaida);

            stmt.execute();

            response.put("success", true);
            response.put("message", ",Movimento inserido com sucesso");
        } catch (SQLException e) {
            response.put("success", false);
            response.put("message", "Erro ao inserir movimento: " + e.getMessage());
        }

        return response;
    }

    public List<Map<String, Object>> buscarMovimento(String data) throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call T09E_MOVIMENTO_GET(?, ?)}")) {

            stmt.setString(1, data);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();

                    for (int i = 1; i <= colCount; i++) {
                        Object value = rs.getObject(i);

                        // Converte oracle.sql.TIMESTAMP (e outros tipos problemáticos)
                        if (value instanceof oracle.sql.TIMESTAMP) {
                            value = ((oracle.sql.TIMESTAMP) value).timestampValue().toLocalDateTime();
                        } else if (value instanceof java.sql.Timestamp) {
                            value = ((Timestamp) value).toLocalDateTime();
                        } else if (value instanceof Blob) {
                            value = "[BLOB]";
                        } else if (value instanceof Clob) {
                            value = "[CLOB]";
                        }

                        row.put(meta.getColumnLabel(i), value);
                    }

                    lista.add(row);
                }
            }

            return lista;
        }
    }

    public Map<String, Object> updateMovimento(int id,MovimentoDTO dto) throws SQLException {
        Map<String, Object> response = new HashMap<>();

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call T09E_MOVIMENTO_UPDATE(?, ?, ?)}")) {

            stmt.setInt(1, id);
            stmt.setInt(2, dto.quantidadeEntrada);
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(dto.horarioEntrada));

            stmt.execute();

            response.put("success", true);
            response.put("message", ",Movimento atualizado com sucesso");
        } catch (SQLException e) {
            response.put("success", false);
            response.put("message", "Erro ao atualizar movimento: " + e.getMessage());
        }

        return response;
    }

}