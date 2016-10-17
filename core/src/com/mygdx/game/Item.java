package com.mygdx.game;

public class Item {
	
	public static final int NOT_SPAWN = 0;
	public static final int IS_SPAWN = 1;
	public static final int IS_COLLECT = 2;

	private int itemType [][];
	private int itemState [][];

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
		itemState = new int [height][width];

		for(int r=0; r < height; r++) {
			for(int c=0; c < width; c++) {
				itemType[r][c] = 0;
				itemState[r][c] = NOT_SPAWN;
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
		return itemState[r][c] == IS_SPAWN;
	}

	public void collect(int r, int c) {
		itemState[r][c] = IS_COLLECT;
	}
	
	public void spawn(int r, int c) {
		if(hasItemAt(r, c) && itemState[r][c] ==  NOT_SPAWN) {
			itemState[r][c] = IS_SPAWN;
		}
	}
	
}
