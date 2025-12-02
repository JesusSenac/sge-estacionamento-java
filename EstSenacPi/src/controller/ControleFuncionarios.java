package controller;


import java.util.List;
import DAO.FuncionariosDAO;
import model.FuncionariosModel;

public class ControleFuncionarios {
    private FuncionariosDAO dao = new FuncionariosDAO();

    public void adicionarFuncionario(FuncionariosModel func) throws Exception {
        dao.inserir(func);
    }

    public List<FuncionariosModel> listarFuncionarios() throws Exception {
        return dao.listar();
    }

    public void atualizarFuncionario(FuncionariosModel func) throws Exception {
        dao.atualizar(func);
    }

    public void excluirFuncionario(int id_func_pk) throws Exception {
        dao.excluir(id_func_pk);
    }
}
