package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BombermanRenderer {

	private static final int DELAY_LENGTH = 4;
	private static final int BOMBERMAN_IMG_PER_POSITION = 8;
	// private static final int DEAD_RENDER_DELAY_LENGTH = 500;


	private SpriteBatch batch;
	private Bomberman bomberman;

	private Texture [] bombermanFrontImg;
	private Texture [] bombermanBackImg;
	private Texture [] bombermanLeftImg;
	private Texture [] bombermanRightImg;
	private Texture bombermanImg;
	private Texture bombermanDeadImg;

	private int lastDirection;
  private int currentDirection;
  private int movingDelay = 0;
  // private int deadRenderDelay = 0;

  private String fileName;

	public BombermanRenderer(SpriteBatch batch, Bomberman bomberman) {
		this.batch = batch;
		this.bomberman = bomberman;
		inintialBombermanTexure();
	}

	public void inintialBombermanTexure() {
		bombermanFrontImg = new Texture [BOMBERMAN_IMG_PER_POSITION];
    bombermanBackImg = new Texture [BOMBERMAN_IMG_PER_POSITION];
    bombermanLeftImg = new Texture [BOMBERMAN_IMG_PER_POSITION];
    bombermanRightImg = new Texture [BOMBERMAN_IMG_PER_POSITION];

    getBombermanImage('F', bombermanFrontImg);
    getBombermanImage('B', bombermanBackImg);
    getBombermanImage('L', bombermanLeftImg);
    getBombermanImage('R', bombermanRightImg);

    bombermanDeadImg = new Texture("BmanDead.png");
    bombermanImg = bombermanFrontImg[0];    
	}

	public void getBombermanImage(char pos, Texture [] texture) {
    for(int i=0; i<BOMBERMAN_IMG_PER_POSITION; i++) {
    	fileName = String.format("Bman_%c_f0%d.png",pos, i);
    	texture[i] = new Texture(Gdx.files.internal(fileName));
    }
	}

	public void renderBombermanByDirection() {

		currentDirection = bomberman.getCurrentDirection();

		if(currentDirection == bomberman.DIRECTION_UP) {
			renderBombermanByDelay(bombermanBackImg);
		} else if (currentDirection == bomberman.DIRECTION_RIGHT) {
			renderBombermanByDelay(bombermanRightImg);
		} else if (currentDirection == bomberman.DIRECTION_DOWN) {
			renderBombermanByDelay(bombermanFrontImg);
		} else if (currentDirection == bomberman.DIRECTION_LEFT) {
			renderBombermanByDelay(bombermanLeftImg);
		}

		updatedMovingDelay();
		lastDirection = currentDirection;

	}


	private void renderBombermanByDelay(Texture [] texture) {
		for(int i=0; i<8; i++) {
			if(movingDelay < DELAY_LENGTH*(i)) {
				bombermanImg = texture[i];
				break;
			}
		}
	}

	private void updatedMovingDelay() {
		movingDelay++;
		if(movingDelayIsMaximun() || directionChange()) {
			movingDelay = 0;
		}	
	}

	private boolean directionChange() {
		return lastDirection != currentDirection;
	}

	private boolean movingDelayIsMaximun() {
		return movingDelay > BOMBERMAN_IMG_PER_POSITION * DELAY_LENGTH;
	}

	public void render () {
    Vector2 pos = bomberman.getPosition();
    batch.begin();

    if (bomberman.isAlive()) {
    	renderBombermanByDirection();
			batch.draw(bombermanImg, pos.x - WorldRenderer.BLOCK_SIZE/2, BombermanGame.HEIGHT - pos.y - WorldRenderer.BLOCK_SIZE/2);
    } else {
			batch.draw(bombermanDeadImg, pos.x - WorldRenderer.BLOCK_SIZE/2, BombermanGame.HEIGHT - pos.y - WorldRenderer.BLOCK_SIZE/2);
    }

    batch.end();
	}
}