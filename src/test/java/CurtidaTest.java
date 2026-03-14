import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Curtida;
import java.time.LocalDateTime;

public class CurtidaTest {

    @Test
    void testContagem() {
        Curtida c1 = new Curtida(1, 10, 5, LocalDateTime.now());

        assertEquals(1, c1.getCurtidaId());
        assertEquals(10, c1.getDenunciaId());
        assertEquals(5, c1.getUsuarioMoradorId());
    }

    @Test
    void testCurtir() {
        int denunciaId = 20;
        int usuarioId = 99;

        Curtida curtida = new Curtida(denunciaId, usuarioId);

        assertEquals(denunciaId, curtida.getDenunciaId());
        assertEquals(usuarioId, curtida.getUsuarioMoradorId());
        assertNotNull(curtida.getDataHora());
    }
}