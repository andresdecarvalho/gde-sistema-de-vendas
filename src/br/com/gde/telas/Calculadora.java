package br.com.gde.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class Calculadora extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=61,-11
	 */
	private final JTextField textField = new JTextField();
	private JTextField txtValor1;
	private JTextField txtValor2;
	private JTextField txtResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora frame = new Calculadora();
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
	public Calculadora() {
		setTitle("Calculadora");
		textField.setColumns(10);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 397, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtValor1 = new JTextField();
		txtValor1.setBounds(33, 51, 86, 20);
		contentPane.add(txtValor1);
		txtValor1.setColumns(10);
		
		txtValor2 = new JTextField();
		txtValor2.setBounds(145, 51, 86, 20);
		contentPane.add(txtValor2);
		txtValor2.setColumns(10);
		
		txtResultado = new JTextField();
		txtResultado.setBounds(259, 51, 77, 20);
		contentPane.add(txtResultado);
		txtResultado.setColumns(10);
		
		JLabel lblIgual = new JLabel("=");
		lblIgual.setBounds(241, 54, 15, 14);
		contentPane.add(lblIgual);
		
		JButton btnSoma = new JButton("+");
		btnSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soma();
			}
		});
		btnSoma.setBounds(21, 98, 59, 73);
		contentPane.add(btnSoma);
		
		JButton btnSubtracao = new JButton("-");
		btnSubtracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sub();
			}
		});
		btnSubtracao.setBounds(90, 98, 59, 73);
		contentPane.add(btnSubtracao);
		
		JButton btnMultiplicacao = new JButton("x");
		btnMultiplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mult();
			}
		});
		btnMultiplicacao.setBounds(158, 98, 59, 73);
		contentPane.add(btnMultiplicacao);
		
		JButton btnDivisao = new JButton("/");
		btnDivisao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				div();
			}
		});
		btnDivisao.setBounds(226, 98, 59, 73);
		contentPane.add(btnDivisao);
		
		JLabel lblNumero1 = new JLabel("Numero 1");
		lblNumero1.setBounds(34, 26, 85, 14);
		contentPane.add(lblNumero1);
		
		JLabel lblNumero2 = new JLabel("Numero 2");
		lblNumero2.setBounds(145, 26, 86, 14);
		contentPane.add(lblNumero2);
		
		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setBounds(259, 26, 77, 14);
		contentPane.add(lblResultado);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(295, 98, 77, 73);
		contentPane.add(btnLimpar);
	}
		private void soma() {
			double valor1,valor2,resultado;
			DecimalFormat formatador= new DecimalFormat();
			valor1 = Double.parseDouble(txtValor1.getText().replace(",","."));
			valor2 = Double.parseDouble(txtValor2.getText().replace(",","."));
			resultado = valor1 + valor2;
			txtResultado.setText(formatador.format(resultado));
		}
		
		private void sub() {
			double valor1,valor2,resultado;
			DecimalFormat formatador= new DecimalFormat();
			valor1 = Double.parseDouble(txtValor1.getText().replace(",","."));
			valor2 = Double.parseDouble(txtValor2.getText().replace(",","."));
			resultado = valor1 - valor2;
			txtResultado.setText(formatador.format(resultado));
		}
		
		private void mult() {
			double valor1,valor2,resultado;
			DecimalFormat formatador= new DecimalFormat();
			valor1 = Double.parseDouble(txtValor1.getText().replace(",","."));
			valor2 = Double.parseDouble(txtValor2.getText().replace(",","."));
			resultado = valor1 * valor2;
			txtResultado.setText(formatador.format(resultado));
		}
		
		private void div() {
			double valor1,valor2,resultado;
			DecimalFormat formatador= new DecimalFormat();
			valor1 = Double.parseDouble(txtValor1.getText().replace(",","."));
			valor2 = Double.parseDouble(txtValor2.getText().replace(",","."));
			resultado = valor1 / valor2;
			txtResultado.setText(formatador.format(resultado));
		}
		
		private void limpar() {
			txtValor1.setText(null);
			txtValor2.setText(null);
			txtResultado.setText(null);


		}
}
