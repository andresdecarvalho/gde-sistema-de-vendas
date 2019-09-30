package br.com.gde.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.gde.dal.ModuloConexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("DGE - Sistema para gerenciamento de Postos de Gasolina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsario = new JLabel("Us\u00FAario");
		lblUsario.setBounds(38, 62, 48, 14);
		contentPane.add(lblUsario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(88, 59, 236, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(38, 107, 48, 14);
		contentPane.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(88, 104, 236, 20);
		contentPane.add(txtSenha);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogin.setBounds(176, 185, 89, 23);
		contentPane.add(btnLogin);

		JLabel lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/br/com/gde/icones/dberror.png")));
		lblStatus.setBounds(358, 59, 32, 32);
		contentPane.add(lblStatus);

		conexao = ModuloConexao.conector();
		if (conexao != null) {
			System.out.println("CONECTADO");
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gde/icones/dbok.png")));
		} else {
			System.out.println("ERRO DE CONEXÃO");
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gde/icones/dberror.png")));
		}
		getRootPane().setDefaultButton(btnLogin); //<enter> automático
	}

	Connection conexao = null; // conexão
	PreparedStatement pst = null; // executar uma query (script) sql
	ResultSet rs = null; // "trazer" os dados

	// login
	private void logar() {
		String read = "select * from tb_usuarios where loginUsuario = ? and senhaUsuario = ? ";
		try {
			pst = conexao.prepareStatement(read);
			pst.setString(1, txtUsuario.getText());// substitua interrogação por o que estiver escrito na label
			pst.setString(2, txtSenha.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				Principal principal = new Principal();
				principal.setLocationRelativeTo(null);
				principal.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuario e/ou senha inválido(s)");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
