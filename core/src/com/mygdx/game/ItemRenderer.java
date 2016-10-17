package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ItemRenderer {

  private SpriteBatch batch;
  private Item item;

  private Texture itemImage;

	public ItemRenderer(SpriteBatch batch, Item item) {
		this.batch = batch;
		this.item = item;

		itemImage = new Texture("BombPowerup.png");
	}

	public void render() {
		batch.begin();
		for(int r=0; r < item.getHeight(); r++) {
			for(int c=0; c < item.getWidth(); c++) {
	        		
	        		int x = c * WorldRenderer.BLOCK_SIZE;
	        		int y = BombermanGame.HEIGHT - (r * WorldRenderer.BLOCK_SIZE) - WorldRenderer.BLOCK_SIZE;
	        		
	        		if (item.hasItemAt(r, c)) {
	        			batch.draw(itemImage, x, y);
	        		}
			}     	
	  }
		batch.end();
	}
}
