package AsciiCrawler;
// to update use String.valueOf(number);
public class Enemy {
	public static void main (String[] Args){
	}
	int x;
	int y;
	//boolean alive = true;
	int life = 3;
	String enemyShape;
	Enemy (int x, int y, String shape) {
		this.x = x;
		this.y = y;
		enemyShape = shape;
	}
	public void move(int dir) {
		switch (dir) {
		case 0 : y--; break;
		case 1 : x++; break;
		case 2 : y++; break;
		case 3 : x--; break;
		}
	}
	public int[] selectSquare(int dir) {
		int[] xy = new int[]{x, y};
		switch (dir) {
		case 0 : xy[1]--; break;
		case 1 : xy[0]++; break;
		case 2 : xy[1]++; break;
		case 3 : xy[0]--; break;
		}
		return xy;
	}
	public int[] preFollow(Player P) {
		int changeX = P.x - x;
		int changeY = P.y - y;
		int[] xy = new int[] {x, y};
		if (Math.abs(changeX) > Math.abs(changeY)) {
			if (changeX > 0) {
				xy = selectSquare(1);
			} else if (changeX < 0) {
				xy = selectSquare(3);
			}
		} else {
			if (changeY > 0) {
				xy = selectSquare(2);
			} else if (changeY < 0) {
				xy = selectSquare(0);
			}
		}
		return xy;
	}
	public void follow(Player P){
		int changeX = P.x - x;
		int changeY = P.y - y;
		if (Math.abs(changeX) > Math.abs(changeY)) {
			if (changeX > 0) {
				move(1);
			} else if (changeX < 0) {
				move(3);
			}
		} else {
			if (changeY > 0) {
				move(2);
			} else if (changeY < 0) {
				move(0);
			}
		}
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
		return enemyShape;
	}
	public void kill() {
		alive = !alive;
	}
	*/
}
