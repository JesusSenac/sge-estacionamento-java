package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import controller.ControleEndereco;
// jesus implementation
import controller.ControleVeiculo;
import model.VeiculoModel;



@SuppressWarnings("unused")
public class fina {
	
	 
    // --- VARIÁVEIS DE CONFIGURAÇÃO GLOBAIS ---
    public static double HOURLY_RATE = 5.00; // Valor da Tarifa (R$ / Hora)
    public static int MAX_SPOTS = 100;      // Número máximo de vagas
    public static int MAX_FLOORS = 3;       // Número máximo de andares
    public static String CURRENT_THEME = "LIGHT"; // Tema atual

    // --- MODELOS DE TABELA ---
    private static DefaultTableModel activeTableModel;
    private static DefaultTableModel historyTableModel;

    // --- ÍCONES (Usando caracteres Unicode para simplicidade) ---
    private static final String ICON_LOGIN = "👤"; // ALTERADO: Boneco de Usuário
    private static final String ICON_REGISTER = "+";
    //private static final String ICON_REGISTER = "➕";
    private static final String ICON_EXIT = "❌";
    private static final String ICON_CHECKOUT = "💲";
    private static final String ICON_VEHICLE = "🚗";
    private static final String ICON_HISTORY = "📜";
    private static final String ICON_CASH = "💵";
    private static final String ICON_CARD = "💳";
    private static final String ICON_PIX = "📱";
    private static final String ICON_SETTINGS = "⚙️";
    
    
    public static void main(String[] args) {
        applyTheme(CURRENT_THEME);

        SwingUtilities.invokeLater(() -> {
            new LoginScreen();
        });
    }
    
