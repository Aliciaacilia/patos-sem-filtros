package service;

import repository.UsuarioRepository;
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
}
