package br.com.gde.telas;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.gde.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class Estoque extends JDialog {
	private JTextField txtPesquisar;
	private JTable tblEstoque;
	private JTextField txtPreco;
	private JTextField txtQuantidade;
	private JTextField txtCombustivel;

	Connection conexao = null; // conexão
	PreparedStatement pst = null; // executar uma query (script) sql
	ResultSet rs = null; // "trazer" os dados
	private JTextField txtCodigo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoque dialog = new Estoque();
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
	public Estoque() {
		setTitle("Estoque");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Estoque.class.getResource("/br/com/gde/icones/pc.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 493, 438);
		getContentPane().setLayout(null);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				pesquisarEstoque();
			}
		});
		txtPesquisar.setBounds(104, 57, 227, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Estoque.class.getResource("/br/com/gde/icones/pesquisar.png")));
		lblNewLabel.setBounds(373, 45, 32, 32);
		getContentPane().add(lblNewLabel);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setBounds(45, 265, 44, 17);
		getContentPane().add(lblCodigo);
		
		JLabel lblCombustivel = new JLabel("Combustivel");
		lblCombustivel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCombustivel.setBounds(10, 299, 79, 14);
		getContentPane().add(lblCombustivel);
		
		txtCombustivel = new JTextField();
		txtCombustivel.setBounds(99, 296, 111, 20);
		getContentPane().add(txtCombustivel);
		txtCombustivel.setColumns(10);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o");
		lblPreco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreco.setBounds(20, 330, 69, 14);
		getContentPane().add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(99, 327, 111, 20);
		getContentPane().add(txtPreco);
		txtPreco.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidade.setBounds(10, 363, 79, 14);
		getContentPane().add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(99, 358, 111, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setToolTipText("Editar Cliente");
		btnEditar.setIcon(new ImageIcon(Estoque.class.getResource("/br/com/gde/icones/update.png")));
		btnEditar.setBounds(341, 283, 64, 64);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnEditar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 102, 437, 131);
		getContentPane().add(scrollPane);
		
		tblEstoque = new JTable();
		tblEstoque.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(tblEstoque);
		tblEstoque.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(99, 263, 111, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblDigiteOCombustivel = new JLabel("Digite o Combustivel");
		lblDigiteOCombustivel.setBounds(104, 32, 227, 14);
		getContentPane().add(lblDigiteOCombustivel);
		
		conexao = ModuloConexao.conector();//conectar com o banco (conexão fechada em login)
		
	}//fim do construtor
		

			//limpar campos do Jframe
			private void limpar() {
				txtCombustivel.setText(null);
				txtPreco.setText(null);
				txtQuantidade.setText(null);
				txtPesquisar.setText(null);
				txtCodigo.setText(null);
				limparTabela();
			
		}
		
		//metodo para limpar a tabela
		private void limparTabela() {
			while (tblEstoque.getRowCount() > 0) {
				((DefaultTableModel) tblEstoque.getModel()).removeRow(0);
			}
		}
			

		//metodo para pesquisar Estoque dinamicamente
		private void pesquisarEstoque() {
			String consultar = "select * from tb_produtos where produto like?";
			try {
				pst = conexao.prepareStatement(consultar);
				//atenção ao "%" na passagem do parametro
				pst.setString(1,  txtPesquisar.getText() + "%");
				rs = pst.executeQuery();
				//a linha abaixo usa a biblioteca rs2xml.jar para "popular" a tabela
				tblEstoque.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		//METODO PARA SETAR OS CAMPOS DO jFRAME
		private void setarCampos() {
			int setar = tblEstoque.getSelectedRow();
			txtCodigo.setText(tblEstoque.getModel().getValueAt(setar, 0).toString());
			txtCombustivel.setText(tblEstoque.getModel().getValueAt(setar, 1).toString());
			txtPreco.setText(tblEstoque.getModel().getValueAt(setar, 2).toString());
			txtQuantidade.setText(tblEstoque.getModel().getValueAt(setar, 3).toString());

			}
		
		//Metodo para alterar cliente
		private void editar() {
			if (txtCodigo.getText().isEmpty() || txtCombustivel.getText().isEmpty() || txtPreco.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
			} else {
				String editar = "update tb_produtos set codigoProduto=?, valor=?, quantidade=? where produto=?";
				try {
					pst = conexao.prepareStatement(editar);	
					pst.setString(1, txtCodigo.getText());
					pst.setString(2, txtPreco.getText().replace(",","."));
					pst.setString(3, txtQuantidade.getText().replace(",","."));
					pst.setString(4, txtCombustivel.getText().replace(",","."));

					int adicionado = pst.executeUpdate();
					if (adicionado == 1) {
						JOptionPane.showMessageDialog(null, "Combustivel " + txtCombustivel.getText() + " atualizado com sucesso");
						limpar();
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível atualizar " + txtCombustivel.getText());
						limpar();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
}