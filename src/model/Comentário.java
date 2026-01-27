package model;

import java.time.LocalDateTime;

public class Comentario {
    private int id;
    private int denunciaId;
    private int usuarioId;
    private String texto;
    private LocalDateTime data;

    public Comentario(int id, int denunciaId, int usuarioId, String texto) {
        this.id = id;
        this.denunciaId = denunciaId;
        this.usuarioId = usuarioId;
        this.texto = texto;
        this.data = LocalDateTime.now();
    }

    // Getters
    public int getId() { return id; }
    public int getDenunciaId() { return denunciaId; }
    public int getUsuarioId() { return usuarioId; }
    public String getTexto() { return texto; }
    public LocalDateTime getData() { return data; }
}