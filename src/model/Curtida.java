package model;

public class Curtida {
    private int id;
    private int denunciaId;
    private int usuarioId;

    public Curtida(int id, int denunciaId, int usuarioId) {
        this.id = id;
        this.denunciaId = denunciaId;
        this.usuarioId = usuarioId;
    }

    // Getters
    public int getId() { return id; }
    public int getDenunciaId() { return denunciaId; }
    public int getUsuarioId() { return usuarioId; }
}