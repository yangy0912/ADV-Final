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
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
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
	    int current = 800;
	     for (int n = 0; n < 10; n++) {
	    	enemies.add(new Regular(new Point(650, current)));
	    	current += 50;
	     }
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 150, 0, null);
		// Draw Enemies
		for (Enemy en: enemies) {
			en.draw(g);
		}
		// Grids
		for (int i = 1250 / 100; i >= 0; i--) {
			g.drawLine(i * 100, 0, i * 100, 800);
		}
		
		for (int i = 800 / 100; i >= 0; i--) {
			g.drawLine(0, i * 100, 1250, i * 100);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enemies.size() > 0) {
			for (Enemy en: enemies) {
				en.move();
				System.out.println(en.getX());
				if (en.getX() <= 200) {
					System.out.println("Removed");
					enemies.remove(en);
				}
			}
		}
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		repaint();
		
	}
}
