package com.mygdx.game;

public class World {
	private Bomberman bomberman;
	private Bomberman bomberman2;
    private Bomberman bomberman3;
    private Bomberman bomberman4;

    private Creep creep;

    private BombermanGame bombermanGame;
    private Maze maze;
    private Bomb bomb;
    private Item item;
    
    World(BombermanGame bombermanGame) {
    	this.bombermanGame = bombermanGame;
    	maze = new Maze();
    	bomb = new Bomb(maze, this);
        item = new Item(maze);
    	bomberman = new Bomberman(60, 60, maze, bomb, item, 1);
    	bomberman2 = new Bomberman(700, 460, maze, bomb, item, 2);
        bomberman3 = new Bomberman(60, 460, maze, bomb, item, 3);
        bomberman4 = new Bomberman(700, 60, maze, bomb, item, 4);

        // creep = new Creep(380, 260);
    }
    
    public Bomberman getBomberman() {
    	return bomberman;
    }
    
    public Bomberman getBomberman2() {
    	return bomberman2;
    }

    public Bomberman getBomberman3() {
        return bomberman3;
    }

    public Bomberman getBomberman4() {
        return bomberman4;
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

    // public Creep getCreep() {
    //     return creep;
    // }
    
    public void update(float delta) {
    	if(bomberman.isAlive()) {
    		bomberman.update();
    	}
    	    	
    	if(bomberman2.isAlive()) {
    		bomberman2.update();
    	}

        if(bomberman3.isAlive()) {
            bomberman3.update();
        }

        if(bomberman4.isAlive()) {
            bomberman4.update();
        }
    }
    
    public void explode(int r, int c) {
        if(maze.hasBoxAt(r, c)) {
            item.spawn(r, c);
        }
    	maze.explodeBox(r, c);
    }
    
}
