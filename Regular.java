import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class Regular extends Enemy{
	private int x = 650;
	private int y;
	public ArrayList<Point> way;
	private int index = 0;
	Image current;
	Image faceLeft;
	Image faceRight;
	
	public Regular(Point start) {
		super();
		this.health = 100;
		this.speed = 8;
		this.current_health = this.health;
		
		// Initial spawn
		Move temp = new Move(speed, start);
		way = temp.createPath();
		
		// Image
		try {
	    	faceLeft = ImageIO.read(new File("Ugh.png"));
	    	faceRight = ImageIO.read(new File("FacedRight.png"));
	    	current = faceLeft;
	    } catch (Exception e) {
	    	System.out.println("No Image Found");
	    }
	
		}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return 65;
	}
	
	public int getHeight() {
		return 105;
	}
	
	public void hit(int damage) {
		this.current_health -= damage;
	}
	
	public int getHealth() {
		return this.current_health;
	}
	
	public void move() {
		index += 1;
		Point p = way.get(index);
		if (current == faceLeft && this.x < p.x) {
			current = faceRight;
		} else if (current == faceRight && this.x > p.x) {
			current = faceLeft;
		}
		//System.out.println(p);
		this.x = p.x;
		this.y = p.y;
	}
	
	public void draw(Graphics g) {
		// image
		g.drawImage(current, x, y, null);
		// hitbox
		g.setColor(Color.RED);
		g.drawRect(x, y, 65, 105);
		// Health Bar
		double ratio = (double)this.current_health / this.health;
		g.setColor(Color.RED);
		g.fillRect(x + 4, y - 8, 50, 10);
		g.setColor(Color.GREEN);
		g.fillRect(x + 4, y - 8, (int) (50 * ratio), 10);
	}
	

}
