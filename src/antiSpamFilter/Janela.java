package antiSpamFilter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela {

	private JFrame frmFiltroAntispam;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
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
	public Janela() {
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
		
		JLabel lblRulescf = new JLabel("Rules.cf");
		lblRulescf.setBounds(60, 33, 79, 14);
		frmFiltroAntispam.getContentPane().add(lblRulescf);
		
		textField = new JTextField();
		textField.setBounds(139, 30, 173, 20);
		frmFiltroAntispam.getContentPane().add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(347, 30, 21, 21);
		frmFiltroAntispam.getContentPane().add(chckbxNewCheckBox);
		
		JLabel lblSpamlog = new JLabel("Spam.log");
		lblSpamlog.setBounds(60, 89, 69, 14);
		frmFiltroAntispam.getContentPane().add(lblSpamlog);
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 86, 173, 20);
		frmFiltroAntispam.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setBounds(347, 86, 21, 21);
		frmFiltroAntispam.getContentPane().add(chckbxNewCheckBox_1);
		
		JLabel lblHamlog = new JLabel("Ham.log");
		lblHamlog.setBounds(62, 145, 67, 14);
		frmFiltroAntispam.getContentPane().add(lblHamlog);
		
		textField_2 = new JTextField();
		textField_2.setBounds(139, 142, 173, 20);
		frmFiltroAntispam.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
		chckbxNewCheckBox_2.setBounds(347, 142, 21, 21);
		frmFiltroAntispam.getContentPane().add(chckbxNewCheckBox_2);
		
		JButton btnSeguinte = new JButton("Seguinte");
		btnSeguinte.setBounds(173, 199, 109, 23);
		btnSeguinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Janela2 j = new Janela2();
				j.NewScreen();
			}
		});
		frmFiltroAntispam.getContentPane().add(btnSeguinte);
	}

}
