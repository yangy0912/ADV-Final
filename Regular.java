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
		this.speed = 10;
		
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
		double ratio = 75.0 / health;
		g.setColor(Color.RED);
		g.fillRect(x + 4, y - 8, 25, 10);
		g.setColor(Color.GREEN);
		g.fillRect(x + 4, y - 8, (int) (25 * ratio), 10);
	}
}
