import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Usuario;

public class LoginTest {

    @Test
    void testLoginSucesso() {

        String senhaCadastrada = String.valueOf("alicia123".hashCode());
        Usuario user = new Usuario(1, "Alicia", "alicia@email.com", senhaCadastrada, true, "MORADOR");
        String tentativaSenha = "alicia123";

        String senhaDigitadaHash = String.valueOf(tentativaSenha.hashCode());

        assertEquals(user.getSenha(), senhaDigitadaHash, "O login deve ser bem-sucedido.");
    }

    @Test
    void testLoginSenhaIncorreta() {
 
        String senhaCadastrada = String.valueOf("alicia123".hashCode());
        Usuario user = new Usuario(1, "Alicia", "alicia@email.com", senhaCadastrada, true, "MORADOR");
        String tentativaErrada = "senhaErrada";
        String senhaErradaHash = String.valueOf(tentativaErrada.hashCode());

        assertNotEquals(user.getSenha(), senhaErradaHash, "O login não deve permitir senha incorreta.");
    }

    @Test
    void testLoginDadosVazios() {

        String senhaCadastrada = String.valueOf("alicia123".hashCode());
        Usuario user = new Usuario(1, "Alicia", "alicia@email.com", senhaCadastrada, true, "MORADOR");

        String senhaVaziaHash = String.valueOf("".hashCode());

        assertNotEquals(user.getSenha(), senhaVaziaHash, "O sistema deve rejeitar campos vazios.");
    }
}