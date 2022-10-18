import static org.junit.Assert.*;

import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;
import java.awt.event.ActionEvent;

public class GameGUITest{

	private static GameGUI game;
	private static World world=new World(20,30);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGameGUI() {
		game=new GameGUI("LifeGame",world);
	}

	@Test
	public void testInitGameGUI() {
		game.initGameGUI();
		String bu1name=game.get_btnBeginAndOver();
		String bu2name=game.get_btnrandomInit();
		String bu3name=game.get_btnStopAndContinue();
		boolean flag=false;
		if(bu1name.equals("开始游戏")&&bu2name.equals("随机生成细胞")&&bu3name.equals("暂停游戏")) {
			//assertEquals(false,game.Running());
			flag=true;
		}
		assertEquals(true,flag);
	}
	@Test
	public void testActionPerformed() {
		game.actionPerformed(null);

		String bu1name=game.get_btnBeginAndOver();
		String bu2name=game.get_btnrandomInit();
		String bu3name=game.get_btnStopAndContinue();
	
		if(bu1name.equals("结束游戏")&&bu2name.equals("重新生成")&&bu3name.equals("继续游戏")) {
			assertEquals(false,game.Running());
		}
	}
	@Test
	public void testChange() {
		game.Change();
		String t1=game.Text_NowGeneration();
		int t2=game.getNowGeneration();
		boolean flag=false;
		if(t1.equals("当前代数："+t2)) {
			flag=true;
		}
		assertEquals(true,flag);
	}


}
