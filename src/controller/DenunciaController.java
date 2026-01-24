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

    public void minhasDenuncias(int usuarioMoradorId) {
        List<Denuncia> denuncias = service.minhasDenuncias(usuarioMoradorId);
        System.out.println("Histórico de denúncias:");
        for (Denuncia d : denuncias) {
            System.out.println("- ID: " + d.getDenunciaId()
                + " | Descrição: " + d.getDescricao()
                + " | Status: " + d.getStatus()
                + " | Visibilidade: " + d.getVisibilidade()
                + " | Categoria: " + d.getCategoriaId()
                + " | Data/Hora: " + d.getDataHora());
        }
    }
}