package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	
	public static final int BLOCK_SIZE = 40;
	
	private BombermanGame bombermanGame;
	private Bomberman bomberman;

	private World world;
	private SpriteBatch batch;

	private BombermanRenderer bombermanRenderer;
	private BombermanRenderer bombermanRenderer2;

  private MazeRenderer mazeRenderer;
  private BombRenderer bombRenderer;
  private ItemRenderer itemRenderer;

	public WorldRenderer(BombermanGame bombermanGame, World world) {
		this.bombermanGame = bombermanGame;
    batch = bombermanGame.batch;
    this.world = world; 

		bombermanRenderer = new BombermanRenderer(bombermanGame.batch, world.getBomberman());
		bombermanRenderer2 = new BombermanRenderer(bombermanGame.batch, world.getBomberman2());

    mazeRenderer = new MazeRenderer(bombermanGame.batch, world.getMaze());
    bombRenderer = new BombRenderer(bombermanGame.batch, world.getBomb());
    itemRenderer = new ItemRenderer(bombermanGame.batch, world.getItem());
	}
	
	public void render (float delta) {
    mazeRenderer.render();
    bombRenderer.render();
    itemRenderer.render();

    bombermanRenderer.render();
    bombermanRenderer2.render();

	}


}
