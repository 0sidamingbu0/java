import java.awt.*;
import java.awt.event.*;


public class MyGame extends Frame {
	private static final long serialVersionUID = 1L;

	Image offScreenImage =null;
	Tank t1 = new Tank(100,100,true);
	Tank t2 = new Tank(200,100,true);
	public void paint(Graphics g) {
		g.drawString("x = "+t1.getX(), 50, 50);
		t1.draw(g);
		t2.draw(g);
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

	public void lauchFrame(){
		this.setLocation(100,50);
		this.setSize(800,600);
		
		this.addKeyListener(new KeyMonitor());
		this.addKeyListener(new KeyMonitor2());
		
		this.setVisible(true);
		this.setBackground(Color.GRAY);
		new Thread(new PaintThread()).start();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
		this.setResizable(false);
	}
	private class PaintThread implements Runnable{

		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key)
				{
				case KeyEvent.VK_UP:
					t1.setY(t1.getY()-10);
					break;
				case KeyEvent.VK_RIGHT:
					t1.setX(t1.getX()+10);
					break;
				case KeyEvent.VK_DOWN:
					t1.setY(t1.getY()+10);
					break;
				case KeyEvent.VK_LEFT:
					t1.setX(t1.getX()-10);
					break;		
					
//				case KeyEvent.VK_W:
//					t2.setY(t2.getY()-10);
//					break;
//				case KeyEvent.VK_D:
//					t2.setX(t2.getX()+10);
//					break;
//				case KeyEvent.VK_S:
//					t2.setY(t2.getY()+10);
//					break;
//				case KeyEvent.VK_A:
//					t2.setX(t2.getX()-10);
//					break;	
				}
			}
		}
		
private class KeyMonitor2 extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key)
				{
//				case KeyEvent.VK_UP:
//					t1.setY(t1.getY()-10);
//					break;
//				case KeyEvent.VK_RIGHT:
//					t1.setX(t1.getX()+10);
//					break;
//				case KeyEvent.VK_DOWN:
//					t1.setY(t1.getY()+10);
//					break;
//				case KeyEvent.VK_LEFT:
//					t1.setX(t1.getX()-10);
//					break;		
					
				case KeyEvent.VK_W:
					t2.setY(t2.getY()-10);
					break;
				case KeyEvent.VK_D:
					t2.setX(t2.getX()+10);
					break;
				case KeyEvent.VK_S:
					t2.setY(t2.getY()+10);
					break;
				case KeyEvent.VK_A:
					t2.setX(t2.getX()-10);
					break;	
				}
			}
		}
	

	
	
	
	
	
	public static void main(String[] args) {
		MyGame mg = new MyGame();
		mg.lauchFrame();
	}

}
