import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Usuario;
import model.UsuarioMorador;
import model.UsuarioModerador;

public class UsuarioTest {

    @Test
    void testMorador() {
        Usuario user = new UsuarioMorador(1, "Alicia", "alicia@email.com", "123", false, "MORADOR", "000.000.000-00");

        assertEquals("Alicia", user.getNome());
        assertEquals("MORADOR", user.getTipo());
        assertFalse(user.isEmailVerificado());
    }

    @Test
    void testModerador() {
        Usuario user = new UsuarioModerador(2, "Darysthon", "darys@email.com", "456", true, "MODERADOR", "Nivel 1");

        assertEquals("Darysthon", user.getNome());
        assertEquals("MODERADOR", user.getTipo());
        assertTrue(user.isEmailVerificado());
    }

    @Test
    void testEmail() {
        Usuario user = new UsuarioMorador();
        user.setEmailVerificado(true);
        assertTrue(user.isEmailVerificado());
    }
}