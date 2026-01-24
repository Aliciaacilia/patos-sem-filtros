package view;

import java.util.Scanner;

public class HomeView {
    private Scanner scanner = new Scanner(System.in);
    
    public void exibirMenuInterno(String nomeUsuario) {
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
                case "1": exibirFeed(); break;
                case "2": criarDenuncia(); break;
                case "3": verHistorico(); break;
                case "4": verPerfil(); break;
                case "5": logado = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }
}
