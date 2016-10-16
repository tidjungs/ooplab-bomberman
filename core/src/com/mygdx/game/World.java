package com.mygdx.game;

public class World {
	private Bomberman bomberman;
    private BombermanGame bombermanGame;
    private Maze maze;
    private Bomb bomb;
    
    World(BombermanGame bombermanGame) {
    	this.bombermanGame = bombermanGame;
    	maze = new Maze();
    	bomb = new Bomb(maze, this);
    	bomberman = new Bomberman(60, 60, maze, bomb);
    }
    
    public Bomberman getBomberman() {
    	return bomberman;
    }
    
    public Maze getMaze() {
    	return maze;
    }
    
    public Bomb getBomb() {
    	return bomb;
    }
    
    public void update(float delta) {
    	if(bomberman.isAlive()) {
    		bomberman.update();
    	}
    }
    
    public void explode(int row, int col) {
    	if(bomberman.getRow() == row && bomberman.getCol() == col) {
    		bomberman.die();
    	}
    }
    
}
