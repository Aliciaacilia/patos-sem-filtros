package service;

import repository.CurtidaRepository;

public class CurtidaService {
    private CurtidaRepository repository = new CurtidaRepository();

    public void curtirDenuncia(int denunciaId, int usuarioId) {
        if (!repository.usuarioCurtiu(denunciaId, usuarioId)) {
            repository.salvar(denunciaId, usuarioId);
        }
    }

    public int contarCurtidas(int denunciaId) {
        return repository.contarCurtidas(denunciaId);
    }

    public boolean usuarioCurtiu(int denunciaId, int usuarioId) {
        return repository.usuarioCurtiu(denunciaId, usuarioId);
    }
}