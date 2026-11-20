package pt.ecl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    public synchronized void adicionar(String nome, String sala,
            String equipamento, String descricao) throws SQLException {
        String sql = "INSERT INTO ocorrencias (nome, sala, tipo, equipamento, descricao) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = BaseDados.ligar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome); ps.setString(2, sala);
            ps.setString(3, "Equipamento"); ps.setString(4, equipamento);
            ps.setString(5, descricao); ps.executeUpdate();
        }
    }

    public synchronized void adicionarAvaria(String sala, String tipo,
            String descricao) throws SQLException {
        String sql = "INSERT INTO ocorrencias (nome, sala, tipo, equipamento, descricao) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = BaseDados.ligar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "Não indicado"); ps.setString(2, sala);
            ps.setString(3, tipo); ps.setString(4, tipo);
            ps.setString(5, descricao); ps.executeUpdate();
        }
    }

    public synchronized List<Pedido> listar() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT id, nome, sala, equipamento, descricao, DATE_FORMAT(data_registo, '%d/%m/%Y %H:%i') AS data_texto, estado, observacao FROM ocorrencias ORDER BY id DESC";
        try (Connection c = BaseDados.ligar(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) pedidos.add(new Pedido(rs.getInt("id"), rs.getString("nome"),
                rs.getString("sala"), rs.getString("equipamento"), rs.getString("descricao"),
                rs.getString("data_texto"), rs.getString("estado"), rs.getString("observacao")));
        }
        return pedidos;
    }

    public synchronized boolean atualizar(int id, String estado, String observacao) throws SQLException {
        String sql = "UPDATE ocorrencias SET estado = ?, observacao = ? WHERE id = ?";
        try (Connection c = BaseDados.ligar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, estado); ps.setString(2, observacao); ps.setInt(3, id);
            return ps.executeUpdate() == 1;
        }
    }
}
