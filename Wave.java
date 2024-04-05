import java.util.*;
public class Wave {
	private int waveNumber;
	private ArrayList<Enemy> waveOfEnemy;
	
	public Wave() {
		this.waveNumber = 1;
		this.waveOfEnemy = new ArrayList<Enemy>();
	}

	public void addEnemies() {
		int current = 800;
		for (int count = 0; count <= waveNumber * 5; count++) {
			this.waveOfEnemy.add(new Regular(new Point(850, current)));
			current += 50;
		}
	}
	
	public boolean waveOver() {
		if (this.waveOfEnemy.isEmpty()) {
			this.waveNumber += 1;
			return true;
		}
		return false;
	}
	
	public int moveEnemies() {
		int damage = 0;
		for (int n = 0; n < this.waveOfEnemy.size(); n++) {
			Enemy en = this.waveOfEnemy.get(n);
			en.move();
			if (en.getX() <= 400) {
				damage += en.current_health;
				this.waveOfEnemy.remove(en);
			}
		}
		return damage;
	}
	
	public ArrayList<Enemy> getList() {
		return this.waveOfEnemy;
	}
	
	public String waveNumber() {
		return "Wave: " + this.waveNumber;
	}
}
