import java.awt.Color;
import java.awt.Graphics;


public class Disappear {
	int size = ChessMan.CHESSSIZE;
	int x,y;
	Chess ch;
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		if(size>=0){
			g.fillOval(x - ChessMan.CHESSSIZE/2 + (ChessMan.CHESSSIZE - size)/2, y - ChessMan.CHESSSIZE/2 + (ChessMan.CHESSSIZE - size)/2, size, size);
			size -= 3;
		}
		else{
			ch.disappears.remove(this);
		}
	}
	public Disappear(int x, int y,Chess ch) {
		this.x = x;
		this.y = y;
		this.ch = ch;
	}
	
	
}
