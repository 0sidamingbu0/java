import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Blood {
	int x,y;
	MyGame mg;
	static boolean haven = false;
	
	public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.GREEN);
			g.fillRect(x, y, 10, 10);
			g.setColor(Color.RED);
			g.drawRect(x-1, y-1, 11, 11);
			g.setColor(c);
			haven = true;
		
	}
	public void eaten(){
		if(mg.t1.getRect().intersects(this.getRect())){
			mg.t1.setBlood(100);
			mg.bloods.remove(this);
			haven = false;
		}
	}
	public Rectangle getRect(){
		return new Rectangle(x,y,10,10);
	}

	public boolean isHaven() {
		return haven;
	}
	public Blood(int x, int y,MyGame mg) {
		this.x = x;
		this.y = y;
		this.mg = mg;
	}

}
