package Gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	Dimension screen ;
	MyPanel mp;
	
	MyFrame myFrame;

	public MyFrame() {
		// TODO Auto-generated constructor stub
		
		screen = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(395+120+100, 785 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(((int)screen.getWidth()-360)/2 ,((int) screen.getHeight()-720)/2);
		setResizable(false);
		setTitle("Inverted Index");
		setLayout(null);
		setLocationRelativeTo(null);
		
		mp = new MyPanel(this);
		this.getContentPane().add(mp);

		

		setVisible(true);

	}
	

}
