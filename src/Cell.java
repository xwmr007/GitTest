
public class Cell {

	private int x,y;//细胞位置
	private boolean isLive;//细胞状态
	
	public Cell(int x,int y) {
		this.x=x;
		this.y=y;
		isLive=false;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x=x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y=y;
	}
	public boolean getIsLive() {
		return isLive;
	}
	public void setIsLive(boolean live) {
		isLive=live;
	}
}