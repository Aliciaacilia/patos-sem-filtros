package repository;

import java.sql.*;
import databaseconfig.DatabaseConfig;

public class CurtidaRepository {

    public void salvar(int denunciaId, int usuarioId) {
        String sql = "INSERT INTO curtidas (denuncia_id, usuario_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, denunciaId);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int contarCurtidas(int denunciaId) {
        String sql = "SELECT COUNT(*) FROM curtidas WHERE denuncia_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, denunciaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean usuarioCurtiu(int denunciaId, int usuarioId) {
        String sql = "SELECT 1 FROM curtidas WHERE denuncia_id = ? AND usuario_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, denunciaId);
            stmt.setInt(2, usuarioId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}