import controller.UsuarioController;
import view.UsuarioView;

public class Main {
    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();
        UsuarioView usuarioView = new UsuarioView(controller);
        usuarioView.exibirMenu();
    }
}