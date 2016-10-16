package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bomberman {
	private Vector2 position;
	
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_STILL = 0;
	
	private static final int [][] DIR_OFFSETS = new int [][] {
		{0, 0},
		{0, -1},
		{1, 0},
		{0, 1},
		{-1, 0}
	};
	
	public static final int SPEED = 5;
	
	private int currentDirection;
	private int nextDirection;
		
	private Maze maze;
	private Bomb bomb;
	
	private boolean alive = true;
		
	public Bomberman(int x, int y, Maze maze, Bomb bomb) {
		position = new Vector2(x, y);
		currentDirection = DIRECTION_STILL;
        nextDirection = DIRECTION_STILL;
        this.maze = maze;
        this.bomb = bomb;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void move(int dir) {
		position.x += SPEED * DIR_OFFSETS[dir][0];
		position.y += SPEED * DIR_OFFSETS[dir][1];
	}
	
	public void setNextDirection(int dir) {
        nextDirection = dir;
	}
	
	public void update() {
		if (isAtCenter()) {
			if(canMoveInDirection(nextDirection)) {
	            currentDirection = nextDirection;
			} else {
	            currentDirection = DIRECTION_STILL;
			}
		}
        position.x += SPEED * DIR_OFFSETS[currentDirection][0];
        position.y += SPEED * DIR_OFFSETS[currentDirection][1];
	}
	
	public boolean isAtCenter() {
        int blockSize = WorldRenderer.BLOCK_SIZE;
        return ((((int)position.x - blockSize/2) % blockSize) == 0) &&
                ((((int)position.y - blockSize/2) % blockSize) == 0);
	}
	
	private boolean canMoveInDirection(int dir) {
		int newRow = getRow() + DIR_OFFSETS[dir][1];
		int newCol = getCol() + DIR_OFFSETS[dir][0];
		return !maze.hasWallAt(newRow, newCol) && !maze.hasBoxAt(newRow, newCol) && bomb.canPassBomb(newRow, newCol);
	}
	
	public int getRow() {
        return ((int)position.y) / WorldRenderer.BLOCK_SIZE; 
	}
	
	public int getCol() {
        return ((int)position.x) / WorldRenderer.BLOCK_SIZE; 
    }
	
	public void plantBomp() {
		bomb.newBomp(getRow(), getCol());
	}
	
	public void die() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
}
