package view;

import controller.ControleClientes;
import model.ClientesModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ClientesView extends JFrame {
    private ControleClientes controle;
    private JTextField txtId, txtNome, txtCpf, txtTelefone, txtEstacionamento;
    private JTable tabela;
    private DefaultTableModel modelo;

    public ClientesView() {
        controle = new ControleClientes();

        setTitle("Sistema de Clientes - Swing");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel(new BorderLayout());

        // Painel de formulário
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        txtId = new JTextField();
        txtNome = new JTextField();
        txtCpf = new JTextField();
        txtTelefone = new JTextField();
        txtEstacionamento = new JTextField();

        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Nome:"));
        form.add(txtNome);
        form.add(new JLabel("CPF:"));
        form.add(txtCpf);
        form.add(new JLabel("Telefone:"));
        form.add(txtTelefone);
        form.add(new JLabel("ID Estacionamento:"));
        form.add(txtEstacionamento);

        painel.add(form, BorderLayout.NORTH);

        // Botões
        JPanel botoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        botoes.add(btnAdicionar);
        botoes.add(btnListar);
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);

        painel.add(botoes, BorderLayout.CENTER);

        // Tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "CPF", "Telefone", "Estacionamento"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.SOUTH);

        add(painel);

        // Eventos dos botões
        btnAdicionar.addActionListener(e -> {
            try {
                ClientesModel cliente = new ClientesModel();
                cliente.setCli_nome(txtNome.getText());
                cliente.setCli_cpf(txtCpf.getText());
                cliente.setCli_telefone(txtTelefone.getText());
                cliente.setId_esta_fk(Integer.parseInt(txtEstacionamento.getText()));
                controle.adicionarCliente(cliente);
                JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");
                listarClientes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        btnListar.addActionListener(e -> listarClientes());

        btnAtualizar.addActionListener(e -> {
            try {
                ClientesModel cliente = new ClientesModel();
                cliente.setId_cli_pk(Integer.parseInt(txtId.getText()));
                cliente.setCli_nome(txtNome.getText());
                cliente.setCli_cpf(txtCpf.getText());
                cliente.setCli_telefone(txtTelefone.getText());
                cliente.setId_esta_fk(Integer.parseInt(txtEstacionamento.getText()));
                controle.atualizarCliente(cliente);
                JOptionPane.showMessageDialog(this, "Cliente atualizado!");
                listarClientes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                controle.excluirCliente(id);
                JOptionPane.showMessageDialog(this, "Cliente excluído!");
                listarClientes();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });
    }

    private void listarClientes() {
        try {
            modelo.setRowCount(0); // limpa tabela
            List<ClientesModel> lista = controle.listarClientes();
            for (ClientesModel c : lista) {
                modelo.addRow(new Object[]{
                        c.getId_cli_pk(),
                        c.getCli_nome(),
                        c.getCli_cpf(),
                        c.getCli_telefone(),
                        c.getId_esta_fk()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientesView().setVisible(true));
    }
}
