package com.mygdx.game;

public class Bomb {
	private int [][] timeBomb;
	private int height;
	private int width;
	private Maze maze;
	
	private int BOMBAREA = 1;
	
	public Bomb(Maze maze) {
		this.maze = maze;
		height = maze.getHeight();
		width = maze.getWidth();
		initBombData();
	}
	
	private void initBombData() {
		timeBomb = new int [height][width];
		
		for(int r=0; r < height; r++) {
			for(int c=0; c < width; c++) {
				timeBomb[r][c] = 0;
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
	
	public void plantBomp(int row, int col) {
		timeBomb[row][col] = 150;
	}
	
	public void decreaseTimeBomb(int row, int col) {
		timeBomb[row][col]--;
		checkExplode(row, col);
	}
	
	public boolean canPassBomb(int row, int col) {
		return timeBomb[row][col] > 120 || timeBomb[row][col] == 0;
	}
	
	private void checkExplode(int row, int col) {
		if(timeBomb[row][col] == 0) {
			for(int i=row-BOMBAREA; i<=row+BOMBAREA; i++) {
				for(int j=col-BOMBAREA; j<=col+BOMBAREA; j++) {
					if(i == row || j == col) {
						maze.explodeBox(i, j);
					}
				}
			}
		}
	}
	
	
}
