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
    private MazeRenderer mazeRenderer;

	public WorldRenderer(BombermanGame bombermanGame, World world) {
		this.bombermanGame = bombermanGame;
        batch = bombermanGame.batch;
        this.world = world; 
		bombermanImg = new Texture("bomberman.png");
		bomberman = world.getBomberman();
        mazeRenderer = new MazeRenderer(bombermanGame.batch, world.getMaze());
	}
	
	public void render (float delta) {
        mazeRenderer.render();
        Vector2 pos = bomberman.getPosition();
        batch.begin();
        batch.draw(bombermanImg, pos.x - 20, BombermanGame.HEIGHT - pos.y - 20);
        batch.end();
	}
}
