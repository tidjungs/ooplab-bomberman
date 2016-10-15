package com.mygdx.game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private BombermanGame bombermanGame;
	private Texture bombermanImg;
	
	public GameScreen(BombermanGame bombermanGame) {
		this.bombermanGame = bombermanGame;
		bombermanImg = new Texture("bomberman.png");
	}
	
	public void render (float delta) {
        SpriteBatch batch = bombermanGame.batch;
        batch.begin();
        batch.draw(bombermanImg, 100, 100);
        batch.end();
	}
}
