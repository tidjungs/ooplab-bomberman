package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bomberman {
	private Vector2 postition;
	
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 3;
	public static final int DIRECTION_LEFT = 4;
	public static final int DIRECTION_STILL = 0;
	
	public Bomberman(int x, int y) {
		postition = new Vector2(x, y);
	}
	
	public Vector2 getPosition() {
		return postition;
	}
	
	public void move(int dir) {
		switch(dir) {
			case DIRECTION_UP:
				postition.y -= 10;
				break;
			case DIRECTION_RIGHT:
				postition.x += 10;
				break;
			case DIRECTION_DOWN:
				postition.y += 10;
				break;
			case DIRECTION_LEFT:
				postition.x -= 10;
				break;
		}
	}
}
