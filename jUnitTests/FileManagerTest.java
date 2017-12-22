package antiSpamFilter_METIA1_55.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.junit.Test;

import antiSpamFilter_METIA1_55.FileManager;

public class FileManagerTest {
	
	private FileManager fm = FileManager.getInstance();
	
	/**
	 * 
	 * Teste para verificar se o construtor � inicializado corretamente.
	 * 
	 */
	@Test
	public void testFileManager() {
	}

	/**
	 * 
	 * Teste para verificar se a o explorador de ficheiro inicia corretamente.
	 * 
	 */
	@Test
	public void testFileFolderPrompt() {
		assertNotNull("should not be null", fm.fileFolderPrompt(new JFrame()));
		assertEquals(fm.getChooser().showOpenDialog(new JFrame()), JFileChooser.APPROVE_OPTION);
	}

	/**
	 * 
	 * Teste para verificar se o conversor de ficheiros para as listas com o conte�do dos ficheiros
	 * ham.log, spam.log e rules.cf n�o devolve listas nulas.
	 * 
	 */
	@Test
	public void testFolderParser() {
		fm.folderParser(fm.getExecutionPath());
		assertNotNull(fm.getHam());
		assertNotNull(fm.getSpam());
		assertNotNull(fm.getRules());		
	}

	/**
	 * 
	 * Teste para verificar se o m�todo read() l� bem um ficheiro e coloca os conte�dos do mesmo numa
	 * ArrayListe que a mesma n�o � nula.
	 * 
	 */
	@Test
	public void testRead() {
		fm.read(new File(""));
		assertNotNull("should not be null", fm.getfileLines());
	}
	
	/**
	 * 
	 * Teste para verificar se o m�todo getExecutionPath() retorna corretamente o caminho de execu��o
	 * do programa e que o mesmo n�o � nulo.
	 * 
	 */
	@Test
	public void testGetExecutionPath(){
		assertNotNull("should not be null", fm.getExecutionPath());	
	}
	
	/**
	 * 
	 * Teste para verificar que o m�todo getFiles() devolve corretamente a lista com os ficheiros ham-log, spam.log
	 * e rules.cf e que o mesmo n�o � nulo.
	 * 
	 */
	@Test
	public void testGetFiles(){
		assertNotNull("should not be null", fm.getFiles());	
	}
	
	/**
	 * 
	 * Teste para verificar que o m�todo getWeights devolve a lista contendo os pesos gerados pelo m�todo
	 * generateRandomWeights() e que a mesma n�o � nula.
	 * 
	 */
	@Test
	public void testGetWeights(){
		fm.generateRandomWeights();
		assertNotNull("should not be null", fm.getWeights());	
	}
	
	/**
	 * 
	 * Teste para verificar que o m�todo getSolutions() devolve com sucesso a lista com as solu��es geradas pelo algoritmo
	 * NGSA-II e que a mesma n�o � nula.
	 * 
	 */
	@Test
	public void testGetSolutions(){
		fm.folderParser("experimentBaseDirectory/referenceFronts");
		assertNotNull("should not be null", fm.getSolutions());	
	}
	
	/**
	 * 
	 * Teste para verificar se o m�todo que gera uma lista de pesos aleat�rios n�o devolve uma lista nula e que
	 * o valor dos pesos gerados est�o dentro do intervalo de valores [-5 5].
	 * 
	 */
	@Test
	public void testGenerateRandomWeights() {
		ArrayList<String> testRandomWeightArray = fm.generateRandomWeights();
		
		assertNotNull("should not be null ", testRandomWeightArray);
		
		for(int i = 0; i < testRandomWeightArray.size(); i++) {
			assertFalse(Double.parseDouble(testRandomWeightArray.get(i)) < -5.00000);
			assertFalse(Double.parseDouble(testRandomWeightArray.get(i)) > 5.00000);
		}
	}
}
