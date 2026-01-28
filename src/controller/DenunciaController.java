package controller;

import java.time.LocalDateTime;
import java.util.List;
import model.Denuncia;
import service.DenunciaService;

public class DenunciaController {
    private DenunciaService service = new DenunciaService();

    public void registrarDenuncia(int usuarioMoradorId, String descricao, String status,
                                  String visibilidade, String foto, String video, int categoriaId) {
        Denuncia denuncia = new Denuncia(0, usuarioMoradorId, descricao, LocalDateTime.now(),
                                         status, visibilidade, foto, video, categoriaId);
        service.registrarDenuncia(denuncia);
        System.out.println("Denúncia registrada com sucesso!");
    }

    public List<Denuncia> minhasDenuncias(int usuarioMoradorId) {
    return service.minhasDenuncias(usuarioMoradorId);
    }

    public List<Denuncia> listarDenuncias() {
        return service.listarFeed();
    }

        public Denuncia buscarPorId(int id) {
        return service.buscarPorId(id);
    }
}