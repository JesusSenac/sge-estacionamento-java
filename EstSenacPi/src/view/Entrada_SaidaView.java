package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.ControleEntrada_Saida;
import model.Entrada_SaidaModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;

public class Entrada_SaidaView extends JFrame {
    private JTextField txtId, txtIdVeiculo;
    private JButton btnInserir, btnListar, btnAtualizar, btnExcluir;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ControleEntrada_Saida controle;

    public Entrada_SaidaView() {
        super("CRUD Entrada_Saida - Estacionamento");
        controle = new ControleEntrada_Saida();

        // Painel de inputs
        JPanel painelInputs = new JPanel(new GridLayout(2, 2));
        painelInputs.add(new JLabel("ID Entrada:"));
        txtId = new JTextField();
        painelInputs.add(txtId);

        painelInputs.add(new JLabel("ID Veículo:"));
        txtIdVeiculo = new JTextField();
        painelInputs.add(txtIdVeiculo);

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
        modelo = new DefaultTableModel(new String[]{"ID", "Entrada", "Saída", "Veículo"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        // Layout principal
        setLayout(new BorderLayout());
        add(painelInputs, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Eventos
        btnInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Entrada_SaidaModel es = new Entrada_SaidaModel();
                    es.setEntra_data_hora_entrada(new Date());
                    es.setEntra_data_hora_saida(null);
                    es.setId_vei_fk(Integer.parseInt(txtIdVeiculo.getText()));
                    controle.adicionarEntradaSaida(es);
                    JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    modelo.setRowCount(0); // limpa tabela
                    List<Entrada_SaidaModel> lista = controle.listarEntradasSaidas();
                    for (Entrada_SaidaModel es : lista) {
                        modelo.addRow(new Object[]{
                                es.getId_entra_pk(),
                                es.getEntra_data_hora_entrada(),
                                es.getEntra_data_hora_saida(),
                                es.getId_vei_fk()
                        });
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Entrada_SaidaModel es = new Entrada_SaidaModel();
                    es.setId_entra_pk(Integer.parseInt(txtId.getText()));
                    es.setEntra_data_hora_entrada(new Date());
                    es.setEntra_data_hora_saida(new Date());
                    es.setId_vei_fk(Integer.parseInt(txtIdVeiculo.getText()));
                    controle.atualizarEntradaSaida(es);
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    controle.excluirEntradaSaida(id);
                    JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });

        // Configurações da janela
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método main para rodar a View
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Entrada_SaidaView());
    }
}

