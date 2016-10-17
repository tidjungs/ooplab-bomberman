package com.mygdx.game;

public class World {
	private Bomberman bomberman;
	private Bomberman bomberman2;

    private BombermanGame bombermanGame;
    private Maze maze;
    private Bomb bomb;
    private Item item;
    
    World(BombermanGame bombermanGame) {
    	this.bombermanGame = bombermanGame;
    	maze = new Maze();
    	bomb = new Bomb(maze, this);
    	bomberman = new Bomberman(60, 60, maze, bomb);
    	bomberman2 = new Bomberman(700, 460, maze, bomb);
    	item = new Item(maze);
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

    public Item getItem() {
        return item;
    }
    
    public void update(float delta) {
    	if(bomberman.isAlive()) {
    		bomberman.update();
    	}
    	    	
    	if(bomberman2.isAlive()) {
    		bomberman2.update();
    	}
    }
    
    public void explode(int r, int c) {
        if(maze.hasBoxAt(r, c)) {
            item.spawn(r, c);
        }
    	maze.explodeBox(r, c);
    }
    
}
