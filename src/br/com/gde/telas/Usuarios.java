package br.com.gde.telas;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.gde.dal.ModuloConexao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Usuarios extends JDialog {
	private JTextField txtUsuId;
	private JTextField txtUsu;
	private JTextField txtUsuFone;
	private JTextField txtUsuLogin;
	private JTextField txtUsuSenha;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/br/com/gde/icones/pc.png")));
		setModal(true);
		setTitle("Usuarios");
		setResizable(false);
		setBounds(100, 100, 432, 300);
		getContentPane().setLayout(null);

		JLabel lblcamposObrigatorios = new JLabel("*Campos Obrigatorios");
		lblcamposObrigatorios.setBounds(273, 11, 143, 14);
		getContentPane().add(lblcamposObrigatorios);

		txtUsuId = new JTextField();
		txtUsuId.setBounds(78, 21, 96, 20);
		getContentPane().add(txtUsuId);
		txtUsuId.setColumns(10);

		JLabel lblId = new JLabel("*Id");
		lblId.setBounds(46, 24, 22, 14);
		getContentPane().add(lblId);

		JLabel lblUsuario = new JLabel("*Usuario");
		lblUsuario.setBounds(20, 55, 58, 14);
		getContentPane().add(lblUsuario);

		txtUsu = new JTextField();
		txtUsu.setBounds(79, 52, 291, 20);
		getContentPane().add(txtUsu);
		txtUsu.setColumns(10);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(40, 117, 38, 14);
		getContentPane().add(lblLogin);

		txtUsuFone = new JTextField();
		txtUsuFone.setBounds(78, 83, 107, 20);
		getContentPane().add(txtUsuFone);
		txtUsuFone.setColumns(10);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(79, 114, 107, 20);
		getContentPane().add(txtUsuLogin);
		txtUsuLogin.setColumns(10);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(40, 80, 38, 26);
		getContentPane().add(lblFone);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(220, 117, 37, 14);
		getContentPane().add(lblSenha);

		txtUsuSenha = new JTextField();
		txtUsuSenha.setBounds(267, 114, 114, 20);
		getContentPane().add(txtUsuSenha);
		txtUsuSenha.setColumns(10);

		JButton btnUsuCreate = new JButton("");
		btnUsuCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnUsuCreate.setToolTipText("Adicionar Usuario");
		btnUsuCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/br/com/gde/icones/create.png")));
		btnUsuCreate.setBounds(38, 180, 64, 64);
		btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnUsuCreate);

		JButton btnUsuRead = new JButton("");
		btnUsuRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnUsuRead.setToolTipText("Pesquisar Usuario");
		btnUsuRead.setIcon(new ImageIcon(Usuarios.class.getResource("/br/com/gde/icones/read.png")));
		btnUsuRead.setBounds(134, 180, 64, 64);
		btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnUsuRead);

		JButton btnUsuUpdate = new JButton("");
		btnUsuUpdate.setToolTipText("Editar Usuario");
		btnUsuUpdate.setIcon(new ImageIcon(Usuarios.class.getResource("/br/com/gde/icones/update.png")));
		btnUsuUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarUsuario();
			}
		});
		btnUsuUpdate.setBounds(228, 180, 64, 64);
		btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnUsuUpdate);

		JButton btnUsuDelete = new JButton("");
		btnUsuDelete.setToolTipText("Deletar Usuario");
		btnUsuDelete.setIcon(new ImageIcon(Usuarios.class.getResource("/br/com/gde/icones/delete.png")));
		btnUsuDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apagar();
			}
		});
		btnUsuDelete.setBounds(322, 180, 64, 64);
		btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnUsuDelete);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(224, 86, 48, 14);
		getContentPane().add(lblPerfil);
		
		JComboBox cmbPerfil = new JComboBox();
		cmbPerfil.setModel(new DefaultComboBoxModel(new String[] {"Usu\u00E1rio", "Administrador"}));
		cmbPerfil.setBounds(267, 83, 114, 22);
		getContentPane().add(cmbPerfil);

		conexao = ModuloConexao.conector();
	}
	
	Connection conexao = null; // conexão
	PreparedStatement pst = null; // executar uma query (script) sql
	ResultSet rs = null; // "trazer" os dados

	private void adicionar() 
	{
		// validação dos campos obrigatórios
		if (txtUsuId.getText().isEmpty() || txtUsu.getText().isEmpty() || txtUsuFone.getText().isEmpty() || txtUsuLogin.getText().isEmpty() || txtUsuSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos Obrigatórios");
		} else {
			// insert
			String create = "insert into tb_usuarios(idUsuario,usuario,foneUsuario,loginUsuario,senhaUsuario) values (?,?,?,?,?)";
			try {
				pst = conexao.prepareStatement(create);
				// armazenar no banco o conteúdo do Jframe Formulário
				pst.setString(1, txtUsuId.getText());
				pst.setString(2, txtUsu.getText());
				pst.setString(3, txtUsuFone.getText());
				pst.setString(4, txtUsuLogin.getText());
				pst.setString(5, txtUsuSenha.getText());
				// Criando uma estrutura para "avisar" o usuário que o contato foi cadastrado
				int adicionado = pst.executeUpdate();
				// a linha abaixo é utilizada para entender a lógica
				System.out.println(adicionado);
				if (adicionado == 1) {
					JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso");
					limpar();
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possivel adicionar o contato");
				}

			} catch (Exception ConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "Código ID já existente");
				// System.out.println(e);

			}
		}
	}
	
	private void pesquisar() {
		String read = "select * from tb_usuarios where idUsuario = ?"; // ? é um parâmetro
		try {
			pst = conexao.prepareStatement(read);
			pst.setString(1, txtUsuId.getText()); // substituir o ? pelo valor da caixa de texto de nome txtId
			rs = pst.executeQuery(); // executa o script e retorna os dados
			if (rs.next()) {
				//txtUsuId.setText(rs.getString(1));
				txtUsu.setText(rs.getString(2));
				txtUsuFone.setText(rs.getString(3));
				txtUsuLogin.setText(rs.getString(4));
				txtUsuSenha.setText(rs.getString(5));

			} else {
				JOptionPane.showMessageDialog(null, "contato inexistente");
				limpar();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void editarUsuario() {
		if (txtUsuId.getText().isEmpty() || txtUsu.getText().isEmpty() || txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
		} else {
			String editar = "update tb_usuarios set usuario = ?,foneUsuario = ?,loginUsuario = ?,senhaUsuario = ? where idUsuario = ?";
			try {
				pst = conexao.prepareStatement(editar);				
				pst.setString(1, txtUsu.getText());
				pst.setString(2, txtUsuFone.getText());
				pst.setString(3, txtUsuLogin.getText());
				pst.setString(4, txtUsuSenha.getText());
				pst.setString(5, txtUsuId.getText());
				int adicionado = pst.executeUpdate();
				if (adicionado == 1) {
					JOptionPane.showMessageDialog(null, "Usuário editado com sucesso");
					limpar();
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível editar o usuário");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	
	private void apagar( ) {
		//a linha abaixo cria uma caixa de confirmação
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste registro?","ATENÇÃO", JOptionPane.YES_NO_OPTION);
 		if(confirma == JOptionPane.YES_OPTION) { 
 			String delete = "delete from tb_usuarios where idUsuario = ?"; 
 			try {
				pst=conexao.prepareStatement(delete); 
				pst.setString(1, txtUsuId.getText());
				int removido = pst.executeUpdate();
				if(removido == 1) {
					limpar(); 
					JOptionPane.showMessageDialog(null, "Contato Removido");				
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
 		}
		
	} 
	
	private void limpar() {
		txtUsuId.setText(null);
		txtUsu.setText(null);
		txtUsuFone.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
	
	

}
}
