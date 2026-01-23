package controller;

import service.UsuarioService;
import model.Usuario;          

public class UsuarioController {

    private UsuarioService service = new UsuarioService();

    public void cadastrar(String nome, String email, String senha, String tipo) {

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
