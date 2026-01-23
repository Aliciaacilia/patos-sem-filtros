import controller.UsuarioController;

public class Main {
    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();
        
        UsuarioView tela = new UsuarioView();
        tela.exibirMenu();
    }
}