package tests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mainPackage.GeneticEngine;
import mainPackage.Tile;
import mainPackage.TileProblemPopulation;
import mainPackage.constructions.Construction;

import org.junit.Test;

public class GeneticEngineTests {

	@Test
	public void serialize() {
		
		
		
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		
		Construction c1=new Construction("C1");
		Construction c2=new Construction("C2");
		Construction c3=new Construction("C3");
		
		TileProblemPopulation p=new TileProblemPopulation(new Tile[]{t1,t2,t3}, new Construction[]{c1,c2,c3}, 2,1.0);
		GeneticEngine gen=new GeneticEngine(p, 0.01, 2);
		gen.iterate();
		
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("popTest.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(gen);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	          fail("Exception Thrown");
	      }
		
		GeneticEngine gen2=null;
		try
	      {
	         FileInputStream fileIn = new FileInputStream("popTest.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         gen2 = (GeneticEngine) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         fail("Exception Thrown");
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         fail("Exception Thrown");
	      }
		
		
		
	}

}
