package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BombRenderer {
	
	private Bomb bomb;
    private SpriteBatch batch;
    private Texture bombImage1;
    private Texture bombImage2;
    private Texture bombImage3;
    
    private Texture bombImage;
    
    private int counter = 0;
	
	public BombRenderer(SpriteBatch batch, Bomb bomb) {
		this.bomb = bomb;
        this.batch = batch;
        bombImage1 = new Texture("Bomb_f01.png");
        bombImage2 = new Texture("Bomb_f02.png");
        bombImage3 = new Texture("Bomb_f03.png");
        
        bombImage = bombImage1;
	}
	
	public void render() {
		changeBombImage();
		batch.begin();
		for(int r=0; r < bomb.getHeight(); r++) {
			for(int c=0; c < bomb.getWidth(); c++) {
	        		
	        		int x = c * WorldRenderer.BLOCK_SIZE;
	        		int y = BombermanGame.HEIGHT - (r * WorldRenderer.BLOCK_SIZE) - WorldRenderer.BLOCK_SIZE;
	        		
	        		if (bomb.hasBombAt(r, c)) {
	                    batch.draw(bombImage, x, y);
	                    bomb.decreaseTimeBomb(r, c);
	        		} 
	        	
			}     	
	    }
		batch.end();
	}
	
	public void changeBombImage() {
		if(counter == 10) {
			bombImage = bombImage2;
		} else if (counter == 20) {
			bombImage = bombImage3;
		} else {
			bombImage = bombImage1;
		}
		
		if(counter++ == 20) {
			counter = 0;
		}
	}
}
