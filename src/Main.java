

public class Main {
	private static GameGUI Game;
	private static World world;
	
	public static void main(String arg[]) {
		world=new World(20,30);
		// 调用父类JFrame的构造方法设置窗体标题
		Game =new GameGUI("生命游戏",world);
	}
	
}
