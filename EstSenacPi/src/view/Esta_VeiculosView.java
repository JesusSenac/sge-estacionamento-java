
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.ControleEsta_Veiculos;
import model.Esta_VeiculosModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Esta_VeiculosView extends JFrame {
    private JTextField txtId, txtIdEsta, txtIdVei;
    private JButton btnInserir, btnAtualizar, btnExcluir, btnListar;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ControleEsta_Veiculos controle;

    public Esta_VeiculosView() {
        super("CRUD Esta_Veiculos - Swing");
        controle = new ControleEsta_Veiculos();

        // Painel de entrada
        JPanel painelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        painelForm.add(new JLabel("ID Registro:"));
        txtId = new JTextField();
        painelForm.add(txtId);

        painelForm.add(new JLabel("ID Estacionamento:"));
        txtIdEsta = new JTextField();
        painelForm.add(txtIdEsta);

        painelForm.add(new JLabel("ID Veículo:"));
        txtIdVei = new JTextField();
        painelForm.add(txtIdVei);

        // Botões
        btnInserir = new JButton("Inserir");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnListar = new JButton("Listar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnInserir);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListar);

        // Tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Estacionamento", "Veículo"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        // Layout principal
        setLayout(new BorderLayout());
        add(painelForm, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Eventos
        btnInserir.addActionListener(e -> inserir());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());
        btnListar.addActionListener(e -> listar());

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inserir() {
        try {
            int idEsta = Integer.parseInt(txtIdEsta.getText());
            int idVei = Integer.parseInt(txtIdVei.getText());
            Esta_VeiculosModel ev = new Esta_VeiculosModel(0, idEsta, idVei);
            controle.adicionar(ev);
            JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
        }
    }

    private void atualizar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            int idEsta = Integer.parseInt(txtIdEsta.getText());
            int idVei = Integer.parseInt(txtIdVei.getText());
            Esta_VeiculosModel ev = new Esta_VeiculosModel(id, idEsta, idVei);
            controle.atualizar(ev);
            JOptionPane.showMessageDialog(this, "Registro atualizado com sucesso!");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluir() {
        try {
            int id = Integer.parseInt(txtId.getText());
            controle.excluir(id);
            JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void listar() {
        try {
            modelo.setRowCount(0); // limpa tabela
            List<Esta_VeiculosModel> lista = controle.listarTodos();
            for (Esta_VeiculosModel ev : lista) {
                modelo.addRow(new Object[]{
                    ev.getId_esta_vei_pk(),
                    ev.getId_esta_fk(),
                    ev.getId_vei_fk()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Esta_VeiculosView::new);
    }
}
