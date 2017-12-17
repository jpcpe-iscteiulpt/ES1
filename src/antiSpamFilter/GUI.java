package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frmFiltroAntispam;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String folder;
	private File[] files;
	private String executionPath;
	private ArrayList<String> spam;
	private ArrayList<String> ham;
	private ArrayList<String> rules;
	private FileManager fm;

	private JFrame solutionFrame;
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private Object selectedValue = new Object();

	private int solutionSelection = -1;

	private DefaultListModel<String> model = new DefaultListModel<>();

	private DefaultTableModel model2 = new DefaultTableModel();

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
		frmFiltroAntispam.setLayout(new CardLayout(0, 0));

		// JPanel 1
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		frmFiltroAntispam.getContentPane().add(panel1);
		panel1.setVisible(true);

		JLabel lblRulescf = new JLabel("Rules.cf");
		lblRulescf.setBounds(60, 33, 79, 14);
		panel1.add(lblRulescf);

		JTextField textFieldR = new JTextField();
		textFieldR.setBounds(139, 30, 173, 20);
		panel1.add(textFieldR);
		textFieldR.setColumns(10);
		textFieldR.setEditable(false);

		JCheckBox rules_check = new JCheckBox("");
		rules_check.setBounds(347, 30, 21, 21);
		rules_check.setEnabled(false);
		panel1.add(rules_check);

		JLabel lblSpamlog = new JLabel("Spam.log");
		lblSpamlog.setBounds(60, 89, 69, 14);
		panel1.add(lblSpamlog);

		JTextField textFieldS = new JTextField();
		textFieldS.setBounds(139, 86, 173, 20);
		panel1.add(textFieldS);
		textFieldS.setColumns(10);
		textFieldS.setEditable(false);

		JCheckBox spam_check = new JCheckBox("");
		spam_check.setEnabled(false);
		spam_check.setBounds(347, 86, 21, 21);
		panel1.add(spam_check);
		//

		JLabel lblHamlog = new JLabel("Ham.log");
		lblHamlog.setBounds(62, 145, 67, 14);
		panel1.add(lblHamlog);

		textField_2 = new JTextField();
		textField_2.setBounds(139, 142, 173, 20);
		panel1.add(textField_2);
		textField_2.setColumns(10);

		//
		textField_2.setEditable(false);

		JCheckBox ham_check = new JCheckBox("");
		ham_check.setEnabled(false);
		ham_check.setBounds(347, 142, 21, 21);
		panel1.add(ham_check);

		// JPanel 2

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

		// JPanel3
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		frmFiltroAntispam.getContentPane().add(panel3);
		panel3.setVisible(false);
		JLabel lblConfiguraoManual1 = new JLabel("Configura\u00E7\u00E3o Manual");
		lblConfiguraoManual1.setBounds(165, 21, 145, 25);
		panel3.add(lblConfiguraoManual1);

		JLabel lblFalsosPositivos = new JLabel("Falsos Positivos");
		lblFalsosPositivos.setBounds(50, 229, 120, 14);
		panel3.add(lblFalsosPositivos);

		JLabel lblFalsosNegativos = new JLabel("Falsos Negativos");
		lblFalsosNegativos.setBounds(230, 229, 120, 14);
		panel3.add(lblFalsosNegativos);

		JTextField textFieldm = new JTextField();
		textFieldm.setBounds(144, 226, 50, 20);
		panel3.add(textFieldm);
		textFieldm.setColumns(10);
		textFieldm.setEditable(false);

		JTextField textField_1m = new JTextField();
		textField_1m.setBounds(327, 226, 50, 20);
		panel3.add(textField_1m);

		textField_1m.setColumns(10);
		textField_1m.setEditable(false);

		// JPanel4

		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		frmFiltroAntispam.getContentPane().add(panel4);
		panel4.setVisible(false);
		JLabel lblConfiguraoAutomtica1 = new JLabel("Configura\u00E7\u00E3o Autom\u00E1tica");
		lblConfiguraoAutomtica1.setBounds(161, 11, 166, 25);
		panel4.add(lblConfiguraoAutomtica1);

		JLabel label_1 = new JLabel("Falsos Positivos");
		label_1.setBounds(45, 219, 120, 14);
		panel4.add(label_1);

		JLabel label_2 = new JLabel("Falsos Negativos");
		label_2.setBounds(225, 219, 120, 14);
		panel4.add(label_2);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(140, 219, 50, 20);
		textField.setEditable(false);
		panel4.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(323, 219, 50, 20);
		textField_1.setEditable(false);
		panel4.add(textField_1);

		// botões painel1
		JButton btnEscolher = new JButton("Importar");
		btnEscolher.setBounds(90, 188, 109, 23);
		panel1.add(btnEscolher);
		btnEscolher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				fm = new FileManager();

				folder = fm.FileFolderPrompt(getFrmFiltroAntispam());

				files = fm.getFiles();
				boolean ham = false;
				boolean spam = false;
				boolean rules = false;
				int hami = 0;
				int spami = 0;
				int rulesi = 0;
				int count = 0;
				for (File f : files) {
					if (f.getName().contains("ham")) {
						ham = true;
						hami = count;
					}
					if (f.getName().contains("spam")) {
						spam = true;
						spami = count;
					}
					if (f.getName().contains("rules")) {
						rules = true;
						rulesi = count;
					}
					count++;
				}
				if (rules) {
					rules_check.setSelected(true);
					textFieldR.setText(files[rulesi].getPath());
				}
				if (spam) {
					spam_check.setSelected(true);
					textFieldS.setText(files[spami].getPath());
				}
				if (ham) {
					ham_check.setSelected(true);
					textField_2.setText(files[hami].getPath());
				}
			}

		});

		JButton btnSeguinte = new JButton("Seguinte");
		btnSeguinte.setBounds(215, 188, 109, 23);
		btnSeguinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (rules_check.isSelected() && spam_check.isSelected() && ham_check.isSelected()) {
					panel1.setVisible(false);
					panel2.setVisible(true);
					ham = fm.Read(files[0]);
					rules = fm.Read(files[1]);
					spam = fm.Read(files[2]);

					// Configuração manual
					JPanel container = new JPanel();
					container.setLayout(new BorderLayout());
					panel3.add(container);
					container.setBounds(50, 45, 377, 155);
					String[] header = { "Regras", "Pesos" };
					String[][] data = new String[rules.size()][2];
					for (int i = 0; i < rules.size(); i++) {
						data[i][0] = rules.get(i);
						data[i][1] = fm.generateRandomWeights().get(i);
					}

					DefaultTableModel model = new DefaultTableModel(data, header);

					JTable table = new JTable(model);

					table.getModel().addTableModelListener(new TableModelListener() {

						@Override
						public void tableChanged(TableModelEvent e) {
							for (int i = 0; i < rules.size(); i++) {
								try {

									if (Double.parseDouble(model.getValueAt(i, 1).toString()) > 5.0) {
										JOptionPane.showMessageDialog(frmFiltroAntispam,
												"Valor inválido, por favor insira um valor entre -5.0 e 5.0");
										model.setValueAt(5.0, i, 1);
									}

									if (Double.parseDouble(model.getValueAt(i, 1).toString()) < -5.0) {
										JOptionPane.showMessageDialog(frmFiltroAntispam,
												"Valor inválido, por favor insira um valor entre -5.0 e 5.0");
										model.setValueAt(-5.0, i, 1);
									}

								} catch (NumberFormatException exception) {
									JOptionPane.showMessageDialog(frmFiltroAntispam,
											"Formato incorreto, insira um número!");
									model.setValueAt(data[i][1], i, 1);
								}
							}
						}
					});

					container.add(table, BorderLayout.CENTER);

					JScrollPane js = new JScrollPane(table);
					js.setVisible(true);
					container.add(js);

					JButton evaluate = new JButton("Refazer");
					evaluate.setBounds(190, 202, 80, 20);
					panel3.add(evaluate);

					evaluate.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							double[] pesos = new double[model.getRowCount()];
							for (int i = 0; i < model.getRowCount(); i++) {
								pesos[i] = Double.parseDouble(model.getValueAt(i, 1).toString());
								evaluate(textFieldm, textField_1m, pesos);
							}

						}

					});

					// Configuração automática

					JPanel container2 = new JPanel();
					container2.setLayout(new BorderLayout());
					panel4.add(container2);
					container2.setBounds(50, 45, 377, 170);

					String[][] data2 = new String[rules.size()][2];
					String[] header2 = { "Regras", "Pesos" };
					data2 = new String[rules.size()][2];
					for (int i = 0; i < rules.size(); i++) {
						data2[i][0] = rules.get(i);
						if (solutionSelection == -1) {
							data2[i][1] = " ";
						} else {
							data2[i][1] = fm.getWeights().get(0).split(" ")[i];
						}
					}

					model2 = new DefaultTableModel(data2, header2) {
						@Override
						public boolean isCellEditable(int row, int column) {
							// all cells false
							return false;
						}
					};

					JTable table2 = new JTable(model2);
					container2.add(table2, BorderLayout.CENTER);
					table2.setEnabled(false);
					JScrollPane js2 = new JScrollPane(table2);
					js.setVisible(true);
					container2.add(js2);
					JButton runAuto = new JButton("Iniciar");
					runAuto.setBounds(190, 240, 80, 20);
					panel4.add(runAuto);

					runAuto.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							String[] args = {};
							try {
								new AntiSpamFilterAutomaticConfiguration();
								AntiSpamFilterAutomaticConfiguration.main(args);
								displaySolutions();

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});

				} else {
					JOptionPane.showMessageDialog(frmFiltroAntispam, "Por favor preencha todos os campos.");
				}

			}
		});
		panel1.add(btnSeguinte);

		// botões painel2
		JButton btnAnterior2 = new JButton("Anterior");
		btnAnterior2.setBounds(90, 188, 109, 23);
		panel2.add(btnAnterior2);
		btnAnterior2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel2.setVisible(false);
				panel1.setVisible(true);

			}

		});

		JButton btnSeguinte2 = new JButton("Seguinte");
		btnSeguinte2.setBounds(215, 188, 109, 23);
		panel2.add(btnSeguinte2);
		btnSeguinte2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (manual_check.isSelected()) {
					panel2.setVisible(false);
					panel3.setVisible(true);
				} else if (auto_check.isSelected()) {
					panel2.setVisible(false);
					panel4.setVisible(true);
				}

			}

		});

	}

	public int findRules(String s) {
		int posicao = 0;

		for (int i = 0; i < rules.size(); i++) {
			if (rules.get(i).contains(s)) {
				posicao = i;
			}
		}
		return posicao;
	}

	public void evaluate(JTextField fpt, JTextField fnt, double[] x) {
		int fp = 0;
		for (int i = 0; i < ham.size(); i++) {
			double acumulator = 0;
			String[] line = ham.get(i).split("\t");
			for (int j = 1; j < line.length; j++) {
				// System.out.println(j+" "+ line[j]);
				int position = findRules(line[j]);
				acumulator += x[position];
			}
			if (acumulator >= 5) {
				fp++;
			}
		}

		int fn = 0;
		for (int i = 0; i < spam.size(); i++) {
			double acumulator = 0;
			String[] line = spam.get(i).split("\t");
			for (int j = 1; j < line.length; j++) {
				int position = findRules(line[j]);
				acumulator += x[position];
			}
			if (acumulator < 5) {
				fn++;
			}
		}
		fpt.setText(String.valueOf(fp));
		fnt.setText(String.valueOf(fn));
		fpt.revalidate();
		fnt.revalidate();
	}

	public void displaySolutions() {
		fm.folderParser(Paths
				.get(System.getProperty("user.home"), "git/ES1-2017-METIA1-55/experimentBaseDirectory/referenceFronts")
				.toString());

		solutionFrame = new JFrame();
		solutionFrame.setTitle("Solu\u00E7\u00F5es Geradas");
		solutionFrame.setBounds(100, 100, 450, 300);
		solutionFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(101, 11, 206, 239);
		solutionFrame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblEscolhaUmaSoluo = new JLabel("Escolha uma solu\u00E7\u00E3o:");
		panel_1.add(lblEscolhaUmaSoluo);
		lblEscolhaUmaSoluo.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("(Falsos Positivos/Falsos Negativos)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);

		/**
		 * Criação da Lista de soluções
		 */
		int s = 0;
		
		for (int i = 0; i < fm.getSolutions().size(); i++) {
			model.addElement(fm.getSolutions().get(i));
			if(Double.parseDouble(fm.getSolutions().get(i).split("\\s+")[1]) < Double.parseDouble(fm.getSolutions().get(s).split("\\s+")[1])) {
				s = i;
			}
		}

		JList list = new JList<>(model);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(list);
		list.setSelectedIndex(s);

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				solutionSelection = 0;
				if (!e.getValueIsAdjusting()) {
					selectedValue = list.getSelectedValue();
					solutionSelection = list.getSelectedIndex();
				}
			}
		});

		JButton confButton = new JButton("Confirmar");
		panel.add(confButton, BorderLayout.SOUTH);
		confButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < rules.size(); i++) {
					model2.setValueAt(fm.getWeights().get(solutionSelection).split(" ")[i], i, 1);
					textField.setText(fm.getSolutions().get(solutionSelection).split(" ")[0]);
					textField_1.setText(fm.getSolutions().get(solutionSelection).split(" ")[1]);
				}
			}
		});

		solutionFrame.setVisible(true);
		solutionFrame.setLocationRelativeTo(null);
		// solutionFrame.pack();
	}

	public JFrame getFrmFiltroAntispam() {
		return frmFiltroAntispam;
	}

	public void setFrmFiltroAntispam(JFrame frmFiltroAntispam) {
		this.frmFiltroAntispam = frmFiltroAntispam;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

}
