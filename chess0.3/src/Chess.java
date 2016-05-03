import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class Chess extends Frame{
	private static final long serialVersionUID = 1L;
	Image OffScreenImage = null;
	int selectNo = 0;
	int mouseX,mouseY;
	List<ChessMan> gChessMans = new ArrayList<ChessMan>();
	List<ChessMan> eChessMans = new ArrayList<ChessMan>();
	public void update(Graphics g) {
		if(OffScreenImage == null){
			OffScreenImage = this.createImage(500,500);
		}
		Graphics gg = OffScreenImage.getGraphics();
		gg.setColor(Color.LIGHT_GRAY);
		gg.fillRect(0, 0, 500, 500);
		paint(gg);
		g.drawImage(OffScreenImage, 0, 0, null);		
	}


	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		for(int i=0;i<4;i++){
			g.drawLine(100+i*100, 100, 100+i*100, 400);
			g.drawLine(100, 100+i*100, 400, 100+i*100);
		}
		for(int i =0;i<gChessMans.size();i++){
			ChessMan cm = gChessMans.get(i);
			cm.draw(g);
		}
		for(int i =0;i<eChessMans.size();i++){
			ChessMan cm = eChessMans.get(i);
			cm.draw(g);
		}
	}
	

	void lauchFrame(){
		this.setSize(500, 500);
		this.setLocation(200,50);
		this.setBackground(Color.LIGHT_GRAY);
		this.addKeyListener(new KeyMontior());
		this.addMouseListener(new MouseMontior());
		this.setTitle("����һ  by��������");
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
		this.setResizable(false);
		for(int i=0;i<4;i++){
			eChessMans.add(new ChessMan (100+i*100,100,false,this));
		}
		for(int i=0;i<4;i++){
			gChessMans.add(new ChessMan (100+i*100,400,true,this));
		}
		
		
		
		this.setVisible(true);
		new Thread(new ThreaPaint()).start();
	}
	class ThreaPaint implements Runnable{

		public void run() {
			while(true){				
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
		
	}
	
	private class MouseMontior extends MouseAdapter{

		public void mouseReleased(MouseEvent e) {			
			mouseX = e.getX();
			mouseY = e.getY();
			gChessMans.get(selectNo).released();
		}

		public void mousePressed(MouseEvent e) {

				for(int i=0;i<gChessMans.size();i++){
					ChessMan cm = gChessMans.get(i);
					if(cm.getRect().contains(e.getX(),e.getY()))
					{						
						cm.selected = true;
						selectNo = i;
						break;
					}				
				}
			}
		}
		

	
	private class KeyMontior extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			int highNo = 0;
			int highScore = 0;
			int score[] = {0,0,0,0};

			if(key==KeyEvent.VK_ENTER){	
				
				eChessMans.get(0).score();
				
//				for(int i=0;i<eChessMans.size();i++){
//					score[i] = eChessMans.get(i).score();					
//				}
//				highScore = score[0];
//				for(int i=0;i<score.length;i++){
//					if(highScore < score[i]){highScore = score[i];highNo = i;}
//				}
//				eChessMans.get(highNo).del();
			}
		}
		
	}
	public static void main(String[] args) {
		Chess ch = new Chess();
		ch.lauchFrame();
	}

}
