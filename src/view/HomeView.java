package view;

import java.util.List;
import java.util.Scanner;
import controller.DenunciaController;
import controller.ComentarioController;
import controller.CurtidaController;
import model.Denuncia;
import model.Comentario;

public class HomeView {
    private Scanner scanner = new Scanner(System.in);
    private int usuarioMoradorId;
    private String nomeUsuario;
    private DenunciaController denunciaController = new DenunciaController();
    private ComentarioController comentarioController = new ComentarioController();
    private CurtidaController curtidaController = new CurtidaController();

    // Construtor recebe o ID e nome do usuário logado
    public HomeView(int usuarioMoradorId, String nomeUsuario) {
        this.usuarioMoradorId = usuarioMoradorId;
        this.nomeUsuario = nomeUsuario;
    }

    public void exibirMenuInterno() {
        boolean logado = true;

        while (logado) {
            System.out.println("\n--- Usuário: " + nomeUsuario.toUpperCase() + " ---");
            System.out.println("1. Feed de denúncias");
            System.out.println("2. Enviar nova denúncia");
            System.out.println("3. Minhas denúncias");
            System.out.println("4. Meu perfil");
            System.out.println("5. Logout");
            System.out.print("Opção: ");

            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    exibirFeed();
                    break;
                case "2":
                    criarDenuncia();
                    break;
                case "3":
                    verHistorico();
                    break;
                case "4":
                    verPerfil();
                    break;
                case "5":
                    System.out.println("Logout");
                    logado = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void exibirFeed() {
        System.out.println("\n--- Feed de denúncias ---");
        List<Denuncia> lista = denunciaController.listarDenuncias();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma denúncia registrada ainda.");
        } else {
            for (Denuncia d : lista) {
                System.out.println("----------------------------------");
                System.out.println("ID: " + d.getDenunciaId());
                System.out.println("Descrição: " + d.getDescricao());
                System.out.println("Status: [" + d.getStatus() + "]");
                System.out.println("Categoria: " + d.getCategoriaId());
                System.out.println("Postado em: " + d.getDataHora());
                System.out.println("Curtidas: " + curtidaController.contarCurtidas(d.getDenunciaId()));
            }

            System.out.print("\nDigite o ID da denúncia para ver detalhes ou ENTER para voltar: ");
            String entrada = scanner.nextLine();
            if (!entrada.isEmpty()) {
                int denunciaId = Integer.parseInt(entrada);
                verDetalhesDenuncia(denunciaId);
            }
        }
    }

    private void criarDenuncia() {
        System.out.println("\n--- Nova denúncia ---");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Status: ");
        String status = scanner.nextLine();
        System.out.print("Visibilidade (publico/anonimo): ");
        String visibilidade = scanner.nextLine();
        System.out.print("Foto (URL ou caminho): ");
        String foto = scanner.nextLine();
        System.out.print("Video (URL ou caminho): ");
        String video = scanner.nextLine();
        System.out.print("ID da categoria: ");
        int categoriaId = Integer.parseInt(scanner.nextLine());

        denunciaController.registrarDenuncia(usuarioMoradorId, descricao, status, visibilidade, foto, video, categoriaId);
    }

    private void verHistorico() {
        System.out.println("\n--- Minhas denúncias ---");
        List<Denuncia> minhas = denunciaController.minhasDenuncias(usuarioMoradorId);

        if (minhas.isEmpty()) {
            System.out.println("Você ainda não fez nenhuma denúncia.");
        } else {
            for (Denuncia d : minhas) {
                System.out.println("----------------------------------");
                System.out.println("ID: " + d.getDenunciaId());
                System.out.println("Descrição: " + d.getDescricao());
                System.out.println("Status: [" + d.getStatus() + "]");
                System.out.println("Categoria: " + d.getCategoriaId());
                System.out.println("Postado em: " + d.getDataHora());
            }
        }
    }

    private void verPerfil() {
        System.out.println("\n--- Meu perfil ---");
        System.out.println("Nome: " + nomeUsuario);
        System.out.println("ID: " + usuarioMoradorId);
    }

    private void verDetalhesDenuncia(int denunciaId) {
        Denuncia d = denunciaController.buscarPorId(denunciaId);
        if (d == null) {
            System.out.println("Denúncia não encontrada.");
            return;
        }

        System.out.println("\n--- Detalhes da denúncia ---");
        System.out.println("Descrição: " + d.getDescricao());
        System.out.println("Status: " + d.getStatus());
        System.out.println("Categoria: " + d.getCategoriaId());
        System.out.println("Visibilidade: " + d.getVisibilidade());
        System.out.println("Curtidas: " + curtidaController.contarCurtidas(d.getDenunciaId()));

        List<Comentario> comentarios = comentarioController.listarComentarios(denunciaId);
        if (comentarios.isEmpty()) {
            System.out.println("Nenhum comentário ainda.");
        } else {
            System.out.println("\nComentários:");
            for (Comentario c : comentarios) {
                System.out.println("- " + c.getComentario() + " (Usuário " + c.getUsuarioMoradorId() + ")");
            }
        }

        System.out.println("\nOpções:");
        System.out.println("1. Curtir denúncia");
        System.out.println("2. Adicionar comentário");
        System.out.println("ENTER para voltar");
        String escolha = scanner.nextLine();

        switch (escolha) {
            case "1":
                curtidaController.curtirDenuncia(denunciaId, usuarioMoradorId);
                System.out.println("Você curtiu esta denúncia!");
                break;
            case "2":
                System.out.print("Digite seu comentário: ");
                String texto = scanner.nextLine();
                comentarioController.adicionarComentario(denunciaId, usuarioMoradorId, texto);
                System.out.println("Comentário adicionado!");
                break;
            default:
                break;
        }
    }
}