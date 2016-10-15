package com.mygdx.game;

public class World {
	private Bomberman bomberman;
    private BombermanGame bombermanGame;
    
    World(BombermanGame bombermanGame) {
    	this.bombermanGame = bombermanGame;
    	bomberman = new Bomberman(100, 100);
    }
    
    Bomberman getBomberman() {
    	return bomberman;
    }
}
