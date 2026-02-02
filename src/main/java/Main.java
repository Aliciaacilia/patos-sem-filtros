import controller.UsuarioController;
import view.UsuarioView;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        UsuarioController controller = new UsuarioController();
        UsuarioView usuarioView = new UsuarioView(controller);
        usuarioView.exibirMenu();
    }
}