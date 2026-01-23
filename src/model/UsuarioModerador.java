package model;

import model.Usuario;

public class UsuarioModerador extends Usuario {

    private String nivelAcesso;

    public UsuarioModerador() {
        super();
    }

    public UsuarioModerador(int id, String nome, String email, String senha,
                             boolean emailVerificado, String tipo, String nivelAcesso) {
        super(id, nome, email, senha, emailVerificado, tipo);
        this.nivelAcesso = nivelAcesso;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}