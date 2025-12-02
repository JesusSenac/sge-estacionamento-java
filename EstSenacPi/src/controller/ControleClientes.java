package controller;

import DAO.ClientesDAO;
import model.ClientesModel;
import java.util.List;

public class ControleClientes {
    private ClientesDAO dao;

    public ControleClientes() {
        dao = new ClientesDAO();
    }

    public void adicionarCliente(ClientesModel cliente) throws Exception {
        dao.inserir(cliente);
    }

    public List<ClientesModel> listarClientes() throws Exception {
        return dao.listar();
    }

    public void atualizarCliente(ClientesModel cliente) throws Exception {
        dao.atualizar(cliente);
    }

    public void excluirCliente(int id_cli_pk) throws Exception {
        dao.excluir(id_cli_pk);
    }
}
