package AsciiCrawler;
import java.util.Scanner;
import java.util.Random;

public class AsciiMain {
	public static void main (String[] Args) {
		Scanner sn = new Scanner(System.in);
		System.out.println("Are you sure you would like to enter the dungeon?");
		System.out.println("Warning: Horrid Ascii graphics ahead.");
		String cont = sn.nextLine();
		boolean action = true;
		if (cont.equals("yes")) {
			System.out.println("This game is most totally best played in full screen. Recommended screen settings are 144p quality and dark. Most pleasureable play exprience nowhere.");
			System.out.println("Prepare thyself.");
			int length;
			int width; // AKA Height
			int score = 0;
			int difficulty = 0;
			int starterInfo;
			Player P = new Player(0, 0, "");
			Wall[] border;
			Enemy[] enemies;
			Destructable[] obstacles;
			Random r = new Random();
			arenaGenerator arena;
			printable[] printout;
			while (P.life > 0) {
				//Arena Initialization
				length = r.nextInt(10) + 20 + 2*difficulty;
				width = r.nextInt(5) + 7 + difficulty;
				//System.out.println(length);
				//System.out.println(width);
				starterInfo = (width + (width)%2)/2;
				arena = new arenaGenerator(length, width, starterInfo);
				border = new Wall[arena.wallGen().length + 1];
				for (int i = 0; i < arena.wallGen().length; i++){border[i] = arena.wallGen()[i];}
				border[border.length - 1] = new Wall(length, starterInfo,"}");
				obstacles = arena.obstacleGen(r.nextInt(5)+difficulty*difficulty+1);
				enemies = arena.enemyGen((difficulty - difficulty%2) * (difficulty - difficulty%2) + 1);
				P = new Player(0, starterInfo, "O");
				boolean Cont = true;
				//Playing loop
				while (!(P.x == length - 1 && P.y == starterInfo)) {
					//change
					if (P.life <= 0) {
						break;
					}
					String move = sn.nextLine();
					if (collider(score, P.selectSquarePlayer(move), new Player[0], enemies, border, obstacles)) {
						P.move(move);
					}
					Enemy[] custEnemy;
					if (action) {
						for (int i = 0; i < enemies.length; i++) {
							if (enemies[i].life > 0) {
								custEnemy = new Enemy[enemies.length-1];
								int e = 0;
								for (int a = 0; a < enemies.length; a++) {
									if (a != i) {
										custEnemy[e] = enemies[a];
										e++;
									}
								}
								if (collider(score, enemies[i].preFollow(P),new Player[]{P}, custEnemy, border, obstacles)) {
									enemies[i].follow(P);
								}
							}
						}
					}
					int lol;
					for (lol = 0; lol < enemies.length; lol++) {
						if (enemies[lol].life > 0){
							break;
						}
					}
					if (lol >= enemies.length) {
						border = arena.wallGen();
					}
					for (int i = 0; i < obstacles.length; i++) {
						obstacles[i].update();
					}
					// compilation into a printable[]
					//printout = new printable[border.length + enemies.length + obstacles.length + 1];
					printout = new printable[0];
					printout = printpend(printout, new printable(P.x, P.y, P.playerShape));
					for (int i = 0; i < border.length; i++) {
						printout = printpend(printout, new printable(border[i].x, border[i].y, border[i].shape));
					}
					for (int i = 0; i < enemies.length; i++) {
						if (enemies[i].life > 0) {
						printout = printpend(printout, new printable(enemies[i].x, enemies[i].y, enemies[i].enemyShape));
						}
					}
					for (int i = 0; i < obstacles.length; i ++) {
						printout = printpend(printout, new printable(obstacles[i].x, obstacles[i].y, obstacles[i].wallShape));
					}
					//output
					//System.out.println(P.life);
					System.out.print("Life : ");System.out.print(P.life); System.out.print(" Score : "); System.out.println(score);
					printBoard(printout, " ");
					action = !action;
				}
				if (P.life > 0) {
					difficulty++;
					score++;
				}
			}
			System.out.println("You died...");
			sn.nextLine();
		}else {
			System.out.println("I'm okay with that too...");
			sn.nextLine();
		}
		printGG();
	}
	public static printable[] printpend (printable[] P, printable p) {
		printable[] ret = new printable[P.length + 1];
		for (int i = 0; i < P.length; i++) {
			ret[i] = P[i];
		}
		ret[ret.length - 1] = p;
		return ret;
	}
	public static boolean collider(int score, int[] xy, Player[] P, Enemy[] enemies, Wall[] border, Destructable[] obstacles){
		// Includes killing
		boolean Cont = true;
		for (int i = 0; i < P.length; i++) {
			if(xy[0] == P[i].x && xy[1] == P[i].y){
				P[i].life--;
				Cont = false;
				break;
			}
		}
		for (int i = 0; i < enemies.length; i++){
			if (xy[0] == enemies[i].x && xy[1] == enemies[i].y && enemies[i].life > 0){
				enemies[i].life--;
				score++;
				System.out.println(enemies[i].life);
				Cont = false;
				break;
			}
		}
		//System.out.println(border.length);
		for (int i = 0; i < border.length; i++){
			if (xy[0] == border[i].x && xy[1] == border[i].y) {
				Cont = false;
				break;
			}
		}

		for (int i = 0; i < obstacles.length; i++){
			if(xy[0] == obstacles[i].x && xy[1] == obstacles[i].y && obstacles[i].life > 1){
				obstacles[i].life--;
				obstacles[i].update();
				score++;
				Cont = false;
				break;
			}
		}
		return Cont;
	}
	public static void printBoard(printable[] p, String Filler){
		int [] printY = new int[p.length];
		int [] printX = new int[p.length];
		for (int i = 0; i < p.length; i ++) {
			printX[i] = p[i].x;
			printY[i] = p[i].y;
		}
		for (int y = 0; y <= findBounds(printY); y++) {
			for (int x = 0; x <= findBounds(printX); x++) {
				int i = 0;
				
				while (i < p.length) {
					if (p[i].x  == x && p[i].y == y) {
						System.out.print(p[i].print);
						break;
					}
					i++;
				}
				
				if (i >= p.length) {
					System.out.print(Filler);
				}
			}
			System.out.println();
		}
	}
	public static int findBounds (int[] compList) {
		int checkOne = 0;
		int f = checkOne + 1;
		while (f != compList.length) {
			while (f < compList.length) {
				if (compList[checkOne] < compList[f]) {
					checkOne = f;
					break;
				}
				f++;
			}
		}
		return compList[checkOne];
	}
	public static void printGG () {
		for (int Z = 1; Z < 143; Z++) {
			System.out.println();
		}
		
		for (int X = 1; X < 59; X++) {
			System.out.print(" ");
		} System.out.println("  GGGGGG         AA       MM       MM  EEEEEEEEEE");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("GG      G       A  A      M M     M M  E");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("G              A    A     M   M M   M  EEEEEE");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("G     GGGGG   AAAAAAAA    M    M    M  E");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("GG      GG   A        A   M         M  E");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("  GGGGGG    A          A  M         M  EEEEEEEEEE");
		
		System.out.println();
		System.out.println();
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("  OOOOOO    V          V   EEEEEEEEEE  RRRRRR");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("OO      OO   V        V    E           R      R");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("O        O    V      V     EEEEEE      R      R");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("O        O     V    V      E           RRRRRR");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("OO      OO      V  V       E           R     R");
		
		for (int W = 1; W < 59; W++) {
			System.out.print(" ");
		} System.out.println("  OOOOOO         VV        EEEEEEEEEE  R      R");
		
		
		for (int Y = 1; Y < 23;Y++) {
			System.out.println();
		}
	}
}