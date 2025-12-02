package view;


import javax.swing.*;
import java.awt.*;
import java.util.List;

import controller.ControleEndereco;
import model.EnderecoModel;

public class EnderecoView extends JFrame {

    private final ControleEndereco controle;
    private final JTextArea areaTexto;

    public EnderecoView() {
        this.controle = new ControleEndereco();

        // Configuração da janela
        setTitle("🗺️ CRUD Endereço (MVC)");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(1, 6, 5, 5));
        JButton btnAdicionar = new JButton("➕ Adicionar");
        JButton btnListar = new JButton("📝 Listar");
        JButton btnBuscar = new JButton("🔍 Buscar");
        JButton btnAtualizar = new JButton("✏️ Atualizar");
        JButton btnDeletar = new JButton("🗑️ Deletar");
        JButton btnSair = new JButton("🚪 Sair");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDeletar);
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.NORTH);

        // Área de texto para exibir resultados
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        // Ações dos botões
        btnAdicionar.addActionListener(e -> adicionarNovoEndereco());
        btnListar.addActionListener(e -> listarTodosEnderecos());
        btnBuscar.addActionListener(e -> buscarEnderecoPorId());
        btnAtualizar.addActionListener(e -> atualizarEndereco());
        btnDeletar.addActionListener(e -> deletarEndereco());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void adicionarNovoEndereco() {
        String rua = JOptionPane.showInputDialog(this, "Rua:");
        String bairro = JOptionPane.showInputDialog(this, "Bairro:");
        String cidade = JOptionPane.showInputDialog(this, "Cidade:");
        String pais = JOptionPane.showInputDialog(this, "País:");

        if (rua != null && bairro != null && cidade != null && pais != null) {
            EnderecoModel novoEndereco = new EnderecoModel(rua, bairro, cidade, pais);
            controle.adicionarEndereco(novoEndereco);
            areaTexto.setText("✅ Endereço adicionado com sucesso!");
        }
    }

    private void listarTodosEnderecos() {
        List<EnderecoModel> enderecos = controle.listarTodosEnderecos();
        if (enderecos.isEmpty()) {
            areaTexto.setText("Nenhum endereço encontrado.");
        } else {
            StringBuilder sb = new StringBuilder("--- Lista de Endereços ---\n");
            for (EnderecoModel end : enderecos) {
                sb.append(end).append("\n");
            }
            areaTexto.setText(sb.toString());
        }
    }

    private void buscarEnderecoPorId() {
        String input = JOptionPane.showInputDialog(this, "Digite o ID do Endereço:");
        try {
            int id = Integer.parseInt(input);
            EnderecoModel endereco = controle.buscarEnderecoPorId(id);
            if (endereco != null) {
                areaTexto.setText("Endereço encontrado:\n" + endereco);
            } else {
                areaTexto.setText("🚫 Endereço com ID " + id + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            areaTexto.setText("🚫 ID inválido.");
        }
    }

    private void atualizarEndereco() {
        String input = JOptionPane.showInputDialog(this, "Digite o ID do Endereço a ser atualizado:");
        try {
            int id = Integer.parseInt(input);
            EnderecoModel existente = controle.buscarEnderecoPorId(id);
            if (existente == null) {
                areaTexto.setText("🚫 Endereço com ID " + id + " não encontrado.");
                return;
            }

            String novaRua = JOptionPane.showInputDialog(this, "Nova Rua:", existente.getEnd_rua());
            String novoBairro = JOptionPane.showInputDialog(this, "Novo Bairro:", existente.getEnd_bairro());
            String novaCidade = JOptionPane.showInputDialog(this, "Nova Cidade:", existente.getEnd_cidade());
            String novoPais = JOptionPane.showInputDialog(this, "Novo País:", existente.getEnd_pais());

            EnderecoModel atualizado = new EnderecoModel(id, novaRua, novoBairro, novaCidade, novoPais);
            controle.atualizarEndereco(atualizado);
            areaTexto.setText("✅ Endereço atualizado com sucesso!");
        } catch (NumberFormatException e) {
            areaTexto.setText("🚫 ID inválido.");
        }
    }

    private void deletarEndereco() {
        String input = JOptionPane.showInputDialog(this, "Digite o ID do Endereço a ser deletado:");
        try {
            int id = Integer.parseInt(input);
            controle.deletarEndereco(id);
            areaTexto.setText("✅ Endereço deletado com sucesso!");
        } catch (NumberFormatException e) {
            areaTexto.setText("🚫 ID inválido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EnderecoView view = new EnderecoView();
            view.setVisible(true);
        });
    }
}

