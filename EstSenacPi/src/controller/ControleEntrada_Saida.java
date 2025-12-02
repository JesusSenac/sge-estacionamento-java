package controller;


import java.util.List;

import DAO.Entrada_SaidaDAO;
import model.Entrada_SaidaModel;

public class ControleEntrada_Saida {
    private Entrada_SaidaDAO dao;

    public ControleEntrada_Saida() {
        dao = new Entrada_SaidaDAO();
    }

    public void adicionarEntradaSaida(Entrada_SaidaModel es) throws Exception {
        dao.inserir(es);
    }

    public List<Entrada_SaidaModel> listarEntradasSaidas() throws Exception {
        return dao.listar();
    }

    public void atualizarEntradaSaida(Entrada_SaidaModel es) throws Exception {
        dao.atualizar(es);
    }

    public void excluirEntradaSaida(int id) throws Exception {
        dao.excluir(id);
    }
}

