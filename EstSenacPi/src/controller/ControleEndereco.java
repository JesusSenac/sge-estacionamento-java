package controller;

import java.util.List;

import DAO.EnderecoDAO;
import model.EnderecoModel;

public class ControleEndereco {

    private final EnderecoDAO enderecoDAO;

    public ControleEndereco() {
        this.enderecoDAO = new EnderecoDAO();
    }

    // Métodos que chamam o DAO

    public void adicionarEndereco(EnderecoModel endereco) {
        enderecoDAO.inserir(endereco);
    }

    public List<EnderecoModel> listarTodosEnderecos() {
        return enderecoDAO.listarTodos();
    }
    
    public EnderecoModel buscarEnderecoPorId(int id) {
        return enderecoDAO.buscarPorId(id);
    }

    public void atualizarEndereco(EnderecoModel endereco) {
        enderecoDAO.atualizar(endereco);
    }

    public void deletarEndereco(int id) {
        enderecoDAO.deletar(id);
    }
}

