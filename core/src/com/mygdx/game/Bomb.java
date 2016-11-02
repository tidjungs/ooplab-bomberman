package com.mygdx.game;

public class Bomb {
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

	private void checkExplode(int row, int col) {
		if(timeBomb[row][col] == 0) {
			
			Bomberman bomberman = getBomberman(owner[row][col]);
			bomberman.receivePlantBomp();
			bombArea = bomberman.getBombArea();
			owner[row][col] = 0;

			for(int i=row; i>=row-bombArea; i--) {
				
				if(maze.hasWallAt(i, col)) {
					break;
				}

				fire[i][col] = 50;

				if(maze.hasBoxAt(i, col)) {
					world.explode(i, col);
					break;
				}
			}

			for(int i=row; i<=row+bombArea; i++) {

				if(maze.hasWallAt(i, col)) {
					break;
				}
				
				fire[i][col] = 50;

				if(maze.hasBoxAt(i, col)) {
					world.explode(i, col);
					break;
				}
			}

			for(int j=col; j>=col-bombArea; j--) {
				
				if(maze.hasWallAt(row, j)) {
					break;
				}
				
				fire[row][j] = 50;
			
				if(maze.hasBoxAt(row, j)) {
					world.explode(row, j);
					break;
				}
			}

			for(int j=col; j<=col+bombArea; j++) {
				
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
