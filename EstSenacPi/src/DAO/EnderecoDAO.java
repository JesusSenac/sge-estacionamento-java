package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EnderecoModel;

public class EnderecoDAO {

    // Parâmetros de conexão com o Mysql8
    private  String DRIVER = BdMysql8.DRIVER; //"com.mysql.cj.jdbc.Driver";
    private  String URL = BdMysql8.URL; //"jdbc:mysql://127.0.0.1:3306/estacionamentosenac?useTimezone=true&serverTimezone=UTC";
    private  String USER = BdMysql8.USER; //"root";
    private  String PASSWORD = BdMysql8.PASSWORD; 

    // Método para obter a conexão com o banco de dados
    private Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado: " + e.getMessage());
        }
    }

    /** C - Create (Inserir) **/
    public void inserir(EnderecoModel endereco) {
        String sql = "INSERT INTO Endereco (end_rua, end_bairro, end_cidade, end_pais) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, endereco.getEnd_rua());
            stmt.setString(2, endereco.getEnd_bairro());
            stmt.setString(3, endereco.getEnd_cidade());
            stmt.setString(4, endereco.getEnd_pais());

            stmt.executeUpdate();
            System.out.println("✅ Endereço inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir endereço: " + e.getMessage());
        }
    }

    /** R - Read (Listar Todos) **/
    public List<EnderecoModel> listarTodos() {
        String sql = "SELECT * FROM Endereco";
        List<EnderecoModel> listaEnderecos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EnderecoModel endereco = new EnderecoModel();
                endereco.setId_end_pk(rs.getInt("id_end_pk"));
                endereco.setEnd_rua(rs.getString("end_rua"));
                endereco.setEnd_bairro(rs.getString("end_bairro"));
                endereco.setEnd_cidade(rs.getString("end_cidade"));
                endereco.setEnd_pais(rs.getString("end_pais"));
                listaEnderecos.add(endereco);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar endereços: " + e.getMessage());
        }
        return listaEnderecos;
    }
    
    /** R - Read (Buscar por ID) **/
    public EnderecoModel buscarPorId(int id) {
        String sql = "SELECT * FROM Endereco WHERE id_end_pk = ?";
        EnderecoModel endereco = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    endereco = new EnderecoModel();
                    endereco.setId_end_pk(rs.getInt("id_end_pk"));
                    endereco.setEnd_rua(rs.getString("end_rua"));
                    endereco.setEnd_bairro(rs.getString("end_bairro"));
                    endereco.setEnd_cidade(rs.getString("end_cidade"));
                    endereco.setEnd_pais(rs.getString("end_pais"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar endereço: " + e.getMessage());
        }
        return endereco;
    }


    /** U - Update (Atualizar) **/
    public void atualizar(EnderecoModel endereco) {
        String sql = "UPDATE Endereco SET end_rua = ?, end_bairro = ?, end_cidade = ?, end_pais = ? WHERE id_end_pk = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, endereco.getEnd_rua());
            stmt.setString(2, endereco.getEnd_bairro());
            stmt.setString(3, endereco.getEnd_cidade());
            stmt.setString(4, endereco.getEnd_pais());
            stmt.setInt(5, endereco.getId_end_pk());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                 System.out.println("✅ Endereço ID " + endereco.getId_end_pk() + " atualizado com sucesso!");
            } else {
                 System.out.println("⚠️ Endereço ID " + endereco.getId_end_pk() + " não encontrado para atualização.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar endereço: " + e.getMessage());
        }
    }

    /** D - Delete (Deletar) **/
    public void deletar(int id) {
        String sql = "DELETE FROM Endereco WHERE id_end_pk = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
             if (linhasAfetadas > 0) {
                 System.out.println("✅ Endereço ID " + id + " deletado com sucesso!");
            } else {
                 System.out.println("⚠️ Endereço ID " + id + " não encontrado para exclusão.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao deletar endereço: " + e.getMessage());
        }
    }
}

