import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.MouseInfo;

public class GamePanel extends JPanel implements ActionListener, MouseListener{
	private final boolean grid = true;
	Image img; 
	Image sign;
	Image money;
	Image heart;
	Base base;
	Defense defense;
	Wave wave;
	Timer timer;
	Artillery artillery;
	public Point initial;
	public Point post;
	public boolean aiming = false;
	private int width = 1450;
	private int height = 775;
	
	public GamePanel( ) {
		this.setFocusable(true);
	    this.requestFocusInWindow();
	    this.setPreferredSize(new Dimension(width, height));
	    this.addMouseListener(this);
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
	    
	    // Create defense
	    defense = new Defense();
	    
	    // Create Enemies
	    this.wave = new Wave();
	    wave.addEnemies();
	    
	    // Create tower
	    artillery = new Artillery(390, 300);
	    
	    // Center point is (490, 370)
	}
	
	// Draw wooden panel
	public void drawTimerPanel(Graphics g) {
		// Draw signs
		g.drawImage(sign, 25, 15, null);
	}
	
	public void paint(Graphics g) {
		// Clear black board
		g.clearRect(0, 0, width, height);
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
		g.drawString(": " + base.getMoney(), 220, 340);
		
		// Draw bg
		g.drawImage(img, 350, 0, null);
		
		base.draw(g);
		
		// Draw Enemies
		for (Enemy en: wave.getList()) {
			en.draw(g);
		}
		
		
		// Draw defense
		g.setColor(Color.BLACK);
		defense.draw(g);
		
		
		g.setColor(Color.YELLOW);
		// Grids
		if (grid) {
			for (int i = 1450 / 100; i >= 0; i--) {
				g.drawLine(i * 100, 0, i * 100, height);
			}
			
			for (int i = 800 / 100; i >= 0; i--) {
				g.drawLine(0, i * 100, width, i * 100);
			}
		}
		
		// Draw tower
		artillery.draw(g);
		if (aiming) {
			int x = Math.abs(MouseInfo.getPointerInfo().getLocation().x);
			int y = (MouseInfo.getPointerInfo().getLocation().y);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(x, y, 500, 376);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Enemies
		int damage = wave.moveEnemies();
		if (this.wave.waveOver()) {
			this.wave.addEnemies();
			this.base.changeMoney((Integer.parseInt(this.wave.waveNumber().substring(6)) - 1) * 200);
		}
		// Base
		base.collision(damage);
		if (base.gameOver()) {
			timer.stop();
		}
		// Defense
		for (Tower tower: this.defense.getList()) {
			ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(5);
			exec.schedule(new Runnable() {
			          public void run() {
			              tower.fire();
			          }
			     }, 5, TimeUnit.SECONDS);
		}
		// DELAY
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if ((e.getX() > 390 && e.getX() < 590) && (e.getY() > 300 && e.getY() < 440)) {
			System.out.println("held");
			aiming = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Released");
		aiming = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
