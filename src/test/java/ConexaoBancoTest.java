import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Test;

public class ConexaoBancoTest {

    @Test
    void testarConexaoComPostgreSQL() {
        String url = "jdbc:postgresql://localhost:5432/patos_sem_filtros?charSet=UTF-8";
        String usuario = "postgres";
        String senha = "SUA_SENHA_AQUI";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            assertNotNull(conexao, "A conexão não deveria ser nula!");
            System.out.println("Conexão com o banco realizada com sucesso!");
        } catch (Exception e) {
            fail("Falha ao conectar ao banco: " + e.getMessage());
        }
    }
}