import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class Rock {

	private double angle;
	private int damage;
	private double speed;
	private Image rock;
	private int xVel;
	private int yVel;
	private int xPos = 450;
	private int yPos = 350;
	
	public Rock(double angle) {
		this.angle = angle;
		this.damage = 50;
		this.speed = 10;
		this.xVel = (int) (Math.cos(angle) * speed);
		this.yVel = (int) (Math.sin(angle) * speed);
		try {
	    	rock = ImageIO.read(new File("rock.png"));
	    } catch (Exception e) {
	    	System.out.println("No Image Found");
	    }
	}
	
	public void draw(Graphics g) {
		g.drawImage(rock, xPos, yPos, null);
		g.drawOval(xPos + 10, yPos + 15, 80, 80);
	}
	
	public void move() {
		xPos += Math.abs(xVel);
		yPos -= yVel;
	}
}
