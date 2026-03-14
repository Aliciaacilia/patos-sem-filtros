import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Usuario;

public class LoginTest {

    private Usuario user;

    @BeforeEach
    void setup() {
        String senhaCadastrada = String.valueOf("alicia123".hashCode());
        user = new Usuario(1, "Alicia", "alicia@email.com", senhaCadastrada, true, "MORADOR");
    }

    @Test
    void testLoginSucesso() {
        String tentativaHash = String.valueOf("alicia123".hashCode());
        assertEquals(user.getSenha(), tentativaHash);
    }

    @Test
    void testLoginSenhaIncorreta() {
        String tentativaErradaHash = String.valueOf("senhaErrada".hashCode());
        assertNotEquals(user.getSenha(), tentativaErradaHash);
    }

    @Test
    void testLoginDadosVazios() {
        String tentativaVaziaHash = String.valueOf("".hashCode());
        assertNotEquals(user.getSenha(), tentativaVaziaHash);
    }
}