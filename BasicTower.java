import java.awt.Font;
import java.awt.Graphics;

public class BasicTower extends Tower{
	
	public BasicTower(Point point) {
		super();
		this.position = point;
	}
	
	public void fire() {

	}
	
	public void draw(Graphics g) {
		g.drawRect(position.x, position.y, 75, 75);
		g.setFont(new Font("Times", Font.BOLD, 80));
		g.drawString("X", position.x + 12, position.y + 65);
	}

}
