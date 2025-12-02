package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Entrada_SaidaModel;

public class Entrada_SaidaDAO {
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
    public void inserir(Entrada_SaidaModel entradaSaida) throws Exception {
        Connection con = conectar();
        String sql = "INSERT INTO Entrada_Saida (entra_data_hora_entrada, entra_data_hora_saida, id_vei_fk) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setTimestamp(1, new Timestamp(entradaSaida.getEntra_data_hora_entrada().getTime()));
        if (entradaSaida.getEntra_data_hora_saida() != null) {
            stmt.setTimestamp(2, new Timestamp(entradaSaida.getEntra_data_hora_saida().getTime()));
        } else {
            stmt.setNull(2, Types.TIMESTAMP);
        }
        stmt.setInt(3, entradaSaida.getId_vei_fk());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    // READ
    public List<Entrada_SaidaModel> listar() throws Exception {
        Connection con = conectar();
        String sql = "SELECT * FROM Entrada_Saida";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Entrada_SaidaModel> lista = new ArrayList<>();
        while (rs.next()) {
            Entrada_SaidaModel es = new Entrada_SaidaModel();
            es.setId_entra_pk(rs.getInt("id_entra_pk"));
            es.setEntra_data_hora_entrada(rs.getTimestamp("entra_data_hora_entrada"));
            es.setEntra_data_hora_saida(rs.getTimestamp("entra_data_hora_saida"));
            es.setId_vei_fk(rs.getInt("id_vei_fk"));
            lista.add(es);
        }
        rs.close();
        stmt.close();
        con.close();
        return lista;
    }

    // UPDATE
    public void atualizar(Entrada_SaidaModel entradaSaida) throws Exception {
        Connection con = conectar();
        String sql = "UPDATE Entrada_Saida SET entra_data_hora_entrada=?, entra_data_hora_saida=?, id_vei_fk=? WHERE id_entra_pk=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setTimestamp(1, new Timestamp(entradaSaida.getEntra_data_hora_entrada().getTime()));
        if (entradaSaida.getEntra_data_hora_saida() != null) {
            stmt.setTimestamp(2, new Timestamp(entradaSaida.getEntra_data_hora_saida().getTime()));
        } else {
            stmt.setNull(2, Types.TIMESTAMP);
        }
        stmt.setInt(3, entradaSaida.getId_vei_fk());
        stmt.setInt(4, entradaSaida.getId_entra_pk());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    // DELETE
    public void excluir(int id) throws Exception {
        Connection con = conectar();
        String sql = "DELETE FROM Entrada_Saida WHERE id_entra_pk=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
}

