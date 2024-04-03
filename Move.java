import java.util.ArrayList;

public class Move {
	public int speed;
	public ArrayList<Point> path;
	public Point start;
	
	public Move(int speed, Point start) {
		this.speed = speed;
		this.start = start;
		path = new ArrayList<Point>();
	}
	
	// 650. 800
	public ArrayList<Point> createPath() {
		path.add(start);
		// Make up for any yards
		if (start.y > 800) {
			for(int n = (start.y - 800) / speed; n >= 0; n--) {
				Point last = path.get(path.size() - 1);
				path.add(new Point(last.x, last.y - speed));
			}
		}
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
		for (int n = 180 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x + speed, last.y));
		}
		// Go Down
		for (int n = 170 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x, last.y + speed));
		}
		// Go Left til end
		for (int n = 555 / speed; n >= 0; n--) {
			Point last = path.get(path.size() - 1);
			path.add(new Point(last.x - speed, last.y));
		}
		return path;
	}
}
