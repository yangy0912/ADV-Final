import java.awt.Graphics;
import java.util.ArrayList;

public class Defense {
	
	public ArrayList<Tower> df;
	
	public Defense() {
		df = new ArrayList<>();
		initalSetup();
	}
	

	public void initalSetup() {
		this.df.add(new BasicTower(new Point(800, 185)));
		this.df.add(new BasicTower(new Point(600, 500)));
	}
	
	public void draw(Graphics g) {
		for (Tower tower : df) {
			tower.draw(g);
		}
	}
	
	public ArrayList<Tower> getList() {
		return this.df;
	}
}
