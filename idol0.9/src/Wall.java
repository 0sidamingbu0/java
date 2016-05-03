import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Wall {
	public static final int WALLSIZE = 50;	
	int x,y;
	MyGame mg;	
	
	
	public Wall(int x, int y, MyGame mg) {
		this.x = x;
		this.y = y;
		this.mg = mg;
	}
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x+2, y+2, WALLSIZE-4, WALLSIZE-4);
		g.setColor(c);
	}
	public Rectangle getRect(){
		return new Rectangle(x+2,y+2,WALLSIZE-4,WALLSIZE-4);
	}
	
}
