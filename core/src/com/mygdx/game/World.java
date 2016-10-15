package com.mygdx.game;

public class World {
	private Bomberman bomberman;
    private BombermanGame bombermanGame;
    private Maze maze;
    
    World(BombermanGame bombermanGame) {
    	this.bombermanGame = bombermanGame;
    	bomberman = new Bomberman(100, 100);
    	maze = new Maze();
    }
    
    Bomberman getBomberman() {
    	return bomberman;
    }
    
    Maze getMaze() {
    	return maze;
    }
}
