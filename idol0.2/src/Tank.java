import java.awt.Color;
import java.awt.Graphics;


public class Tank {
	private int x,y;
//	MyGame mg = new MyGame();
	boolean good;
	
	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		//this.mg = mg;
		this.good = good;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 50, 50);
		g.setColor(c);
	}

	public int getX() {
		return x;
	}
	
}
