import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import org.junit.jupiter.api.Test;
import model.Usuario;
import databaseconfig.DatabaseConfig;

public class UsuarioCadastroTest {

    @Test
    void testarCadastroUsuarioBase() {
        Usuario user = new Usuario(0, "Alicia Mendes", "alicia@email.com", "123456", false, "COMUM");

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "INSERT INTO usuarios (nome, email, senha, email_verificado, tipo_id, tipo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getSenha());
            pstmt.setBoolean(4, user.isEmailVerificado());
            pstmt.setInt(5, 1); 
            pstmt.setString(6, user.getTipo());

            int rows = pstmt.executeUpdate();
            assertEquals(1, rows);

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }

            assertTrue(user.getId() > 0);

            try (PreparedStatement pstmtDel = conn.prepareStatement("DELETE FROM usuarios WHERE usuario_id = ?")) {
                pstmtDel.setInt(1, user.getId());
                pstmtDel.executeUpdate();
            }

            System.out.println("Cadastrado com sucesso!");

        } catch (SQLException e) {
            fail("Erro no banco de dados: " + e.getMessage());
        }
    }
}