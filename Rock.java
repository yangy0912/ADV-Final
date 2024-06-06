import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class Rock {

	private double angle;
	private static int damage = 2;
	private static int speed = 15;
	private Image rock;
	private int xVel;
	private int yVel;
	private int xPos = 450;
	private int yPos = 350;
	private int xCenter = xPos + 10 + 80 / 2;
	private int yCenter = yPos + 15 + 80 / 2;
	private int rad = 40;
	private static int speedUpgrade = 1;
	private static int damageUpgrade = 1;
	
	public Rock(double angle) {
		this.angle = angle;
		this.xVel = (int) (Math.cos(angle) * speed);
		this.yVel = (int) (Math.sin(angle) * speed);
		try {
	    	rock = ImageIO.read(new File("rock.png"));
	    } catch (Exception e) {
	    	System.out.println("No Image Found");
	    }
	}
	
	public static void upgradeDamage() {
		System.out.println(damage);
		damage = (int) (damage * Math.pow(1.1, damageUpgrade));
		damageUpgrade += 1;
	}
	
	public static void upgradeSpeed() {
		System.out.println(speed);
		speed = (int) (speed * Math.pow(1.05, speedUpgrade));
		speedUpgrade += 1;
	}
	
	public void draw(Graphics g) {
		g.drawImage(rock, xPos, yPos, null);
		g.drawOval(xPos + 10, yPos + 15, 80, 80);
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void move() {
		xPos += Math.abs(xVel);
		yPos -= yVel;
		xCenter = xPos + 10 + 80 / 2;
		yCenter = yPos + 15 + 80 / 2;
	}
	
	
	// Help from StackOverflow
	public boolean isCircleCollidingWithRectangle(double rectX, double rectY, double rectWidth, double rectHeight) {
		// Find the closest point on the rectangle to the circle
		double closestX = clamp(this.xCenter, rectX, rectX + rectWidth);
		double closestY = clamp(this.yCenter, rectY, rectY + rectHeight);

		// Calculate the distance between the circle's center and this closest point
		double distanceX = this.xCenter - closestX;
		double distanceY = this.yCenter - closestY;
		double distanceSquared = distanceX * distanceX + distanceY * distanceY;

		// Check if the distance is less than or equal to the circle's radius squared
		return distanceSquared <= rad * rad;
	}
	
    // Helper method to clamp a value between a min and max
    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }
}
