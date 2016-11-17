package com.mygdx.game;

public class Bomb {
	private static final int BOMB_TIME = 150;
	private static final int BOMB_STRONG_TIME = 120;
	private static final int FRAME_TIME = 40;

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

	private boolean isOutOfLength(int row, int col) {
		return row >= height - 1 || col >= width - 1 || row < 0 || col < 0;
	}


	private void setRowFireTarget(int row, int col, int dir) {
		for(int i=1; i<=bombArea; i++) {
			
			if (maze.hasWallAt(row + dir*i, col)) {
				break;
			}

			fire[row + dir*i][col] = FRAME_TIME;

			if (maze.hasBoxAt(row + dir*i, col)) {
				world.explode(row + dir*i, col);
				break;
			}
		}
	} 

	private void setColFireTarget(int row, int col, int dir) {
		for(int i=1; i<=bombArea; i++) {
			
			if (maze.hasWallAt(row, col + dir*i)) {
				break;
			}

			fire[row][col + dir*i] = FRAME_TIME;

			if (maze.hasBoxAt(row, col + dir*i)) {
				world.explode(row , col + dir*i);
				break;
			}
		}
	} 

	private void setFireTarget(int row, int col) {
		fire[row][col] = FRAME_TIME;
		setRowFireTarget(row, col, -1);
		setRowFireTarget(row, col, +1);
		setColFireTarget(row ,col, -1);
		setColFireTarget(row ,col, +1);
	}

	private boolean isTimeBombOver(int row, int col) {
		return timeBomb[row][col] == 0;
	}

	private void checkExplode(int row, int col) {
		if (isTimeBombOver(row, col)) {
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
