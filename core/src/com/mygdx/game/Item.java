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

	private static final int NUMBER_OF_POWERUP_BOMP= 10;
	private static final int NUMBER_OF_POWERUP_FRAME= 6;

	public static final int POWERUP_BOMP= 11;
	public static final int POWERUP_FRAME= 12;

	public Item(Maze maze) {
		this.maze = maze;
		
		height = maze.getHeight();
		width = maze.getWidth();
		
		initialItem();

		randomSpawnPoint(NUMBER_OF_POWERUP_BOMP, POWERUP_BOMP);
		randomSpawnPoint(NUMBER_OF_POWERUP_FRAME, POWERUP_FRAME);

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

	private void randomSpawnPoint(int numberOfItem, int type) {
		int itemLeft = numberOfItem;
		while(itemLeft > 0) {
			
			int rowRandom = (int)(Math.random() * height);
			int colRandom = (int)(Math.random() * width);
			
			if (!hasItemAt(rowRandom, colRandom) && maze.hasBoxAt(rowRandom, colRandom)) {
				itemType[rowRandom][colRandom] = type;
				itemLeft--;
			}
		}
	}

	public boolean hasItemAt(int r, int c) {
		return itemType[r][c] == POWERUP_BOMP|| itemType[r][c] == POWERUP_FRAME;
	}

	public boolean isSpawn(int r, int c) {
		return itemState[r][c] == IS_SPAWN;
	}

	public boolean isNotSpawn(int r, int c) {
		return itemState[r][c] == NOT_SPAWN;
	}


	public void collect(int r, int c) {
		itemState[r][c] = IS_COLLECT;
	}

	public int getItemType(int r, int c) {
		return itemType[r][c];
	}
	
	public void spawn(int r, int c) {
		if(hasItemAt(r, c) && itemState[r][c] ==  NOT_SPAWN) {
			itemState[r][c] = IS_SPAWN;
		}
	}
	
}
