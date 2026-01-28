package service;

import java.util.List;
import model.Comentário;
import repository.ComentarioRepository;

public class ComentarioService {
    private ComentarioRepository repository = new ComentarioRepository();

    public void adicionarComentario(Comentário comentario) {
        repository.salvar(comentario);
    }

    public List<Comentário> listarComentarios(int denunciaId) {
        return repository.listarPorDenuncia(denunciaId);
    }
}