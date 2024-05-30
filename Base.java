import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;
public class Base {
	private int money;
	private int maxHealth;
	private int currentHealth;
	private Image img;
	
	public Base(int maxHealth, int currentHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		this.money = 500;
		try {
	    	img = ImageIO.read(new File("castle.png"));
	    } catch (Exception e) {
	    	System.out.println("BASE: No Image Found");
	    }
	}
	
	public void collision(int enemyHealth) {
		this.currentHealth -= enemyHealth;
	}
	
	public boolean gameOver() {
		if (this.currentHealth < 0) {
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g) {
		// Draw base
		g.drawImage(img, 325, 125, null);
		// Draw health Bar
		double ratio = (double) currentHealth / maxHealth;
		g.setColor(Color.RED);
		g.fillRect(345, 100, 200, 25);
		g.setColor(Color.GREEN);
		g.fillRect(345, 100, (int)(200 * ratio), 25);
	
	}
	
	public int getHealth() {
		return this.currentHealth;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void changeMoney(int num) {
		this.money += num;
	}
}
