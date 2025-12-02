package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.ControleTarifas;
import model.TarifasModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TarifasView extends JFrame {
    private JTextField txtId, txtTipo, txtValor, txtPeriodo, txtIdEsta;
    private JButton btnInserir, btnListar, btnAtualizar, btnExcluir;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ControleTarifas controle;

    public TarifasView() {
        super("CRUD Tarifas - Estacionamento");
        controle = new ControleTarifas();

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(5, 2));
        painelForm.add(new JLabel("ID Tarifa:"));
        txtId = new JTextField();
        painelForm.add(txtId);

        painelForm.add(new JLabel("Tipo:"));
        txtTipo = new JTextField();
        painelForm.add(txtTipo);

        painelForm.add(new JLabel("Valor:"));
        txtValor = new JTextField();
        painelForm.add(txtValor);

        painelForm.add(new JLabel("Período:"));
        txtPeriodo = new JTextField();
        painelForm.add(txtPeriodo);

        painelForm.add(new JLabel("ID Estacionamento:"));
        txtIdEsta = new JTextField();
        painelForm.add(txtIdEsta);

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        btnInserir = new JButton("Inserir");
        btnListar = new JButton("Listar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnInserir);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);

        // Tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Tipo", "Valor", "Período", "ID Estacionamento"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        // Layout principal
        setLayout(new BorderLayout());
        add(painelForm, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Eventos
        btnInserir.addActionListener(e -> inserir());
        btnListar.addActionListener(e -> listar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());

        // Configuração da janela
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inserir() {
        try {
            TarifasModel t = new TarifasModel(
                0,
                txtTipo.getText(),
                Double.parseDouble(txtValor.getText()),
                txtPeriodo.getText(),
                Integer.parseInt(txtIdEsta.getText())
            );
            controle.adicionarTarifa(t);
            JOptionPane.showMessageDialog(this, "Tarifa inserida com sucesso!");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
        }
    }

    private void listar() {
        try {
            modelo.setRowCount(0);
            List<TarifasModel> lista = controle.listarTarifas();
            for (TarifasModel t : lista) {
                modelo.addRow(new Object[]{
                    t.getIdTarifPk(),
                    t.getTarifTipo(),
                    t.getTarifValor(),
                    t.getTarifPeriodo(),
                    t.getIdEstaFk()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

    private void atualizar() {
        try {
            TarifasModel t = new TarifasModel(
                Integer.parseInt(txtId.getText()),
                txtTipo.getText(),
                Double.parseDouble(txtValor.getText()),
                txtPeriodo.getText(),
                Integer.parseInt(txtIdEsta.getText())
            );
            controle.atualizarTarifa(t);
            JOptionPane.showMessageDialog(this, "Tarifa atualizada com sucesso!");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluir() {
        try {
            int id = Integer.parseInt(txtId.getText());
            controle.excluirTarifa(id);
            JOptionPane.showMessageDialog(this, "Tarifa excluída com sucesso!");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TarifasView());
    }
}

