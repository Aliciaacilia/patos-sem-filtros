package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import databaseconfig.DatabaseConfig;
import model.Usuario;

public class UsuarioRepository {

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha, email_verificado, tipo_id) " +
                     "VALUES (?, ?, ?, false, (SELECT tipo_id FROM tipos_usuario WHERE nome_tipo = ?))";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            if (usuario.getTipo() != null) {
            String tipoOriginal = usuario.getTipo().trim();
            String tipoFormatado = tipoOriginal.substring(0, 1).toUpperCase() + tipoOriginal.substring(1).toLowerCase();
            stmt.setString(4, tipoFormatado);
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT usuario_id, nome, email, senha, email_verificado, tipo_id " +
                     "FROM usuarios WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getBoolean("email_verificado"),
                    String.valueOf(rs.getInt("tipo_id"))
                );
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT usuario_id, nome, email, senha, email_verificado, tipo_id " +
                     "FROM usuarios WHERE usuario_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getBoolean("email_verificado"),
                    String.valueOf(rs.getInt("tipo_id"))
                );
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}