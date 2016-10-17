package com.mygdx.game;

public class Item {
	
	private int itemType [][];
	private boolean isItemSpawn [][];
	
	private int height;
	private int width;

	private Maze maze;

	private static final int NUMBEROFITEM = 60;
	
	public Item(Maze maze) {
		this.maze = maze;
		
		height = maze.getHeight();
		width = maze.getWidth();
		
		initialItem();
		randomSpawnPoint();
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void initialItem() {
		itemType = new int [height][width];
		isItemSpawn = new boolean [height][width];

		for(int r=0; r < height; r++) {
			for(int c=0; c < width; c++) {
				itemType[r][c] = 0;
				isItemSpawn[r][c] = false;
			}
		}
	}

	private void randomSpawnPoint() {
		int itemLeft = NUMBEROFITEM;
		while(itemLeft > 0) {
			
			int rowRandom = (int)(Math.random() * height);
			int colRandom = (int)(Math.random() * width);
			
			if (!hasItemAt(rowRandom, colRandom) && maze.hasBoxAt(rowRandom, colRandom)) {
				itemType[rowRandom][colRandom] = 1;
				itemLeft--;
			}
		}
	}

	public boolean hasItemAt(int r, int c) {
		return itemType[r][c] == 1;
	}

	public boolean isSpawn(int r, int c) {
		return isItemSpawn[r][c];
	}
	
	public void spawn(int r, int c) {
		if(hasItemAt(r, c) && !isItemSpawn[r][c]) {
			isItemSpawn[r][c] = true;
		}
	}
	
}
