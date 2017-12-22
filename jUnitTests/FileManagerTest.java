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
	 * Teste para verificar se o construtor é inicializado corretamente.
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
	 * Teste para verificar se o conversor de ficheiros para as listas com o conteúdo dos ficheiros
	 * ham.log, spam.log e rules.cf não devolve listas nulas.
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
	 * Teste para verificar se o método read() lê bem um ficheiro e coloca os conteúdos do mesmo numa
	 * ArrayListe que a mesma não é nula.
	 * 
	 */
	@Test
	public void testRead() {
		fm.read(new File(""));
		assertNotNull("should not be null", fm.getfileLines());
	}
	
	/**
	 * 
	 * Teste para verificar se o método getExecutionPath() retorna corretamente o caminho de execução
	 * do programa e que o mesmo não é nulo.
	 * 
	 */
	@Test
	public void testGetExecutionPath(){
		assertNotNull("should not be null", fm.getExecutionPath());	
	}
	
	/**
	 * 
	 * Teste para verificar que o método getFiles() devolve corretamente a lista com os ficheiros ham-log, spam.log
	 * e rules.cf e que o mesmo não é nulo.
	 * 
	 */
	@Test
	public void testGetFiles(){
		assertNotNull("should not be null", fm.getFiles());	
	}
	
	/**
	 * 
	 * Teste para verificar que o método getWeights devolve a lista contendo os pesos gerados pelo método
	 * generateRandomWeights() e que a mesma não é nula.
	 * 
	 */
	@Test
	public void testGetWeights(){
		fm.generateRandomWeights();
		assertNotNull("should not be null", fm.getWeights());	
	}
	
	/**
	 * 
	 * Teste para verificar que o método getSolutions() devolve com sucesso a lista com as soluções geradas pelo algoritmo
	 * NGSA-II e que a mesma não é nula.
	 * 
	 */
	@Test
	public void testGetSolutions(){
		fm.folderParser("experimentBaseDirectory/referenceFronts");
		assertNotNull("should not be null", fm.getSolutions());	
	}
	
	/**
	 * 
	 * Teste para verificar se o método que gera uma lista de pesos aleatórios não devolve uma lista nula e que
	 * o valor dos pesos gerados estão dentro do intervalo de valores [-5 5].
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
