package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bomberman {
	private Vector2 position;
	
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_STILL = 0;
	public static final int GHOST_WALK_LIMIT_TIME = 800;

	
	private static final int [][] DIR_OFFSETS = new int [][] {
		{0, 0},
		{0, -1},
		{1, 0},
		{0, 1},
		{-1, 0}
	};
	
	private static final double SPEED = 2.5;

	private int currentDirection;
	private int nextDirection;
		
	private Maze maze;
	private Bomb bomb;
	private Item item;
	
	private boolean alive = true;
	private boolean ghostWalk = false;
	private int ghostWalkTime = 0;

	private int bombLimit = 1;
	private int bombPlant = 0;

	private int bombArea = 1;

	private int player;
	private int delay = 0;
		
	public Bomberman(int x, int y, Maze maze, Bomb bomb, Item item, int player) {
		position = new Vector2(x, y);
		currentDirection = DIRECTION_STILL;
    nextDirection = DIRECTION_STILL;
    this.maze = maze;
    this.bomb = bomb;
    this.item = item;
    this.player = player;
	}
	
	public int getCurrentDirection() {
		return currentDirection;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public int getGhostWalkTimeLeft() {
		return ghostWalkTime;
	}
	
	public void move(int dir) {
		position.x += SPEED * DIR_OFFSETS[dir][0];
		position.y += SPEED * DIR_OFFSETS[dir][1];
	}
	
	public void setNextDirection(int dir) {
    nextDirection = dir;
	}
	
	public void update() {
		// check dead //
		if(isTouchingFire() || (maze.hasBoxAt(getRow(), getCol()) && !ghostWalk)) {
			die();
		}

		if(isAtFirstPortal()) {
			warp(+15);
		} else if (isAtSecondPortal()) {
			warp(-14);
		}

		if(isTouchingItem()) {
			collectItem();
		}

		if (isAtCenter()) {
			if(canMoveInDirection(nextDirection)) {
	      currentDirection = nextDirection;
			} else {
	      currentDirection = DIRECTION_STILL;
			}
		}

		move(currentDirection);

		// ghost walk //
		if (ghostWalk) {
			if (--ghostWalkTime <= 0) {
				ghostWalk = false;
			}
		}


	}	

	private void warp(int dir) {
			position.x = (getCol() + dir) * WorldRenderer.BLOCK_SIZE;
	}

	private boolean isAtFirstPortal() {
		return maze.hasFirstPortalAt(getRow(), getCol());
	}

	private boolean isAtSecondPortal() {
		return maze.hasSecondPortalAt(getRow(), getCol());
	}
	
	public boolean isAtCenter() {
    int blockSize = WorldRenderer.BLOCK_SIZE;
    return ((((int)position.x - blockSize/2) % blockSize) == 0) &&
            ((((int)position.y - blockSize/2) % blockSize) == 0);
	}
	
	private boolean canMoveInDirection(int dir) {
		int newRow = getRow() + DIR_OFFSETS[dir][1];
		int newCol = getCol() + DIR_OFFSETS[dir][0];
		return !maze.hasWallAt(newRow, newCol) && bomb.canPassBomb(newRow, newCol) && (!maze.hasBoxAt(newRow, newCol) || ghostWalk);
	}
	
	public int getRow() {
    return ((int)position.y) / WorldRenderer.BLOCK_SIZE; 
	}
	
	public int getCol() {
    return ((int)position.x) / WorldRenderer.BLOCK_SIZE; 
  }
	
	public void plantBomp() {

		int r = getRow();
		int c = getCol();

		if (canBombAgain() && !bomb.hasBombAt(r, c) && isAlive() && !maze.hasBoxAt(getRow(), getCol())) {
			bomb.newBomp(r, c, player);
			bombPlant++;
		}
	}
	
	public boolean isTouchingFire() {
		return bomb.hasFireAt(getRow(), getCol());
	}
	
	public void die() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	private boolean canBombAgain() {
		return bombPlant < bombLimit;
	}
	
	public void receivePlantBomp() {
		bombPlant--;
	}

	public boolean isTouchingItem() {
		return item.isSpawn(getRow(), getCol());
	}

	public int getBombArea() {
		return bombArea;
	}

	public int getBombLimit() {
		return bombLimit;
	}

	public void collectItem() {

		int r = getRow();
		int c = getCol();

		item.collect(r, c);
		int type = item.getItemType(r, c);
		if (type == item.POWERUP_BOMP) {
			bombLimit++;
		} else if (type == item.POWERUP_FRAME) {
			bombArea++;
		} else if (type == item.POWERUP_GHOST_WALK) {
			ghostWalk = true;
			ghostWalkTime = GHOST_WALK_LIMIT_TIME;
		}
	}
}
