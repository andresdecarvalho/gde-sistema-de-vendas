package br.com.gde.telas;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDayChooser;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Relatorios extends JDialog {
	private JButton btnRelatorioFaturamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios dialog = new Relatorios();
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
	public Relatorios() {
		setModal(true);
		setTitle("Relatorios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/br/com/gde/icones/pc.png")));
		setResizable(false);
		setBounds(100, 100, 601, 359);
		getContentPane().setLayout(null);
		
		JButton btnRelatorioClientes = new JButton("");
		btnRelatorioClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnRelatorioClientes.setIcon(new ImageIcon(Relatorios.class.getResource("/br/com/gde/icones/report clientes.png")));
		btnRelatorioClientes.setToolTipText("Clientes Cadastrados");
		btnRelatorioClientes.setBounds(39, 168, 128, 128);
		btnRelatorioClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnRelatorioClientes);
		
		JButton btnRelatorioOs = new JButton("");
		btnRelatorioOs.setIcon(new ImageIcon(Relatorios.class.getResource("/br/com/gde/icones/report os.png")));
		btnRelatorioOs.setToolTipText("Servi\u00E7os");
		btnRelatorioOs.setBounds(227, 168, 128, 128);
		btnRelatorioOs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnRelatorioOs);
		
		JButton btnRelatorioFaturamento = new JButton("");
		btnRelatorioFaturamento.setIcon(new ImageIcon(Relatorios.class.getResource("/br/com/gde/icones/report faturamento.png")));
		btnRelatorioFaturamento.setToolTipText("Faturamento");
		btnRelatorioFaturamento.setBounds(425, 168, 128, 128);
		btnRelatorioFaturamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnRelatorioFaturamento);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(205, 67, 202, 20);
		getContentPane().add(dateChooser);
		
		JLabel lblDataDoRelatorio = new JLabel("Data do Relatorio");
		lblDataDoRelatorio.setBounds(93, 73, 102, 14);
		getContentPane().add(lblDataDoRelatorio);

	}//fim do construtor
		//relatorio de clientes
		private void relatorioClientes() {
			Document document = new Document();
			//gerar documento
			try {
				PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
				document.open();
				document.add(new Paragraph("Clientes cadastrados"));
				//mensagem de relatorio gerado com sucesso
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				document.close();
			}
			//exibir o documento no leitor de pdf padrão 
			try {
				Desktop.getDesktop().open(new File("clientes.pdf"));
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
}