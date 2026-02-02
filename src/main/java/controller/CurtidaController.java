package controller;

import java.sql.*;
import databaseconfig.DatabaseConfig;

public class CurtidaController {

    public CurtidaController() {
        
    }

    public int contarCurtidas(int denunciaId) {
        String sql = "SELECT COUNT(*) FROM curtidas_denuncia WHERE denuncia_id = ?";
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

    public void curtirDenuncia(int denunciaId, int usuarioMoradorId) {
        if (!this.usuarioCurtiu(denunciaId, usuarioMoradorId)) {
            
            String sql = "INSERT INTO curtidas_denuncia (denuncia_id, usuario_morador_id) VALUES (?, ?)";
            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, denunciaId);
                stmt.setInt(2, usuarioMoradorId);
                stmt.executeUpdate();
                
                System.out.println("Você curtiu esta denúncia!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Atenção: Você já curtiu esta denúncia.");
        }
    }

    private boolean usuarioCurtiu(int denunciaId, int usuarioMoradorId) {
        String sql = "SELECT 1 FROM curtidas_denuncia WHERE denuncia_id = ? AND usuario_morador_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, denunciaId);
            stmt.setInt(2, usuarioMoradorId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}