    // --- MÉTODO: Aplica o tema visual (Melhorado para tema LIGHT) ---
    public static void applyTheme(String theme) {
        CURRENT_THEME = theme;
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // Ignora
            }
        }
        
        // Configurações para o tema DARK
        if ("DARK".equals(theme)) {
             UIManager.put("control", new Color(68, 68, 68));
             UIManager.put("info", new Color(128, 128, 128));
             UIManager.put("nimbusBase", new Color(18, 30, 49));
             UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
             UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
             UIManager.put("nimbusFocus", new Color(115, 164, 209));
             UIManager.put("nimbusGreen", new Color(176, 202, 0));
             UIManager.put("nimbusInfoBlue", new Color(66, 139, 202));
             UIManager.put("nimbusLightBackground", new Color(42, 42, 42));
             UIManager.put("nimbusOrange", new Color(191, 98, 4));
             UIManager.put("nimbusRed", new Color(169, 46, 34));
             UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
             UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
             UIManager.put("text", new Color(230, 230, 230));
        } 
        // Configurações aprimoradas para o tema LIGHT
        else {
             UIManager.put("control", new Color(245, 245, 245)); // Fundo de controle claro
             UIManager.put("info", new Color(240, 240, 240));
             UIManager.put("nimbusBase", new Color(255, 255, 255)); // Base branca
             UIManager.put("nimbusAlertYellow", new Color(255, 255, 153));
             UIManager.put("nimbusDisabledText", new Color(140, 140, 140));
             UIManager.put("nimbusFocus", new Color(180, 200, 220));
             UIManager.put("nimbusLightBackground", new Color(250, 250, 250)); // Fundo principal
             UIManager.put("text", new Color(30, 30, 30)); // Texto escuro
             UIManager.put("Table.gridColor", new Color(220, 220, 220)); // Grid suave
             UIManager.put("Table.background", Color.WHITE);
             UIManager.put("Table.alternateRowColor", new Color(240, 240, 240)); // Linhas alternadas
        }
    }


    // =================================================================
    // 1. TELA DE LOGIN
    // =================================================================
    static class LoginScreen extends JFrame implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final JTextField userField;
        private final JPasswordField passwordField;
        private final JButton loginButton;

        public LoginScreen() {
            setTitle("Sistema de Estacionamento - Login");
            setSize(350, 250); 
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Fundo baseado no tema
            mainPanel.setBackground(UIManager.getColor("nimbusLightBackground")); 

            // Painel de inputs
            JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 15));
            inputPanel.setOpaque(false);

            // Ícone de Usuário (Alterado)
            inputPanel.add(new JLabel(ICON_LOGIN + " Usuário:", SwingConstants.RIGHT));
            userField = new JTextField(15);
            inputPanel.add(userField);

            inputPanel.add(new JLabel("🔒 Senha:", SwingConstants.RIGHT));
            passwordField = new JPasswordField(15);
            inputPanel.add(passwordField);

            // Painel do botão
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setOpaque(false);
            
            //loginButton = new JButton("Entrar no Sistema " + ICON_LOGIN);
            loginButton = new JButton("Entrar no Sistema");
            loginButton.addActionListener(this);
            loginButton.setPreferredSize(new Dimension(250, 40));
            //loginButton.setBackground(new Color(46, 204, 113)); 
            loginButton.setBackground(new Color(0, 191, 255)); 
            //loginButton.setForeground(Color.WHITE);
            loginButton.setForeground(Color.BLACK);
            loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            loginButton.setFocusPainted(false); 
            loginButton.setBorder(BorderFactory.createRaisedBevelBorder());

            buttonPanel.add(loginButton);

            mainPanel.add(inputPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(mainPanel, BorderLayout.CENTER);
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("123")) {
                dispose();
                new MainTableScreen();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // =================================================================
    // 2. TELA PRINCIPAL
    // =================================================================
    static class MainTableScreen extends JFrame implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final JMenuItem registerVehicleItem;
        private final JMenuItem exitItem;
        private final JMenuItem settingsItem; 
        //private final JMenuItem settingsItemCadastro;
        private JTable activeTable;
        private JTable historyTable; 
        
      //--variaveis para implementar o metodo listar
        private ControleVeiculo controleVeiculo;
        
        public MainTableScreen() {
        	
        	controleVeiculo = new ControleVeiculo();
        	
        	
            setTitle("Sistema de Estacionamento - Gestão Completa");
            setSize(1200, 700);
            
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
            setLocationRelativeTo(null);

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    gerarRelatorioEFechar(); 
                }
            });

            // --- MENU SUPERIOR ---
            JMenuBar menuBar = new JMenuBar();
            
            JMenu optionsMenu = new JMenu("⚙️ Opções");
            optionsMenu.setMnemonic('O');

            settingsItem = new JMenuItem(ICON_SETTINGS + " Configurações");
            settingsItem.addActionListener(this);
            optionsMenu.add(settingsItem);
            optionsMenu.addSeparator();
            
            registerVehicleItem = new JMenuItem(ICON_REGISTER + " Registrar Veículo");
            registerVehicleItem.addActionListener(this);
            optionsMenu.add(registerVehicleItem);
            optionsMenu.addSeparator();

            exitItem = new JMenuItem(ICON_EXIT + " Sair e Fechar Caixa");
            exitItem.addActionListener(this);
            exitItem.setBackground(new Color(231, 76, 60)); 
            exitItem.setForeground(Color.WHITE);
            optionsMenu.add(exitItem);
            
            
            
            menuBar.add(optionsMenu);
            setJMenuBar(menuBar);
            
            // --- MENU SUPERIOR CADASTROS---
            
            //JMenuBar menuBarCadastro = new JMenuBar();
            //optionsMenu.setMnemonic('1');
            //JMenu optionsMenuCadastros = new JMenu("⚙️ Cadastros");
            //setJMenuBar(menuBarCadastro);
            
            
            //-------------------------------------------------------;
            

            // --- CRIAÇÃO DAS TABELAS (Modelos) ---
            String[] activeCols = {"Id", "Nome", "Modelo", "Placa", "Cor", "Telefone", "Vaga", "Andar", "Entrada"};
            activeTableModel = new DefaultTableModel(activeCols, 0);

            String[] historyCols = {"Placa", "Entrada", "Saída", "Tempo Total", "Valor Pago (R$)", "Forma Pagamento"};
            historyTableModel = new DefaultTableModel(historyCols, 0);

            // --- CONFIGURAÇÃO DAS TABELAS ---
            activeTable = new JTable(activeTableModel);
            historyTable = new JTable(historyTableModel);
            
            setupTableVisuals(activeTable);
            setupTableVisuals(historyTable);
            
            customizeActiveTableRenderer(activeTable);

            JScrollPane scrollActive = new JScrollPane(activeTable);
            setupActiveTableContextMenu();

            customizeHistoryTableRenderer(historyTable);
            JScrollPane scrollHistory = new JScrollPane(historyTable);

            // --- PAINEL PRINCIPAL (LAYOUT) ---
            JPanel mainContentPanel = new JPanel(new BorderLayout());
            mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            mainContentPanel.setBackground(UIManager.getColor("nimbusLightBackground")); 

            // --- Painel de Ações (Top Bar) ---
            JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            actionPanel.setOpaque(false);
            
            JButton registerButton = new JButton(ICON_REGISTER + " Registrar Novo Veículo");
            registerButton.addActionListener(e -> new RegisterVehicleScreen(this));
            registerButton.setBackground(new Color(52, 152, 219)); 
            registerButton.setForeground(Color.WHITE);
            registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            registerButton.setFocusPainted(false);
                      
            actionPanel.add(registerButton);
            mainContentPanel.add(actionPanel, BorderLayout.NORTH);
            
            
            //-----jesus---atualizarButton-------------------------------------------------
            JButton atualizarButton = new JButton(ICON_REGISTER + " Atualizar Veículo");
            
            
            atualizarButton.setBackground(new Color(52, 152, 219)); 
            atualizarButton.setForeground(Color.WHITE);
            atualizarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            atualizarButton.setFocusPainted(false);
            
            actionPanel.add(atualizarButton);
            mainContentPanel.add(actionPanel, BorderLayout.NORTH);
            
            
            

            // Adiciona ação ao botão atualizarButton ----------------jesus---------------
            
            atualizarButton.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           // Chama a nova tela
                    	   
                    	   //VeiculoView veiculoView = new VeiculoView();
                    	   //veiculoView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    	   //veiculoView.setVisible(true);
                    	   
                    	   SwingUtilities.invokeLater(() -> new VeiculoView());
                    	   
                       }
                   });

            
           
            
            //----------------------------------------------------------------------------
            
            
          //-----jesus---listarButton-------------------------------------------------
            JButton listarButton = new JButton(ICON_REGISTER + " Listar Veículo");
            
            
            listarButton.setBackground(new Color(52, 152, 219)); 
            listarButton.setForeground(Color.WHITE);
            listarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            listarButton.setFocusPainted(false);
            
            actionPanel.add(listarButton);
            mainContentPanel.add(actionPanel, BorderLayout.NORTH);
            
            listarButton.addActionListener(e -> listarVeiculos());
            
           
           
            
            //----------------------------------------------------------------------------
            
            
            
            

            // --- ABAS (Veículos Ativos e Histórico) ---
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab(ICON_VEHICLE + " Veículos Estacionados", scrollActive);
            tabbedPane.addTab(ICON_HISTORY + " Histórico de Saídas", scrollHistory);
            
            mainContentPanel.add(tabbedPane, BorderLayout.CENTER);
            add(mainContentPanel, BorderLayout.CENTER);
            setVisible(true);
        }
        
        //---jesus---listarVeiculos()----------------------------------------------

        public void listarVeiculos() {
        	
        	 try {
             	
        		
                 List<VeiculoModel> lista = controleVeiculo.listarVeiculos();
                 
                
                 activeTableModel.setRowCount(0);
                 
                 for (VeiculoModel v : lista) {
                 	activeTableModel.addRow(new Object[]{
                             v.getIdVeiPk(),
                             v.getVeiNome(),
                             v.getVeiModelo(),
                             v.getVeiPlaca(),
                             v.getVeiCor(),
                             v.getVeiTel(),
                             v.getVeiVaga(),
                             v.getVeiAndar(),
                             v.getVeiHora(),
                             v.getVeiDtEntrada()
                             
                             
                     });
                 	
                 	
                 	
                 }
             } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
                 
             }
			
		}

      //---------------------------------------------
        
        
		private void setupTableVisuals(JTable table) {
            table.setRowHeight(30); 
            table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            // Cores do tema LIGHT/DARK
            table.setGridColor(UIManager.getColor("Table.gridColor")); 
            table.setShowGrid(true); 
            table.setIntercellSpacing(new Dimension(1, 1)); 

            // Estilo do cabeçalho
            JTableHeader header = table.getTableHeader();
            header.setFont(new Font("Segoe UI", Font.BOLD, 15));
            header.setBackground(new Color(44, 62, 80)); 
            header.setForeground(Color.WHITE);
            header.setPreferredSize(new Dimension(header.getWidth(), 35));
            
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        private void customizeActiveTableRenderer(JTable table) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    c.setForeground(new Color(211, 84, 0)); 
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                    ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
                    return c;
                }
            });
            
            table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    c.setForeground(new Color(192, 57, 43)); 
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                    ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
                    return c;
                }
            });
            
            table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        }

        private void customizeHistoryTableRenderer(JTable table) {
            table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    String pagamento = (String) value;
                    JLabel label = (JLabel) c;
                    
                    if (pagamento != null) {
                        if (pagamento.startsWith("Cartão (Crédito)")) {
                            label.setText(ICON_CARD + " " + pagamento);
                            label.setForeground(new Color(41, 128, 185)); 
                        } else if (pagamento.startsWith("Cartão (Débito)")) {
                            label.setText(ICON_CARD + " " + pagamento);
                            label.setForeground(new Color(243, 156, 18)); 
                        } else if (pagamento.equals("Dinheiro")) {
                            label.setText(ICON_CASH + " " + pagamento);
                            label.setForeground(new Color(39, 174, 96)); 
                        } else if (pagamento.equals("Pix")) {
                            label.setText(ICON_PIX + " " + pagamento);
                            label.setForeground(new Color(142, 68, 173)); 
                        } else {
                            label.setForeground(Color.BLACK);
                        }
                    }
                    label.setFont(c.getFont().deriveFont(Font.BOLD));
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return c;
                }
            });
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
            table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 
            table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); 
        }

        private void gerarRelatorioEFechar() {
            double totalDoDia = 0.0;
            int totalCarrosAtendidos = historyTableModel.getRowCount();
            int carrosAtivos = activeTableModel.getRowCount();

            for (int i = 0; i < historyTableModel.getRowCount(); i++) {
                String valorStr = (String) historyTableModel.getValueAt(i, 4);
                valorStr = valorStr.replace(",", "."); 
                try {
                    totalDoDia += Double.parseDouble(valorStr);
                } catch (NumberFormatException ex) {
                }
            }

            String mensagem = "=== 💰 FECHAMENTO DE CAIXA ===\n\n" +
                              "Veículos Atendidos Hoje: " + totalCarrosAtendidos + "\n" +
                              "Veículos Ativos (pendentes): " + carrosAtivos + "\n" +
                              "Faturamento Total: R$ " + String.format("%.2f", totalDoDia) + "\n\n" +
                              "Deseja realmente fechar o sistema?";

            int opcao = JOptionPane.showConfirmDialog(this, mensagem, "Relatório Diário", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcao == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

        private void setupActiveTableContextMenu() {
        	
            JPopupMenu contextMenu = new JPopupMenu();
            JMenuItem checkoutItem = new JMenuItem(ICON_CHECKOUT + " Finalizar/Receber Pagamento");
            contextMenu.add(checkoutItem);

            checkoutItem.addActionListener(e -> {
                int selectedRow = activeTable.getSelectedRow();
                if (selectedRow != -1) {
                    performCheckout(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione um veículo para finalizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });

            activeTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) { checkPopup(e); }
                @Override
                public void mousePressed(MouseEvent e) { checkPopup(e); }

                private void checkPopup(MouseEvent e) {
                    if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
                        int row = activeTable.rowAtPoint(e.getPoint());
                        if (row >= 0 && row < activeTable.getRowCount()) {
                            activeTable.setRowSelectionInterval(row, row);
                            contextMenu.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            });
        }

        private void performCheckout(int row) {
            try {
            	
            	//$ Finalizar/Receber Pagamento
            	
            	Integer id_veiculo = (Integer) activeTableModel.getValueAt(row, 0);
            	
            	//System.out.println("id_veiculo = "+ id_veiculo.toString() );
            	
                String placa = (String) activeTableModel.getValueAt(row, 2);
                
                //System.out.println("placa = "+placa); ok
                
                //String entradaStr = (String) activeTableModel.getValueAt(row, 7);
                String entradaStr = (String) activeTableModel.getValueAt(row, 8); //ok jesus
                
                System.out.println("entrada = "+entradaStr);

                //System.out.println("placa = "+placa+" data de entrada = "+entradaStr);
                
                String saidaStr = (String) JOptionPane.showInputDialog(this,
                        "Veículo: " + placa + "\nEntrada: " + entradaStr + "\n\nDigite a Saída (HH:mm):", "Registro de Saída", JOptionPane.QUESTION_MESSAGE, UIManager.getIcon("OptionPane.questionIcon"), null, java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));

                
                
                if (saidaStr == null) return;

                if (!isValidTime(saidaStr)) {
                    JOptionPane.showMessageDialog(this, "Horário inválido! Use HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int minutosEntrada = timeToMinutes(entradaStr);
                int minutosSaida = timeToMinutes(saidaStr);
                int diferencaMinutos = minutosSaida - minutosEntrada;
                if (diferencaMinutos <= 0) diferencaMinutos += (24 * 60);

                double valorTotal = (diferencaMinutos) * (fina.HOURLY_RATE / 60.0); 

                int horasExibicao = diferencaMinutos / 60;
                int minutosExibicao = diferencaMinutos % 60;
                String tempoFormatado = String.format("%dh %dmin", horasExibicao, minutosExibicao);
                String valorFormatado = String.format("%.2f", valorTotal);

                String pagamento = null;
                
                String[] opcoesPagamento = {"Dinheiro", "Cartão", "Pix"};
                String selecaoPagamento = (String) JOptionPane.showInputDialog(this,
                        "Total: R$ " + valorFormatado + "\nTempo: " + tempoFormatado + "\n\nSelecione a Forma de Pagamento:",
                        "Caixa - Recebimento", JOptionPane.QUESTION_MESSAGE, null, opcoesPagamento, opcoesPagamento[0]);

                if (selecaoPagamento == null) return;

                if (selecaoPagamento.equals("Cartão")) {
                    String[] opcoesCartao = {"Crédito", "Débito"};
                    String tipoCartao = (String) JOptionPane.showInputDialog(this,
                            "Cartão: Selecione o tipo:", "Tipo de Cartão", 
                            JOptionPane.QUESTION_MESSAGE, null, opcoesCartao, opcoesCartao[0]);
                    
                    if (tipoCartao == null) return;
                    pagamento = selecaoPagamento + " (" + tipoCartao + ")";
                } else {
                    pagamento = selecaoPagamento;
                }
                
                int confirm = JOptionPane.showConfirmDialog(this, "Confirmar recebimento de R$ " + valorFormatado + " (" + pagamento + ") e saída do veículo?", "Finalizar Transação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    historyTableModel.addRow(new Object[]{
                        placa,
                        entradaStr,
                        saidaStr,
                        tempoFormatado,
                        valorFormatado.replace(".", ","), 
                        pagamento
                    });
                    
                    //--jesus----removendo-o registro da tabela------------------------
                    try {
						controleVeiculo.excluirVeiculo( id_veiculo ); 
						
						activeTableModel.removeRow(row);
						JOptionPane.showMessageDialog(this, "Baixa efetuada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					
                    } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    //-------------------------------------------------------------------
                
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro de Processamento", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private boolean isValidTime(String time) { return time.matches("\\d{1,2}:\\d{2}"); }
        private int timeToMinutes(String time) {
            String[] parts = time.split(":");
            return (Integer.parseInt(parts[0]) * 60) + Integer.parseInt(parts[1]);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerVehicleItem) {
                new RegisterVehicleScreen(this);
            } else if (e.getSource() == exitItem) {
                gerarRelatorioEFechar();
            } else if (e.getSource() == settingsItem) {
                new SettingsScreen(this);
            }
        }

        //original
        //public static void addVehicleToTable(String nome, String modelo, String placa, String cor, String telefone, String vaga, String andar, String hora) {
        //    if (activeTableModel != null) {
        //        activeTableModel.addRow(new Object[]{nome, modelo, placa, cor, telefone, vaga, andar, hora});
        //    }
        //}
        
        //mpdificado por jesus
        public static void addVehicleToTable(String id, String nome, String modelo, String placa, String cor, String telefone, String vaga, String andar, String hora) {
            if (activeTableModel != null) {
                activeTableModel.addRow(new Object[]{id, nome, modelo, placa, cor, telefone, vaga, andar, hora});
            }
        }

        //original
        //public static boolean isSpotTaken(String vaga, String andar) {
        //    if (activeTableModel == null) return false;
        //    for (int i = 0; i < activeTableModel.getRowCount(); i++) {
        //        String existingVaga = (String) activeTableModel.getValueAt(i, 5);
        //        String existingAndar = (String) activeTableModel.getValueAt(i, 6);
        //        if (existingVaga.equals(vaga) && existingAndar.equals(andar)) return true;
        //    }
        //    return false;
        //}
        
        
        //modificado por jesus ----------------
        public static boolean isSpotTaken(String vaga, String andar) {
            if (activeTableModel == null) return false;
            for (int i = 0; i < activeTableModel.getRowCount(); i++) {
                String existingVaga = (String) activeTableModel.getValueAt(i, 6);
                String existingAndar = (String) activeTableModel.getValueAt(i, 7);
                if (existingVaga.equals(vaga) && existingAndar.equals(andar)) return true;
            }
            return false;
        }
        
        
        
        
        
    }

    // =================================================================
    // 3. TELA DE REGISTRO
    // =================================================================
    static class RegisterVehicleScreen extends JDialog implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final JTextField nameField, modelField, plateField, colorField, phoneField, entryTimeField;
        private final JComboBox<String> spotComboBox, floorComboBox;
        private final JButton registerButton;

        public RegisterVehicleScreen(JFrame parent) {
            super(parent, "Registrar Novo Veículo " + ICON_VEHICLE, true);
            setSize(500, 600); 
            setLocationRelativeTo(parent);

            Vector<String> spotNumbers = new Vector<>();
            for (int i = 1; i <= fina.MAX_SPOTS; i++) spotNumbers.add("Vaga " + String.format("%04d", i)); 
            spotComboBox = new JComboBox<>(spotNumbers);

            Vector<String> floorOptions = new Vector<>();
            for (int i = 1; i <= fina.MAX_FLOORS; i++) floorOptions.add("Andar " + i);
            floorComboBox = new JComboBox<>(floorOptions);

            JPanel panel = new JPanel(new GridLayout(9, 2, 15, 20)); 
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            // Fundo basedo no tema LIGHT
            panel.setBackground(UIManager.getColor("nimbusLightBackground"));

            panel.add(new JLabel("👤 Nome Completo:"));
            nameField = new JTextField();
            panel.add(nameField);

            panel.add(new JLabel("⚙️ Modelo:"));
            modelField = new JTextField();
            panel.add(modelField);

            panel.add(new JLabel("🅿️ Placa:"));
            plateField = new JTextField();
            panel.add(plateField);

            panel.add(new JLabel("🎨 Cor:"));
            colorField = new JTextField();
            panel.add(colorField);

            panel.add(new JLabel("📞 Telefone:"));
            phoneField = new JTextField();
            panel.add(phoneField);

            panel.add(new JLabel("⌚ Entrada (HH:mm):"));
            entryTimeField = new JTextField(java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
            panel.add(entryTimeField);

            panel.add(new JLabel("📍 Vaga:"));
            panel.add(spotComboBox);

            panel.add(new JLabel("🏢 Andar:"));
            panel.add(floorComboBox);

            registerButton = new JButton(ICON_REGISTER + " Registrar Veículo");
            registerButton.addActionListener(this);
            registerButton.setBackground(new Color(39, 174, 96)); 
            registerButton.setForeground(Color.WHITE);
            registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
            registerButton.setFocusPainted(false);
            
            panel.add(new JLabel());
            panel.add(registerButton);

            add(panel, BorderLayout.CENTER);
            setVisible(true);
        }
        

         // Método que chama a tela VeiculoView jesus------------------------
         public void AtualizaVeiculoScreen() {
             VeiculoView telaVeiculo = new VeiculoView();
             telaVeiculo.setVisible(true);
         }
         //---jesus------------------------ 

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerButton) {
            	
            	String id = "1";
                String nome = nameField.getText();
                String modelo = modelField.getText();
                String placa = plateField.getText().toUpperCase().trim();
                String cor = colorField.getText();
                String telefone = phoneField.getText();
                String hora = entryTimeField.getText().trim();
                String vaga = (String) spotComboBox.getSelectedItem();
                String andar = (String) floorComboBox.getSelectedItem();
                
                // Jesus implementation
                // inserir aqui a implementaçao da rotina do inserte no BD 
                // chamando o controler veiculo
                
                ControleVeiculo controleVeiculo = new ControleVeiculo();
                VeiculoModel v = new VeiculoModel();
                //System.out.print("Nome: ");
                v.setVeiNome(nome);
                //System.out.print("Modelo: ");
                v.setVeiModelo(modelo);
                //System.out.print("Placa: ");
                v.setVeiPlaca(placa);
                //System.out.print("Cor: ");
                v.setVeiCor(cor);
                //System.out.print("Telefone: ");
                v.setVeiTel(telefone);
                //System.out.print("Hora: ");
                v.setVeiHora(hora);
                v.setVeiDtEntrada(new java.util.Date());
                //System.out.print("Vaga: ");
                v.setVeiVaga(vaga);
                //System.out.print("Andar: ");
                v.setVeiAndar(andar);
                //controller.adicionarVeiculo(v);
                try {
				
					 if (nome.isEmpty() || modelo.isEmpty() || placa.isEmpty() || hora.isEmpty()) {
		                    JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Aviso", JOptionPane.WARNING_MESSAGE);
		                    return;
		                }
		                
		                if (!hora.matches("\\d{1,2}:\\d{2}")) {
		                    JOptionPane.showMessageDialog(this, "Hora inválida! Use o formato HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }

		                if (MainTableScreen.isSpotTaken(vaga, andar)) {
		                    JOptionPane.showMessageDialog(this, "Esta vaga já está ocupada! Escolha outra.", "Erro de Vaga", JOptionPane.ERROR_MESSAGE);
		                    return;
		                } else
		                {
		                	controleVeiculo.adicionarVeiculo(v);
		                	
		                	//MainTableScreen.addVehicleToTable(nome, modelo, placa, cor, telefone, vaga, andar, hora);
		                	MainTableScreen.addVehicleToTable(id ,nome, modelo, placa, cor, telefone, vaga, andar, hora);
		                	
		                	//listarVeiculos();
		                	 try {
		                      	
		                 		
		                         List<VeiculoModel> lista = controleVeiculo.listarVeiculos();
		                         
		                        
		                         activeTableModel.setRowCount(0);
		                         
		                         for (VeiculoModel t : lista) {
		                         	activeTableModel.addRow(new Object[]{
		                                     t.getIdVeiPk(),
		                                     t.getVeiNome(),
		                                     t.getVeiModelo(),
		                                     t.getVeiPlaca(),
		                                     t.getVeiCor(),
		                                     t.getVeiTel(),
		                                     t.getVeiVaga(),
		                                     t.getVeiAndar(),
		                                     t.getVeiHora(),
		                                     t.getVeiDtEntrada()
		                                     
		                                     
		                             });
		                         	
		                         	
		                         	
		                         }
		                     } catch (Exception ex) {
		                         JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
		                         
		                     }
		                	
		                	//----------------------------------------------
		                	
		                	JOptionPane.showMessageDialog(this, "Veículo Registrado com Sucesso!", "Registro Completo", JOptionPane.INFORMATION_MESSAGE);
			                
		                	//controleVeiculo.listarVeiculos();
		                	
		                	dispose();
			                
			                
						
		                }

		                
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                //System.out.println("Veículo adicionado com sucesso!");
  
                //--JESUS------------------------------------------
                
                
//
//                if (nome.isEmpty() || modelo.isEmpty() || placa.isEmpty() || hora.isEmpty()) {
//                    JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Aviso", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//                
//                if (!hora.matches("\\d{1,2}:\\d{2}")) {
//                    JOptionPane.showMessageDialog(this, "Hora inválida! Use o formato HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//
//                if (MainTableScreen.isSpotTaken(vaga, andar)) {
//                    JOptionPane.showMessageDialog(this, "Esta vaga já está ocupada! Escolha outra.", "Erro de Vaga", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//
//                MainTableScreen.addVehicleToTable(nome, modelo, placa, cor, telefone, vaga, andar, hora);
//                JOptionPane.showMessageDialog(this, "Veículo Registrado com Sucesso!", "Registro Completo", JOptionPane.INFORMATION_MESSAGE);
//                dispose();
            }
        }
    }

    // =================================================================
    // 4. TELA DE CONFIGURAÇÕES (NOVA)
    // =================================================================
    static class SettingsScreen extends JDialog implements ActionListener {
        private static final long serialVersionUID = 1L;
        private final JComboBox<String> themeComboBox;
        private final JTextField rateField;
        private final JSpinner spotsSpinner;
        private final JSpinner floorsSpinner;
        private final JButton saveButton;
        private final MainTableScreen parentFrame;

        public SettingsScreen(MainTableScreen parent) {
            super(parent, "Configurações do Sistema " + ICON_SETTINGS, true);
            this.parentFrame = parent;
            setSize(450, 400); 
            setLocationRelativeTo(parent);

            JPanel panel = new JPanel(new GridLayout(5, 2, 15, 20)); 
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panel.setBackground(UIManager.getColor("nimbusLightBackground"));

            // --- 1. Tema ---
            panel.add(new JLabel("🎨 Tema Visual:"));
            themeComboBox = new JComboBox<>(new String[]{"LIGHT", "DARK"});
            themeComboBox.setSelectedItem(fina.CURRENT_THEME);
            panel.add(themeComboBox);

            // --- 2. Tarifa ---
            panel.add(new JLabel("💰 Tarifa Horária (R$):"));
            rateField = new JTextField(String.format("%.2f", fina.HOURLY_RATE).replace('.', ','));
            panel.add(rateField);

            // --- 3. Vagas ---
            panel.add(new JLabel("🅿️ Qtd. Vagas (1 a 1000):"));
            SpinnerModel spotsModel = new SpinnerNumberModel(fina.MAX_SPOTS, 1, 1000, 1);
            spotsSpinner = new JSpinner(spotsModel);
            panel.add(spotsSpinner);

            // --- 4. Andares ---
            panel.add(new JLabel("🏢 Qtd. Andares (1 a 100):"));
            SpinnerModel floorsModel = new SpinnerNumberModel(fina.MAX_FLOORS, 1, 100, 1);
            floorsSpinner = new JSpinner(floorsModel);
            panel.add(floorsSpinner);

            // --- 5. Botão Salvar ---
            panel.add(new JLabel());
            saveButton = new JButton("Salvar Configurações");
            saveButton.addActionListener(this);
            saveButton.setBackground(new Color(52, 152, 219)); 
            saveButton.setForeground(Color.WHITE);
            saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            panel.add(saveButton);

            add(panel, BorderLayout.CENTER);
            setVisible(true);
        }
        
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveButton) {
                try {
                    String rateText = rateField.getText().trim().replace(',', '.');
                    double newRate = Double.parseDouble(rateText);
                    if (newRate <= 0) {
                         JOptionPane.showMessageDialog(this, "A tarifa deve ser um valor positivo.", "Erro de Configuração", JOptionPane.ERROR_MESSAGE);
                         return;
                    }
                    fina.HOURLY_RATE = newRate;

                    int newSpots = (Integer) spotsSpinner.getValue();
                    int newFloors = (Integer) floorsSpinner.getValue();
                    
                    if (newSpots < activeTableModel.getRowCount()) {
                        JOptionPane.showMessageDialog(this, "Não é possível reduzir o número de vagas. Veículos ativos excedem o novo limite.", "Erro de Configuração", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    fina.MAX_SPOTS = newSpots;
                    fina.MAX_FLOORS = newFloors;

                    String newTheme = (String) themeComboBox.getSelectedItem();
                    boolean themeChanged = !fina.CURRENT_THEME.equals(newTheme);
                    fina.CURRENT_THEME = newTheme;

                    JOptionPane.showMessageDialog(this, "Configurações salvas com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    
                    if (themeChanged) {
                        // Recarrega o Look and Feel
                        fina.applyTheme(newTheme);
                        // Disposição do frame antigo e criação de um novo para aplicar o tema em todos os componentes
                        parentFrame.dispose();
                        SwingUtilities.invokeLater(() -> new MainTableScreen());
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Erro: Insira um valor numérico válido para a Tarifa.", "Erro de Configuração", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                     JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro Desconhecido", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
