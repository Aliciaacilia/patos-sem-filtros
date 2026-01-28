package controller;

import repository.CurtidaRepository;

public class CurtidaController {
    private CurtidaRepository curtidaRepository;

    public CurtidaController() {
        this.curtidaRepository = new CurtidaRepository();
    }

    public int contarCurtidas(int denunciaId) {
        return curtidaRepository.contarCurtidas(denunciaId);
    }

    public void curtirDenuncia(int denunciaId, int usuarioMoradorId) {
        if (!curtidaRepository.usuarioCurtiu(denunciaId, usuarioMoradorId)) {
            curtidaRepository.salvar(denunciaId, usuarioMoradorId);
            System.out.println("Usuário " + usuarioMoradorId + " curtiu a denúncia " + denunciaId);
        } else {
            System.out.println("Usuário " + usuarioMoradorId + " já curtiu esta denúncia.");
        }
    }
}