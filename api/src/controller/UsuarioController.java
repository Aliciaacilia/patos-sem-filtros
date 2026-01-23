package controller;

import model.Usuario;
import service.UsuarioService;

public class UsuarioController {

    private UsuarioService service = new UsuarioService();

    public void cadastrar(String nome, String email, String senha, String tipo) {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTipo(tipo);

        boolean sucesso = service.cadastrar(usuario);

        if (sucesso) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Email já cadastrado.");
        }
    }
}
