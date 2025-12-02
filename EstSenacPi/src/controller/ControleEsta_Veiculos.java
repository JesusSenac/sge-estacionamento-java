package controller;

import java.util.List;
import DAO.Esta_VeiculosDAO;
import model.Esta_VeiculosModel;

public class ControleEsta_Veiculos {
    private Esta_VeiculosDAO dao;

    public ControleEsta_Veiculos() {
        dao = new Esta_VeiculosDAO();
    }

    public void adicionar(Esta_VeiculosModel ev) throws Exception {
        dao.inserir(ev);
    }

    public List<Esta_VeiculosModel> listarTodos() throws Exception {
        return dao.listar();
    }

    public void atualizar(Esta_VeiculosModel ev) throws Exception {
        dao.atualizar(ev);
    }

    public void excluir(int id) throws Exception {
        dao.excluir(id);
    }
}

