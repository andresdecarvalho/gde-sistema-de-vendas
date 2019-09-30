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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PDV extends JDialog {
	private JTextField txtPesquisar;
	private JTable tblVendas1;
	private JTextField txtPreco;
	private JTextField txtQuantidade;
	private JTextField txtLitro;
	private JTextField txtTotal;
	private JTextField txtRestante;
	private JTextField txtCombustivel;
	private JTextField txtPagamento;
	private JTextField txtTroco;
	private JLabel lblData;
	private JTextField txtCodigo;
	
	
	Connection conexao = null; // conexão
	PreparedStatement pst = null; // executar uma query (script) sql
	ResultSet rs = null; // "trazer" os dados
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PDV dialog = new PDV();
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
	public PDV() {
		setTitle("Ponto de Vendas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PDV.class.getResource("/br/com/gde/icones/pc.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 726, 468);
		getContentPane().setLayout(null);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				pesquisarVendas1();
			}
		});
		txtPesquisar.setBounds(97, 23, 265, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PDV.class.getResource("/br/com/gde/icones/pesquisar.png")));
		lblNewLabel.setBounds(384, 23, 32, 32);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o");
		lblPreco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreco.setBounds(46, 262, 69, 14);
		getContentPane().add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setText("0,00");
		txtPreco.setBounds(86, 276, 79, 20);
		getContentPane().add(txtPreco);
		txtPreco.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidade.setBounds(66, 307, 79, 14);
		getContentPane().add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setText("0,00");
		txtQuantidade.setBounds(86, 322, 79, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vender();
			}
		});
		btnVender.setToolTipText("Finalizar Venda");
		btnVender.setBounds(384, 367, 89, 45);
		btnVender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnVender);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 66, 506, 121);
		getContentPane().add(scrollPane);
		
		tblVendas1 = new JTable();
		tblVendas1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(tblVendas1);
		tblVendas1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcular();
			}
		});
		btnCalcular.setBounds(224, 367, 89, 45);
		getContentPane().add(btnCalcular);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setBounds(184, 279, 15, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblLitro = new JLabel("Litro");
		lblLitro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLitro.setBounds(195, 262, 39, 14);
		getContentPane().add(lblLitro);
		
		txtLitro = new JTextField();
		txtLitro.setText("0,00");
		txtLitro.setBounds(209, 276, 79, 20);
		getContentPane().add(txtLitro);
		txtLitro.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(300, 262, 48, 14);
		getContentPane().add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setText("0,00");
		txtTotal.setBounds(323, 276, 79, 20);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblRestante = new JLabel("Sobra");
		lblRestante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRestante.setBounds(195, 307, 48, 14);
		getContentPane().add(lblRestante);
		
		txtRestante = new JTextField();
		txtRestante.setText("0,00");
		txtRestante.setBounds(209, 322, 79, 20);
		getContentPane().add(txtRestante);
		txtRestante.setColumns(10);
		
		txtCombustivel = new JTextField();
		txtCombustivel.setHorizontalAlignment(SwingConstants.LEFT);
		txtCombustivel.setText("Nome do Combustivel");
		txtCombustivel.setBounds(209, 233, 193, 20);
		getContentPane().add(txtCombustivel);
		txtCombustivel.setColumns(10);
		
		JLabel lblCombustivel = new JLabel("Combustivel");
		lblCombustivel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCombustivel.setBounds(192, 214, 79, 20);
		getContentPane().add(lblCombustivel);
		
		JLabel label = new JLabel("=");
		label.setBounds(298, 279, 15, 14);
		getContentPane().add(label);
		
		txtPagamento = new JTextField();
		txtPagamento.setText("0,00");
		txtPagamento.setBounds(438, 276, 79, 20);
		getContentPane().add(txtPagamento);
		txtPagamento.setColumns(10);
		
		txtTroco = new JTextField();
		txtTroco.setText("0,00");
		txtTroco.setBounds(555, 276, 79, 20);
		getContentPane().add(txtTroco);
		txtTroco.setColumns(10);
		
		JLabel lblValorPago = new JLabel("Valor Pago");
		lblValorPago.setBounds(437, 262, 80, 14);
		getContentPane().add(lblValorPago);
		
		JLabel lblTroco = new JLabel("Troco");
		lblTroco.setBounds(555, 262, 48, 14);
		getContentPane().add(lblTroco);
		
		lblData = new JLabel("");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setBounds(477, 26, 192, 14);
		getContentPane().add(lblData);
				
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(86, 233, 79, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(86, 217, 48, 14);
		getContentPane().add(lblCodigo);
		
		lblData.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		
		conexao = ModuloConexao.conector();//conectar com o banco (conexão fechada em login)
		
	}//fim do construtor
		

			//limpar campos do Jframe
			private void limpar() {
				//txtCombustivel.setText(null);
				txtPreco.setText(null);
				txtQuantidade.setText(null);
				txtCombustivel.setText(null);
				txtPesquisar.setText(null);
				txtLitro.setText(null);
				txtTotal.setText(null);
				txtRestante.setText(null);
				txtTroco.setText(null);
				txtPagamento.setText(null);
				txtCodigo.setText(null);
				limparTabela();
			
		}
		
		//metodo para limpar a tabela
		private void limparTabela() {
			while (tblVendas1.getRowCount() > 0) {
				((DefaultTableModel) tblVendas1.getModel()).removeRow(0);
			}
		}
			

		//metodo para pesquisar Vendas1 dinamicamente
		private void pesquisarVendas1() {
			String consultar = "select * from tb_produtos where produto like?";
			try {
				pst = conexao.prepareStatement(consultar);
				//atenção ao "%" na passagem do parametro
				pst.setString(1,  txtPesquisar.getText() + "%");
				rs = pst.executeQuery();
				//a linha abaixo usa a biblioteca rs2xml.jar para "popular" a tabela
				tblVendas1.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		//METODO PARA SETAR OS CAMPOS DO jFRAME
		private void setarCampos() {
			int setar = tblVendas1.getSelectedRow();
			txtCodigo.setText(tblVendas1.getValueAt(setar, 0).toString());
			txtCombustivel.setText(tblVendas1.getModel().getValueAt(setar, 1).toString());
			txtPreco.setText(tblVendas1.getModel().getValueAt(setar, 2).toString());
			txtQuantidade.setText(tblVendas1.getModel().getValueAt(setar, 3).toString());

			}
		
		//Metodo para alterar cliente
		private void vender() {
			if (txtQuantidade.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
			} else {
				String editar = "update tb_produtos set quantidade=?  where produto=?";
				try {
					pst = conexao.prepareStatement(editar);	
					pst.setString(1, txtRestante.getText().replace(",","."));
					pst.setString(2, txtCombustivel.getText().replace(",","."));
					

					int adicionado = pst.executeUpdate();
					if (adicionado == 1) {
						JOptionPane.showMessageDialog(null, "Venda de " + txtCombustivel.getText() + " realizada, seu troco é de : " + txtTroco.getText().replace(".",",") + " reais");
						limpar();
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível realizar a venda");
						limpar();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		
		private void calcular() {
			DecimalFormat formatador= new DecimalFormat("0.00");
			double litro,preco,total,restante,quantidade,pagamento,troco;
			preco = Double.parseDouble(txtPreco.getText().replace(",","."));
			litro = Double.parseDouble(txtLitro.getText().replace(",","."));
			quantidade = Double.parseDouble(txtQuantidade.getText().replace(",","."));
			troco = Double.parseDouble(txtTroco.getText().replace(",","."));
			pagamento = Double.parseDouble(txtPagamento.getText().replace(",","."));
			total = preco * litro;
			restante = quantidade - litro;
			troco = pagamento - total;
			txtTotal.setText(formatador.format(total));
			txtRestante.setText(formatador.format(restante));
			txtTroco.setText(formatador.format(troco));
						
		}
}
