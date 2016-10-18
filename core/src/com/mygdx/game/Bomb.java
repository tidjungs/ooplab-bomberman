package com.mygdx.game;

public class Bomb {
	private int [][] timeBomb;
	private int [][] fire;
	private int [][] owner;

	private int height;
	private int width;
	
	private Maze maze;
	
	private int BOMBAREA;
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
		
		for(int r=0; r < height; r++) {
			for(int c=0; c < width; c++) {
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
			timeBomb[row][col] = 150;
			owner[row][col] = player;
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
			
			if(owner[row][col] == 1) {
				world.getBomberman().receivePlantBomp();
				BOMBAREA = world.getBomberman().getBombArea();
			} else if (owner[row][col] == 2) {
				world.getBomberman2().receivePlantBomp();
				BOMBAREA = world.getBomberman2().getBombArea();
			}

			owner[row][col] = 0;

			for(int i=row; i>=row-BOMBAREA; i--) {
				
				if(maze.hasWallAt(i, col)) {
					break;
				}

				fire[i][col] = 50;

				if(maze.hasBoxAt(i, col)) {
					world.explode(i, col);
					break;
				}
			}

			for(int i=row; i<=row+BOMBAREA; i++) {

				if(maze.hasWallAt(i, col)) {
					break;
				}
				
				fire[i][col] = 50;

				if(maze.hasBoxAt(i, col)) {
					world.explode(i, col);
					break;
				}
			}

			for(int j=col; j>=col-BOMBAREA; j--) {
				
				if(maze.hasWallAt(row, j)) {
					break;
				}
				
				fire[row][j] = 50;
			
				if(maze.hasBoxAt(row, j)) {
					world.explode(row, j);
					break;
				}
			}

			for(int j=col; j<=col+BOMBAREA; j++) {
				
				if(maze.hasWallAt(row, j)) {
					break;
				}
				
				fire[row][j] = 50;

				if(maze.hasBoxAt(row, j)) {
					world.explode(row, j);
					break;
				}
			}

		}
	}

}
