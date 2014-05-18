package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gui.*;

public class GuiTests {
	
	@Test
	public void getChromoTest(){
		
		
		String name01 = "1- Exemplo";
		String name02 = "2- Exemplo";
		String name03 = "4- Exemplo";
		String name04 = "8- Exemplo";
		String name05 = "16- Exemplo";
		
		assertEquals(1, gui.RestrictionsDialog.getChromosome(name01));
		
		
	}

}
