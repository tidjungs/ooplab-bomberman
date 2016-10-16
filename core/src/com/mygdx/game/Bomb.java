package com.mygdx.game;

public class Bomb {
	private int [][] timeBomb;
	private int height;
	private int width;
	
	public Bomb(Maze maze) {
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
		timeBomb[row][col] = 300;
	}
	
	public void decreaseTimeBomb(int row, int col) {
		timeBomb[row][col]--;
	}
	
}
