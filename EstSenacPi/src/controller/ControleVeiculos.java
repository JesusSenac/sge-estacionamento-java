package controller;


import java.util.List;
import DAO.VeiculosDAO;
import model.VeiculosModel;

public class ControleVeiculos {
    private VeiculosDAO dao;

    public ControleVeiculos() {
        dao = new VeiculosDAO();
    }

    public void adicionarVeiculo(VeiculosModel veiculo) throws Exception {
        dao.inserir(veiculo);
    }

    public List<VeiculosModel> listarVeiculos() throws Exception {
        return dao.listar();
    }

    public void atualizarVeiculo(VeiculosModel veiculo) throws Exception {
        dao.atualizar(veiculo);
    }

    public void excluirVeiculo(int id) throws Exception {
        dao.excluir(id);
    }
}

