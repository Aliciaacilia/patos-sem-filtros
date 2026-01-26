package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import databaseconfig.DatabaseConfig;
import model.Usuario;
import model.UsuarioMorador;
import repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    public boolean cadastrar(Usuario usuario) {
        
    if (repository.buscarPorEmail(usuario.getEmail()) != null) {
        return false;
    }

    String senhaHash = String.valueOf(usuario.getSenha().hashCode());
    usuario.setSenha(senhaHash);

    repository.salvar(usuario);

    if (usuario instanceof UsuarioMorador || "Morador".equalsIgnoreCase(usuario.getTipo())) {
        
        String sqlMorador = "INSERT INTO usuarios_moradores (usuario_id, nome_perfil) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlMorador)) {
            
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNome());   
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    return true;
}

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = repository.buscarPorEmail(email);
        if (usuario != null) {
            String senhaDigitadaHash = String.valueOf(senha.hashCode());
            if (usuario.getSenha().equals(senhaDigitadaHash)) {
                return usuario;
            }
        }
        return null;
    }

    public void atualizarAnonimato(int usuarioId, boolean anonimo) {
        Usuario usuario = repository.buscarPorId(usuarioId);
        if (usuario != null) {
            usuario.setAnonimo(anonimo);
        }
    }
} 