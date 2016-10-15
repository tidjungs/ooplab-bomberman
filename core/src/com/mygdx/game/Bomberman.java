package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bomberman {
	private Vector2 postition;
	
	public Bomberman(int x, int y) {
		postition = new Vector2(x, y);
	}
	
	public Vector2 getPosition() {
		return postition;
	}
}
