package AsciiCrawler;
import java.util.Random;
public class Destructable {
	String ID = "REGULAR";
	int x;
	int y;
	int life = 3;
	String wallShape = "#";
	Destructable (int x, int y, int life) {
		this.life = life;
		this.x = x;
		this.y = y;
		update();
	}
	public void switchToTrap() {
		switch(new Random().nextInt(3)) {
		case (0) : ID = "BOMB";
		case (1) : ID = "ACID JAR";
		case (2) : ID = "LIFE";
		}
	}
	public void update() {
		if (life >= 3) {
			wallShape = "#";
		} else if (life <= 0) {
			wallShape = " ";
		}
		switch(life) {
		case 3 : wallShape = "#"; break;
		case 2 : wallShape = "+"; break;
		case 1 : wallShape = "^"; break;
		case 0 : wallShape = " "; break;
		}
	}

	/*
	public int[] getXY() {
		int[] ret = new int[2];
		ret[0] = x;
		ret[1] = y;
		return ret;
	}
	public String getShape() {
		return wallShape;
	}
	public void kill() {
		alive = !alive;
	}
	*/
}
