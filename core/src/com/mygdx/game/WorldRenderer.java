package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	
	public static final int BLOCK_SIZE = 40;
	private static final int DELAY_LENGTH = 2;
	private static final int BOMBERMAN_IMG_PER_POSITION = 8;
	
	private BombermanGame bombermanGame;
	private Bomberman bomberman;
	// Bomberman bomberman2;

	private World world;
	private SpriteBatch batch;

	private Texture [] bombermanFrontImg;
	private Texture [] bombermanBackImg;
	private Texture [] bombermanLeftImg;
	private Texture [] bombermanRightImg;
	private Texture bombermanImg;

  private MazeRenderer mazeRenderer;
  private BombRenderer bombRenderer;
  private ItemRenderer itemRenderer;

  private int lastDirection;
  private int currentDirection;
  private int movingDelay = 0;

  private String fileName;

	public WorldRenderer(BombermanGame bombermanGame, World world) {
		this.bombermanGame = bombermanGame;
    batch = bombermanGame.batch;
    this.world = world; 
    
    bombermanFrontImg = new Texture [BOMBERMAN_IMG_PER_POSITION];
    bombermanBackImg = new Texture [BOMBERMAN_IMG_PER_POSITION];
    bombermanLeftImg = new Texture [BOMBERMAN_IMG_PER_POSITION];
    bombermanRightImg = new Texture [BOMBERMAN_IMG_PER_POSITION];

    inintialBombermanTexure('F', bombermanFrontImg);
    inintialBombermanTexure('B', bombermanBackImg);
    inintialBombermanTexure('L', bombermanLeftImg);
    inintialBombermanTexure('R', bombermanRightImg);
    
    bombermanImg = bombermanFrontImg[0];
        
		bomberman = world.getBomberman();
		// bomberman2 = world.getBomberman2();

    mazeRenderer = new MazeRenderer(bombermanGame.batch, world.getMaze());
    bombRenderer = new BombRenderer(bombermanGame.batch, world.getBomb());
    itemRenderer = new ItemRenderer(bombermanGame.batch, world.getItem());
	}

	public void inintialBombermanTexure(char pos, Texture [] texture) {
    for(int i=0; i<8; i++) {
    	fileName = String.format("Bman_%c_f0%d.png",pos, i);
   		// System.out.println(fileName);
    	texture[i] = new Texture(Gdx.files.internal(fileName));
    }
	}
	
	public void render (float delta) {
    mazeRenderer.render();
    bombRenderer.render();
    itemRenderer.render();
    Vector2 pos = bomberman.getPosition();
//        Vector2 pos2 = bomberman2.getPosition();
    
    batch.begin();
    
    if(bomberman.isAlive()) {
    	renderBombermanByDirection();
			batch.draw(bombermanImg, pos.x - BLOCK_SIZE/2, BombermanGame.HEIGHT - pos.y - BLOCK_SIZE/2);
    }
    
//        if(bomberman2.isAlive()) {
//        	batch.draw(bombermanImg, pos2.x - BLOCK_SIZE/2, BombermanGame.HEIGHT - pos2.y - BLOCK_SIZE/2);
//        }
    
    batch.end();
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
			if(movingDelay < DELAY_LENGTH*(i+1)) {
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
		return movingDelay == BOMBERMAN_IMG_PER_POSITION * DELAY_LENGTH;
	}


}
