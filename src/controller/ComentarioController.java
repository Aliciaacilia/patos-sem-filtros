package controller;

import java.util.ArrayList;
import java.util.List;
import model.Comentário;

public class ComentarioController {
    public List<Comentário> listarComentarios(int denunciaId) {
        return new ArrayList<>(); 
    }
    public void adicionarComentario(int denunciaId, int usuarioId, String texto) {
        System.out.println("Comentário: " + texto);
    }
}