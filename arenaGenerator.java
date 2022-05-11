package AsciiCrawler;
import java.util.*;
public class arenaGenerator {
	int length;
	int width;
	int startToFinish;
	arenaGenerator (int length, int width, int startToFinish) {
		this.length = length + 1;
		this.width = width + 1;
		this.startToFinish = startToFinish;
	}
	public Wall[] wallGen () {
		Wall[] ret = new Wall[(2 * (length + 1) + 2 * (width - 1)) - 6];
		//System.out.println(ret.length);
		int i = 0;
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < length; x++) {
				//if (!((startToFinish[0][0] == x && startToFinish[0][1] == y)||(startToFinish[1][0] == x && startToFinish[1][1] == y))) {
				if (y == 0 || y == width-1) {
					ret[i] = new Wall(x, y, "#"); i++;
					//System.out.println(i);
				} else if (y == startToFinish) {
					y++;
				}else if (x == 0 || x == length-1) {
					ret[i] = new Wall(x, y, "#"); i++;
				}
			}
		}
		ret[ret.length - 1] = new Wall(0, startToFinish + 1, "#");
		/*
		for (i = 0; i < ret.length; i ++){
			if (ret[i].x == -1){}
			System.out.print(i);
			System.out.print(" ");
		}
		*/
		return ret;
	}
	
	public Destructable[] obstacleGen (int tryGen) {
		Random r = new Random();
		Destructable[] ret = new Destructable[tryGen];
		for (int i = 0; i < tryGen; i++) {
			ret[i] = new Destructable(r.nextInt(length - 2) + 1, r.nextInt(width - 2) + 1, r.nextInt(4) + 1);
			if ((ret[i].y != length-1 || ret[i].y != 1) && r.nextInt(2) == 0) {
				ret[i].switchToTrap();
			}
		}
		return ret;
	}
	
	public Enemy[] enemyGen (int tryGen) {
		Random r = new Random();
		Enemy[] ret = new Enemy[tryGen];
		for (int i = 0; i < tryGen; i++) {
			ret[i] = new Enemy(r.nextInt(length - 3) + 2, r.nextInt(width - 2) + 1, "X");
		}
		return ret;
	}
}
