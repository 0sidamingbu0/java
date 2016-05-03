import java.awt.*;


public class MyGame extends Frame {

		public void lauchFrame(){
			this.setLocation(100,100);
			this.setSize(800,600);
			this.setVisible(true);
			
		}

	public static void main(String[] args) {
		MyGame mg = new MyGame();
		mg.lauchFrame();
	}
}
