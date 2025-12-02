package controller;



import java.util.List;
import DAO.EstacionamentoDAO;
import model.EstacionamentoModel;

public class ControleEstacionamento {
    private EstacionamentoDAO dao;

    public ControleEstacionamento() {
        dao = new EstacionamentoDAO();
    }

    public void adicionarEstacionamento(EstacionamentoModel est) throws Exception {
        dao.inserir(est);
    }

    public List<EstacionamentoModel> listarEstacionamentos() throws Exception {
        return dao.listar();
    }

    public void atualizarEstacionamento(EstacionamentoModel est) throws Exception {
        dao.atualizar(est);
    }

    public void excluirEstacionamento(int id) throws Exception {
        dao.excluir(id);
    }
}
