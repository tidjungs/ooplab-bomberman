package com.mygdx.game;

public class Bomb {
	private static final int BOMB_TIME = 150;
	private static final int BOMB_STRONG_TIME = 120;
	private static final int FRAME_TIME = 50;

	private int [][] timeBomb;
	private int [][] fire;
	private int [][] owner;

	private int height;
	private int width;
	
	private Maze maze;
	
	private int bombArea;
	private World world;
	
	public Bomb(Maze maze, World world) {
		this.maze = maze;
		this.world = world;
		height = maze.getHeight();
		width = maze.getWidth();
		initialData();
	}
	
	private void initialData() {
		timeBomb = new int [height][width];
		fire = new int [height][width];
		owner = new int [height][width];
		
		for (int r=0; r < height; r++) {
			for (int c=0; c < width; c++) {
				timeBomb[r][c] = 0;
				fire[r][c] = 0;
				owner[r][c] = 0;
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean hasBombAt(int row, int col) {
		return timeBomb[row][col] != 0;
	}
	
	public boolean hasFireAt(int row, int col) {
		return fire[row][col] != 0;
	}
	
	public int getTimeBomb(int row, int col) {
		return timeBomb[row][col];
	}
	
	public void newBomp(int row, int col, int player) {
			timeBomb[row][col] = BOMB_TIME;
			owner[row][col] = player;
	}
	
	private void decreaseTimeBomb(int row, int col) {
		timeBomb[row][col]--;
		checkExplode(row, col);
	}
	
	private void decreaseFire(int row, int col) {
		fire[row][col]--;
	}
	
	public boolean canPassBomb(int row, int col) {
		return timeBomb[row][col] > BOMB_STRONG_TIME || timeBomb[row][col] == 0;
	}
	
	private Bomberman getBomberman(int index) {
		switch(index) {
			case 1:
				return world.getBomberman();
			case 2:
				return world.getBomberman2();
			case 3:
				return world.getBomberman3();
			case 4:
				return world.getBomberman4();
			default:
				return null;
		}
	}

	private void setFireTarget(int row, int col) {
		for(int i=row; i>=row-bombArea; i--) {
			
			if(maze.hasWallAt(i, col)) {
				break;
			}

			fire[i][col] = FRAME_TIME;

			if(maze.hasBoxAt(i, col)) {
				world.explode(i, col);
				break;
			}
		}

		for(int i=row; i<=row+bombArea; i++) {

			if(maze.hasWallAt(i, col)) {
				break;
			}

			fire[i][col] = FRAME_TIME;

			if(maze.hasBoxAt(i, col)) {
				world.explode(i, col);
				break;
			}
		}

		for(int j=col; j>=col-bombArea; j--) {
			
			if(maze.hasWallAt(row, j)) {
				break;
			}
			
			fire[row][j] = FRAME_TIME;
		
			if(maze.hasBoxAt(row, j)) {
				world.explode(row, j);
				break;
			}
		}

		for(int j=col; j<=col+bombArea; j++) {
			
			if(maze.hasWallAt(row, j)) {
				break;
			}
			
			fire[row][j] = FRAME_TIME;

			if(maze.hasBoxAt(row, j)) {
				world.explode(row, j);
				break;
			}
		}
	}

	private void checkExplode(int row, int col) {
		if(timeBomb[row][col] == 0) {
			
			Bomberman bomberman = getBomberman(owner[row][col]);
			bomberman.receivePlantBomp();
			bombArea = bomberman.getBombArea();
			owner[row][col] = 0;
			setFireTarget(row, col);

		}
	}

	public void update() {
			
		for (int r=0; r < height; r++) {
			for (int c=0; c < width; c++) {
				
				if (hasBombAt(r, c)) {
					decreaseTimeBomb(r, c);
				}

				if (hasFireAt(r, c)) {
					decreaseFire(r, c);
				}

			}
		}

	}

}
