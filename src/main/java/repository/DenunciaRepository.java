package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Denuncia;
import databaseconfig.DatabaseConfig;

public class DenunciaRepository {

    public void salvar(Denuncia denuncia) {
        String sql = "INSERT INTO denuncias (usuario_morador_id, descricao, status, visibilidade, foto, video, categoria_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, denuncia.getUsuarioMoradorId());
            stmt.setString(2, denuncia.getDescricao());
            stmt.setString(3, denuncia.getStatus());
            stmt.setString(4, denuncia.getVisibilidade());
            stmt.setString(5, denuncia.getFoto());
            stmt.setString(6, denuncia.getVideo());
            stmt.setInt(7, denuncia.getCategoriaId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Denuncia> listarPorUsuarioMorador(int usuarioMoradorId) {
        List<Denuncia> resultado = new ArrayList<>();
        String sql = "SELECT * FROM denuncias WHERE usuario_morador_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioMoradorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Denuncia d = new Denuncia(
                    rs.getInt("denuncia_id"),
                    rs.getInt("usuario_morador_id"),
                    rs.getString("descricao"),
                    rs.getTimestamp("data_hora").toLocalDateTime(),
                    rs.getString("status"),
                    rs.getString("visibilidade"),
                    rs.getString("foto"),
                    rs.getString("video"),
                    rs.getInt("categoria_id")
                );
                resultado.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public List<Denuncia> consultarFeedGeral() {
        List<Denuncia> denuncias = new ArrayList<>();
        String sql = "SELECT * FROM denuncias ORDER BY data_hora DESC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Denuncia d = new Denuncia();
                d.setDenunciaId(rs.getInt("denuncia_id"));
                d.setUsuarioMoradorId(rs.getInt("usuario_morador_id"));
                d.setDescricao(rs.getString("descricao"));
                d.setStatus(rs.getString("status"));
                d.setVisibilidade(rs.getString("visibilidade"));
                d.setFoto(rs.getString("foto"));
                d.setVideo(rs.getString("video"));
                d.setCategoriaId(rs.getInt("categoria_id"));
                d.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());

                denuncias.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return denuncias;
    }

    public Denuncia buscarPorId(int id) {
        String sql = "SELECT * FROM denuncias WHERE denuncia_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Denuncia(
                    rs.getInt("denuncia_id"),
                    rs.getInt("usuario_morador_id"),
                    rs.getString("descricao"),
                    rs.getTimestamp("data_hora").toLocalDateTime(),
                    rs.getString("status"),
                    rs.getString("visibilidade"),
                    rs.getString("foto"),
                    rs.getString("video"),
                    rs.getInt("categoria_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}