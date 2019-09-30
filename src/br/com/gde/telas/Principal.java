package br.com.gde.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.gde.telas.Sobre;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private final JLabel lblData = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				alterarLabel();
			}
		});
		setTitle("DGE - Sistema para gerenciamento de Postos de Gasolina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSobre = new JButton("");
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/br/com/gde/icones/about.png")));
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
				
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(706, 23, 64, 64);
		contentPane.add(btnSobre);
		
		JButton btnPDV = new JButton("");
		btnPDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PDV vendas1 = new PDV();
				vendas1.setVisible(true);
			}
		});
		btnPDV.setIcon(new ImageIcon(Principal.class.getResource("/br/com/gde/icones/gasoline.png")));
		btnPDV.setToolTipText("Ponto de Vendas");
		btnPDV.setBounds(56, 361, 128, 128);
		contentPane.add(btnPDV);
		
		JButton btnUsuarios = new JButton("");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
		    }
		});
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/br/com/gde/icones/users.png")));
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.setBounds(422, 361, 128, 128);
		contentPane.add(btnUsuarios);
		
		JButton btnRelatorios = new JButton("");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/br/com/gde/icones/report2.png")));
		btnRelatorios.setToolTipText("Relatorios");
		btnRelatorios.setBounds(613, 361, 128, 128);
		contentPane.add(btnRelatorios);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/br/com/gde/icones/gasolinegroup.png")));
		lblNewLabel.setBounds(249, 46, 256, 256);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 520, 794, 51);
		contentPane.add(panel);	
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(SystemColor.text);
		lblData.setBounds(130, 0, 564, 33);
		panel.setLayout(null);
		panel.add(lblData);
		
		JButton btnEstoque = new JButton("");
		btnEstoque.setIcon(new ImageIcon(Principal.class.getResource("/br/com/gde/icones/estoque.png")));
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoque estoque = new Estoque();
				estoque.setVisible(true);
			}
		});
		btnEstoque.setToolTipText("Estoque");
		btnEstoque.setBounds(238, 361, 128, 128);
		contentPane.add(btnEstoque);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calculadora calculadora = new Calculadora();
				calculadora.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/br/com/gde/icones/calc.png")));
		btnNewButton.setBounds(615, 23, 64, 64);
		contentPane.add(btnNewButton);
		
	}
		private void alterarLabel() {
			Date data = new Date();
			DateFormat formatador =
            DateFormat.getDateInstance(DateFormat.FULL);
			lblData.setText(formatador.format(data));
		}
}

