public class UsuarioMorador extends Usuario {

    private String cpf;

    public UsuarioMorador() {
        super();
    }

    public UsuarioMorador(int id, String nome, String email, String senha,
                           boolean emailVerificado, String tipo, String cpf) {
        super(id, nome, email, senha, emailVerificado, tipo);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}