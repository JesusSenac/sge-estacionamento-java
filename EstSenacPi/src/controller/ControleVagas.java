package controller;

import DAO.VagasDAO;
import model.VagasModel;
import java.util.List;

public class ControleVagas {
    private VagasDAO dao;

    public ControleVagas() {
        dao = new VagasDAO();
    }

    public void adicionarVaga(VagasModel vaga) throws Exception {
        dao.inserir(vaga);
    }

    public List<VagasModel> listarVagas() throws Exception {
        return dao.listar();
    }

    public void atualizarVaga(VagasModel vaga) throws Exception {
        dao.atualizar(vaga);
    }

    public void excluirVaga(String codigo) throws Exception {
        dao.excluir(codigo);
    }
}

