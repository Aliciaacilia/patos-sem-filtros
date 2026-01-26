package service;

import java.util.List;
import model.Denuncia;
import repository.DenunciaRepository;

public class DenunciaService {
    private DenunciaRepository repository = new DenunciaRepository();

    public void registrarDenuncia(Denuncia denuncia) {
        repository.salvar(denuncia);
    }

    public List<Denuncia> minhasDenuncias(int usuarioMoradorId) {
        return repository.listarPorUsuarioMorador(usuarioMoradorId);
    }

    public List<Denuncia> listarFeed() {
    return repository.consultarFeedGeral();
}
}