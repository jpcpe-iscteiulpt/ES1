package antiSpamFilter.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.Solution;

import antiSpamFilter.AntiSpamFilterProblem;

public class AntiSpamFilterProblemTest {
	
	AntiSpamFilterProblem aspf = new AntiSpamFilterProblem();
	

	@Test
	public void testFindRules() {
		assertNotNull("Should not be null", aspf.findRules(new String()));
	}

	@Test
	public void testEvaluate() {
		DoubleSolution solution = new DoubleSolution() {
			@Override
			public void setVariableValue(int arg0, Double arg1) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void setObjective(int arg0, double arg1) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void setAttribute(Object arg0, Object arg1) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public String getVariableValueString(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Double getVariableValue(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public double getObjective(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getNumberOfVariables() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getNumberOfObjectives() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getAttribute(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Solution<Double> copy() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Double getUpperBound(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Double getLowerBound(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		aspf.evaluate(solution);
		assertNotNull("Should not be null", aspf.getSolution());
	}

}
