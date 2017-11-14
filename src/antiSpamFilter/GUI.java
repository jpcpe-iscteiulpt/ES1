package antiSpamFilter;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class GUI {

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
					GUI window = new GUI();
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
	public GUI() {
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
		frmFiltroAntispam.setLayout(new CardLayout(0,0));
		
		//JPanel 1
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		frmFiltroAntispam.getContentPane().add(panel1);
		panel1.setVisible(true);
		
		JLabel lblRulescf = new JLabel("Rules.cf");
		lblRulescf.setBounds(60, 33, 79, 14);
		panel1.add(lblRulescf);
		
		textField = new JTextField();
		textField.setBounds(139, 30, 173, 20);
		panel1.add(textField);
		textField.setColumns(10);
		
		
		JCheckBox rules_check = new JCheckBox("");
		rules_check.setBounds(347, 30, 21, 21);
		rules_check.setEnabled(false);
		panel1.add(rules_check);
		
		textField.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField t=(JTextField)e.getSource();
				if(!t.getText().equals("")){
					rules_check.setSelected(true);
				} else {
					rules_check.setSelected(false);
				}
			}
			
		});
		
		JLabel lblSpamlog = new JLabel("Spam.log");
		lblSpamlog.setBounds(60, 89, 69, 14);
		panel1.add(lblSpamlog);
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 86, 173, 20);
		panel1.add(textField_1);
		textField_1.setColumns(10);
		
		JCheckBox spam_check = new JCheckBox("");
		spam_check.setEnabled(false);
		spam_check.setBounds(347, 86, 21, 21);
		panel1.add(spam_check);

		
		textField_1.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField t=(JTextField)e.getSource();
				if(!t.getText().equals("")){
					spam_check.setSelected(true);
				} else {
					spam_check.setSelected(false);
				}
			}

		});
		
		JLabel lblHamlog = new JLabel("Ham.log");
		lblHamlog.setBounds(62, 145, 67, 14);
		panel1.add(lblHamlog);
		
		textField_2 = new JTextField();
		textField_2.setBounds(139, 142, 173, 20);
		panel1.add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox ham_check = new JCheckBox("");
		ham_check.setEnabled(false);
		ham_check.setBounds(347, 142, 21, 21);
		panel1.add(ham_check);
		
		textField_2.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField t=(JTextField)e.getSource();
				if(!t.getText().equals("")){
					ham_check.setSelected(true);
				} else {
					ham_check.setSelected(false);
				}
			}

		});
		
		//JPanel 2
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		frmFiltroAntispam.getContentPane().add(panel2);
		panel2.setVisible(false);
		JLabel lblConfiguraoManual = new JLabel("Configura\u00E7\u00E3o Manual");
		lblConfiguraoManual.setBounds(90, 53, 165, 14);
		panel2.add(lblConfiguraoManual);
		
		JRadioButton manual_check = new JRadioButton("");
		manual_check.setBounds(303, 46, 21, 21);
		panel2.add(manual_check);
		
		JLabel lblConfiguraoAutomtica = new JLabel("Configura\u00E7\u00E3o Autom\u00E1tica");
		lblConfiguraoAutomtica.setBounds(90, 105, 181, 14);
		panel2.add(lblConfiguraoAutomtica);
		
		JRadioButton auto_check = new JRadioButton("");
		auto_check.setBounds(303, 102, 21, 21);
		panel2.add(auto_check);
		
		ButtonGroup group = new ButtonGroup();
		group.add(manual_check);
		group.add(auto_check);
		//JPanel3
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		frmFiltroAntispam.getContentPane().add(panel3);
		panel3.setVisible(false);
		JLabel lblConfiguraoManual1 = new JLabel("Configura\u00E7\u00E3o Manual");
		lblConfiguraoManual1.setBounds(165, 21, 145, 25);
		panel3.add(lblConfiguraoManual1);
		
		JLabel lblFalsosPositivos = new JLabel("Falsos Positivos");
		lblFalsosPositivos.setBounds(60, 229, 75, 14);
		panel3.add(lblFalsosPositivos);
		
		JLabel lblFalsosNegativos = new JLabel("Falsos Negativos");
		lblFalsosNegativos.setBounds(240, 229, 81, 14);
		panel3.add(lblFalsosNegativos);
		
		textField = new JTextField();
		textField.setBounds(144, 226, 50, 20);
		panel3.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(327, 226, 50, 20);
		panel3.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		JLabel lblRegras = new JLabel("Regras");
		lblRegras.setBounds(89, 50, 46, 14);
		panel3.add(lblRegras);
		
		JLabel lblPesos = new JLabel("Pesos");
		lblPesos.setBounds(275, 50, 46, 14);
		panel3.add(lblPesos);
		
		//JPanel4
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		frmFiltroAntispam.getContentPane().add(panel4);
		panel4.setVisible(false);
		JLabel lblConfiguraoAutomtica1 = new JLabel("Configura\u00E7\u00E3o Autom\u00E1tica");
		lblConfiguraoAutomtica1.setBounds(161, 11, 166, 25);
		panel4.add(lblConfiguraoAutomtica1);
		
		JLabel label_1 = new JLabel("Falsos Positivos");
		label_1.setBounds(56, 219, 75, 14);
		panel4.add(label_1);
		
		JLabel label_2 = new JLabel("Falsos Negativos");
		label_2.setBounds(236, 219, 81, 14);
		panel4.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(140, 216, 50, 20);
		textField.setEditable(false);
		panel4.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(323, 216, 50, 20);
		textField_1.setEditable(false);
		panel4.add(textField_1);
		
		JLabel label_3 = new JLabel("Regras");
		label_3.setBounds(85, 40, 46, 14);
		panel4.add(label_3);
		
		JLabel label_4 = new JLabel("Pesos");
		label_4.setBounds(271, 40, 46, 14);
		panel4.add(label_4);
		
		//Butões painel1
		JButton btnSeguinte = new JButton("Seguinte");
		btnSeguinte.setBounds(173, 199, 109, 23);
		btnSeguinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(rules_check.isSelected() && spam_check.isSelected() && ham_check.isSelected()){
					panel1.setVisible(false);
					panel2.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frmFiltroAntispam, "Por favor preencha todos os campos.");
				}
				
			}
		});
		panel1.add(btnSeguinte);
		
		//Butões painel2
		JButton btnAnterior2 = new JButton("Anterior");
		btnAnterior2.setBounds(90, 188, 109, 23);
		panel2.add(btnAnterior2);
		btnAnterior2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel2.setVisible(false);
				panel1.setVisible(true);
				
			}
			
		});
		
		JButton btnSeguinte2 = new JButton("Seguinte");
		btnSeguinte2.setBounds(215, 188, 109, 23);
		panel2.add(btnSeguinte2);
		btnSeguinte2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(manual_check.isSelected()){
					panel2.setVisible(false);
					panel3.setVisible(true);
				} else if(auto_check.isSelected()){
					panel2.setVisible(false);
					panel4.setVisible(true);
				}
				
			}
			
		});
		
		
	}

	
	

}
