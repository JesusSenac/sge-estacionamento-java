package controller;


import java.util.List;
import DAO.VeiculoDAO;
import model.VeiculoModel;

public class ControleVeiculo {
    private VeiculoDAO dao;

    public ControleVeiculo() {
        dao = new VeiculoDAO();
    }

    public void adicionarVeiculo(VeiculoModel v) throws Exception {
        dao.inserir(v);
    }

    public List<VeiculoModel> listarVeiculos() throws Exception {
        return dao.listar();
    }

    public void atualizarVeiculo(VeiculoModel v) throws Exception {
        dao.atualizar(v);
    }

    public void excluirVeiculo(int id) throws Exception {
        dao.excluir(id);
    }
}

