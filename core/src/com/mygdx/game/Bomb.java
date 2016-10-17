package com.mygdx.game;

public class Bomb {
	private int [][] timeBomb;
	private int [][] fire;
	private int height;
	private int width;
	
	private Maze maze;
	
	private int BOMBAREA = 1;
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
		
		for(int r=0; r < height; r++) {
			for(int c=0; c < width; c++) {
				timeBomb[r][c] = 0;
				fire[r][c] = 0;
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
	
	public void newBomp(int row, int col) {
			timeBomb[row][col] = 150;
	}
	
	public void decreaseTimeBomb(int row, int col) {
		timeBomb[row][col]--;
		checkExplode(row, col);
	}
	
	public void decreaseFire(int row, int col) {
		fire[row][col]--;
	}
	
	public boolean canPassBomb(int row, int col) {
		return timeBomb[row][col] > 120 || timeBomb[row][col] == 0;
	}
	
	private void checkExplode(int row, int col) {
		if(timeBomb[row][col] == 0) {
			world.getBomberman().receivePlantBomp();
			for(int i=row-BOMBAREA; i<=row+BOMBAREA; i++) {
				for(int j=col-BOMBAREA; j<=col+BOMBAREA; j++) {
					if(i == row || j == col) {
						world.explode(i, j);
						if(!maze.hasWallAt(i, j)) {
							fire[i][j] = 50;
						}
					}
				}
			}
		}
	}
	
}
