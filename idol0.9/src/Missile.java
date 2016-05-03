import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Missile {
	int x,y;
	boolean big = false;
	boolean good = true;
	boolean live = true;
	private MyGame mg;
	public static final int PTSPEED = 4;
	public static final int PDSIZE = 15;
	
	private Tank.Direction dir = Tank.Direction.D;
	
	public void draw(Graphics g){
		Color c = g.getColor();
		if(big){
			g.setColor(Color.YELLOW);
		}
		else if(good){
			g.setColor(Color.RED);
		}
		else{
			g.setColor(Color.GREEN);
		}
		g.fillOval(x, y, PDSIZE, PDSIZE);
		g.setColor(c);
		move();
		if(!big){
		collideWalls();
		collideOthers();
		}
	}
	public Rectangle getRect(){
		return new Rectangle(x,y,PDSIZE,PDSIZE);
	}
	
	public void collideWalls(){
		for(int i=0;i<mg.walls.size();i++){
			Wall w = mg.walls.get(i);
			if(this.getRect().intersects(w.getRect())){
				mg.explodes.add(new Explode(x - 25 + PDSIZE/2,y - 25 + PDSIZE/2,mg));
				mg.missiles.remove(this);
				return;
			}	
		}
	}
	public void collideOthers(){
		for(int i=0;i<mg.missiles.size();i++){
			Missile m = mg.missiles.get(i);
			if(this.good!=m.good&&this.getRect().intersects(m.getRect())){
				mg.explodes.add(new Explode(x - 25 + PDSIZE/2,y - 25 + PDSIZE/2,mg));
				mg.missiles.remove(this);
				mg.missiles.remove(m);
				return;
			}	
		}
	}
	public void move(){
		switch(dir){
		case L:
			x -= PTSPEED;
			break;
		case LU:
			x -= PTSPEED;
			y -= PTSPEED;
			break;
		case U:
			y -= PTSPEED;
			break;
		case RU:
			x += PTSPEED;
			y -= PTSPEED;
			break;
		case R:
			x += PTSPEED;
			break;
		case RD:
			x += PTSPEED;
			y += PTSPEED;
			break;
		case D:
			y += PTSPEED;
			break;
		case LD:
			x -= PTSPEED;
			y += PTSPEED;
			break;
			
		}
		if(x<0||y<0||!live||x>800||y>600){
			mg.missiles.remove(this);
		}
	}
	
	public Missile(int x, int y,Tank.Direction dir,boolean good,MyGame mg) {
		this.x = x + 25 - PDSIZE/2;
		this.mg = mg;
		this.y = y + 25 - PDSIZE/2;
		this.dir = dir;
		this.good = good;
	}
	public Missile(int x, int y,Tank.Direction dir,boolean good,MyGame mg,boolean big) {
		this.x = x + 25 - PDSIZE/2;
		this.mg = mg;
		this.y = y + 25 - PDSIZE/2;
		this.dir = dir;
		this.good = good;
		this.big = big;
	}

	public void setDir(Tank.Direction dir) {
		this.dir = dir;
	}
	
}
