package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    protected int id;
    protected String nome;
    protected String email;
    protected String senha;
    protected boolean emailVerificado;
    protected String tipo;
    protected boolean anonimo;

    // Histórico de denúncias
    private List<Denuncia> denuncias = new ArrayList<>();

    public Usuario() {}

    public Usuario(int id, String nome, String email, String senha, boolean emailVerificado, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.emailVerificado = emailVerificado;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return anonimo ? "Anônimo" : nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean isEmailVerificado() { return emailVerificado; }
    public void setEmailVerificado(boolean emailVerificado) { this.emailVerificado = emailVerificado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean isAnonimo() { return anonimo; }
    public void setAnonimo(boolean anonimo) { this.anonimo = anonimo; }

    // Histórico de denúncias
    public void adicionarDenuncia(Denuncia denuncia) {
        denuncias.add(denuncia);
    }

    public List<Denuncia> getDenuncias() {
        return denuncias;
    }
}