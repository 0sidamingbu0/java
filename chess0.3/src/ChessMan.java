import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class ChessMan {
	public static final int CHESSSIZE = 60;
	int x,y,oldx,oldy;
	int highNo;	
	static int deleteX = 0;
	static int deleteY = 0;
	int scoreL = 0;
	int scoreU = 0;
	int scoreR = 0;
	int scoreD = 0;
	int dellX = 0;
	int dellY = 0;
	int deluX = 0;
	int deluY = 0;
	int delrX = 0;
	int delrY = 0;
	int deldX = 0;
	int deldY = 0;
	int dir = 0;
	int score = 1000;
	boolean good;
	boolean selected = false;
	boolean first = false;
	Chess ch;
	public int score(){
		dir = 0;
		if(left()){
			if(eat(true)){
				scoreL += 1000;
				delete(true,deleteX,deleteY);
				dellX = deleteX;
				dellY = deleteY;
			}
			if(goDead()){scoreL -= 200;}
			
			for(int i=0;i<ch.eChessMans.size();i++){
				if(ch.eChessMans.get(i).chance(i)){scoreL += 50;}
				if(ch.eChessMans.get(i).attack(i)){scoreL += 30;}
			}
			for(int i=0;i<ch.gChessMans.size();i++){
				if(ch.gChessMans.get(i).chance(i)){scoreL -= 50;}
				if(ch.gChessMans.get(i).attack(i)){scoreL -= 30;}
			}
			if(eat(false)){
				ch.gChessMans.add(new ChessMan(deleteX,deleteY,true,ch));
			}
			this.x = oldx;
			this.y = oldy;
		}//left
		if(up()){
			if(eat(true)){
				scoreU += 1000;
				delete(true,deleteX,deleteY);
				deluX = deleteX;
				deluY = deleteY;
			}
			if(goDead()){scoreU -= 200;}
			
			for(int i=0;i<ch.eChessMans.size();i++){
				if(ch.eChessMans.get(i).chance(i)){scoreU += 50;}
				if(ch.eChessMans.get(i).attack(i)){scoreU += 30;}
			}
			for(int i=0;i<ch.gChessMans.size();i++){
				if(ch.gChessMans.get(i).chance(i)){scoreU -= 50;}
				if(ch.gChessMans.get(i).attack(i)){scoreU -= 30;}
			}
			if(eat(false)){
				ch.gChessMans.add(new ChessMan(deleteX,deleteY,true,ch));
			}
			this.x = oldx;
			this.y = oldy;
		}//up
		if(right()){
			if(eat(true)){
				scoreR += 1000;
				delete(true,deleteX,deleteY);
				delrX = deleteX;
				delrY = deleteY;
			}
			if(goDead()){scoreR -= 200;}
			
			for(int i=0;i<ch.eChessMans.size();i++){
				if(ch.eChessMans.get(i).chance(i)){scoreR += 50;}
				if(ch.eChessMans.get(i).attack(i)){scoreR += 30;}
			}
			for(int i=0;i<ch.gChessMans.size();i++){
				if(ch.gChessMans.get(i).chance(i)){scoreR -= 50;}
				if(ch.gChessMans.get(i).attack(i)){scoreR -= 30;}
			}
			if(eat(false)){
				ch.gChessMans.add(new ChessMan(deleteX,deleteY,true,ch));
			}
			this.x = oldx;
			this.y = oldy;
		}//right
		if(down()){
			if(eat(true)){
				scoreD += 1000;
				delete(true,deleteX,deleteY);
				deldX = deleteX;
				deldY = deleteY;
			}
			if(goDead()){
				scoreD -= 200;
				}
			
			for(int i=0;i<ch.eChessMans.size();i++){
				if(ch.eChessMans.get(i).chance(i)){
					scoreD += 50;
				}
				if(ch.eChessMans.get(i).attack(i)){
					scoreD += 30;
				}
			}
			for(int i=0;i<ch.gChessMans.size();i++){
				if(ch.gChessMans.get(i).chance(i)){
					scoreD -= 50;
				}
				if(ch.gChessMans.get(i).attack(i)){
					scoreD -= 30;
				}
			}
			if(eat(false)){
				ch.gChessMans.add(new ChessMan(deleteX,deleteY,true,ch));
			}
			this.x = oldx;
			this.y = oldy;
		}//down
		score = scoreL;dir = 1;
		if(score<scoreU){score = scoreU;dir = 2;}
		if(score<scoreR){score = scoreR;dir = 3;}
		if(score<scoreD){score = scoreD;dir = 4;}	
		
		return score;
	}
	public void del(){
		if(dir!=0){
			switch(dir){
			case 1:
				left();
				delete(true,dellX,dellY);
				break;
			case 2:
				up();
				delete(true,deluX,deluY);
				break;
			case 3:
				right();
				delete(true,delrX,delrY);
				break;
			case 4:
				down();
				delete(true,deldX,deldY);
				break;
			}
			dir = 0;
		}
	}
	public void delete(boolean good2,int xx,int yy){
		if(good2){
			for(int i=0;i<ch.gChessMans.size();i++){
				if(ch.gChessMans.get(i).x==xx&&ch.gChessMans.get(i).y==yy){
					ch.gChessMans.remove(i);
				}
			}
		}
		else{
			for(int i=0;i<ch.eChessMans.size();i++){
				if(ch.eChessMans.get(i).x==xx&&ch.eChessMans.get(i).y==yy){
					ch.eChessMans.remove(i);
				}
			}			
		}
	}
	
	
	public boolean goDead(){
		for(int i=0;i<ch.gChessMans.size();i++){
			if(ch.gChessMans.get(i).chance(i)){
				return true;
			}
		}//for		
		return false;
	}//goDead
	
	public boolean chance(int j){
		ChessMan cm;
		if(!good){
			cm = ch.eChessMans.get(j);
		}else{
			cm = ch.gChessMans.get(j);
		}
		if(cm.left()){
			if(cm.eat(false)){
				cm.x = cm.oldx;
				cm.y = cm.oldy;
				return true;
			}//if eat
			cm.x = cm.oldx;
			cm.y = cm.oldy;
		}//if left
		if(cm.up()){
			if(cm.eat(false)){
				cm.x = cm.oldx;
				cm.y = cm.oldy;
				return true;
			}//if eat
			cm.x = cm.oldx;
			cm.y = cm.oldy;
		}//if up
		if(cm.right()){
			if(cm.eat(false)){
				cm.x = cm.oldx;
				cm.y = cm.oldy;
				return true;
			}//if eat
			cm.x = cm.oldx;
			cm.y = cm.oldy;
		}//if right
		if(cm.down()){
			if(cm.eat(false)){
				cm.x = cm.oldx;
				cm.y = cm.oldy;
				return true;
			}//if eat
			cm.x = cm.oldx;
			cm.y = cm.oldy;
		}//if down	
		return false;
	}//chance
	

	public boolean attack(int j){
		ChessMan cm;
		if(!good){
			cm = ch.eChessMans.get(j);		
			if(cm.left()){
				for(int i=0;i<ch.eChessMans.size();i++){
					if(ch.eChessMans.get(i).chance(i)){
						cm.x = cm.oldx;
						cm.y = cm.oldy;
						return true;
					}
				}			
				cm.x = cm.oldx;
				cm.y = cm.oldy;
			}//left
			if(cm.up()){
				for(int i=0;i<ch.eChessMans.size();i++){
					if(ch.eChessMans.get(i).chance(i)){
						cm.x = cm.oldx;
						cm.y = cm.oldy;
						return true;
					}
				}			
				cm.x = cm.oldx;
				cm.y = cm.oldy;
			}//up
			if(cm.right()){
				for(int i=0;i<ch.eChessMans.size();i++){
					if(ch.eChessMans.get(i).chance(i)){
						cm.x = cm.oldx;
						cm.y = cm.oldy;
						return true;
					}
				}			
				cm.x = cm.oldx;
				cm.y = cm.oldy;
			}//right
			if(cm.down()){
				for(int i=0;i<ch.eChessMans.size();i++){
					if(ch.eChessMans.get(i).chance(i)){
						cm.x = cm.oldx;
						cm.y = cm.oldy;
						return true;
					}
				}			
				cm.x = cm.oldx;
				cm.y = cm.oldy;
			}//down
		}//if !good
		else{///////////////////////////////////////////////////else
			cm = ch.gChessMans.get(j);		
			if(cm.left()){
				for(int i=0;i<ch.gChessMans.size();i++){
					if(ch.gChessMans.get(i).chance(i)){
						cm.x = cm.oldx;
						cm.y = cm.oldy;
						return true;
					}
				}			
				cm.x = cm.oldx;
				cm.y = cm.oldy;
			}//left
			if(cm.up()){
				for(int i=0;i<ch.gChessMans.size();i++){
					if(ch.gChessMans.get(i).chance(i)){
						cm.x = cm.oldx;
						cm.y = cm.oldy;
						return true;
					}
				}			
				cm.x = cm.oldx;
				cm.y = cm.oldy;
			}//up
			if(cm.right()){
				for(int i=0;i<ch.gChessMans.size();i++){
					if(ch.gChessMans.get(i).chance(i)){
						cm.x = cm.oldx;
						cm.y = cm.oldy;
						return true;
					}
				}			
				cm.x = cm.oldx;
				cm.y = cm.oldy;
			}//right
			if(cm.down()){
				for(int i=0;i<ch.gChessMans.size();i++){
					if(ch.gChessMans.get(i).chance(i)){
						cm.x = cm.oldx;
						cm.y = cm.oldy;
						return true;
					}
				}			
				cm.x = cm.oldx;
				cm.y = cm.oldy;
			}//down
		}//else	
		return false;
	}
	
	
	
	public void draw(Graphics g){
		if(good){
			if(selected){
				g.setColor(Color.RED);
			}else{
				g.setColor(Color.WHITE);
			}
		}else{
			g.setColor(Color.DARK_GRAY);
		}
		g.fillOval(x-CHESSSIZE/2, y-CHESSSIZE/2, CHESSSIZE, CHESSSIZE);
	}
	public Rectangle getRect(){
		return new Rectangle(x-CHESSSIZE/2,y-CHESSSIZE/2,CHESSSIZE,CHESSSIZE);
	}
	public void released(){
		this.selected = false;
		if(ch.mouseX>= 50&&ch.mouseX<150)this.x = 100;
		if(ch.mouseX>=150&&ch.mouseX<250)this.x = 200;
		if(ch.mouseX>=250&&ch.mouseX<350)this.x = 300;
		if(ch.mouseX>=350&&ch.mouseX<450)this.x = 400;

		if(ch.mouseY>= 50&&ch.mouseY<150)this.y = 100;
		if(ch.mouseY>=150&&ch.mouseY<250)this.y = 200;
		if(ch.mouseY>=250&&ch.mouseY<350)this.y = 300;
		if(ch.mouseY>=350&&ch.mouseY<450)this.y = 400;
	}
	
	public boolean left(){		
		if(getL(1)==0){
			oldx = x;
			oldy = y;
			x -= 100;
			return true;
		}
		return false;
	}
	public boolean up(){
		if(getU(1)==0){
			oldx = x;
			oldy = y;
			y -= 100;
			return true;
		}
		return false;
	}
	public boolean right(){
		if(getR(1)==0){
			oldx = x;
			oldy = y;
			x += 100;
			return true;
		}
		return false;
	}
	public boolean down(){
		if(getD(1)==0){
			oldx = x;
			oldy = y;
			y += 100;
			return true;
		}
		return false;
	}
	
	
	public boolean eat(boolean rm){
		if(!good){
				switch(this.x){
				case 100:
					if(getR(1)==1&&getR(2)==2&&getR(3)<=0){		
						if(rm){
							deleteX = this.x + 200;
							deleteY = this.y;
						}
						return true;						
					}
					break;
				case 200:
					if(getL(1)<=0&&getR(1)==1&&getR(2)==2){
						if(rm){
							deleteX = this.x + 200;
							deleteY = this.y;
						}
						return true;						
					}
					if(getL(1)==2&&getR(1)==1&&getR(2)<=0){
						if(rm){
							deleteX = this.x - 100;
							deleteY = this.y;
						}
						return true;						
					}
					break;
				case 300:
					if(getR(1)<=0&&getL(1)==1&&getL(2)==2){
						if(rm){
							deleteX = this.x - 200;
							deleteY = this.y;
						}
						return true;						
					}
					if(getR(1)==2&&getL(1)==1&&getL(2)<=0){
						if(rm){
							deleteX = this.x + 100;
							deleteY = this.y;
						}
						return true;						
					}
					break;
				case 400:
					if(getL(3)<=0&&getL(1)==1&&getL(2)==2){
						if(rm){
							deleteX = this.x - 200;
							deleteY = this.y;
						}
						return true;						
					}
					break;
				}
				
				switch(this.y){
				case 100:
					if(getD(1)==1&&getD(2)==2&&getD(3)<=0){
						if(rm){
							deleteX = this.x;
							deleteY = this.y + 200;
						}
						return true;						
					}
					break;
				case 200:
					if(getU(1)<=0&&getD(1)==1&&getD(2)==2){
						if(rm){
							deleteX = this.x;
							deleteY = this.y + 200;
						}
						return true;						
					}
					if(getU(1)==2&&getD(1)==1&&getD(2)<=0){
						if(rm){
							deleteX = this.x;
							deleteY = this.y - 100;
						}
						return true;						
					}
					break;
				case 300:
					if(getD(1)<=0&&getU(1)==1&&getU(2)==2){
						if(rm){
							deleteX = this.x;
							deleteY = this.y - 200;
						}
						return true;						
					}
					if(getD(1)==2&&getU(1)==1&&getU(2)<=0){
						if(rm){
							deleteX = this.x;
							deleteY = this.y + 100;
						}
						return true;						
					}
					break;
				case 400:
					if(getU(3)<=0&&getU(1)==1&&getU(2)==2){
						if(rm){
							deleteX = this.x;
							deleteY = this.y - 200;
						}
						return true;						
					}
					break;
				}
				
		}else{
			switch(this.x){
			case 100:
				if(getR(1)==2&&getR(2)==1&&getR(3)<=0){
					if(rm){
						deleteX = this.x + 200;
						deleteY = this.y;
					}
					return true;					
				}
				break;
			case 200:
				if(getL(1)<=0&&getR(1)==2&&getR(2)==1){
					if(rm){
						deleteX = this.x + 200;
						deleteY = this.y;
					}
					return true;					
				}
				if(getL(1)==1&&getR(1)==2&&getR(2)<=0){
					if(rm){
						deleteX = this.x - 100;
						deleteY = this.y;
					}
					return true;					
				}
				break;
			case 300:
				if(getR(1)<=0&&getL(1)==2&&getL(2)==1){
					if(rm){
						deleteX = this.x - 200;
						deleteY = this.y;
					}
					return true;					
				}
				if(getR(1)==1&&getL(1)==2&&getL(2)<=0){
					if(rm){
						deleteX = this.x + 100;
						deleteY = this.y;
					}
					return true;					
				}
				break;
			case 400:
				if(getL(3)<=0&&getL(1)==2&&getL(2)==1){
					if(rm){
						deleteX = this.x - 200;
						deleteY = this.y;
					}
					return true;					
				}
				break;
			}
			
			switch(this.y){
			case 100:
				if(getD(1)==2&&getD(2)==1&&getD(3)<=0){
					if(rm){
						deleteX = this.x;
						deleteY = this.y + 200;
					}
					return true;					
				}
				break;
			case 200:
				if(getU(1)<=0&&getD(1)==2&&getD(2)==1){
					if(rm){
						deleteX = this.x;
						deleteY = this.y + 200;
					}
					return true;					
				}
				if(getU(1)==1&&getD(1)==2&&getD(2)<=0){
					if(rm){
						deleteX = this.x;
						deleteY = this.y - 100;
					}
					return true;					
				}
				break;
			case 300:
				if(getD(1)<=0&&getU(1)==2&&getU(2)==1){
					if(rm){
						deleteX = this.x;
						deleteY = this.y - 200;
					}
					return true;					
				}
				if(getD(1)==1&&getU(1)==2&&getU(2)<=0){
					if(rm){
						deleteX = this.x;
						deleteY = this.y + 100;
					}
					return true;					
				}
				break;
			case 400:
				if(getU(3)<=0&&getU(1)==2&&getU(2)==1){
					if(rm){
						deleteX = this.x;
						deleteY = this.y - 200;
					}
					return true;					
				}
				break;
			}
			
		}
		return false;	
	}
	public int getU(int u){
		int targetX = this.x;
		int targetY = this.y - u*100;
		if(targetY<100){
			return -1;
		}
		for(int i=0;i<ch.eChessMans.size();i++){
			ChessMan c = ch.eChessMans.get(i);
			if(targetX==c.x&&targetY==c.y&&c!=this){
				return 1;
			}
		}
		for(int i=0;i<ch.gChessMans.size();i++){
			ChessMan c = ch.gChessMans.get(i);
			if(targetX==c.x&&targetY==c.y&&c!=this){
				return 2;
			}
		}
		return 0;
	}
	public int getD(int d){
		int targetX = this.x;
		int targetY = this.y + d*100;
		if(targetY>400){
			return -1;
		}
		for(int i=0;i<ch.eChessMans.size();i++){
			ChessMan c = ch.eChessMans.get(i);
			if(targetX==c.x&&targetY==c.y&&c!=this){
				return 1;
			}
		}
		for(int i=0;i<ch.gChessMans.size();i++){
			ChessMan c = ch.gChessMans.get(i);
			if(targetX==c.x&&targetY==c.y&&c!=this){
				return 2;
			}
		}
		return 0;
	}
	public int getL(int l){
		int targetX = this.x - l*100;
		int targetY = this.y;
		if(targetX<100){
			return -1;
		}
		for(int i=0;i<ch.eChessMans.size();i++){
			ChessMan c = ch.eChessMans.get(i);
			if(targetX==c.x&&targetY==c.y&&c!=this){
				return 1;
			}
		}
		for(int i=0;i<ch.gChessMans.size();i++){
			ChessMan c = ch.gChessMans.get(i);
			if(targetX==c.x&&targetY==c.y&&c!=this){
				return 2;
			}
		}
		return 0;
	}
	public int getR(int r){
		int targetX = this.x + r*100;
		int targetY = this.y;
		if(targetX>400){
			return -1;
		}
		for(int i=0;i<ch.eChessMans.size();i++){
			ChessMan c = ch.eChessMans.get(i);
			if(targetX==c.x&&targetY==c.y&&c!=this){
				return 1;
			}
		}
		for(int i=0;i<ch.gChessMans.size();i++){
			ChessMan c = ch.gChessMans.get(i);
			if(targetX==c.x&&targetY==c.y&&c!=this){
				return 2;
			}
		}
		return 0;
	}
	

	public ChessMan(int x, int y, boolean good,Chess ch) {
		this.x = x;
		this.y = y;
		oldx = x;
		oldy = y;
		this.good = good;
		this.ch = ch;
	}
}
