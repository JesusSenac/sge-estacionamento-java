package view;


import controller.ControleVagas;
import model.VagasModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VagasView extends JFrame {
    private ControleVagas controle;
    private JTextField txtCodigo, txtAndar, txtTipo, txtIdEstacionamento;
    private JCheckBox chkPreferencial;
    private JComboBox<String> cbStatus;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public VagasView() {
        controle = new ControleVagas();
        setTitle("Gerenciamento de Vagas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel(new BorderLayout());

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Dados da Vaga"));

        txtCodigo = new JTextField();
        cbStatus = new JComboBox<>(new String[]{"Livre", "Ocupada", "Reservada"});
        chkPreferencial = new JCheckBox("Preferencial");
        txtAndar = new JTextField();
        txtTipo = new JTextField();
        txtIdEstacionamento = new JTextField();

        formPanel.add(new JLabel("Código:"));
        formPanel.add(txtCodigo);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(cbStatus);
        formPanel.add(new JLabel("Preferencial:"));
        formPanel.add(chkPreferencial);
        formPanel.add(new JLabel("Andar:"));
        formPanel.add(txtAndar);
        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(txtTipo);
        formPanel.add(new JLabel("ID Estacionamento:"));
        formPanel.add(txtIdEstacionamento);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnInserir = new JButton("Inserir");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        buttonPanel.add(btnInserir);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnExcluir);

        // Tabela
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Código", "Status", "Preferencial", "Andar", "Tipo", "Estacionamento"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabela);

        painel.add(formPanel, BorderLayout.NORTH);
        painel.add(buttonPanel, BorderLayout.CENTER);
        painel.add(scrollTabela, BorderLayout.SOUTH);

        add(painel);

        // Eventos dos botões
        btnInserir.addActionListener(e -> inserirVaga());
        btnListar.addActionListener(e -> listarVagas());
        btnAtualizar.addActionListener(e -> atualizarVaga());
        btnExcluir.addActionListener(e -> excluirVaga());
    }

    private void inserirVaga() {
        try {
            VagasModel vaga = new VagasModel();
            vaga.setVagaCodigo(txtCodigo.getText());
            vaga.setVagaStatus(cbStatus.getSelectedItem().toString());
            vaga.setVagaPreferencial(chkPreferencial.isSelected());
            vaga.setVagaAndar(Integer.parseInt(txtAndar.getText()));
            vaga.setVagaTipo(txtTipo.getText());
            vaga.setIdEstaFk(Integer.parseInt(txtIdEstacionamento.getText()));

            controle.adicionarVaga(vaga);
            JOptionPane.showMessageDialog(this, "Vaga inserida com sucesso!");
            listarVagas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
        }
    }

    private void listarVagas() {
        try {
            modeloTabela.setRowCount(0); // limpar tabela
            List<VagasModel> lista = controle.listarVagas();
            for (VagasModel v : lista) {
                modeloTabela.addRow(new Object[]{
                        v.getIdVagaPk(),
                        v.getVagaCodigo(),
                        v.getVagaStatus(),
                        v.isVagaPreferencial(),
                        v.getVagaAndar(),
                        v.getVagaTipo(),
                        v.getIdEstaFk()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

    private void atualizarVaga() {
        try {
            VagasModel vaga = new VagasModel();
            vaga.setVagaCodigo(txtCodigo.getText());
            vaga.setVagaStatus(cbStatus.getSelectedItem().toString());
            vaga.setVagaPreferencial(chkPreferencial.isSelected());
            vaga.setVagaAndar(Integer.parseInt(txtAndar.getText()));
            vaga.setVagaTipo(txtTipo.getText());
            vaga.setIdEstaFk(Integer.parseInt(txtIdEstacionamento.getText()));

            controle.atualizarVaga(vaga);
            JOptionPane.showMessageDialog(this, "Vaga atualizada com sucesso!");
            listarVagas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluirVaga() {
        try {
            String codigo = txtCodigo.getText();
            controle.excluirVaga(codigo);
            JOptionPane.showMessageDialog(this, "Vaga excluída com sucesso!");
            listarVagas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VagasView().setVisible(true));
    }
}
