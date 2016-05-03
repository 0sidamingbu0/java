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
		if(x>700){x=100;}
		if(y>700){y=100;}
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 50, 50);
		g.setColor(c);
		x += 5;
		y += 3;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	
}
