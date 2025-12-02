package DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.FuncionariosModel;

public class FuncionariosDAO {
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
    public void inserir(FuncionariosModel func) throws Exception {
        String sql = "INSERT INTO Funcionarios (func_nome, func_funcao, func_jornada_de_trabalho, func_login, func_senha, func_salario, id_esta_fk) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, func.getFunc_nome());
            pst.setString(2, func.getFunc_funcao());
            pst.setString(3, func.getFunc_jornada_de_trabalho());
            pst.setString(4, func.getFunc_login());
            pst.setString(5, func.getFunc_senha());
            pst.setDouble(6, func.getFunc_salario());
            pst.setInt(7, func.getId_esta_fk());
            pst.executeUpdate();
        }
    }

    // READ
    public List<FuncionariosModel> listar() throws Exception {
        List<FuncionariosModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Funcionarios";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                FuncionariosModel f = new FuncionariosModel();
                f.setId_func_pk(rs.getInt("id_func_pk"));
                f.setFunc_nome(rs.getString("func_nome"));
                f.setFunc_funcao(rs.getString("func_funcao"));
                f.setFunc_jornada_de_trabalho(rs.getString("func_jornada_de_trabalho"));
                f.setFunc_login(rs.getString("func_login"));
                f.setFunc_senha(rs.getString("func_senha"));
                f.setFunc_salario(rs.getDouble("func_salario"));
                f.setId_esta_fk(rs.getInt("id_esta_fk"));
                lista.add(f);
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(FuncionariosModel func) throws Exception {
        String sql = "UPDATE Funcionarios SET func_nome=?, func_funcao=?, func_jornada_de_trabalho=?, func_login=?, func_senha=?, func_salario=?, id_esta_fk=? WHERE id_func_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, func.getFunc_nome());
            pst.setString(2, func.getFunc_funcao());
            pst.setString(3, func.getFunc_jornada_de_trabalho());
            pst.setString(4, func.getFunc_login());
            pst.setString(5, func.getFunc_senha());
            pst.setDouble(6, func.getFunc_salario());
            pst.setInt(7, func.getId_esta_fk());
            pst.setInt(8, func.getId_func_pk());
            pst.executeUpdate();
        }
    }

    // DELETE
    public void excluir(int id_func_pk) throws Exception {
        String sql = "DELETE FROM Funcionarios WHERE id_func_pk=?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id_func_pk);
            pst.executeUpdate();
        }
    }
}

