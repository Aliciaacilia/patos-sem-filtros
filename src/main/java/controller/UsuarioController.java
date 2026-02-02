package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import databaseconfig.DatabaseConfig;
import model.Usuario;
import model.UsuarioModerador;
import model.UsuarioMorador;

public class UsuarioController {

    public UsuarioController() {
    }

    public void cadastrar(String nome, String email, String senha, String tipo) {
        if (buscarPorEmail(email) != null) {
            System.out.println("Erro: Email já cadastrado.");
            return;
        }

        Usuario usuario;
        switch (tipo.toUpperCase()) {
            case "MORADOR":
                usuario = new UsuarioMorador();
                break;
            case "MODERADOR":
                usuario = new UsuarioModerador();
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido");
        }

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(String.valueOf(senha.hashCode()));
        usuario.setTipo(tipo);

        String sql = "INSERT INTO usuarios (nome, email, senha, email_verificado, tipo_id) " +
                     "VALUES (?, ?, ?, false, (SELECT tipo_id FROM tipos_usuario WHERE nome_tipo = ?))";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            
            String tipoFormatado = tipo.substring(0, 1).toUpperCase() + tipo.substring(1).toLowerCase();
            stmt.setString(4, tipoFormatado);
            
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }

            if (usuario instanceof UsuarioMorador || "Morador".equalsIgnoreCase(tipo)) {
                salvarPerfilMorador(usuario.getId(), usuario.getNome());
            }

            System.out.println("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro técnico ao cadastrar usuário.");
        }
    }

    public Usuario fazerLogin(String email, String senha) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario != null) {
            String senhaDigitadaHash = String.valueOf(senha.hashCode());
            if (usuario.getSenha().equals(senhaDigitadaHash)) {
                return usuario;
            }
        }
        return null;
    }

    private Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("usuario_id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getBoolean("email_verificado"),
                    String.valueOf(rs.getInt("tipo_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void salvarPerfilMorador(int usuarioId, String nome) {
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

    public void atualizarAnonimato(int usuarioId, boolean anonimo) {
        System.out.println("Anonimato atualizado para: " + anonimo);
    }
}