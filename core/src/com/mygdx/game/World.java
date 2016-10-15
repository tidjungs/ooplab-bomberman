package com.mygdx.game;

public class World {
	private Bomberman bomberman;
    private BombermanGame bombermanGame;
    private Maze maze;
    
    World(BombermanGame bombermanGame) {
    	this.bombermanGame = bombermanGame;
    	maze = new Maze();
    	bomberman = new Bomberman(60, 60, maze);
    }
    
    Bomberman getBomberman() {
    	return bomberman;
    }
    
    Maze getMaze() {
    	return maze;
    }
    
    public void update(float delta) {
        bomberman.update();
    }
}
