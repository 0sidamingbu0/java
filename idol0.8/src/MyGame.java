import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MyGame extends Frame {
	private static final long serialVersionUID = 1L;
	
	boolean flagAE = false;
	Image offScreenImage =null;
	Tank t1 = new Tank(100,100,true,this);
	List<Missile> missiles = new ArrayList<Missile>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<Explode> explodes = new ArrayList<Explode>();
	List<Wall> walls = new ArrayList<Wall>();
	int flagWall[][]=
			{{1},{0},{1},{1},{1}};
	
	
	public void paint(Graphics g) {
		g.drawString("Missile count = " + missiles.size(), 10, 50);
		g.drawString("Enemy count = " + tanks.size(), 10, 70);
		g.drawString("Explode count = " + explodes.size(), 10, 90);
		if(t1.live){
			t1.draw(g);
			t1.eatme();
		}
		for(int i=0;i<missiles.size();i++){
			Missile m = missiles.get(i);
			m.draw(g);
		}

		for(int i=0;i<walls.size();i++){
			Wall w = walls.get(i);
			w.draw(g);
		}
		
		for(int i=0;i<tanks.size();i++){
			Tank t = tanks.get(i);
			t.draw(g);
			t.eat();
		}
		for(int i=0;i<explodes.size();i++){
			Explode e = explodes.get(i);
			e.draw(g);
		}
	}
	
	
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(800,600);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GRAY);
		gOffScreen.fillRect(0, 0, 800, 600);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
		
 	}
	public void addEnemy(){
		for(int i=0;i<6;i++){
			Tank t = new Tank((i+2)*70,500,false,this);
			for(int j=0;j<tanks.size();j++){
				Tank t2 = tanks.get(j);			
				if(t.getRect().intersects(t2.getRect())||t1.getRect().intersects(t.getRect())){
					flagAE = true;
				}
			}
			if(!flagAE){
				tanks.add(t);				
			}
			flagAE = false;
		
		}
	}
	public void lauchFrame(){
		this.setLocation(100,50);
		this.setSize(800,600);
		//flagWall[3][5] = true;
		this.addKeyListener(new KeyMonitor());
		addEnemy();		
		for(int i=0;i<5;i++){
			for(int j=0;j<1;j++){
				if(flagWall[i][j]==1){
					walls.add(new Wall((i+1)*50,(j+1)*50,this));
				}
			}
		}
		
		
		
		this.setBackground(Color.GRAY);
		new Thread(new PaintThread()).start();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
		this.setResizable(false);
		this.setVisible(true);
	}
	private class PaintThread implements Runnable{

		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter{
		

		public void keyReleased(KeyEvent e) {
			t1.KeyReleased(e);
		}
		public void keyPressed(KeyEvent e) {
			t1.KeyPressed(e);
		}
	}
			
	
	
	public static void main(String[] args) {
		MyGame mg = new MyGame();
		mg.lauchFrame();
	}

}
