package com.mygdx.game;

public class World {
	private Bomberman bomberman;
	private Bomberman bomberman2;

    private BombermanGame bombermanGame;
    private Maze maze;
    private Bomb bomb;
    
    World(BombermanGame bombermanGame) {
    	this.bombermanGame = bombermanGame;
    	maze = new Maze();
    	bomb = new Bomb(maze, this);
    	bomberman = new Bomberman(60, 60, maze, bomb);
    	bomberman2 = new Bomberman(700, 460, maze, bomb);
    }
    
    public Bomberman getBomberman() {
    	return bomberman;
    }
    
    public Bomberman getBomberman2() {
    	return bomberman2;
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
    	    	
    	if(bomberman2.isAlive()) {
    		bomberman2.update();
    	}
    }
    
}
