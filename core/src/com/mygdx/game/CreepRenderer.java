package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CreepRenderer {

	private SpriteBatch batch;
	private Creep creep;
	private Texture creepImg;

	public CreepRenderer(SpriteBatch batch, Creep creep) {
		this.batch = batch;
		this.creep = creep;
		creepImg = new Texture("Creep_F_f00.png");
	}

	public void render() {
		Vector2 pos = creep.getPosition();

		batch.begin();
			batch.draw(creepImg, pos.x - WorldRenderer.BLOCK_SIZE/2, BombermanGame.HEIGHT - pos.y - WorldRenderer.BLOCK_SIZE/2);
		batch.end();
	}
}