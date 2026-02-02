package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import databaseconfig.DatabaseConfig;
import model.Denuncia;

public class DenunciaController {

    public DenunciaController() {

    }

    public void registrarDenuncia(int usuarioMoradorId, String descricao, String status,
                                  String visibilidade, String foto, String video, int categoriaId) {
        
        String sql = "INSERT INTO denuncias (usuario_morador_id, descricao, status, visibilidade, foto, video, categoria_id, data_hora) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioMoradorId);
            stmt.setString(2, descricao);
            stmt.setString(3, status);
            stmt.setString(4, visibilidade);
            stmt.setString(5, foto);
            stmt.setString(6, video);
            stmt.setInt(7, categoriaId);
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();
            System.out.println("Denúncia registrada com sucesso no sistema!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar denúncia.");
            e.printStackTrace();
        }
    }

    public List<Denuncia> minhasDenuncias(int usuarioMoradorId) {
        List<Denuncia> resultado = new ArrayList<>();
        String sql = "SELECT * FROM denuncias WHERE usuario_morador_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, usuarioMoradorId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                resultado.add(extrairDenuncia(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public List<Denuncia> listarDenuncias() {
        List<Denuncia> denuncias = new ArrayList<>();
        String sql = "SELECT * FROM denuncias ORDER BY data_hora DESC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                denuncias.add(extrairDenuncia(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return denuncias;
    }

    public Denuncia buscarPorId(int id) {
        String sql = "SELECT * FROM denuncias WHERE denuncia_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extrairDenuncia(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Denuncia extrairDenuncia(ResultSet rs) throws SQLException {
        return new Denuncia(
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
    }

    public List<Denuncia> listarDenunciasComFiltro(String status, int categoriaId) {
    List<Denuncia> denuncias = new ArrayList<>();
    
    String sql = "SELECT * FROM denuncias WHERE " +
                 "(? = '' OR status = ?) AND " +
                 "(? = 0 OR categoria_id = ?) " +
                 "ORDER BY data_hora DESC";

    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, status);   
        stmt.setString(2, status);  
        
        stmt.setInt(3, categoriaId); 
        stmt.setInt(4, categoriaId); 

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                denuncias.add(extrairDenuncia(rs));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return denuncias;
}
}