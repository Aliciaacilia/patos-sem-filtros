package controller;

import java.util.List;

import model.Comentário;
import repository.ComentarioRepository;

public class ComentarioController {
    private ComentarioRepository comentarioRepository;

    public ComentarioController() {
        this.comentarioRepository = new ComentarioRepository();
    }

    public List<Comentário> listarComentarios(int denunciaId) {
        return comentarioRepository.listarPorDenuncia(denunciaId);
    }

    public void adicionarComentario(int denunciaId, int usuarioMoradorId, String texto) {
        Comentário comentario = new Comentário(denunciaId, usuarioMoradorId, texto);
        comentarioRepository.salvar(comentario);
        System.out.println("Comentário salvo: " + texto);
    }
}