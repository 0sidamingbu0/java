import java.awt.Color;
import java.awt.Graphics;


public class Explode {
	int x,y;
	int i = 0;
	boolean live = true;
	MyGame mg;
	int[] index ={2,8,12,18,24,32,46,50,34,6};	
	
	public void draw(Graphics g){
		if(i>9){mg.explodes.remove(this);}
		else{
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x+50/2-index[i]/2, y+50/2-index[i]/2, index[i], index[i]);		
		g.setColor(c);
		i++;
		}
	}

	public Explode(int x, int y,MyGame mg) {
		this.x = x;
		this.mg = mg;
		this.y = y;
	}
	
	
	
}
