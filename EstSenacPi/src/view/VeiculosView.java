package view;


import controller.ControleVeiculos;
import model.VeiculosModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VeiculosView extends JFrame {
    private ControleVeiculos controle;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private JTextField txtId, txtModelo, txtCor, txtMarca, txtPlaca;

    public VeiculosView() {
        controle = new ControleVeiculos();

        setTitle("Cadastro de Veículos");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel(new BorderLayout());

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(6, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createTitledBorder("Dados do Veículo"));

        painelForm.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        painelForm.add(txtId);

        painelForm.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        painelForm.add(txtModelo);

        painelForm.add(new JLabel("Cor:"));
        txtCor = new JTextField();
        painelForm.add(txtCor);

        painelForm.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        painelForm.add(txtMarca);

        painelForm.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        painelForm.add(txtPlaca);

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnInserir = new JButton("Inserir");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnListar = new JButton("Listar");

        painelBotoes.add(btnInserir);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListar);

        painelForm.add(painelBotoes);

        // Tabela
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Modelo", "Cor", "Marca", "Placa"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabela);

        painel.add(painelForm, BorderLayout.NORTH);
        painel.add(scrollTabela, BorderLayout.CENTER);

        add(painel);

        // Eventos
        btnInserir.addActionListener(e -> inserirVeiculo());
        btnAtualizar.addActionListener(e -> atualizarVeiculo());
        btnExcluir.addActionListener(e -> excluirVeiculo());
        btnListar.addActionListener(e -> listarVeiculos());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha != -1) {
                    txtId.setText(modeloTabela.getValueAt(linha, 0).toString());
                    txtModelo.setText(modeloTabela.getValueAt(linha, 1).toString());
                    txtCor.setText(modeloTabela.getValueAt(linha, 2).toString());
                    txtMarca.setText(modeloTabela.getValueAt(linha, 3).toString());
                    txtPlaca.setText(modeloTabela.getValueAt(linha, 4).toString());
                }
            }
        });
    }

    private void inserirVeiculo() {
        try {
            VeiculosModel v = new VeiculosModel(0, txtModelo.getText(), txtCor.getText(), txtMarca.getText(), txtPlaca.getText());
            controle.adicionarVeiculo(v);
            JOptionPane.showMessageDialog(this, "Veículo inserido com sucesso!");
            listarVeiculos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
        }
    }

    private void atualizarVeiculo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            VeiculosModel v = new VeiculosModel(id, txtModelo.getText(), txtCor.getText(), txtMarca.getText(), txtPlaca.getText());
            controle.atualizarVeiculo(v);
            JOptionPane.showMessageDialog(this, "Veículo atualizado com sucesso!");
            listarVeiculos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluirVeiculo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            controle.excluirVeiculo(id);
            JOptionPane.showMessageDialog(this, "Veículo excluído com sucesso!");
            listarVeiculos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void listarVeiculos() {
        try {
            modeloTabela.setRowCount(0);
            List<VeiculosModel> lista = controle.listarVeiculos();
            for (VeiculosModel v : lista) {
                modeloTabela.addRow(new Object[]{
                        v.getIdVeiPk(),
                        v.getVeiModelo(),
                        v.getVeiCor(),
                        v.getVeiMarca(),
                        v.getVeiPlaca()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VeiculosView().setVisible(true));
    }
}

