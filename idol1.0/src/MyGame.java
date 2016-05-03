import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGame extends Frame {
	private static final long serialVersionUID = 1L;
	int kill = 0;
	int xMouse = 0;
	int yMouse = 0;
	int bigCount = 1;
	boolean dead = false;
	private Random r = new Random();
	PaintThread PaintThread1 = new PaintThread();
	Thread thread1 = new Thread(PaintThread1);
	boolean flagAE = false;
	boolean flagAB = false;
	Image offScreenImage =null;
	Tank t1 = new Tank(50,75,true,this);
	List<Missile> missiles = new ArrayList<Missile>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<Explode> explodes = new ArrayList<Explode>();
	List<Wall> walls = new ArrayList<Wall>();
	List<Blood> bloods = new ArrayList<Blood>();
	int flagWall[][]=
			{{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,1,0,1,0,1,1,1,1,1,0,0,0},
			 {1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0},
			 {1,1,1,0,1,0,1,1,1,1,0,0,1,0,0,0},
			 {0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0},
			 {0,0,0,1,1,0,0,1,0,1,1,1,1,1,1,1},
			 {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
			 {1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			 };
	public void cleanEnemy(){
		tanks.clear();
		missiles.clear();
	}
	
	public void paint(Graphics g) {
		if((tanks.size() <= 1)&&missiles.size()==0){
			addEnemy();
		}
		if(!Blood.haven){
			addBlood();
		}

		for(int i=0;i<walls.size();i++){
			Wall w = walls.get(i);
			w.draw(g);
		}
		for(int i=0;i<missiles.size();i++){
			Missile m = missiles.get(i);
			m.draw(g);
		}
		for(int i=0;i<bloods.size();i++){
			Blood b = bloods.get(i);
			b.draw(g);
			b.eaten();
		}
		for(int i=0;i<tanks.size();i++){
			Tank t = tanks.get(i);
			t.draw(g);
			t.eat();
		}

		if(t1.live){
			t1.draw(g);
			t1.eatme();
		}else{
			Color c = g.getColor();
			g.setColor(Color.RED);
			Font f1 = g.getFont();
			Font f = new Font("Arial" ,1,100);
			g.setFont(f);
			g.drawString("GAME OVER " , 100, 250);
			g.drawString("SCORE= " + kill, 160, 400);
			
			Font f2 = new Font("Arial" ,1,50);
			g.setColor(Color.WHITE);
			g.setFont(f2);
			g.drawString("PRESS F3 TO RESTART " , 108, 550);
			
			g.setFont(f1);
			g.setColor(c);
		}
		
		for(int i=0;i<explodes.size();i++){
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		Color c2 = g.getColor();		
		g.setColor(Color.WHITE);
		g.drawString("Missile count = " + missiles.size(), 10, 50);
		g.drawString("Enemy count = " + tanks.size(), 10, 70);
		g.drawString("Explode count = " + explodes.size(), 10, 90);
		g.drawString("Kill count  =" + kill, 10, 110);
		g.drawString("必杀剩余：" + t1.superCount +"  超杀剩余："+bigCount, 10, 130);
		//g.drawString(xMouse+" "+yMouse, 10, 130);
		g.drawString("**=增加敌人       F3=重生/加血        F=开火         A=必杀       回车=想不到                        By:吴汪洋 V1.0", 260, 35);
		g.setColor(c2);
	}
	
	
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(800,600);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, 800, 600);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
		
 	}
	public void addBlood(){
		int rx = (r.nextInt(77)+1)*10;
		int ry = (r.nextInt(57)+1)*10;
		if(rx<=5||rx>=780||ry<=5||ry>=580);				
		else{				
			Blood b = new Blood(rx,ry,this);
			
			for(int j=0;j<tanks.size();j++){
				Tank t2 = tanks.get(j);			
				if(b.getRect().intersects(t2.getRect())||t1.getRect().intersects(b.getRect())){
					flagAB = true;
				}
			}
			
			for(int k=0;k<walls.size();k++){
				Wall w = walls.get(k);
				if(b.getRect().intersects(w.getRect())){
					flagAB = true;
				}
			}
			if(b.getRect().intersects(t1.getRect())){
				flagAB = true;
			}
			if(!flagAB){
				bloods.add(b);				
			}
			flagAB = false;
		}
		
	}
	
	public void addEnemy(){
		for(int i=0;i<20;i++){
			int rx = (r.nextInt(9)+1)*70;
			int ry = (r.nextInt(9)+1)*70;
			if(rx<=5||rx>=750||ry<=5||ry>=550);				
			else{				
				Tank t = new Tank(rx,ry,false,this);
				
				for(int j=0;j<tanks.size();j++){
					Tank t2 = tanks.get(j);			
					if(t.getRect().intersects(t2.getRect())||t1.getRect().intersects(t.getRect())){
						flagAE = true;
					}
				}
				
				for(int k=0;k<walls.size();k++){
					Wall w = walls.get(k);
					if(t.getRect().intersects(w.getRect())){
						flagAE = true;
					}
				}
				if(t.getRect().intersects(t1.getRect())){
					flagAE = true;
				}
				if(!flagAE){
					tanks.add(t);				
				}
				flagAE = false;
			}
		}
	}
	public void lauchFrame(){
		this.setLocation(100,50);
		this.setSize(800,600);
		this.setTitle("坦克大战 V1.0   by：吴汪洋");
		this.addKeyListener(new KeyMonitor());
		this.addMouseListener(new MouseMonitor());
			
		for(int i=0;i<11;i++){
			for(int j=0;j<16;j++){
				if(flagWall[i][j]==1){
					walls.add(new Wall((j)*50,(i)*50+22,this));
				}
			}
		}
		addEnemy();	
		this.setBackground(Color.BLACK);
		//new Thread(new PaintThread()).start();

		thread1.start();
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
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	public void bigFire(){
		for(int i=0;i<32;i++){
			missiles.add(new Missile((i)*25,15,Tank.Direction.D,true,MyGame.this,true));
			missiles.add(new Missile((i)*25+12,585,Tank.Direction.U,true,MyGame.this,true));
		}
		bigCount--;
	}
	private class KeyMonitor extends KeyAdapter{
		

		public void keyReleased(KeyEvent e) {
			t1.KeyReleased(e);
			int key = e.getKeyCode();
			if(!dead&&key==KeyEvent.VK_ENTER&&bigCount>0){
				bigFire();
			}

		}
		public void keyPressed(KeyEvent e) {
			t1.KeyPressed(e);
		}
	}
	private class MouseMonitor extends MouseAdapter{
		public void mouseReleased(MouseEvent e) {
			xMouse = e.getX();
			yMouse = e.getY();
		}		
	}
			
	
	
	public static void main(String[] args) {
		MyGame mg = new MyGame();
		mg.lauchFrame();
	}

}
