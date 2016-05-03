import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.Toolkit;


public class Tank {
	private int x,y,oldx,oldy;
	private boolean stop = true;
	public static final int TANKSIZE = 48;
	public static final int TANKSPEED = 2;
	boolean good = true;
	boolean live = true;
	boolean flagDK = false;
	int bigCount = 1;
	int superCount = 3;
	boolean bL,bU,bR,bD;
	private static Random r = new Random();
	
	private int blood = 100;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	Image[] gimgs = {
			tk.getImage(Tank.class.getClassLoader().getResource("images/gTL.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/gTLU.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/gTU.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/gTRU.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/gTR.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/gTRD.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/gTD.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/gTLD.gif"))
	};
	Image[] imgs = {
			tk.getImage(Tank.class.getClassLoader().getResource("images/TL.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/TLU.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/TU.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/TRU.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/TR.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/TRD.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/TD.gif")),
			tk.getImage(Tank.class.getClassLoader().getResource("images/TLD.gif"))
	};
	private MyGame mg;
	enum Direction  {L,LU,U,RU,R,RD,D,LD,STOP};
	Direction dir = Direction.STOP;
	Direction ptDir = Direction.D;
	Direction[] dirs = Direction.values();
	public Tank(int x, int y, boolean good,MyGame mg) {
		this.x = x;
		this.y = y;
		this.oldx = x;
		this.oldy = y;
		this.mg = mg;
		this.good = good;
	}
	
	public void draw(Graphics g){
		if(good){
			Direct();
			}
			else{
				if(stop){
				    randomDir();
				    stop = false;
				}

			}
		
		for(int j=0;j<dirs.length-1;j++){
			if(ptDir == dirs[j]){
				if(good)g.drawImage(gimgs[j],x,y,null);
				else g.drawImage(imgs[j],x,y,null);
			}
		}
		
		
		Color c = g.getColor();
		if(good){
			g.setColor(Color.GREEN);
			g.drawRect(x, y-10, TANKSIZE, 5);
			g.fillRect(x, y-10, TANKSIZE*blood/100,5 );
			g.setColor(Color.LIGHT_GRAY);
		}
		else{
			g.setColor(Color.PINK);
		}
		
//		g.fillOval(x, y, TANKSIZE, TANKSIZE);
//		g.setColor(Color.BLACK);
//		g.drawLine(x+TANKSIZE/2, y+TANKSIZE/2, xPt, yPt);
		g.setColor(c);

		move();
		collideOthers();
		collideWalls();
		if(!good&&r.nextInt(1000)<10&&live){
			fire(false);
		}

	}
	public void randomDir(){
		int rr = r.nextInt(8);
		dir = dirs[rr];
		ptDir = dir;
	}
	public void move(){
		oldx = x;
		oldy = y;
		
		switch(dir){
		case L:
			x -= TANKSPEED;
			break;
		case LU:
			x -= TANKSPEED;
			y -= TANKSPEED;
			break;
		case U:
			y -= TANKSPEED;
			break;
		case RU:
			x += TANKSPEED;
			y -= TANKSPEED;
			break;
		case R:
			x += TANKSPEED;
			break;
		case RD:
			x += TANKSPEED;
			y += TANKSPEED;
			break;
		case D:
			y += TANKSPEED;
			break;
		case LD:
			x -= TANKSPEED;
			y += TANKSPEED;
			break;
		case STOP:
			break;			
		}
		if(x<=-2||y<=10||x>800-TANKSIZE||y>600-TANKSIZE){
			stay();
			stop = true;
		}
	}
	public void stay(){
		x = oldx;
		y = oldy;
	}
	public boolean collideOthers(){
		for(int i=0;i<mg.tanks.size();i++){
			Tank t = mg.tanks.get(i);
			if((mg.t1.getRect().intersects(t.getRect()))||(t.getRect().intersects(this.getRect())&&t!=this)){
				this.stay();
				stop = true;
				return true;				
			}
		}
		return false;
	}
	
	public boolean collideWalls(){
		for(int i=0;i<mg.walls.size();i++){
			Wall w = mg.walls.get(i);
			if(this.getRect().intersects(w.getRect())){				
					this.stay();
					stop = true;
					return true;
			}	
		}
		return false;
	}
	
	public void KeyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_LEFT){bL = true;}
		if(key==KeyEvent.VK_UP){bU = true;}
		if(key==KeyEvent.VK_RIGHT){bR = true;}
		if(key==KeyEvent.VK_DOWN){bD = true;}
		if(key==KeyEvent.VK_F){fire(true);}
		if(key==KeyEvent.VK_A){fireBig();}
	

	}
	
	public void fire(boolean good){
		if(this.live){
			mg.missiles.add(new Missile(x,y,ptDir,good,this.mg));
		}		
	}
	public void fireBig(){
		if(this.live&&superCount > 0){
			for(int i=0;i<dirs.length-1;i++){
				mg.missiles.add(new Missile(x,y,dirs[i],good,this.mg));				
			}
			superCount--;
		}		
	}
	
	public Rectangle getRect(){
		return new Rectangle(x+8,y+7,34,35);
	}
	public boolean eatme(){
		for(int i=0;i<mg.missiles.size();i++){
			Missile m = mg.missiles.get(i);
			if(mg.t1.getRect().intersects(m.getRect())&&m.good!=mg.t1.good){
				mg.t1.blood -= 34;
				mg.missiles.remove(m);
				mg.explodes.add(new Explode(mg.t1.x,mg.t1.y,mg));
				if(mg.t1.blood <= 0){
					mg.missiles.remove(m);
					mg.t1.setLive(false);
					mg.dead = true;
					mg.explodes.add(new Explode(mg.t1.x,mg.t1.y,mg));
					return true;
				}
			}		
		}
		return false;
	}
	public boolean eat(){
		for(int i=0;i<mg.missiles.size();i++){
			Missile m = mg.missiles.get(i);
			if(m.good!=this.good&&this.getRect().intersects(m.getRect())){
				mg.tanks.remove(this);
				if(!m.big){
					mg.missiles.remove(m);
				}
				mg.explodes.add(new Explode(x,y,mg));
				mg.kill += 1;
				return true;
			}			
		}return false;		
	}
	
	public void KeyReleased(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		case KeyEvent.VK_BACK_SPACE:
			mg.addEnemy();
			break;
		case KeyEvent.VK_F3:
			if(mg.dead){
				mg.t1.setBlood(100);
				mg.t1.setLive(true);
				mg.dead = false;
				mg.bigCount = 1;
				mg.t1.superCount = 3;
				mg.kill = 0;
				mg.t1.x = 50;
				mg.t1.y = 75;
				mg.cleanEnemy();
			}
			break;
		}

	}
	public void Direct(){
			 if(bL && !bU && !bR && !bD){dir = Direction.L;ptDir = Direction.L;}
		else if(bL && bU && !bR && !bD){dir = Direction.LU;ptDir = Direction.LU;}
		else if(!bL && bU && !bR && !bD){dir = Direction.U;ptDir = Direction.U;}
		else if(!bL && bU && bR && !bD){dir = Direction.RU;ptDir = Direction.RU;}
		else if(!bL && !bU && bR && !bD){dir = Direction.R;ptDir = Direction.R;}
		else if(!bL && !bU && bR && bD){dir = Direction.RD;ptDir = Direction.RD;}
		else if(!bL && !bU && !bR && bD){dir = Direction.D;ptDir = Direction.D;}
		else if(bL && !bU && !bR && bD){dir = Direction.LD;ptDir = Direction.LD;}
		else {dir = Direction.STOP;}
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

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}
	
}
