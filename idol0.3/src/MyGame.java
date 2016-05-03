import java.awt.*;
import java.awt.event.*;


public class MyGame extends Frame {
	private static final long serialVersionUID = 1L;

	Tank t1 = new Tank(100,100,true);
	Tank t2 = new Tank(200,100,true);
	public void paint(Graphics g) {
		g.drawString("x = "+t1.getX(), 50, 50);
		t1.draw(g);
		t2.draw(g);
	}


	public void lauchFrame(){
		this.setLocation(100,50);
		this.setSize(800,600);
		this.setVisible(true);
		this.setBackground(Color.GRAY);
		new Thread(new PaintThread()).start();
		new Thread(new PaintThread2()).start();
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
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	
	private class PaintThread2 implements  Runnable {
		public void run() {
			while(true){
				t1.setX(150);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
	}
	
	
	
	
	
	public static void main(String[] args) {
		MyGame mg = new MyGame();
		mg.lauchFrame();
	}

}
