package model;

import java.time.LocalDateTime;

public class Comentário {
    private int comentarioId;
    private int denunciaId;
    private int usuarioMoradorId;
    private String comentario;
    private LocalDateTime dataHora;

    public Comentário(int comentarioId, int denunciaId, int usuarioMoradorId, String comentario, LocalDateTime dataHora) {
        this.comentarioId = comentarioId;
        this.denunciaId = denunciaId;
        this.usuarioMoradorId = usuarioMoradorId;
        this.comentario = comentario;
        this.dataHora = dataHora;
    }

    public Comentário(int denunciaId, int usuarioMoradorId, String comentario) {
        this.denunciaId = denunciaId;
        this.usuarioMoradorId = usuarioMoradorId;
        this.comentario = comentario;
        this.dataHora = LocalDateTime.now();
    }

    public int getComentarioId() { return comentarioId; }
    public int getDenunciaId() { return denunciaId; }
    public int getUsuarioMoradorId() { return usuarioMoradorId; }
    public String getComentario() { return comentario; }
    public LocalDateTime getDataHora() { return dataHora; }
}