package view;

import controller.UsuarioController;
import java.util.Scanner;
import model.Usuario;

public class UsuarioView {
    private UsuarioController controller = new UsuarioController();
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n------- PATOS SEM FILTROS -------");
            System.out.println("1. Login");
            System.out.println("2. cadastrar novo usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opcao: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    Login();
                    break;
                case "2":
                    solicitarDadosCadastro();
                    break;
                case "3":
                    System.out.println("Saindo...");
                    rodando = false;
                    break;
                default:
                    System.out.println("Opcao inválida! Tente novamente.");
            }
        }
    }

    private void solicitarDadosCadastro() {
        System.out.println("\n--- Formulário de Cadastro ---");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Tipo (Morador ou Moderador): ");
        String tipo = scanner.nextLine();

        try {
            controller.cadastrar(nome, email, senha, tipo);
        } catch (IllegalArgumentException e) {
        System.out.println(" Erro: " + e.getMessage());
        }
    }

    private void Login() {
    System.out.println("\n------- Login -------");
    System.out.print("E-mail: ");
    String email = scanner.nextLine();
    System.out.print("Senha: ");
    String senha = scanner.nextLine();

    Usuario userLogado = controller.fazerLogin(email, senha);

    if (userLogado != null) {
        System.out.println("Olá, " + userLogado.getNome());
        
        HomeView home = new HomeView();
        home.exibirMenuInterno(userLogado.getNome()); 
    } else {
        System.out.println("E-mail ou senha incorretos.");
    }
  }
}