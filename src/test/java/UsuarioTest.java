import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import controller.UsuarioController;
import model.Usuario;
import model.UsuarioMorador;
import model.UsuarioModerador;

public class UsuarioTest {

    @Test
    void testMorador() {
        Usuario user = new UsuarioMorador(1, "Alicia", "alicia@email.com", "123", false, "Morador", "000.000.000-00");

        assertEquals("Alicia", user.getNome());
        assertEquals("Morador", user.getTipo());
        assertFalse(user.isEmailVerificado());
    }

    @Test
    void testModerador() {
        Usuario user = new UsuarioModerador(2, "Darysthon", "darys@email.com", "456", true, "Moderador", "Nivel 1");

        assertEquals("Darysthon", user.getNome());
        assertEquals("Moderador", user.getTipo());
        assertTrue(user.isEmailVerificado());
    }

    @Test
    void testEmail() {
        Usuario user = new UsuarioMorador();
        user.setEmailVerificado(true);
        assertTrue(user.isEmailVerificado());
    }

    @Test
    void testImpedirEmailDuplicado() {

        UsuarioController controller = new UsuarioController();
    
        controller.cadastrar("Mariana", "Mari123@email.com", "123", "Morador");
    
        boolean resultado = controller.cadastrar("Maria", "Mari123@email.com", "456", "Morador");
    
        assertFalse(resultado, "O sistema permitiu cadastrar um e-mail que ja existia");
    }
}