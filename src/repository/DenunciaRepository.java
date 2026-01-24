package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Denuncia;
import databaseconfig.DatabaseConfig;

public class DenunciaRepository {

    public void salvar(Denuncia denuncia) {
        String sql = "INSERT INTO denuncias (usuario_morador_id, descricao, status, visibilidade, foto, video, categoria_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, denuncia.getUsuarioMoradorId());
            stmt.setString(2, denuncia.getDescricao());
            stmt.setString(3, denuncia.getStatus());
            stmt.setString(4, denuncia.getVisibilidade());
            stmt.setString(5, denuncia.getFoto());
            stmt.setString(6, denuncia.getVideo());
            stmt.setInt(7, denuncia.getCategoriaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Denuncia> listarPorUsuarioMorador(int usuarioMoradorId) {
        List<Denuncia> resultado = new ArrayList<>();
        String sql = "SELECT * FROM denuncias WHERE usuario_morador_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioMoradorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Denuncia d = new Denuncia(
                    rs.getInt("denuncia_id"),
                    rs.getInt("usuario_morador_id"),
                    rs.getString("descricao"),
                    rs.getTimestamp("data_hora").toLocalDateTime(),
                    rs.getString("status"),
                    rs.getString("visibilidade"),
                    rs.getString("foto"),
                    rs.getString("video"),
                    rs.getInt("categoria_id")
                );
                resultado.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}