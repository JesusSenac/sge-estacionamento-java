package view;

import controller.ControleFuncionarios;
import model.FuncionariosModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FuncionariosView extends JFrame {
    private JTextField txtId, txtNome, txtFuncao, txtJornada, txtLogin, txtSenha, txtSalario, txtEstacionamento;
    private JButton btnInserir, btnAtualizar, btnExcluir, btnListar;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ControleFuncionarios controle;

    public FuncionariosView() {
        super("CRUD Funcionários - Estacionamento");
        controle = new ControleFuncionarios();

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(8, 2));
        painelForm.add(new JLabel("ID:"));
        txtId = new JTextField();
        painelForm.add(txtId);

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Função:"));
        txtFuncao = new JTextField();
        painelForm.add(txtFuncao);

        painelForm.add(new JLabel("Jornada:"));
        txtJornada = new JTextField();
        painelForm.add(txtJornada);

        painelForm.add(new JLabel("Login:"));
        txtLogin = new JTextField();
        painelForm.add(txtLogin);

        painelForm.add(new JLabel("Senha:"));
        txtSenha = new JTextField();
        painelForm.add(txtSenha);

        painelForm.add(new JLabel("Salário:"));
        txtSalario = new JTextField();
        painelForm.add(txtSalario);

        painelForm.add(new JLabel("ID Estacionamento:"));
        txtEstacionamento = new JTextField();
        painelForm.add(txtEstacionamento);

        // Botões
        JPanel painelBotoes = new JPanel();
        btnInserir = new JButton("Inserir");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnListar = new JButton("Listar");
        painelBotoes.add(btnInserir);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListar);

        // Tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Função", "Jornada", "Login", "Senha", "Salário", "Estacionamento"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        // Layout principal
        setLayout(new BorderLayout());
        add(painelForm, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Eventos
        btnInserir.addActionListener(e -> inserirFuncionario());
        btnAtualizar.addActionListener(e -> atualizarFuncionario());
        btnExcluir.addActionListener(e -> excluirFuncionario());
        btnListar.addActionListener(e -> listarFuncionarios());

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void inserirFuncionario() {
        try {
            FuncionariosModel f = new FuncionariosModel();
            f.setFunc_nome(txtNome.getText());
            f.setFunc_funcao(txtFuncao.getText());
            f.setFunc_jornada_de_trabalho(txtJornada.getText());
            f.setFunc_login(txtLogin.getText());
            f.setFunc_senha(txtSenha.getText());
            f.setFunc_salario(Double.parseDouble(txtSalario.getText()));
            f.setId_esta_fk(Integer.parseInt(txtEstacionamento.getText()));
            controle.adicionarFuncionario(f);
            JOptionPane.showMessageDialog(this, "Funcionário inserido com sucesso!");
            listarFuncionarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
        }
    }

    private void atualizarFuncionario() {
        try {
            FuncionariosModel f = new FuncionariosModel();
            f.setId_func_pk(Integer.parseInt(txtId.getText()));
            f.setFunc_nome(txtNome.getText());
            f.setFunc_funcao(txtFuncao.getText());
            f.setFunc_jornada_de_trabalho(txtJornada.getText());
            f.setFunc_login(txtLogin.getText());
            f.setFunc_senha(txtSenha.getText());
            f.setFunc_salario(Double.parseDouble(txtSalario.getText()));
            f.setId_esta_fk(Integer.parseInt(txtEstacionamento.getText()));
            controle.atualizarFuncionario(f);
            JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");
            listarFuncionarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluirFuncionario() {
        try {
            int id = Integer.parseInt(txtId.getText());
            controle.excluirFuncionario(id);
            JOptionPane.showMessageDialog(this, "Funcionário excluído com sucesso!");
            listarFuncionarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void listarFuncionarios() {
        try {
            modelo.setRowCount(0);
            List<FuncionariosModel> lista = controle.listarFuncionarios();
            for (FuncionariosModel f : lista) {
                modelo.addRow(new Object[]{
                        f.getId_func_pk(),
                        f.getFunc_nome(),
                        f.getFunc_funcao(),
                        f.getFunc_jornada_de_trabalho(),
                        f.getFunc_login(),
                        f.getFunc_senha(),
                        f.getFunc_salario(),
                        f.getId_esta_fk()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FuncionariosView::new);
    }
}

