package antiSpamFilter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Janela4 {

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
					Janela4 window = new Janela4();
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
	public Janela4() {
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
		
		JLabel lblConfiguraoAutomtica = new JLabel("Configura\u00E7\u00E3o Autom\u00E1tica");
		lblConfiguraoAutomtica.setBounds(161, 11, 166, 25);
		frmFiltroAntispam.getContentPane().add(lblConfiguraoAutomtica);
		
		JLabel label_1 = new JLabel("Falsos Positivos");
		label_1.setBounds(56, 219, 75, 14);
		frmFiltroAntispam.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Falsos Negativos");
		label_2.setBounds(236, 219, 81, 14);
		frmFiltroAntispam.getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(140, 216, 50, 20);
		frmFiltroAntispam.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(323, 216, 50, 20);
		frmFiltroAntispam.getContentPane().add(textField_1);
		
		JLabel label_3 = new JLabel("Regras");
		label_3.setBounds(85, 40, 46, 14);
		frmFiltroAntispam.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Pesos");
		label_4.setBounds(271, 40, 46, 14);
		frmFiltroAntispam.getContentPane().add(label_4);
	}

}
