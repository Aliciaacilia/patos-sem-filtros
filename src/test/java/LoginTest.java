import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Usuario;

public class LoginTest {

    @Test
    void testLoginSucesso() {
        String senhaCadastrada = String.valueOf("alicia123".hashCode());
        Usuario user = new Usuario(1, "Alicia", "alicia@email.com", senhaCadastrada, true, "MORADOR");
        
        String tentativaHash = String.valueOf("alicia123".hashCode());

        assertEquals(user.getSenha(), tentativaHash);
    }

    @Test
    void testLoginSenhaIncorreta() {
        String senhaCadastrada = String.valueOf("alicia123".hashCode());
        Usuario user = new Usuario(1, "Alicia", "alicia@email.com", senhaCadastrada, true, "MORADOR");
        
        String tentativaErradaHash = String.valueOf("senhaErrada".hashCode());

        assertNotEquals(user.getSenha(), tentativaErradaHash);
    }

    @Test
    void testLoginDadosVazios() {
        String senhaCadastrada = String.valueOf("alicia123".hashCode());
        Usuario user = new Usuario(1, "Alicia", "alicia@email.com", senhaCadastrada, true, "MORADOR");

        String tentativaVaziaHash = String.valueOf("".hashCode());

        assertNotEquals(user.getSenha(), tentativaVaziaHash);
    }
}