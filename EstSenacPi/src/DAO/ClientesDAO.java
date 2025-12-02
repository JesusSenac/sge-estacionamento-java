package DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ClientesModel;

public class ClientesDAO {
	// Parâmetros de conexão com o Mysql8
    private  String DRIVER = BdMysql8.DRIVER; //"com.mysql.cj.jdbc.Driver";
    private  String URL = BdMysql8.URL; //"jdbc:mysql://127.0.0.1:3306/estacionamentosenac?useTimezone=true&serverTimezone=UTC";
    private  String USER = BdMysql8.USER; //"root";
    private  String PASSWORD = BdMysql8.PASSWORD; //

    private Connection conectar() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE
    public void inserir(ClientesModel cliente) throws Exception {
        String sql = "INSERT INTO Clientes (cli_nome, cli_cpf, cli_telefone, id_esta_fk) VALUES (?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, cliente.getCli_nome());
            pst.setString(2, cliente.getCli_cpf());
            pst.setString(3, cliente.getCli_telefone());
            pst.setInt(4, cliente.getId_esta_fk());
            pst.executeUpdate();
        }
    }

    // READ
    public List<ClientesModel> listar() throws Exception {
        List<ClientesModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";
        try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ClientesModel cliente = new ClientesModel(
                    rs.getInt("id_cli_pk"),
                    rs.getString("cli_nome"),
                    rs.getString("cli_cpf"),
                    rs.getString("cli_telefone"),
                    rs.getInt("id_esta_fk")
                );
                lista.add(cliente);
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(ClientesModel cliente) throws Exception {
        String sql = "UPDATE Clientes SET cli_nome=?, cli_cpf=?, cli_telefone=?, id_esta_fk=? WHERE id_cli_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, cliente.getCli_nome());
            pst.setString(2, cliente.getCli_cpf());
            pst.setString(3, cliente.getCli_telefone());
            pst.setInt(4, cliente.getId_esta_fk());
            pst.setInt(5, cliente.getId_cli_pk());
            pst.executeUpdate();
        }
    }

    // DELETE
    public void excluir(int id_cli_pk) throws Exception {
        String sql = "DELETE FROM Clientes WHERE id_cli_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id_cli_pk);
            pst.executeUpdate();
        }
    }
}

