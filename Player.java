package AsciiCrawler;

public class Player {
	int x;
	int y;
	int life = 3;
	String playerShape;

	Player (int x, int y, String shape) {
		this.x = x;
		this.y = y;
		playerShape = shape;
	}

	public void move(String dir) {
		switch (dir) {
		case "w" : y--; break;
		case "d" : x++; break;
		case "s" : y++; break;
		case "a" : x--; break;
		}
	}
	
	public int[] selectSquarePlayer(String dir) {
		int[] xy = new int[]{x, y};
		switch (dir) {
		case "w" : xy[1]--; break;
		case "d" : xy[0]++; break;
		case "s" : xy[1]++; break;
		case "a" : xy[0]--; break;
		}
		return xy;
	}
	/*
	public void changeXY (int[] xy) {
		x = xy[0];
		y = xy[1];
	}
	public int[] getXY() {
		int[] ret = new int[2];
		ret[0] = x;
		ret[1] = y;
		return ret;
	}
	public String getShape() {
		return playerShape;
	}
	public void kill() {
		alive = !alive;
	}
	*/
}
