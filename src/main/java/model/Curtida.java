package model;

import java.time.LocalDateTime;

public class Curtida {
    private int curtidaId;
    private int denunciaId;
    private int usuarioMoradorId;
    private LocalDateTime dataHora;

    public Curtida(int curtidaId, int denunciaId, int usuarioMoradorId, LocalDateTime dataHora) {
        this.curtidaId = curtidaId;
        this.denunciaId = denunciaId;
        this.usuarioMoradorId = usuarioMoradorId;
        this.dataHora = dataHora;
    }

    public Curtida(int denunciaId, int usuarioMoradorId) {
        this.denunciaId = denunciaId;
        this.usuarioMoradorId = usuarioMoradorId;
        this.dataHora = LocalDateTime.now();
    }

    public int getCurtidaId() { return curtidaId; }
    public int getDenunciaId() { return denunciaId; }
    public int getUsuarioMoradorId() { return usuarioMoradorId; }
    public LocalDateTime getDataHora() { return dataHora; }
}