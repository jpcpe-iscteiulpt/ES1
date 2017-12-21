package antiSpamFilter_METIA1_55;

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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame mainWindowFrame;
	private JTextField falsePositivesTextField_AC;
	private JTextField falseNegativesTextField_AC;
	private JTextField hamFilePathTextField;
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

	private int numberOfTimesPressed_AC = 0;
	private int numberOfTimesPressed_MC = 0;

	private DefaultListModel<String> model = new DefaultListModel<>();
	private DefaultTableModel manualConfigurationTableModel;

	public DefaultTableModel getManualConfigurationTableModel() {
		return manualConfigurationTableModel;
	}

	private DefaultTableModel automaticConfigurationTableModel = new DefaultTableModel();

	/**
	 * Começar a aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.mainWindowFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor da aplicação, que a inicia.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Inicializa os conteúdos do GUI.
	 */

	private void initialize() {
		mainWindowFrame = new JFrame();
		mainWindowFrame.setTitle("Filtro Anti-Spam");
		mainWindowFrame.setBounds(100, 100, 450, 300);
		mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindowFrame.setLayout(new CardLayout(0, 0));

		// Painel para escolher a pasta dos ficheiros ham, spam e rules

		JPanel filePathChooserPanel = new JPanel();
		filePathChooserPanel.setLayout(null);
		mainWindowFrame.getContentPane().add(filePathChooserPanel);
		filePathChooserPanel.setVisible(true);

		JLabel rulesFileLabel = new JLabel("Rules.cf");
		rulesFileLabel.setBounds(60, 33, 79, 14);
		filePathChooserPanel.add(rulesFileLabel);

		JTextField rulesFilePathTextField = new JTextField();
		rulesFilePathTextField.setBounds(139, 30, 173, 20);
		filePathChooserPanel.add(rulesFilePathTextField);
		rulesFilePathTextField.setColumns(10);
		rulesFilePathTextField.setEditable(false);

		JCheckBox rulesFileEncountered = new JCheckBox("");
		rulesFileEncountered.setBounds(347, 30, 21, 21);
		rulesFileEncountered.setEnabled(false);
		filePathChooserPanel.add(rulesFileEncountered);

		JLabel spamFileLabel = new JLabel("Spam.log");
		spamFileLabel.setBounds(60, 89, 69, 14);
		filePathChooserPanel.add(spamFileLabel);

		JTextField spamFilePathTextField = new JTextField();
		spamFilePathTextField.setBounds(139, 86, 173, 20);
		filePathChooserPanel.add(spamFilePathTextField);
		spamFilePathTextField.setColumns(10);
		spamFilePathTextField.setEditable(false);

		JCheckBox spamFileEncountered = new JCheckBox("");
		spamFileEncountered.setEnabled(false);
		spamFileEncountered.setBounds(347, 86, 21, 21);
		filePathChooserPanel.add(spamFileEncountered);

		JLabel hamFileLabel = new JLabel("Ham.log");
		hamFileLabel.setBounds(62, 145, 67, 14);
		filePathChooserPanel.add(hamFileLabel);

		hamFilePathTextField = new JTextField();
		hamFilePathTextField.setBounds(139, 142, 173, 20);
		filePathChooserPanel.add(hamFilePathTextField);
		hamFilePathTextField.setColumns(10);
		hamFilePathTextField.setEditable(false);

		JCheckBox hamFileEncountered = new JCheckBox("");
		hamFileEncountered.setEnabled(false);
		hamFileEncountered.setBounds(347, 142, 21, 21);
		filePathChooserPanel.add(hamFileEncountered);

		// Painel de escolha do tipo de configuração

		JPanel configurationTypeChooserPanel = new JPanel();
		configurationTypeChooserPanel.setLayout(null);
		mainWindowFrame.getContentPane().add(configurationTypeChooserPanel);
		configurationTypeChooserPanel.setVisible(false);

		JLabel manualConfigurationLabel = new JLabel("Configura\u00E7\u00E3o Manual");
		manualConfigurationLabel.setBounds(90, 53, 165, 14);
		configurationTypeChooserPanel.add(manualConfigurationLabel);

		JRadioButton chooseManualConfiguration = new JRadioButton("");
		chooseManualConfiguration.setBounds(303, 46, 21, 21);
		configurationTypeChooserPanel.add(chooseManualConfiguration);

		JLabel automaticConfigurationLabel = new JLabel("Configura\u00E7\u00E3o Autom\u00E1tica");
		automaticConfigurationLabel.setBounds(90, 105, 181, 14);
		configurationTypeChooserPanel.add(automaticConfigurationLabel);

		JRadioButton chooseAutomaticConfiguration = new JRadioButton("");
		chooseAutomaticConfiguration.setBounds(303, 102, 21, 21);
		configurationTypeChooserPanel.add(chooseAutomaticConfiguration);

		ButtonGroup configurationTypeChooserButtonGroup = new ButtonGroup();
		configurationTypeChooserButtonGroup.add(chooseManualConfiguration);
		configurationTypeChooserButtonGroup.add(chooseAutomaticConfiguration);

		// Painel de configuração manual

		JPanel manualConfigurationPanel = new JPanel();
		manualConfigurationPanel.setLayout(null);
		mainWindowFrame.getContentPane().add(manualConfigurationPanel);
		manualConfigurationPanel.setVisible(false);

		JLabel manualConfigurationWindowTitle = new JLabel("Configura\u00E7\u00E3o Manual");
		manualConfigurationWindowTitle.setBounds(165, 21, 145, 25);
		manualConfigurationPanel.add(manualConfigurationWindowTitle);

		JLabel falsePositivesLabel_MC = new JLabel("Falsos Positivos");
		falsePositivesLabel_MC.setBounds(50, 229, 120, 14);
		manualConfigurationPanel.add(falsePositivesLabel_MC);

		JLabel falseNegativesLabel_MC = new JLabel("Falsos Negativos");
		falseNegativesLabel_MC.setBounds(230, 229, 120, 14);
		manualConfigurationPanel.add(falseNegativesLabel_MC);

		JTextField falsePositivesTextField_MC = new JTextField();
		falsePositivesTextField_MC.setBounds(144, 226, 50, 20);
		manualConfigurationPanel.add(falsePositivesTextField_MC);
		falsePositivesTextField_MC.setColumns(10);
		falsePositivesTextField_MC.setEditable(false);

		JTextField falseNegativesTextField_MC = new JTextField();
		falseNegativesTextField_MC.setBounds(327, 226, 50, 20);
		manualConfigurationPanel.add(falseNegativesTextField_MC);
		falseNegativesTextField_MC.setColumns(10);
		falseNegativesTextField_MC.setEditable(false);

		// Painel de confirugação automática

		JPanel automaticConfigurationPanel = new JPanel();
		automaticConfigurationPanel.setLayout(null);
		mainWindowFrame.getContentPane().add(automaticConfigurationPanel);
		automaticConfigurationPanel.setVisible(false);

		JLabel automaticConfigurationWindowTitle = new JLabel("Configura\u00E7\u00E3o Autom\u00E1tica");
		automaticConfigurationWindowTitle.setBounds(161, 11, 166, 25);
		automaticConfigurationPanel.add(automaticConfigurationWindowTitle);

		JLabel falsePositivesLabel_AC = new JLabel("Falsos Positivos");
		falsePositivesLabel_AC.setBounds(45, 219, 120, 14);
		automaticConfigurationPanel.add(falsePositivesLabel_AC);

		JLabel falseNegativesLabel_AC = new JLabel("Falsos Negativos");
		falseNegativesLabel_AC.setBounds(225, 219, 120, 14);
		automaticConfigurationPanel.add(falseNegativesLabel_AC);

		falsePositivesTextField_AC = new JTextField();
		falsePositivesTextField_AC.setColumns(10);
		falsePositivesTextField_AC.setBounds(140, 219, 50, 20);
		falsePositivesTextField_AC.setEditable(false);
		automaticConfigurationPanel.add(falsePositivesTextField_AC);

		falseNegativesTextField_AC = new JTextField();
		falseNegativesTextField_AC.setColumns(10);
		falseNegativesTextField_AC.setBounds(323, 219, 50, 20);
		falseNegativesTextField_AC.setEditable(false);
		automaticConfigurationPanel.add(falseNegativesTextField_AC);

		// Butões de escolha do painel de escolha dos ficheiros ham, spam e rules

		JButton importFilesButton = new JButton("Importar");
		importFilesButton.setBounds(90, 188, 109, 23);
		filePathChooserPanel.add(importFilesButton);
		importFilesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				fm = FileManager.getInstance();

				folder = fm.fileFolderPrompt(getmainWindowFrame());

				files = fm.getFiles();

				boolean hamFound = false;
				boolean spamFound = false;
				boolean rulesFound = false;

				int hami = 0;
				int spami = 0;
				int rulesi = 0;

				int count = 0;

				for (File f : files) {
					if (f.getName().contains("ham")) {
						hamFound = true;
						hami = count;
					}
					if (f.getName().contains("spam")) {
						spamFound = true;
						spami = count;
					}
					if (f.getName().contains("rules")) {
						rulesFound = true;
						rulesi = count;
					}
					count++;
				}
				if (rulesFound) {
					rulesFileEncountered.setSelected(true);
					rulesFilePathTextField.setText(files[rulesi].getPath());
				}
				if (spamFound) {
					spamFileEncountered.setSelected(true);
					spamFilePathTextField.setText(files[spami].getPath());
				}
				if (hamFound) {
					hamFileEncountered.setSelected(true);
					hamFilePathTextField.setText(files[hami].getPath());
				}
			}

		});

		JButton nextButton = new JButton("Seguinte");
		nextButton.setBounds(215, 188, 109, 23);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (rulesFileEncountered.isSelected() && spamFileEncountered.isSelected()
						&& hamFileEncountered.isSelected()) {
					filePathChooserPanel.setVisible(false);
					configurationTypeChooserPanel.setVisible(true);
					ham = fm.read(files[0]);
					rules = fm.read(files[1]);
					spam = fm.read(files[2]);

					// Manual Configuration

					JPanel manualConfigurationTablePanel = new JPanel();
					manualConfigurationTablePanel.setLayout(new BorderLayout());
					manualConfigurationPanel.add(manualConfigurationTablePanel);
					manualConfigurationTablePanel.setBounds(50, 45, 377, 155);
					String[] tableHeaders = { "Regras", "Pesos" };
					String[][] tableData = new String[rules.size()][2];
					for (int i = 0; i < rules.size(); i++) {
						tableData[i][0] = rules.get(i);
						tableData[i][1] = fm.generateRandomWeights().get(i);
					}

					manualConfigurationTableModel = new DefaultTableModel(tableData, tableHeaders);

					JTable manualConfigurationTable = new JTable(manualConfigurationTableModel);

					manualConfigurationTable.getModel().addTableModelListener(new TableModelListener() {

						@Override
						public void tableChanged(TableModelEvent e) {
							for (int i = 0; i < rules.size(); i++) {
								try {

									if (Double.parseDouble(
											manualConfigurationTableModel.getValueAt(i, 1).toString()) > 5.0) {
										JOptionPane.showMessageDialog(mainWindowFrame,
												"Valor inválido, por favor insira um valor entre -5.0 e 5.0");
										manualConfigurationTableModel.setValueAt(5.0, i, 1);
									}

									if (Double.parseDouble(
											manualConfigurationTableModel.getValueAt(i, 1).toString()) < -5.0) {
										JOptionPane.showMessageDialog(mainWindowFrame,
												"Valor inválido, por favor insira um valor entre -5.0 e 5.0");
										manualConfigurationTableModel.setValueAt(-5.0, i, 1);
									}

								} catch (NumberFormatException exception) {
									JOptionPane.showMessageDialog(mainWindowFrame,
											"Formato incorreto, insira um número!");
									manualConfigurationTableModel.setValueAt(tableData[i][1], i, 1);
								}
							}
						}
					});

					manualConfigurationTablePanel.add(manualConfigurationTable, BorderLayout.CENTER);

					JScrollPane manualConfigurationScrollPane = new JScrollPane(manualConfigurationTable);
					manualConfigurationScrollPane.setVisible(true);
					manualConfigurationTablePanel.add(manualConfigurationScrollPane);

					JPanel manualConfigurationButtonPanel = new JPanel();
					manualConfigurationButtonPanel.setLayout(new GridLayout(0, 2));
					
					JButton saveButton = new JButton("Guardar");
					
					saveButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							writeBestSolutionWeightsToFile_MC();					
						}
					});
					
					JButton evaluateButton = new JButton("Avaliar");
					
					manualConfigurationButtonPanel.add(evaluateButton);
					manualConfigurationButtonPanel.add(saveButton);
					
					manualConfigurationTablePanel.add(manualConfigurationButtonPanel, BorderLayout.SOUTH);
					
					

					evaluateButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							double[] pesos = new double[manualConfigurationTableModel.getRowCount()];
							for (int i = 0; i < manualConfigurationTableModel.getRowCount(); i++) {
								pesos[i] = Double
										.parseDouble(manualConfigurationTableModel.getValueAt(i, 1).toString());
								evaluate(falsePositivesTextField_MC, falseNegativesTextField_MC, pesos);
								
							}

						}

					});

					manualConfigurationButtonPanel.add(evaluateButton);
					manualConfigurationButtonPanel.add(saveButton);
					manualConfigurationTablePanel.add(manualConfigurationButtonPanel, BorderLayout.SOUTH);
					
					// Confirugação automática

					JPanel automaticConfigurationTablePanel = new JPanel();
					automaticConfigurationTablePanel.setLayout(new BorderLayout());
					automaticConfigurationPanel.add(automaticConfigurationTablePanel);
					automaticConfigurationTablePanel.setBounds(50, 45, 377, 170);

					String[][] automaticConfigurationTableData = new String[rules.size()][2];
					String[] automaticConfigurationTableHeaders = { "Regras", "Pesos" };
					automaticConfigurationTableData = new String[rules.size()][2];
					for (int i = 0; i < rules.size(); i++) {
						automaticConfigurationTableData[i][0] = rules.get(i);
						if (solutionSelection == -1) {
							automaticConfigurationTableData[i][1] = " ";
						} else {
							automaticConfigurationTableData[i][1] = fm.getWeights().get(0).split(" ")[i];
						}
					}

					automaticConfigurationTableModel = new DefaultTableModel(automaticConfigurationTableData,
							automaticConfigurationTableHeaders) {
						@Override
						public boolean isCellEditable(int row, int column) {
							// all cells false
							return false;
						}
					};

					JTable automaticConfigurationTable = new JTable(automaticConfigurationTableModel);
					automaticConfigurationTablePanel.add(automaticConfigurationTable, BorderLayout.CENTER);
					automaticConfigurationTable.setEnabled(false);

					JScrollPane automaticConfigurationScrollPane = new JScrollPane(automaticConfigurationTable);
					manualConfigurationScrollPane.setVisible(true);
					automaticConfigurationTablePanel.add(automaticConfigurationScrollPane);

					JPanel autoConfigurationButtonPanel = new JPanel();
					autoConfigurationButtonPanel.setLayout(new GridLayout(0, 2));

					JButton runAutomaticConfiguration = new JButton("Iniciar");
					runAutomaticConfiguration.setBounds(190, 240, 80, 20);

					runAutomaticConfiguration.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							String[] args = {};
							try {
								new AntiSpamFilterAutomaticConfiguration();
								AntiSpamFilterAutomaticConfiguration.main(args);
								displaySolutions();
								generateGraphic();
								generateLatex();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});

					JButton saveBestSolutionToFile = new JButton("Guardar");

					saveBestSolutionToFile.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							writeBestSolutionWeightsToFile_AC();
						}
					});

					autoConfigurationButtonPanel.add(runAutomaticConfiguration);
					autoConfigurationButtonPanel.add(saveBestSolutionToFile);
					automaticConfigurationTablePanel.add(autoConfigurationButtonPanel, BorderLayout.SOUTH);

				} else {
					JOptionPane.showMessageDialog(mainWindowFrame, "Por favor preencha todos os campos.");
				}

			}
		});
		filePathChooserPanel.add(nextButton);

		// Butões do painel de escolha do tipo configuração

		JButton configurationTypeChooserBackButton = new JButton("Anterior");
		configurationTypeChooserBackButton.setBounds(90, 188, 109, 23);
		configurationTypeChooserPanel.add(configurationTypeChooserBackButton);
		configurationTypeChooserBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				configurationTypeChooserPanel.setVisible(false);
				filePathChooserPanel.setVisible(true);

			}

		});

		JButton configurationTypeChooserNextButton = new JButton("Seguinte");
		configurationTypeChooserNextButton.setBounds(215, 188, 109, 23);
		configurationTypeChooserPanel.add(configurationTypeChooserNextButton);
		configurationTypeChooserNextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chooseManualConfiguration.isSelected()) {
					configurationTypeChooserPanel.setVisible(false);
					manualConfigurationPanel.setVisible(true);
				} else if (chooseAutomaticConfiguration.isSelected()) {
					configurationTypeChooserPanel.setVisible(false);
					automaticConfigurationPanel.setVisible(true);
				}

			}

		});

	}

	/**
	 * Percorre o ficheiro rules e retorna a posição de uma determinada regra
	 * 
	 * @param s
	 *            - regra a procurar
	 * @return ruleIndex - posição da regra no vector rules
	 */
	public int findRules(String s) {
		int ruleIndex = 0;

		for (int i = 0; i < rules.size(); i++) {
			if (rules.get(i).contains(s)) {
				ruleIndex = i;
			}
		}
		return ruleIndex;
	}

	/**
	 * método para determinar o númerop de falsos positivos e falsos negativos
	 * 
	 * @param falsePositiveTextField
	 *            - textfield onde são representados os falsos positivos na janela
	 * @param falseNegativeTextField
	 *            - textfield onde são representados os falsos negativos na janela
	 * @param x
	 *            - vector com os pesos gerados
	 */
	public void evaluate(JTextField falsePositiveTextField, JTextField falseNegativeTextField, double[] x) {
		int falsePositives = 0;
		for (int i = 0; i < ham.size(); i++) {
			double threshold = 0;
			String[] hamFileLine = ham.get(i).split("\t");
			for (int j = 1; j < hamFileLine.length; j++) {
				int rulePosition = findRules(hamFileLine[j]);
				threshold += x[rulePosition];
			}
			if (threshold >= 5) {
				falsePositives++;
			}
		}

		int falseNegatives = 0;
		for (int i = 0; i < spam.size(); i++) {
			double threshold = 0;
			String[] spamFileLine = spam.get(i).split("\t");
			for (int j = 1; j < spamFileLine.length; j++) {
				int rulePosition = findRules(spamFileLine[j]);
				threshold += x[rulePosition];
			}
			if (threshold < 5) {
				falseNegatives++;
			}
		}
		falsePositiveTextField.setText(String.valueOf(falsePositives));
		falseNegativeTextField.setText(String.valueOf(falseNegatives));
		falsePositiveTextField.revalidate();
		falseNegativeTextField.revalidate();
	}

	/**
	 * Método para criar uma janela que mostra as soluções geradas pelo algoritmo
	 * NSGAII e permitir ao utilizador escolher a que pretende usar. A solução
	 * óptima é indicada por defeito
	 */

	public void displaySolutions() {
		fm.folderParser(Paths
				.get(System.getProperty("user.home"), "git/ES1-2017-METIA1-55/experimentBaseDirectory/referenceFronts")
				.toString());

		solutionFrame = new JFrame();
		solutionFrame.setTitle("Solu\u00E7\u00F5es Geradas");
		solutionFrame.setBounds(100, 100, 450, 300);
		solutionFrame.getContentPane().setLayout(null);

		JPanel solutionMainPanel = new JPanel();
		solutionMainPanel.setBounds(101, 11, 206, 239);
		solutionFrame.getContentPane().add(solutionMainPanel);
		solutionMainPanel.setLayout(new BorderLayout(0, 0));

		JPanel solutionLabelPanel = new JPanel();
		solutionMainPanel.add(solutionLabelPanel, BorderLayout.NORTH);
		solutionLabelPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel chooseASolutionLabel = new JLabel("Escolha uma solu\u00E7\u00E3o:");
		solutionLabelPanel.add(chooseASolutionLabel);
		chooseASolutionLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel bestSolutionbel = new JLabel("(A melhor está pré-seleccionada)");
		bestSolutionbel.setHorizontalAlignment(SwingConstants.CENTER);
		solutionLabelPanel.add(bestSolutionbel);

		JLabel fpAndFnLabel = new JLabel("Falsos Positivos | Falsos Negativos");
		fpAndFnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		solutionLabelPanel.add(fpAndFnLabel);

		/**
		 * Criação da Lista de soluções
		 */
		int s = 0;

		for (int i = 0; i < fm.getSolutions().size(); i++) {
			model.addElement(fm.getSolutions().get(i));
			if (Double.parseDouble(fm.getSolutions().get(i).split("\\s+")[0]) < Double
					.parseDouble(fm.getSolutions().get(s).split("\\s+")[0])) {
				s = i;
			}
		}
		solutionSelection = s;

		JList solutionList = new JList<>(model);
		solutionList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		solutionMainPanel.add(solutionList);
		solutionList.setSelectedIndex(s);

		solutionList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				solutionSelection = 0;
				if (!e.getValueIsAdjusting()) {
					selectedValue = solutionList.getSelectedValue();
					solutionSelection = solutionList.getSelectedIndex();
				}
			}
		});

		JButton confirmButton = new JButton("Confirmar");
		solutionMainPanel.add(confirmButton, BorderLayout.SOUTH);
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < rules.size(); i++) {
					automaticConfigurationTableModel.setValueAt(fm.getWeights().get(solutionSelection).split(" ")[i], i,
							1);
					falsePositivesTextField_AC.setText(fm.getSolutions().get(solutionSelection).split(" ")[0]);
					falseNegativesTextField_AC.setText(fm.getSolutions().get(solutionSelection).split(" ")[1]);
				}
			}
		});

		solutionFrame.setVisible(true);
		solutionFrame.setLocationRelativeTo(null);
	}

	/**
	 * Método para gerar o ficheiro do gráfico R
	 */

	public void generateGraphic() {
		String[] params = new String[2];
		String[] envp = new String[1];

		params[0] = "C:\\Program Files\\R\\R-3.4.3\\bin\\x64\\Rscript.exe";
		params[1] = "C:\\Users\\sara\\git\\ES1-2017-METIA1-55\\experimentBaseDirectory\\AntiSpamStudy\\R\\HV.Boxplot.R";
		envp[0] = "Path = C:\\Program Files\\R\\R-3.4.3\\bin\\x64";
		try {
			Process p = Runtime.getRuntime().exec(params, envp,
					new File("C:\\Users\\sara\\git\\ES1-2017-METIA1-55\\experimentBaseDirectory\\AntiSpamStudy\\R"));
		} catch (IOException e) {
			System.out.println("Erro a gerar os gráficos R");
		}
	}

	/**
	 * Método para gerar o pdf do latex
	 */
	public void generateLatex() {

		String[] paramsLatex = new String[2];
		String[] envpLatex = new String[1];
		paramsLatex[0] = "C:\\Program Files\\MiKTeX 2.9\\miktex\\bin\\x64\\pdflatex.exe";
		paramsLatex[1] = "C:\\Users\\sara\\git\\ES1-2017-METIA1-55\\experimentBaseDirectory\\AntiSpamStudy\\latex\\AntiSpamStudy.tex";
		envpLatex[0] = "Path = C:\\Program Files\\MiKTeX 2.9\\miktex\\bin\\x64";

		try {
			Process p = Runtime.getRuntime().exec(paramsLatex, envpLatex, new File(
					"C:\\Users\\sara\\git\\ES1-2017-METIA1-55\\experimentBaseDirectory\\AntiSpamStudy\\latex"));
		} catch (IOException e) {
			System.out.println("Erro a gerar o latex");
		}
	}

	/**
	 * 
	 * Escreve o vetor de pesos de uma solução a escolha do utilizador para o
	 * ficheiro rules.cf
	 * 
	 */
	private void writeBestSolutionWeightsToFile_AC() {
		if (numberOfTimesPressed_AC == 0) {
			try {
				BufferedWriter writeToConfigurationFile = new BufferedWriter(new FileWriter(
						new File("C:/Users/Joao/git/ES1-2017-METIA1-55/AntiSpamConfigurationForProfessionalMailbox"
								+ "/rules.cf")));
				String[] chosenSolutionWeights = fm.getWeights().get(solutionSelection).split(" ");

				for (int i = 0; i < rules.size(); i++) {

					writeToConfigurationFile.write(rules.get(i) + "\t" + chosenSolutionWeights[i] + "\n");
				}
				writeToConfigurationFile.close();
				numberOfTimesPressed_AC++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				BufferedWriter writeToConfigurationFile = new BufferedWriter(new FileWriter(
						new File("C:/Users/Joao/git/ES1-2017-METIA1-55/AntiSpamConfigurationForProfessionalMailbox"
								+ "/rules.cf")));
				
				String[] chosenSolutionWeights = fm.getWeights().get(solutionSelection).split(" ");
				
				for (int i = 0; i < rules.size(); i++) {
					ArrayList<String> rules = fm.getRules();
					writeToConfigurationFile.write(rules.get(i) + "\t" + chosenSolutionWeights[i] + "\n");
				}
				writeToConfigurationFile.close();
				numberOfTimesPressed_AC++;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}
	
	private void writeBestSolutionWeightsToFile_MC() {
		if (numberOfTimesPressed_MC == 0) {
			try {
				BufferedWriter writeToConfigurationFile = new BufferedWriter(new FileWriter(
						new File("C:/Users/Joao/git/ES1-2017-METIA1-55/AntiSpamConfigurationForProfessionalMailbox"
								+ "/rules.cf")));
				
				for (int i = 0; i < rules.size(); i++) {
					writeToConfigurationFile.write(rules.get(i) + "\t" + getManualConfigurationTableModel().getValueAt(i, 1).toString() + "\n");
				}
				writeToConfigurationFile.close();
				numberOfTimesPressed_MC++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				BufferedWriter writeToConfigurationFile = new BufferedWriter(new FileWriter(
						new File("C:/Users/Joao/git/ES1-2017-METIA1-55/AntiSpamConfigurationForProfessionalMailbox"
								+ "/rules.cf")));
				
				for (int i = 0; i < rules.size(); i++) {
					ArrayList<String> rules = fm.getRules();
					writeToConfigurationFile.write(rules.get(i) + "\t" + getManualConfigurationTableModel().getValueAt(i, 1).toString() + "\n");
				}
				writeToConfigurationFile.close();
				numberOfTimesPressed_MC++;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

	public JFrame getmainWindowFrame() {
		return mainWindowFrame;
	}

	public void setmainWindowFrame(JFrame mainWindowFrame) {
		this.mainWindowFrame = mainWindowFrame;
	}

	public JTextField getTextField() {
		return falsePositivesTextField_AC;
	}

	public void setTextField(JTextField textField) {
		this.falsePositivesTextField_AC = textField;
	}

	public JTextField getTextField_1() {
		return falseNegativesTextField_AC;
	}

	public void setTextField_1(JTextField textField_1) {
		this.falseNegativesTextField_AC = textField_1;
	}

	public JTextField getTextField_2() {
		return hamFilePathTextField;
	}

	public void setTextField_2(JTextField textField_2) {
		this.hamFilePathTextField = textField_2;
	}

}
