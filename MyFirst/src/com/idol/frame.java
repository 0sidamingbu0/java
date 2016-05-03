package com.idol;

import java.awt.*;

public class frame extends Frame{

	@Override
	public void paint(Graphics p) {
		p.setColor(Color.red);
		p.fillOval(50, 50, 30, 30);
	}
	public void launchframe() {
		setBounds(400,400,300,300);
		this.setVisible(true);
	}
	
	public static void main (String[] args){
		new frame().launchframe();
	}
}
