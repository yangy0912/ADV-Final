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
	Image money;
	Image heart;
	Base base;
	Wave wave;
	Timer timer;
	
	public GamePanel( ) {
		this.setFocusable(true);
	    this.requestFocusInWindow();
	    this.setPreferredSize(new Dimension(1450, 773));
	    try {
	    	sign = ImageIO.read(new File("Sign.png"));
	    	img = ImageIO.read(new File("map.jpg"));
	    	money = ImageIO.read(new File("money.png"));
	    	heart = ImageIO.read(new File("heart.png"));
	    } catch (Exception e) {
	    	System.out.println("No Image Found");
	    }
	    
	    // Start Game
	    timer = new Timer(80, this);
	    timer.start();
	    
	    // Create Base
	    base = new Base(1000, 1000);
	    
	    // Create Enemies
	    this.wave = new Wave();
	    wave.addEnemies();
	}
	
	// Draw wooden panel
	public void drawTimerPanel(Graphics g) {
		// Draw signs
		g.drawImage(sign, 25, 15, null);
	}
	
	public void paint(Graphics g) {
		// Draw sign
		g.drawRect(10, 10, 330, 750);
		drawTimerPanel(g);
		g.setFont(new Font("Serif", Font.BOLD, 50));
		g.drawString(this.wave.waveNumber(), 90, 100);
		
		// Draw stats
		g.setFont(new Font("Serif", Font.BOLD, 40));
		g.drawImage(heart, 35, 160, null);
		g.drawString(": " + base.getHealth(), 220, 240);
		g.drawImage(money, 35, 300, null);
		
		// Draw bg
		g.drawImage(img, 350, 0, null);
		
		base.draw(g);
		
		// Draw Enemies
		for (Enemy en: wave.getList()) {
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
		// Enemies
		int damage = wave.moveEnemies();
		if (this.wave.waveOver()) {
			this.wave.addEnemies();
		}
		// Base
		base.collision(damage);
		if (base.gameOver()) {
			timer.stop();
		}
		// DELAY
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		repaint();
		
	}
}
