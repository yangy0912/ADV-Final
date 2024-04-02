import java.util.*;
import java.io.*;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{
	Image img; 
	Timer timer;
	Regular bot;
	
	public GamePanel( ) {
		this.setFocusable(true);
	    this.requestFocusInWindow();
	    this.setPreferredSize(new Dimension(1250, 773));
	    try {
	    	img = ImageIO.read(new File("map.jpg"));
	    } catch (Exception e) {
	    	System.out.println("No Image Found");
	    }
	    
	    // Start Game
	    timer = new Timer(80, this);
	    timer.start();
	    
	    // Create Enemies
	    bot = new Regular();
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 150, 0, null);
		bot.draw(g);
		
		for (int i = 1250 / 100; i >= 0; i--) {
			g.drawLine(i * 100, 0, i * 100, 800);
		}
		
		for (int i = 800 / 100; i >= 0; i--) {
			g.drawLine(0, i * 100, 1250, i * 100);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Looping");
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		bot.move();
		repaint();
	}
}
