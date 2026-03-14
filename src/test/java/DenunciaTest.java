import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Denuncia;
import java.time.LocalDateTime;

public class DenunciaTest {

    @Test
    void testCriacao() {
        LocalDateTime agora = LocalDateTime.now();
        Denuncia d = new Denuncia(1, 10, "Esgoto no meio da rua", agora, "Nao resolvido", "Publico", "foto.jpg", null, 2);

        assertEquals(1, d.getDenunciaId());
        assertEquals("Esgoto no meio da rua", d.getDescricao());
        assertEquals("Nao resolvido", d.getStatus());
        assertEquals(10, d.getUsuarioMoradorId());
    }

    @Test
    void testStatus() {
        Denuncia d = new Denuncia();
        d.setStatus("Nao resolvido");
        
        assertEquals("Nao resolvido", d.getStatus());
    }
}