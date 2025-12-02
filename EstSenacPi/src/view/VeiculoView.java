package view;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.ControleVeiculo;
import model.VeiculoModel;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VeiculoView extends JFrame {
    private ControleVeiculo controle;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    // Campos de entrada
    private JTextField txtNome, txtModelo, txtPlaca, txtCor, txtTel, txtHora, txtVaga, txtAndar;

    public VeiculoView() {
        super("Sistema de Estacionamento - Veículos");
        
        // ao fecchar a tela somente fechar esta tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // <<< AQUI
        //setLocationRelativeTo(null);
        //----------------------------------------------------------
        
        controle = new ControleVeiculo();
        
        

        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(9, 2));
        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        painelForm.add(txtModelo);

        painelForm.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        painelForm.add(txtPlaca);

        painelForm.add(new JLabel("Cor:"));
        txtCor = new JTextField();
        painelForm.add(txtCor);

        painelForm.add(new JLabel("Telefone:"));
        txtTel = new JTextField();
        painelForm.add(txtTel);

        painelForm.add(new JLabel("Hora:"));
        txtHora = new JTextField();
        painelForm.add(txtHora);

        painelForm.add(new JLabel("Vaga:"));
        txtVaga = new JTextField();
        painelForm.add(txtVaga);

        painelForm.add(new JLabel("Andar:"));
        txtAndar = new JTextField();
        painelForm.add(txtAndar);

        add(painelForm, BorderLayout.NORTH);

        // Botões
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnListar = new JButton("Listar");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListar);

        add(painelBotoes, BorderLayout.CENTER);

        // Tabela
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Modelo", "Placa", "Cor", "Telefone", "Hora", "Data Entrada", "Vaga", "Andar"}, 0);
        tabela = new JTable(modeloTabela);
        add(new JScrollPane(tabela), BorderLayout.SOUTH);

        // Eventos
        btnAdicionar.addActionListener(e -> adicionarVeiculo());
        btnListar.addActionListener(e -> listarVeiculos());
        btnAtualizar.addActionListener(e -> atualizarVeiculo());
        btnExcluir.addActionListener(e -> excluirVeiculo());
        
        

     // ✅ Evento para preencher os campos ao clicar na linha da tabela
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int linhaSelecionada = tabela.getSelectedRow();
              if (linhaSelecionada != -1) {
                         txtNome.setText(tabela.getValueAt(linhaSelecionada, 1).toString());
                         txtModelo.setText(tabela.getValueAt(linhaSelecionada, 2).toString());
                         txtPlaca.setText(tabela.getValueAt(linhaSelecionada, 3).toString());
                         txtCor.setText(tabela.getValueAt(linhaSelecionada, 4).toString());
                         txtTel.setText(tabela.getValueAt(linhaSelecionada, 5).toString());
                         txtHora.setText(tabela.getValueAt(linhaSelecionada, 6).toString());
                         txtVaga.setText(tabela.getValueAt(linhaSelecionada, 8).toString());
                         txtAndar.setText(tabela.getValueAt(linhaSelecionada, 9).toString());
             }
          }
        });

        

        //setSize(900, 600);
        setSize(1200, 900);
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // fecha somente esta tela
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
   

    private void adicionarVeiculo() {
        try {
            VeiculoModel v = new VeiculoModel();
            v.setVeiNome(txtNome.getText());
            v.setVeiModelo(txtModelo.getText());
            v.setVeiPlaca(txtPlaca.getText());
            v.setVeiCor(txtCor.getText());
            v.setVeiTel(txtTel.getText());
            v.setVeiHora(txtHora.getText());
            v.setVeiDtEntrada(new java.util.Date());
            v.setVeiVaga(txtVaga.getText());
            v.setVeiAndar(txtAndar.getText());

            controle.adicionarVeiculo(v);
            JOptionPane.showMessageDialog(this, "Veículo adicionado com sucesso!");
            listarVeiculos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar: " + ex.getMessage());
        }
    }

    private void listarVeiculos() {
        try {
            modeloTabela.setRowCount(0);
            List<VeiculoModel> lista = controle.listarVeiculos();
            for (VeiculoModel v : lista) {
                modeloTabela.addRow(new Object[]{
                        v.getIdVeiPk(),
                        v.getVeiNome(),
                        v.getVeiModelo(),
                        v.getVeiPlaca(),
                        v.getVeiCor(),
                        v.getVeiTel(),
                        v.getVeiHora(),
                        v.getVeiDtEntrada(),
                        v.getVeiVaga(),
                        v.getVeiAndar()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

    private void atualizarVeiculo() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo na tabela para atualizar.");
                return;
            }

            VeiculoModel v = new VeiculoModel();
            v.setIdVeiPk((int) tabela.getValueAt(linha, 0));
            v.setVeiNome(txtNome.getText());
            v.setVeiModelo(txtModelo.getText());
            v.setVeiPlaca(txtPlaca.getText());
            v.setVeiCor(txtCor.getText());
            v.setVeiTel(txtTel.getText());
            v.setVeiHora(txtHora.getText());
            v.setVeiDtEntrada(new java.util.Date());
            v.setVeiVaga(txtVaga.getText());
            v.setVeiAndar(txtAndar.getText());

            controle.atualizarVeiculo(v);
            JOptionPane.showMessageDialog(this, "Veículo atualizado com sucesso!");
            listarVeiculos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluirVeiculo() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo na tabela para excluir.");
                return;
            }

            int id = (int) tabela.getValueAt(linha, 0);
            controle.excluirVeiculo(id);
            JOptionPane.showMessageDialog(this, "Veículo excluído com sucesso!");
            listarVeiculos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VeiculoView());
    }
}
