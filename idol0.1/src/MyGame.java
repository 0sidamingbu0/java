import java.awt.*;
import java.awt.event.*;


public class MyGame extends Frame {


	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(100, 100, 200, 200);
		g.setColor(c);


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
