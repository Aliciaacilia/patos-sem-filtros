package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import databaseconfig.DatabaseConfig;
import model.Comentário;

public class ComentarioController {

    public ComentarioController() {
    }

    public List<Comentário> listarComentarios(int denunciaId) {
    List<Comentário> comentarios = new ArrayList<>();

    String sql = "SELECT c.*, m.nome_perfil FROM comentarios_denuncia c " +
                 "JOIN usuarios_moradores m ON c.usuario_morador_id = m.usuario_morador_id " +
                 "WHERE c.denuncia_id = ? ORDER BY c.data_hora DESC";
    
    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, denunciaId);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Comentário c = new Comentário(
                rs.getInt("comentario_id"),
                rs.getInt("denuncia_id"),
                rs.getInt("usuario_morador_id"),
                rs.getString("comentario"),
                rs.getTimestamp("data_hora").toLocalDateTime()
            );

            c.setNomeAutor(rs.getString("nome_usuario")); 
            comentarios.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return comentarios;
}

    public void adicionarComentario(int denunciaId, int usuarioMoradorId, String texto) {

        Comentário comentario = new Comentário(denunciaId, usuarioMoradorId, texto);
        
        String sql = "INSERT INTO comentarios_denuncia (denuncia_id, usuario_morador_id, comentario, data_hora) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, comentario.getDenunciaId());
            stmt.setInt(2, comentario.getUsuarioMoradorId());
            stmt.setString(3, comentario.getComentario());
            stmt.setTimestamp(4, Timestamp.valueOf(comentario.getDataHora()));
            
            stmt.executeUpdate();
            System.out.println("Comentário salvo com sucesso: " + texto);
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar comentário no banco!");
            e.printStackTrace();
        }
    }
}