package antiSpamFilter;

import java.awt.Component;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoSetAndFrontFromDoubleSolutions;

public class FileManager {

	private ArrayList<String> lines;
	private String executionPath;

	JFileChooser chooser = new JFileChooser();

	private File[] files;

	private ArrayList<String> ham;
	private ArrayList<String> spam;
	private ArrayList<String> rules;

	public FileManager() {
	}

	/**
	 * Cria um prompt para o user indicar onde quer guardar os resultados, o
	 * local escolhido é usado para o experimentBaseDirectory
	 * 
	 * @param parent
	 *            - Janela sobre a qual vai ser desenhado o prompt de
	 *            directoria.
	 * @return Pasta a usar para experimentBaseDirectory.
	 */
	public String BaseDirectoryPrompt(Component parent) {
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Selected Experiment Directory:  " + chooser.getSelectedFile().getAbsolutePath());
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return "experimentBaseDirectory";
	}

	/**
	 * Cria um prompt para o user escolher onde estão os ficheiros ham.log,
	 * spam.log e rules.cf
	 * 
	 * @param parent
	 *            - Janela sobre a qual vai ser desenhado o prompt de
	 *            directoria.
	 * @return Pasta a usar para experimentBaseDirectory.
	 */
	public String FileFolderPrompt(Component parent) {
		// revisao possivel - permitir escolher ficheiros individualmente em vez
		// de
		// pasta inteira
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".log e .cm", "log", "cm");
		chooser.setFileFilter(filter);

		if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Selected Folder:  " + chooser.getSelectedFile().getName());
			this.executionPath = chooser.getSelectedFile().getAbsolutePath();
			folderParser(executionPath);
			return executionPath;
		}
		return null;

	}

	/**
	 * Guarda os ficheiros ham spam e log presentes na pasta e chama o leitor
	 * para os converter em arrays de strings
	 * 
	 * @param executionPath
	 *            path para a pasta a percorrer
	 */
	public void folderParser(String executionPath) {

		files = new File(executionPath).listFiles(new FileFilter() {
			public boolean accept(File f) {
				return (f.getName().endsWith(".cf") || f.getName().endsWith(".log"));
			}
		});

		for (File f : files) {
			if (f.getName().contains("ham")) {
				ham = Read(f);
			}
			if (f.getName().contains("spam")) {
				spam = Read(f);
			}
			if (f.getName().contains("rules")) {
				rules = Read(f);
			}
		}
	}

	/**
	 * Converte ficheiros em arrays de Strings
	 * 
	 * @param file
	 *            Ficheiro a ser lido.
	 */
	public ArrayList<String> Read(File file) {
		lines = new ArrayList<String>();
		if (file.exists()) {
			Scanner scan = null;
			try {
				scan = new Scanner(file);
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					if (line != null) {
						lines.add(line);
					}

				}
			} catch (FileNotFoundException error) {
				System.out.println("Ficheiro " + file.getAbsolutePath() + " não selecionado ou inexistente. ");
			} finally {
				if (scan != null) {
					scan.close();
					return lines;
				}
			}
		} else {
			System.out.println("Ficheiro " + file.getAbsolutePath() + " não seleccionado ou inexistente. ");
		}
		return null;
	}

	/**
	 * Cria um vetor de números aleatórios do tipo double num intervalo de
	 * [-5,5]
	 * 
	 */
	private ArrayList<String> generateRandomWeights() {

		ArrayList<String> randomWeights = new ArrayList<String>();
		Random randomNumberGenerator = new Random();
		for (int i = 0; i < 335; i++) {
			randomWeights.add(new Double(-5.0 + (5.0 - (-5.0)) * randomNumberGenerator.nextDouble()).toString());
		}
		return randomWeights;
	}

	public ArrayList<String> getLines() {
		return lines;
	}

	public String getExecutionPath() {
		return executionPath;
	}

	public File[] getFiles() {
		return files;
	}

	public ArrayList<String> getHam() {
		return ham;
	}

	public ArrayList<String> getSpam() {
		return spam;
	}

	public ArrayList<String> getRules() {
		return rules;
	}

	public JFileChooser getChooser() {
		return chooser;
	}
}
