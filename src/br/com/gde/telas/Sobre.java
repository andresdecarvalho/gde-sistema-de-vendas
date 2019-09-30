package br.com.gde.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Sobre");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 444, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("DGE - Sistema para Gerenciamento de postos de Gasolina");
			lblNewLabel.setBounds(26, 11, 337, 57);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblDesenvolvidoPorAndr = new JLabel("Desenvolvido Streamarq");
			lblDesenvolvidoPorAndr.setForeground(new Color(0, 0, 0));
			lblDesenvolvidoPorAndr.setBounds(26, 57, 337, 57);
			contentPanel.add(lblDesenvolvidoPorAndr);
		}
		{
			JLabel lblSobALicena = new JLabel("Sob a licen\u00E7a GPL");
			lblSobALicena.setForeground(Color.BLACK);
			lblSobALicena.setBounds(26, 109, 337, 57);
			contentPanel.add(lblSobALicena);
		}
		{
			JLabel lblVerso = new JLabel("Vers\u00E3o 1.0");
			lblVerso.setForeground(Color.BLACK);
			lblVerso.setBounds(26, 160, 337, 57);
			contentPanel.add(lblVerso);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(Sobre.class.getResource("/br/com/gde/icones/gpl.png")));
			lblNewLabel_1.setBounds(346, 137, 64, 64);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(352, 227, 92, 44);
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
