package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	BombermanGame bombermanGame;
	Bomberman bomberman;
	World world;
	SpriteBatch batch;
	Texture bombermanImg;
	
	public WorldRenderer(BombermanGame bombermanGame, World world) {
		this.bombermanGame = bombermanGame;
        batch = bombermanGame.batch;
        this.world = world; 
		bombermanImg = new Texture("bomberman.png");
		bomberman = world.getBomberman();
	}
	
	public void render (float delta) {
        batch.begin();
        Vector2 pos = bomberman.getPosition();
        batch.draw(bombermanImg, pos.x, pos.y);
        batch.end();
	}
}