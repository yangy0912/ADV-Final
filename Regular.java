import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class Regular extends Enemy{
	public int x;
	public int y;
	public ArrayList<Point> way;
	public int index = 0;
	Image img;
	
	public Regular() {
		super();
		this.health = 100;
		this.speed = 5;
		
		// Initial spawn
		Move temp = new Move(speed);
		way = temp.createPath();
		
		// Image
		try {
	    	img = ImageIO.read(new File("Ugh.png"));
	    } catch (Exception e) {
	    	System.out.println("No Image Found");
	    }
	
		}
	
	public void move() {
		index += 1;
		Point p = way.get(index);
		System.out.println(p);
		this.x = p.x;
		this.y = p.y;
	}
	
	public void draw(Graphics g) {
		// image
		g.drawImage(img, x, y, null);
		// hitbox
		g.drawOval(x, y, 3, 3);
		// Health Bar
		double ratio = 75.0 / health;
		g.setColor(Color.RED);
		g.fillRect(x + 4, y - 8, 25, 10);
		g.setColor(Color.GREEN);
		g.fillRect(x + 4, y - 8, (int) (25 * ratio), 10);
	}
}
