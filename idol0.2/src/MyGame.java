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
		this.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
		this.setResizable(false);
	}
	
	
	public static void main(String[] args) {
		MyGame mg = new MyGame();
		mg.lauchFrame();
	}

}
