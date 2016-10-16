package com.mygdx.game;

public class Bomb {
	private boolean [][] hasBomb;
	private int height;
	private int width;
	
	public Bomb(Maze maze) {
		height = maze.getHeight();
		width = maze.getWidth();
		initBombData();
	}
	
	private void initBombData() {
		hasBomb = new boolean[height][width];
		for(int r=0; r < height; r++) {
			for(int c=0; c < width; c++) {
				hasBomb[r][c] = false;
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
		return hasBomb[row][col];
	}
	
	public void plantBomp(int row, int col) {
		hasBomb[row][col] = true;
	}
}
