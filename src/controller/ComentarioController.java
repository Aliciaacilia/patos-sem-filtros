package controller;

import java.util.List;
import model.Comentario;
import repository.ComentarioRepository;

public class ComentarioController {
    private ComentarioRepository comentarioRepository;

    public ComentarioController() {
        this.comentarioRepository = new ComentarioRepository();
    }

    public List<Comentario> listarComentarios(int denunciaId) {
        return comentarioRepository.listarPorDenuncia(denunciaId);
    }

    public void adicionarComentario(int denunciaId, int usuarioMoradorId, String texto) {
        Comentario comentario = new Comentario(denunciaId, usuarioMoradorId, texto);
        comentarioRepository.salvar(comentario);
        System.out.println("Comentário salvo: " + texto);
    }
}