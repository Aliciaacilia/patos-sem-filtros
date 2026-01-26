package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import databaseconfig.DatabaseConfig;

public class MoradorRepository {
    public void salvarPerfilMorador(int usuarioId, String nome) {
        String sql = "INSERT INTO usuarios_moradores (usuario_id, nome_perfil) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
}
