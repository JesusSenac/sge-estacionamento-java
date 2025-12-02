package view;


import controller.ControleEstacionamento;
import model.EstacionamentoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EstacionamentoViewSwing extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleEstacionamento controle;
    private JTextField txtNome, txtCnpj, txtFantasia, txtRazao, txtRepresentante, txtIdEnd;
    private JTable tabela;
    private DefaultTableModel modelo;

    public EstacionamentoViewSwing() {
        controle = new ControleEstacionamento();

        setTitle("Cadastro de Estacionamento");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(7, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createTitledBorder("Dados do Estacionamento"));

        txtNome = new JTextField();
        txtCnpj = new JTextField();
        txtFantasia = new JTextField();
        txtRazao = new JTextField();
        txtRepresentante = new JTextField();
        txtIdEnd = new JTextField();

        painelForm.add(new JLabel("Nome:"));
        painelForm.add(txtNome);
        painelForm.add(new JLabel("CNPJ:"));
        painelForm.add(txtCnpj);
        painelForm.add(new JLabel("Nome Fantasia:"));
        painelForm.add(txtFantasia);
        painelForm.add(new JLabel("Razão Social:"));
        painelForm.add(txtRazao);
        painelForm.add(new JLabel("Representante:"));
        painelForm.add(txtRepresentante);
        painelForm.add(new JLabel("ID Endereço:"));
        painelForm.add(txtIdEnd);

        // Botões
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnListar = new JButton("Listar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListar);

        // Tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "CNPJ", "Fantasia", "Razão Social", "Representante", "ID End"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        add(painelForm, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Ações dos botões
        btnAdicionar.addActionListener(e -> adicionar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());
        btnListar.addActionListener(e -> listar());

        setVisible(true);
    }

    private void adicionar() {
        try {
            EstacionamentoModel est = new EstacionamentoModel(
                0,
                txtNome.getText(),
                Integer.parseInt(txtCnpj.getText()),
                txtFantasia.getText(),
                txtRazao.getText(),
                txtRepresentante.getText(),
                Integer.parseInt(txtIdEnd.getText())
            );
            controle.adicionarEstacionamento(est);
            JOptionPane.showMessageDialog(this, "Estacionamento adicionado com sucesso!");
            listar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizar() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelo.getValueAt(linha, 0);
                EstacionamentoModel est = new EstacionamentoModel(
                    id,
                    txtNome.getText(),
                    Integer.parseInt(txtCnpj.getText()),
                    txtFantasia.getText(),
                    txtRazao.getText(),
                    txtRepresentante.getText(),
                    Integer.parseInt(txtIdEnd.getText())
                );
                controle.atualizarEstacionamento(est);
                JOptionPane.showMessageDialog(this, "Estacionamento atualizado!");
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para atualizar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void excluir() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelo.getValueAt(linha, 0);
                controle.excluirEstacionamento(id);
                JOptionPane.showMessageDialog(this, "Estacionamento excluído!");
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void listar() {
        try {
            modelo.setRowCount(0);
            List<EstacionamentoModel> lista = controle.listarEstacionamentos();
            for (EstacionamentoModel e : lista) {
                modelo.addRow(new Object[]{
                    e.getIdEstaPk(),
                    e.getEstaNome(),
                    e.getEstaCnpj(),
                    e.getEstaNomeFantasia(),
                    e.getEstaRazaoSocial(),
                    e.getEstaRepresentante(),
                    e.getIdEndFk()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EstacionamentoViewSwing::new);
    }
}

