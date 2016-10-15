package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private BombermanGame bombermanGame;
	private Texture bombermanImg;
	private Bomberman bomberman;
	World world;
	WorldRenderer worldRenderer;
	
	public GameScreen(BombermanGame bombermanGame) {
		this.bombermanGame = bombermanGame;
        world = new World(bombermanGame);
        worldRenderer = new WorldRenderer(bombermanGame, world);
        bomberman = world.getBomberman();
	}
	
	public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        worldRenderer.render(delta);
	}
	
	private void update(float delta) {
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			bomberman.setNextDirection(Bomberman.DIRECTION_UP);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			bomberman.setNextDirection(Bomberman.DIRECTION_RIGHT);
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			bomberman.setNextDirection(Bomberman.DIRECTION_DOWN);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			bomberman.setNextDirection(Bomberman.DIRECTION_LEFT);
		}
	}
	
}
