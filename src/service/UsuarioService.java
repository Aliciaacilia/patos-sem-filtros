package service;

import model.Usuario;
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
}

