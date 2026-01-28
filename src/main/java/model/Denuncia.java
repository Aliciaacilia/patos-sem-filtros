package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Denuncia {
    private int denunciaId;
    private int usuarioMoradorId;  
    private String descricao;
    private LocalDateTime dataHora; 
    private String status;
    private String visibilidade;  
    private String foto;
    private String video;
    private int categoriaId;      

    private List<Comentário> comentarios = new ArrayList<>();
    private List<Integer> curtidas = new ArrayList<>(); 

    public Denuncia() {
        this.dataHora = LocalDateTime.now();
    }

    public Denuncia(int usuarioMoradorId, String descricao, LocalDateTime dataHora,
                    String status, String visibilidade, String foto, String video, int categoriaId) {
        this.usuarioMoradorId = usuarioMoradorId;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.status = status;
        this.visibilidade = visibilidade;
        this.foto = foto;
        this.video = video;
        this.categoriaId = categoriaId;
    }

    public Denuncia(int denunciaId, int usuarioMoradorId, String descricao, LocalDateTime dataHora,
                    String status, String visibilidade, String foto, String video, int categoriaId) {
        this.denunciaId = denunciaId;
        this.usuarioMoradorId = usuarioMoradorId;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.status = status;
        this.visibilidade = visibilidade;
        this.foto = foto;
        this.video = video;
        this.categoriaId = categoriaId;
    }

    public int getDenunciaId() { return denunciaId; }
    public void setDenunciaId(int denunciaId) { this.denunciaId = denunciaId; }

    public int getUsuarioMoradorId() { return usuarioMoradorId; }
    public void setUsuarioMoradorId(int usuarioMoradorId) { this.usuarioMoradorId = usuarioMoradorId; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getVisibilidade() { return visibilidade; }
    public void setVisibilidade(String visibilidade) { this.visibilidade = visibilidade; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getVideo() { return video; }
    public void setVideo(String video) { this.video = video; }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public void adicionarComentario(Comentário comentario) {
        comentarios.add(comentario);
    }

    public List<Comentário> getComentarios() {
        return comentarios;
    }

    public void adicionarCurtida(int usuarioMoradorId) {
        if (!curtidas.contains(usuarioMoradorId)) {
            curtidas.add(usuarioMoradorId);
        }
    }

    public int getTotalCurtidas() {
        return curtidas.size();
    }
}