package antiSpamFilter_METIA1_55;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	private FileManager fm = FileManager.getInstance();
	private ArrayList<String> ham;
	private ArrayList<String> spam;
	private ArrayList<String> rules;
	
	private DoubleSolution solution;

	/**
	 * Construtor da classe AntiSpamFilterProblem
	 */
	public AntiSpamFilterProblem() {
		// 10 variables (anti-spam filter rules) by default
		this(335);
		fm.folderParser(fm.getExecutionPath());
		this.ham = fm.getHam();
		this.spam = fm.getSpam();
		this.rules = fm.getRules();
	}

	/**
	 *  Construtor da classe AntiSpamFilterProblem
	 * @param numberOfVariables - numero de variaveis a considerar para o problema
	 */
	public AntiSpamFilterProblem(Integer numberOfVariables) {
		setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	/**
	 * Percorre o ficheiro rules e retorna a posição de uma determinada regra
	 * @param s - regra a procurar
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
	 * método para determinar o número de falsos positivos e falsos negativos
	 * @param solution 
	 * Solução a avaliar.
	 */
	public void evaluate(DoubleSolution solution) {
		
		this.solution=solution;

		double aux, xi, xj;
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			x[i] = solution.getVariableValue(i);
			// System.out.println(x[i]);
		}
		//Detectar Falsos Positivos
		int fp = 0;
//		for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
			// fx[0] += Math.abs(x[0]); // Example for testing
			for (int i = 0; i < ham.size(); i++) {
				double acumulator=0;
				String[] line = ham.get(i).split("\t");
				for (int j = 1; j < line.length; j++) {
					// System.out.println(j+" "+ line[j]);
					int position= findRules(line[j]);
					acumulator+=x[position];	
				}
				if(acumulator>=5){
					fp++;
				}
			}
//		}

		int fn = 0;
//		for (int var = 0; var < solution.getNumberOfVariables(); var++) {
//			fx[1] += Math.abs(x[1]); // Example for testing
			for (int i = 0; i < spam.size(); i++) {
				double acumulator=0;
				String[] line = spam.get(i).split("\t");
				for (int j = 1; j < line.length; j++) {
					// System.out.println(j+" "+ line[j]);
					int position= findRules(line[j]);
					acumulator+=x[position];					
				}
				if(acumulator<5){
					fn++;
				}
			}
//		}
		solution.setObjective(0, fp);
		solution.setObjective(1, fn);
//		System.out.println("[+]Falsos Positivos: " + fp );
//		System.out.println("[-]Falsos Negativos: " + fn );
	}
	public DoubleSolution getSolution() {
		return solution;
	}

	public void setSolution(DoubleSolution solution) {
		this.solution = solution;
	}
}
