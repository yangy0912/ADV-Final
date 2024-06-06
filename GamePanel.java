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

public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener{
	private final boolean grid = false;
	Image img; 
	Image sign;
	Image money;
	Image heart;
	Image bomb;
	Image speed;
	Image med;
	Image power;
	Base base;
	Defense defense;
	Wave wave;
	Timer timer;
	Rock rock;
	Artillery artillery;
	public double angle;
	public Point initial;
	public Point post;
	public boolean aiming = false;
	private int width = 1450;
	private int height = 775;
	private ArrayList<Rock> projectiles = new ArrayList<Rock>();;
	
	public GamePanel( ) {
	    this.setPreferredSize(new Dimension(width, height));
	    this.addMouseListener(this);
	    this.addKeyListener(this);
	    try {
	    	bomb = ImageIO.read(new File("bomb.png"));
	    	power = ImageIO.read(new File("arm.png"));
	    	speed = ImageIO.read(new File("speed.png"));
	    	med = ImageIO.read(new File("med.png"));
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
	    base = new Base(5000, 5000);
	    
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
		
		// Draw Upgrades
		g.setColor(Color.BLACK);
		g.drawRect(35, 425, 125, 125);
		g.drawRect(190, 425, 125, 125);
		g.drawRect(35, 580, 125, 125);
		g.drawRect(190, 580, 125, 125);
		g.setFont(new Font("Serif", Font.BOLD, 15));
		g.drawString("Increase Damage", 40, 450);
		g.drawString("100$", 80, 470);
		g.drawImage(power, 50, 460, null);
		g.drawString("Increase Speed", 195, 450);
		g.drawString("100$", 240, 470);
		g.drawImage(speed, 205, 470, null);
		g.drawString("Heal 10% Damage", 40, 605);
		g.drawString("200$", 80, 625);
		g.drawImage(med, 50, 625, null);
		g.drawString("Bomb all Enemies", 195, 605);
		g.drawString("500$", 240, 625);
		g.drawImage(bomb, 205, 605, null);
		
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
		for (Rock r: projectiles) {
			r.draw(g);
			
		}
		
		
		g.setColor(Color.BLACK);
		if (aiming) {
			int x = Math.abs(MouseInfo.getPointerInfo().getLocation().x);
			int y = (MouseInfo.getPointerInfo().getLocation().y);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(x, y, 500, 376);
			g2.drawLine(500, 376, 500 * 2 - x, 376 * 2 - y);
			int x1 = 500 * 2 - x;
			int y1 = 376 * 2 - y ;
			double coeff = ((double) (376 - y1)) / ((double) (x1 - 500));
			angle = (Math.atan(coeff));
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
		for (int count = 0; count < projectiles.size(); count++) {
			projectiles.get(count).move();
			for (int n = 0; n < wave.getList().size(); n++) {
				Regular s = (Regular) wave.getList().get(n);
				if (projectiles.get(count).isCircleCollidingWithRectangle(s.getX(), s.getY(), s.getWidth(), s.getHeight())) {
					s.hit(projectiles.get(count).getDamage());
					if (s.getHealth() < 0) {
						wave.getList().remove(n);
						base.changeMoney(10);
					}
				}
			}
		}
		// DELAY
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		repaint();
		
	}

	public Timer getTimer() {
		return timer;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getX() > 35 && e.getX() < 160) && (e.getY() > 425 && e.getY() < 550)) {
			if (base.getMoney() >= 100) {
				Rock.upgradeDamage();
				this.base.changeMoney(-100);
			}
		} else if ((e.getX() > 190 && e.getX() < 315) && (e.getY() > 425 && e.getY() < 550)) {
			if (base.getMoney() >= 100) {
				Rock.upgradeSpeed();
				this.base.changeMoney(-100);
			}
		} else if ((e.getX() > 35 && e.getX() < 160) && (e.getY() > 580 && e.getY() < 705)) {
			if (base.getMoney() >= 200) {
				this.base.heal();
				this.base.changeMoney(-200);
			}
		} else if ((e.getX() > 190 && e.getX() < 315) && (e.getY() > 580 && e.getY() < 705)) {
			if (base.getMoney() >= 500) {
				this.wave.nuke();
				this.base.changeMoney(-500);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if ((e.getX() > 390 && e.getX() < 590) && (e.getY() > 300 && e.getY() < 440)) {
			aiming = true;
		}
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (aiming) {
			projectiles.add(new Rock(angle));
			aiming = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == 27){
//		   if (timer.isRunning()) {
//			   timer.stop();
//			   Menu menu = new Menu();
//			   menu.startUp();	
//		   } else {
//			   timer.start();
//		   }
//	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
