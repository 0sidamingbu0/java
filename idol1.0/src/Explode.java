import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Explode {
	int x,y;
	int i = 0;
	static boolean init = false;
	boolean live = true;
	MyGame mg;
	int[] index ={2,8,12,18,24,32,46,50,34,6};	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	Image[] imgs = {
			tk.getImage(Explode.class.getClassLoader().getResource("images/1.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/2.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/3.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/4.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/5.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/6.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/7.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/8.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/9.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/10.gif")),
	};
	
	public void draw(Graphics g){
		if(!init){
			for (int j = 0; j < imgs.length; j++) {
				g.drawImage(imgs[j], 0, 0, null);
				init = true;
			}			
		}
		if(i>9){mg.explodes.remove(this);}
		else{
		g.drawImage(imgs[i],x+50/2-30, y+50/2-30,null);

		i++;
		}
	}

	public Explode(int x, int y,MyGame mg) {
		this.x = x;
		this.mg = mg;
		this.y = y;
	}
	
	
	
}
