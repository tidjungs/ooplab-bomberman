package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ItemRenderer {

  private SpriteBatch batch;
  private Item item;

  private Texture pwbImage;
  private Texture pwfImage;

	public ItemRenderer(SpriteBatch batch, Item item) {
		this.batch = batch;
		this.item = item;

		pwbImage = new Texture("BombPowerup.png");
		pwfImage = new Texture("FlamePowerup.png");
	}

	public void render() {
		batch.begin();
		for(int r=0; r < item.getHeight(); r++) {
			for(int c=0; c < item.getWidth(); c++) {
	        		
	        		int x = c * WorldRenderer.BLOCK_SIZE + 6;
	        		int y = BombermanGame.HEIGHT - (r * WorldRenderer.BLOCK_SIZE) - WorldRenderer.BLOCK_SIZE + 6;
	        		
	        		if (item.isSpawn(r, c) || item.isNotSpawn(r, c)) {
	     	        int type = item.getItemType(r, c);
	        			if(type == item.POWERUP_BOMP) {
	        				batch.draw(pwbImage, x, y);
	        			} else if (type == item.POWERUP_FRAME) {
	        				batch.draw(pwfImage, x, y);
	        			}
	        		}
			}     	
	  }
		batch.end();
	}
}
