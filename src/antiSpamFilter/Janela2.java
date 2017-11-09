package antiSpamFilter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Janela2 {

	private JFrame frmFiltroAntispam;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela2 window = new Janela2();
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
	public Janela2() {
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
		lblConfiguraoManual.setBounds(90, 53, 165, 14);
		frmFiltroAntispam.getContentPane().add(lblConfiguraoManual);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(303, 46, 21, 21);
		frmFiltroAntispam.getContentPane().add(checkBox);
		
		JLabel lblConfiguraoAutomtica = new JLabel("Configura\u00E7\u00E3o Autom\u00E1tica");
		lblConfiguraoAutomtica.setBounds(90, 105, 181, 14);
		frmFiltroAntispam.getContentPane().add(lblConfiguraoAutomtica);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(303, 102, 21, 21);
		frmFiltroAntispam.getContentPane().add(chckbxNewCheckBox);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(90, 188, 109, 23);
		frmFiltroAntispam.getContentPane().add(btnAnterior);
		
		JButton btnSeguinte = new JButton("Seguinte");
		btnSeguinte.setBounds(215, 188, 109, 23);
		frmFiltroAntispam.getContentPane().add(btnSeguinte);
	}

}
