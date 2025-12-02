package DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Esta_VeiculosModel;

public class Esta_VeiculosDAO {
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
    public void inserir(Esta_VeiculosModel ev) throws Exception {
        String sql = "INSERT INTO Esta_Veiculos (id_esta_fk, id_vei_fk) VALUES (?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, ev.getId_esta_fk());
            pst.setInt(2, ev.getId_vei_fk());
            pst.executeUpdate();
        }
    }

    // READ
    public List<Esta_VeiculosModel> listar() throws Exception {
        List<Esta_VeiculosModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Esta_Veiculos";
        try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Esta_VeiculosModel ev = new Esta_VeiculosModel(
                    rs.getInt("id_esta_vei_pk"),
                    rs.getInt("id_esta_fk"),
                    rs.getInt("id_vei_fk")
                );
                lista.add(ev);
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(Esta_VeiculosModel ev) throws Exception {
        String sql = "UPDATE Esta_Veiculos SET id_esta_fk=?, id_vei_fk=? WHERE id_esta_vei_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, ev.getId_esta_fk());
            pst.setInt(2, ev.getId_vei_fk());
            pst.setInt(3, ev.getId_esta_vei_pk());
            pst.executeUpdate();
        }
    }

    // DELETE
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM Esta_Veiculos WHERE id_esta_vei_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}

