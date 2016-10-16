package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BombRenderer {
	
	private Bomb bomb;
    private SpriteBatch batch;
    private Texture bombImage;

	
	public BombRenderer(SpriteBatch batch, Bomb bomb) {
		this.bomb = bomb;
        this.batch = batch;
        bombImage = new Texture("bomb.png");
	}
	
	public void render() {
		batch.begin();
		for(int r=0; r < bomb.getHeight(); r++) {
			for(int c=0; c < bomb.getWidth(); c++) {
	        		
	        		int x = c * WorldRenderer.BLOCK_SIZE;
	        		int y = BombermanGame.HEIGHT - (r * WorldRenderer.BLOCK_SIZE) - WorldRenderer.BLOCK_SIZE;
	        		
	        		if (bomb.hasBombAt(r, c)) {
	                    batch.draw(bombImage, x, y);
	        		} 
	        	
			}     	
	    }
		batch.end();
	}
}
