import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {

	private static World world=new World(20,30);
	private Cell[][] cell=new Cell[80][80];
	@Before
	public void setUp() throws Exception {
	}
	
	
	@Test
	public void testWorld() {
		world=new World(30,35);
		 for(int x=0;x<30;x++)
	            for(int y=0;y<35;y++)
	            	assertEquals(false,world.getCellXY(x, y));
	}

	@Test
	public void testRandonInitCell() {
		world.randonInitCell();
		int m=0,n=0;
		 for(int x=0;x<20;x++)
	            for(int y=0;y<30;y++) {
	            	if(world.getCellXY(x, y)==false)
	            		m++;
	            }
		 for(int x=0;x<20;x++)
	            for(int y=0;y<30;y++) {
	            	if(world.getCellXY(x, y)==true)
	            		n++;
	            }
		 boolean t = false;
		 if(n==m) t=true;
		
		 assertEquals(false,t);
	            	
	}

	@Test
	public void testDeleteAllCell() {
		//world.randonInitCell();
		world.deleteAllCell();
		  for(int x=0;x<20;x++)
	            for(int y=0;y<30;y++)
	            	assertEquals(false,world.getCellXY(x, y));
	}

	@Test
	public void testUpdateOfCell() {
		
		world.updateOfCell();
		assertEquals(false,world.getCellXY(11, 15));//四周只有一个活细胞
		assertEquals(true,world.getCellXY(12,16));//四周有三个活细胞
		assertEquals(true,world.getCellXY(12, 17));//四周有两个活细胞，原本状态为true
	}

}
