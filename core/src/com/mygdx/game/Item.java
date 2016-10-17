package com.mygdx.game;

public class Item {
	
	private int item [][];
	
	private int height;
	private int width;
	
	public Item(Maze maze) {
		height = maze.getHeight();
		width = maze.getWidth();
		initialItem();
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void initialItem() {
		item = new int [height][width];
		
		for(int r=0; r < height; r++) {
			for(int c=0; c < width; c++) {
				item[r][c] = 0;
			}
		}
	}

	public boolean hasItemAt(int r, int c) {
		return item[r][c] == 1;
	}
	
	public void spawn(int r, int c) {
		item[r][c] = 1;
	}
	
}
