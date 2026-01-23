import controller.UsuarioController;
import view.UsuarioView;

public class Main {
    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();

        controller.cadastrar("Alícia Mendes", "alicia@email.com", "123456", "MORADOR");

        controller.cadastrar("Carlos Silva", "carlos@email.com", "abcdef", "MODERADOR");
    }
}
