package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Comentario;
import databaseconfig.DatabaseConfig;

public class ComentarioRepository {

    public void salvar(Comentario comentario) {
        String sql = "INSERT INTO comentarios_denuncia (denuncia_id, usuario_morador_id, comentario, data_hora) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, comentario.getDenunciaId());
            stmt.setInt(2, comentario.getUsuarioMoradorId());
            stmt.setString(3, comentario.getComentario());
            stmt.setTimestamp(4, Timestamp.valueOf(comentario.getDataHora()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comentario> listarPorDenuncia(int denunciaId) {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM comentarios_denuncia WHERE denuncia_id = ? ORDER BY data_hora DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, denunciaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comentario c = new Comentario(
                    rs.getInt("comentario_id"),
                    rs.getInt("denuncia_id"),
                    rs.getInt("usuario_morador_id"),
                    rs.getString("comentario"),
                    rs.getTimestamp("data_hora").toLocalDateTime()
                );
                comentarios.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }
}