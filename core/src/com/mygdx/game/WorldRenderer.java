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
	private BombermanRenderer bombermanRenderer3;
	private BombermanRenderer bombermanRenderer4;


	private CreepRenderer creepRenderer;

  private MazeRenderer mazeRenderer;
  private BombRenderer bombRenderer;
  private ItemRenderer itemRenderer;
  private StatusBarRenderer statusBarRenderer;

	public WorldRenderer(BombermanGame bombermanGame, World world) {
		this.bombermanGame = bombermanGame;
    batch = bombermanGame.batch;
    this.world = world; 

		bombermanRenderer = new BombermanRenderer(bombermanGame.batch, world.getBomberman());
		bombermanRenderer2 = new BombermanRenderer(bombermanGame.batch, world.getBomberman2());
		bombermanRenderer3 = new BombermanRenderer(bombermanGame.batch, world.getBomberman3());
		bombermanRenderer4 = new BombermanRenderer(bombermanGame.batch, world.getBomberman4());

		// creepRenderer = new CreepRenderer(bombermanGame.batch, world.getCreep());

    mazeRenderer = new MazeRenderer(bombermanGame.batch, world.getMaze());
    bombRenderer = new BombRenderer(bombermanGame.batch, world.getBomb());
    itemRenderer = new ItemRenderer(bombermanGame.batch, world.getItem());
    statusBarRenderer = new StatusBarRenderer(bombermanGame.batch, world);
	}
	
	public void render (float delta) {
    mazeRenderer.render();
    bombRenderer.render();
    itemRenderer.render();
    // creepRenderer.render();
    bombermanRenderer.render();
    bombermanRenderer2.render();
    bombermanRenderer3.render();
	  bombermanRenderer4.render();
	  statusBarRenderer.render();
	}


}
