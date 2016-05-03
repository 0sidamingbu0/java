import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Wall {
	public static final int WALLSIZE = 50;	
	int x,y;
	MyGame mg;	
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	Image img = tk.getImage(Wall.class.getClassLoader().getResource("images/wall.gif"));
	public Wall(int x, int y, MyGame mg) {
		this.x = x;
		this.y = y;
		this.mg = mg;
	}
	public void draw(Graphics g){

		g.drawImage(img,x,y,WALLSIZE-2,WALLSIZE-2,null);
		//g.fillRect(x+2, y+2, WALLSIZE-4, WALLSIZE-4);
	}
	public Rectangle getRect(){
		return new Rectangle(x,y,WALLSIZE-2,WALLSIZE-2);
	}
	
}
