package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Comentario;
import databaseconfig.DatabaseConfig;

public class ComentarioRepository {

    public void salvar(Comentario comentario) {
        String sql = "INSERT INTO comentarios (denuncia_id, usuario_id, texto, data) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, comentario.getDenunciaId());
            stmt.setInt(2, comentario.getUsuarioId());
            stmt.setString(3, comentario.getTexto());
            stmt.setTimestamp(4, Timestamp.valueOf(comentario.getData()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comentario> listarPorDenuncia(int denunciaId) {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM comentarios WHERE denuncia_id = ? ORDER BY data DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, denunciaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comentario c = new Comentario(
                    rs.getInt("comentario_id"),
                    rs.getInt("denuncia_id"),
                    rs.getInt("usuario_id"),
                    rs.getString("texto")
                );
                comentarios.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }
}