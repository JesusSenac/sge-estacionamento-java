package controller;

import java.util.List;
import DAO.TarifasDAO;
import model.TarifasModel;

public class ControleTarifas {
    private TarifasDAO dao;

    public ControleTarifas() {
        dao = new TarifasDAO();
    }

    public void adicionarTarifa(TarifasModel tarifa) throws Exception {
        dao.inserir(tarifa);
    }

    public List<TarifasModel> listarTarifas() throws Exception {
        return dao.listar();
    }

    public void atualizarTarifa(TarifasModel tarifa) throws Exception {
        dao.atualizar(tarifa);
    }

    public void excluirTarifa(int idTarifPk) throws Exception {
        dao.excluir(idTarifPk);
    }
}

