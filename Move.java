import java.util.ArrayList;

public class Move {
	public int speed;
	public ArrayList<Point> path;
	
	public Move(int speed) {
		this.speed = speed;
		path = new ArrayList<Point>();
	}
	
	public ArrayList<Point> createPath() {
		path.add(new Point(650, 800));
		// Go up
		for (int n = 325 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x, last.y - speed));
		}
		// Go Right
		for (int n = 360 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x + speed, last.y));
		}
		// Go Up
		for (int n = 300 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x, last.y - speed));
		}
		// Go Left
		for (int n = 130 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x - speed, last.y));
		}
		// Go Down
		for (int n = 200 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x, last.y + speed));
		}
		// Go Left
		for (int n = 555 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x - speed, last.y));
		}
		// Go Down
		for (int n = 165 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x, last.y + speed));
		}
		// Go Right
		for (int n = 195 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x + speed, last.y));
		}
		// Go Up
		for (int n = 480 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x, last.y - speed));
		}
		// Go Right
		for (int n = 170 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x + speed, last.y));
		}
		// Go Down
		for (int n = 165 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x, last.y + speed));
		}
		// Go Left
		for (int n = 555 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x - speed, last.y));
		}
		return path;
	}
}
