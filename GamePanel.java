import java.util.*;
import java.io.*;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{
	private final boolean grid = false;
	Image img; 
	Image sign;
	Base base;
	Timer timer;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public GamePanel( ) {
		this.setFocusable(true);
	    this.requestFocusInWindow();
	    this.setPreferredSize(new Dimension(1450, 773));
	    try {
	    	sign = ImageIO.read(new File("Sign.png"));
	    	img = ImageIO.read(new File("map.jpg"));
	    } catch (Exception e) {
	    	System.out.println("No Image Found");
	    }
	    
	    // Start Game
	    timer = new Timer(80, this);
	    timer.start();
	    
	    // Create Base
	    base = new Base(1000, 900);
	    
	    // Create Enemies
	    int current = 800;
	     for (int n = 0; n < 10; n++) {
	    	enemies.add(new Regular(new Point(850, current)));
	    	current += 50;
	     }
	}
	
	// Draw wooden panel
	public void drawTimerPanel(Graphics g) {
		// Draw signs
		g.drawImage(sign, 25, 15, null);
	}
	
	public void paint(Graphics g) {
		
		g.drawRect(10, 10, 330, 790);
		drawTimerPanel(g);
		// Draw bg
		g.drawImage(img, 350, 0, null);
		
		base.draw(g);
		
		// Draw Enemies
		for (Enemy en: enemies) {
			en.draw(g);
		}
		// Grids
		if (grid) {
			for (int i = 1450 / 100; i >= 0; i--) {
				g.drawLine(i * 100, 0, i * 100, 800);
			}
			
			for (int i = 800 / 100; i >= 0; i--) {
				g.drawLine(0, i * 100, 1250, i * 100);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enemies.size() > 0) {
			for (int n = 0; n < enemies.size(); n++) {
				Enemy en = enemies.get(n);
				en.move();
				if (en.getX() <= 400) {
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
