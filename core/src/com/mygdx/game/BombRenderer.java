package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BombRenderer {
	
	private static final int FLAME_RENDER_RATE = 8;

	private Bomb bomb;
  private SpriteBatch batch;
  private Texture bombImage1;
  private Texture bombImage2;
  private Texture bombImage3;
  
  private Texture bombImage;
  
  private Texture fireImage1;
  private Texture fireImage2;
  private Texture fireImage3;
  private Texture fireImage4;
  private Texture fireImage5;

  
  private Texture fireImage;
  
  private int counter = 0;
  private int fireCounter = 0;

	public BombRenderer(SpriteBatch batch, Bomb bomb) {
		this.bomb = bomb;
    this.batch = batch;
    bombImage1 = new Texture("Bomb_f01.png");
    bombImage2 = new Texture("Bomb_f02.png");
    bombImage3 = new Texture("Bomb_f03.png");
    
    bombImage = bombImage1;
    
    fireImage1 = new Texture("Flame_f00.png");
    fireImage2 = new Texture("Flame_f01.png");
    fireImage3 = new Texture("Flame_f02.png");
    fireImage4 = new Texture("Flame_f03.png");
    fireImage5 = new Texture("Flame_f04.png");

    fireImage = fireImage1;
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
	        		
	        		if(bomb.hasFireAt(r, c)) {
	        			changFireImage();
	                    batch.draw(fireImage, x, y);
	                    bomb.decreaseFire(r, c);
	                    
	        		}
	        	
			}     	
	  }
		batch.end();
	}
	
	public void changFireImage() {

		switch(fireCounter) {
			case FLAME_RENDER_RATE*0 :
				fireImage = fireImage1;
				break;
		}

		if(fireCounter == FLAME_RENDER_RATE*0) {
			fireImage = fireImage1;
		} else if (fireCounter == FLAME_RENDER_RATE*1) {
			fireImage = fireImage2;
		} else if (fireCounter == FLAME_RENDER_RATE*2) {
			fireImage = fireImage3;
		} else if (fireCounter == FLAME_RENDER_RATE*3) {
			fireImage = fireImage4;
		} else if (fireCounter == FLAME_RENDER_RATE*4) {
			fireImage = fireImage5;
		}
		
		if(++fireCounter >= FLAME_RENDER_RATE*5) {
			fireCounter = 0;
		}
	}
	
	public void changeBombImage() {
		if(counter == 10) {
			bombImage = bombImage2;
		} else if (counter == 20) {
			bombImage = bombImage3;
		} else {
			bombImage = bombImage1;
		}
		
		if(counter++ >= 30) {
			counter = 0;
		}
	}
}
