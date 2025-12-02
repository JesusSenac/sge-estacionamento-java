package DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.VagasModel;

public class VagasDAO {
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
    public void inserir(VagasModel vaga) throws Exception {
        String sql = "INSERT INTO Vagas (vaga_codigo, vaga_status, vaga_preferencial, vaga_andar, vaga_tipo, id_esta_fk) VALUES (?,?,?,?,?,?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, vaga.getVagaCodigo());
            pst.setString(2, vaga.getVagaStatus());
            pst.setBoolean(3, vaga.isVagaPreferencial());
            pst.setInt(4, vaga.getVagaAndar());
            pst.setString(5, vaga.getVagaTipo());
            pst.setInt(6, vaga.getIdEstaFk());
            pst.executeUpdate();
        }
    }

    // READ
    public List<VagasModel> listar() throws Exception {
        List<VagasModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vagas";
        try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                VagasModel vaga = new VagasModel(
                    rs.getInt("id_vaga_pk"),
                    rs.getString("vaga_codigo"),
                    rs.getString("vaga_status"),
                    rs.getBoolean("vaga_preferencial"),
                    rs.getInt("vaga_andar"),
                    rs.getString("vaga_tipo"),
                    rs.getInt("id_esta_fk")
                );
                lista.add(vaga);
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(VagasModel vaga) throws Exception {
        String sql = "UPDATE Vagas SET vaga_status=?, vaga_preferencial=?, vaga_andar=?, vaga_tipo=?, id_esta_fk=? WHERE vaga_codigo=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, vaga.getVagaStatus());
            pst.setBoolean(2, vaga.isVagaPreferencial());
            pst.setInt(3, vaga.getVagaAndar());
            pst.setString(4, vaga.getVagaTipo());
            pst.setInt(5, vaga.getIdEstaFk());
            pst.setString(6, vaga.getVagaCodigo());
            pst.executeUpdate();
        }
    }

    // DELETE
    public void excluir(String vagaCodigo) throws Exception {
        String sql = "DELETE FROM Vagas WHERE vaga_codigo=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, vagaCodigo);
            pst.executeUpdate();
        }
    }
}

