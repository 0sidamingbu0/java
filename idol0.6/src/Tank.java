import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Tank {
	private int x,y,oldx,oldy,xPt,yPt;
	private int step = 0;
	public static final int TANKSIZE = 50;
	public static final int TANKSPEED = 2;
//	MyGame mg = new MyGame();
	boolean good = true;
	boolean live = true;
	boolean bL,bU,bR,bD;
	private static Random r = new Random();
	
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
		xPt = x + TANKSIZE/2;
		yPt = y + TANKSIZE;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		if(good){
			g.setColor(Color.WHITE);
		}
		else{
			g.setColor(Color.PINK);
		}
		g.fillOval(x, y, TANKSIZE, TANKSIZE);
		g.setColor(Color.BLUE);
		g.drawLine(x+TANKSIZE/2, y+TANKSIZE/2, xPt, yPt);
		g.setColor(c);
		if(good){
		Direct();
		}
		else{
			if(step<=0){
				step =  r.nextInt(50) + 50;
				randomDir();
			}
			else{
				step--;
			}
		}
		move();
		collideOthers();
		if(!good&&r.nextInt(1000)<5&&live){
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
		}
		switch(ptDir){
		case L:
			xPt = x;
			yPt = y + TANKSIZE/2;
			break;
		case LU:
			xPt = x;
			yPt = y;
			break;
		case U:
			xPt = x + TANKSIZE/2;
			yPt = y;
			break;
		case RU:
			xPt = x + TANKSIZE;
			yPt = y;
			break;
		case R:
			xPt = x + TANKSIZE;
			yPt = y + TANKSIZE/2;
			break;
		case RD:
			xPt = x + TANKSIZE;
			yPt = y + TANKSIZE;
			break;
		case D:
			xPt = x + TANKSIZE/2;
			yPt = y + TANKSIZE;
			break;
		case LD:
			xPt = x;
			yPt = y + TANKSIZE;
			break;		
		}
	}
	public void stay(){
		x = oldx;
		y = oldy;
	}
	public void collideOthers(){
		for(int i=0;i<mg.tanks.size();i++){
			Tank t = mg.tanks.get(i);
			if((mg.t1.getRect().intersects(t.getRect()))||(t.getRect().intersects(this.getRect())&&t!=this)){
				stay();
				return;
			}
		}
	}
	public void KeyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		case KeyEvent.VK_SPACE:
			fire(true);
			break;
		}

	}
	
	public void fire(boolean good){
		if(this.live){
		mg.missiles.add(new Missile(x,y,ptDir,good,this.mg));
		}		
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,TANKSIZE,TANKSIZE);
	}
	
	public boolean eat(){
		for(int i=0;i<mg.missiles.size();i++){
			Missile m = mg.missiles.get(i);
			if(mg.t1.getRect().intersects(m.getRect())&&mg.t1.good!=m.good){
				mg.t1.setLive(false);
			}
			if(m.good!=this.good&&getRect().intersects(m.getRect())){
				mg.tanks.remove(this);
				mg.missiles.remove(m);
				mg.explodes.add(new Explode(x,y,mg));
				return true;
			}			
		}
		return false;
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
		case KeyEvent.VK_F2:
			mg.addEnemy();
			break;
		case KeyEvent.VK_F3:
			mg.t1.setLive(true);
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
	
}
