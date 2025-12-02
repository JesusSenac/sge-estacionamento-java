package DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.VeiculoModel;

public class VeiculoDAO {
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
    public void inserir(VeiculoModel v) throws Exception {
        String sql = "INSERT INTO Veiculo (vei_nome, vei_modelo, vei_placa, vei_cor, vei_tel, vei_hora, vei_dt_entrada, vei_vaga, vei_andar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, v.getVeiNome());
            pst.setString(2, v.getVeiModelo());
            pst.setString(3, v.getVeiPlaca());
            pst.setString(4, v.getVeiCor());
            pst.setString(5, v.getVeiTel());
            pst.setString(6, v.getVeiHora());
            pst.setDate(7, new java.sql.Date(v.getVeiDtEntrada().getTime()));
            pst.setString(8, v.getVeiVaga());
            pst.setString(9, v.getVeiAndar());
            pst.executeUpdate();
        }
    }

    // READ
    public List<VeiculoModel> listar() throws Exception {
        List<VeiculoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Veiculo";
        try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                VeiculoModel v = new VeiculoModel();
                v.setIdVeiPk(rs.getInt("id_vei_pk"));
                v.setVeiNome(rs.getString("vei_nome"));
                v.setVeiModelo(rs.getString("vei_modelo"));
                v.setVeiPlaca(rs.getString("vei_placa"));
                v.setVeiCor(rs.getString("vei_cor"));
                v.setVeiTel(rs.getString("vei_tel"));
                v.setVeiHora(rs.getString("vei_hora"));
                v.setVeiDtEntrada(rs.getDate("vei_dt_entrada"));
                v.setVeiVaga(rs.getString("vei_vaga"));
                v.setVeiAndar(rs.getString("vei_andar"));
                lista.add(v);
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(VeiculoModel v) throws Exception {
        String sql = "UPDATE Veiculo SET vei_nome=?, vei_modelo=?, vei_placa=?, vei_cor=?, vei_tel=?, vei_hora=?, vei_dt_entrada=?, vei_vaga=?, vei_andar=? WHERE id_vei_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, v.getVeiNome());
            pst.setString(2, v.getVeiModelo());
            pst.setString(3, v.getVeiPlaca());
            pst.setString(4, v.getVeiCor());
            pst.setString(5, v.getVeiTel());
            pst.setString(6, v.getVeiHora());
            pst.setDate(7, new java.sql.Date(v.getVeiDtEntrada().getTime()));
            pst.setString(8, v.getVeiVaga());
            pst.setString(9, v.getVeiAndar());
            pst.setInt(10, v.getIdVeiPk());
            pst.executeUpdate();
        }
    }

    // DELETE
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM Veiculo WHERE id_vei_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}

