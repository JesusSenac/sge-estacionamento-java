package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.fina.MainTableScreen;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class jlogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jlogin frame = new jlogin();
					frame.setLocationRelativeTo(null); // colocando a tela no centro do monitor
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jlogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 324, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 283, 228);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setBounds(22, 80, 46, 14);
		panel.add(lblNewLabel);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(22, 93, 152, 20);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(22, 122, 46, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// este evento vai capturar o click do botão Entrar
				//JOptionPane.showMessageDialog(btnNewButton, "Clicou no BOTAO ENTRAR");
				// daqui iremos tratar as informações e verificar se existe usuario e senha no banco de dados
				
//				if ( textFieldUsuario.getText() != null &&
//					 !textFieldUsuario.getText().isEmpty() &&
//					 passwordFieldSenha.getText() != null &&
//					 !passwordFieldSenha.getText().isEmpty()
//				   ) {
//					JOptionPane.showMessageDialog(btnNewButton, "Clicou no BOTAO ENTRAR");
//					//verificar se existe usuario e senha no banco de dados
//					aqui
//				} else
//				{
//					JOptionPane.showMessageDialog(btnNewButton, "Preencha os dados do LOGIN ou SENHA ");
//					if ( textFieldUsuario.getText() != null &&
//							 !textFieldUsuario.getText().isEmpty() ) {
//						textFieldUsuario.requestFocusInWindow();
//					}
//				}
				
				
				    String username = textFieldUsuario.getText();
		            String password = new String(passwordFieldSenha.getText());

		            if (username.equals("admin") && password.equals("123")) {
		                dispose();
		                new MainTableScreen();
		            } else {
		            	JOptionPane.showMessageDialog(btnNewButton, "Usuário ou senha inválidos.");
		            	//JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
		            }
				
				
				
				
			}
		});
		
		
		btnNewButton.setBounds(22, 180, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Estacionamento SENAC PI");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(22, 0, 251, 40);
		panel.add(lblNewLabel_2);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(22, 136, 152, 20);
		panel.add(passwordFieldSenha);

	}
}
