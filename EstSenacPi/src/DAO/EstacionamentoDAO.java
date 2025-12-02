package DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.EstacionamentoModel;

public class EstacionamentoDAO {
	// Parâmetros de conexão com o Mysql8
    private  String DRIVER = BdMysql8.DRIVER; //"com.mysql.cj.jdbc.Driver";
    private  String URL = BdMysql8.URL; //"jdbc:mysql://127.0.0.1:3306/estacionamentosenac?useTimezone=true&serverTimezone=UTC";
    private  String USER = BdMysql8.USER; //"root";
    private  String PASSWORD = BdMysql8.PASSWORD; 

    private Connection conectar() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE
    public void inserir(EstacionamentoModel est) throws Exception {
        String sql = "INSERT INTO Estacionamento (esta_nome, esta_cnpj, esta_nome_fantasia, esta_razao_social, esta_representante, id_end_fk) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, est.getEstaNome());
            pst.setInt(2, est.getEstaCnpj());
            pst.setString(3, est.getEstaNomeFantasia());
            pst.setString(4, est.getEstaRazaoSocial());
            pst.setString(5, est.getEstaRepresentante());
            pst.setInt(6, est.getIdEndFk());
            pst.executeUpdate();
        }
    }

    // READ
    public List<EstacionamentoModel> listar() throws Exception {
        List<EstacionamentoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estacionamento";
        try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                EstacionamentoModel est = new EstacionamentoModel(
                    rs.getInt("id_esta_pk"),
                    rs.getString("esta_nome"),
                    rs.getInt("esta_cnpj"),
                    rs.getString("esta_nome_fantasia"),
                    rs.getString("esta_razao_social"),
                    rs.getString("esta_representante"),
                    rs.getInt("id_end_fk")
                );
                lista.add(est);
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(EstacionamentoModel est) throws Exception {
        String sql = "UPDATE Estacionamento SET esta_nome=?, esta_cnpj=?, esta_nome_fantasia=?, esta_razao_social=?, esta_representante=?, id_end_fk=? WHERE id_esta_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, est.getEstaNome());
            pst.setInt(2, est.getEstaCnpj());
            pst.setString(3, est.getEstaNomeFantasia());
            pst.setString(4, est.getEstaRazaoSocial());
            pst.setString(5, est.getEstaRepresentante());
            pst.setInt(6, est.getIdEndFk());
            pst.setInt(7, est.getIdEstaPk());
            pst.executeUpdate();
        }
    }

    // DELETE
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM Estacionamento WHERE id_esta_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}
