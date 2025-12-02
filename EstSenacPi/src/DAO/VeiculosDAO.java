package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.VeiculosModel;

public class VeiculosDAO {
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
    public void inserir(VeiculosModel veiculo) throws Exception {
        String sql = "INSERT INTO Veiculos (vei_modelo, vei_cor, vei_marca, vei_placa) VALUES (?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, veiculo.getVeiModelo());
            pst.setString(2, veiculo.getVeiCor());
            pst.setString(3, veiculo.getVeiMarca());
            pst.setString(4, veiculo.getVeiPlaca());
            pst.executeUpdate();
        }
    }

    // READ
    public List<VeiculosModel> listar() throws Exception {
        List<VeiculosModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Veiculos";
        try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                VeiculosModel v = new VeiculosModel(
                    rs.getInt("id_vei_pk"),
                    rs.getString("vei_modelo"),
                    rs.getString("vei_cor"),
                    rs.getString("vei_marca"),
                    rs.getString("vei_placa")
                );
                lista.add(v);
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(VeiculosModel veiculo) throws Exception {
        String sql = "UPDATE Veiculos SET vei_modelo=?, vei_cor=?, vei_marca=?, vei_placa=? WHERE id_vei_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, veiculo.getVeiModelo());
            pst.setString(2, veiculo.getVeiCor());
            pst.setString(3, veiculo.getVeiMarca());
            pst.setString(4, veiculo.getVeiPlaca());
            pst.setInt(5, veiculo.getIdVeiPk());
            pst.executeUpdate();
        }
    }

    // DELETE
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM Veiculos WHERE id_vei_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}

