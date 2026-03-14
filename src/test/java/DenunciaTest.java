import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Denuncia;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    void testListarDenunciasModelo() {
        List<Denuncia> lista = new ArrayList<>();

        Denuncia d1 = new Denuncia(1, 10, "Buraco na rua",
                LocalDateTime.now(), "Pendente", "Publico", null, null, 3);

        Denuncia d2 = new Denuncia(2, 11, "Lixo acumulado",
                LocalDateTime.now(), "Resolvido", "Publico", null, null, 1);

        lista.add(d1);
        lista.add(d2);

        assertEquals(2, lista.size());
        assertEquals("Buraco na rua", lista.get(0).getDescricao());
        assertEquals("Lixo acumulado", lista.get(1).getDescricao());
        assertEquals("Pendente", lista.get(0).getStatus());
        assertEquals("Resolvido", lista.get(1).getStatus());
    }


}