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
	private Bomberman bomberman2;

	private Bomb bomb;
	World world;
	WorldRenderer worldRenderer;
	
	public GameScreen(BombermanGame bombermanGame) {
		this.bombermanGame = bombermanGame;
        world = new World(bombermanGame);
        worldRenderer = new WorldRenderer(bombermanGame, world);
        bomberman = world.getBomberman();
        bomberman2 = world.getBomberman2();
        bomb = world.getBomb();
	}
	
	public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        worldRenderer.render(delta);
	}
	
	private void update(float delta) {
        updateBombermanDirection();
        updateBomberManActivity();
        world.update(delta);
	}
	
	private void updateBomberManActivity() {
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			bomberman.plantBomp();
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			bomberman2.plantBomp();	
		}
	}
	
	private void updateBombermanDirection() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			bomberman.setNextDirection(Bomberman.DIRECTION_UP);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			bomberman.setNextDirection(Bomberman.DIRECTION_RIGHT);
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			bomberman.setNextDirection(Bomberman.DIRECTION_DOWN);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			bomberman.setNextDirection(Bomberman.DIRECTION_LEFT);
		} else {
			bomberman.setNextDirection(Bomberman.DIRECTION_STILL);
		} 
		
		if (Gdx.input.isKeyPressed(Keys.W)) {
			bomberman2.setNextDirection(Bomberman.DIRECTION_UP);
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			bomberman2.setNextDirection(Bomberman.DIRECTION_RIGHT);
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			bomberman2.setNextDirection(Bomberman.DIRECTION_DOWN);
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			bomberman2.setNextDirection(Bomberman.DIRECTION_LEFT);
		} else {
			bomberman2.setNextDirection(Bomberman.DIRECTION_STILL);
		} 
		
	}
	
}
