package service;

import java.util.List;
import model.Comentario;
import repository.ComentarioRepository;

public class ComentarioService {
    private ComentarioRepository repository = new ComentarioRepository();

    public void adicionarComentario(Comentario comentario) {
        repository.salvar(comentario);
    }

    public List<Comentario> listarComentarios(int denunciaId) {
        return repository.listarPorDenuncia(denunciaId);
    }
}