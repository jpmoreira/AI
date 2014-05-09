package tests;

import static org.junit.Assert.*;
import mainPackage.Tile;

import org.junit.Test;

public class TileTests {

	//TODO maybe some other tests besides adjacencies
	@Test
	public void adjacencieTest() {
		Tile t1=new Tile();
		Tile t2=new Tile();
		Tile t3=new Tile();
		
		t1.addAdjacentTile(t2);
		t3.addAdjacentTile(t1);
		t3.addAdjacentTile(t1);//should do nothing
		t2.addAdjacentTile(t2);//should do nothing
		
		Tile[] supposedAdjForT1={t2,t3};
		Tile[] supposedAdjForT2={t1};
		assertArrayEquals(t1.adjacencies(), supposedAdjForT1);
		assertArrayEquals(t2.adjacencies(), supposedAdjForT2);
		
	}

}
