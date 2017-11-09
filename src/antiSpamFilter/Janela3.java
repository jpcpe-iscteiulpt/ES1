package antiSpamFilter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class Janela3 {

	private JFrame frmFiltroAntispam;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela3 window = new Janela3();
					window.frmFiltroAntispam.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFiltroAntispam = new JFrame();
		frmFiltroAntispam.setTitle("Filtro Anti-Spam");
		frmFiltroAntispam.setBounds(100, 100, 450, 300);
		frmFiltroAntispam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFiltroAntispam.getContentPane().setLayout(null);
		
		JLabel lblConfiguraoManual = new JLabel("Configura\u00E7\u00E3o Manual");
		lblConfiguraoManual.setBounds(165, 21, 145, 25);
		frmFiltroAntispam.getContentPane().add(lblConfiguraoManual);
		
		JLabel lblFalsosPositivos = new JLabel("Falsos Positivos");
		lblFalsosPositivos.setBounds(60, 229, 75, 14);
		frmFiltroAntispam.getContentPane().add(lblFalsosPositivos);
		
		JLabel lblFalsosNegativos = new JLabel("Falsos Negativos");
		lblFalsosNegativos.setBounds(240, 229, 81, 14);
		frmFiltroAntispam.getContentPane().add(lblFalsosNegativos);
		
		textField = new JTextField();
		textField.setBounds(144, 226, 50, 20);
		frmFiltroAntispam.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(327, 226, 50, 20);
		frmFiltroAntispam.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRegras = new JLabel("Regras");
		lblRegras.setBounds(89, 50, 46, 14);
		frmFiltroAntispam.getContentPane().add(lblRegras);
		
		JLabel lblPesos = new JLabel("Pesos");
		lblPesos.setBounds(275, 50, 46, 14);
		frmFiltroAntispam.getContentPane().add(lblPesos);
	}
}
