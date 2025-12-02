package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.TarifasModel;

public class TarifasDAO {
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
    public void inserir(TarifasModel tarifa) throws Exception {
        String sql = "INSERT INTO Tarifas (tarif_tipo, tarif_valor, tarif_periodo, id_esta_fk) VALUES (?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, tarifa.getTarifTipo());
            pst.setDouble(2, tarifa.getTarifValor());
            pst.setString(3, tarifa.getTarifPeriodo());
            pst.setInt(4, tarifa.getIdEstaFk());
            pst.executeUpdate();
        }
    }

    // READ
    public List<TarifasModel> listar() throws Exception {
        List<TarifasModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tarifas";
        try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TarifasModel t = new TarifasModel(
                    rs.getInt("id_tarif_pk"),
                    rs.getString("tarif_tipo"),
                    rs.getDouble("tarif_valor"),
                    rs.getString("tarif_periodo"),
                    rs.getInt("id_esta_fk")
                );
                lista.add(t);
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(TarifasModel tarifa) throws Exception {
        String sql = "UPDATE Tarifas SET tarif_tipo=?, tarif_valor=?, tarif_periodo=?, id_esta_fk=? WHERE id_tarif_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, tarifa.getTarifTipo());
            pst.setDouble(2, tarifa.getTarifValor());
            pst.setString(3, tarifa.getTarifPeriodo());
            pst.setInt(4, tarifa.getIdEstaFk());
            pst.setInt(5, tarifa.getIdTarifPk());
            pst.executeUpdate();
        }
    }

    // DELETE
    public void excluir(int idTarifPk) throws Exception {
        String sql = "DELETE FROM Tarifas WHERE id_tarif_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idTarifPk);
            pst.executeUpdate();
        }
    }
}

