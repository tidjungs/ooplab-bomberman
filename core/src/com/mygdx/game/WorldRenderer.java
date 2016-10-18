package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	
	public static final int BLOCK_SIZE = 40;
	
	BombermanGame bombermanGame;
	Bomberman bomberman;
	// Bomberman bomberman2;

	World world;
	SpriteBatch batch;
	Texture bombermanFrontImg;
	Texture bombermanBackImg;
	Texture bombermanLeftImg;
	Texture bombermanRightImg;
	Texture bombermanImg;

  private MazeRenderer mazeRenderer;
  private BombRenderer bombRenderer;
  private ItemRenderer itemRenderer;

	public WorldRenderer(BombermanGame bombermanGame, World world) {
		this.bombermanGame = bombermanGame;
    batch = bombermanGame.batch;
    this.world = world; 
    
    bombermanFrontImg = new Texture("Bman_F_f00.png");
    bombermanBackImg = new Texture("Bman_B_f00.png");
    bombermanLeftImg = new Texture("Bman_L_f00.png");
    bombermanRightImg = new Texture("Bman_R_f00.png");
    
    bombermanImg = bombermanFrontImg;
        
		bomberman = world.getBomberman();
		// bomberman2 = world.getBomberman2();

    mazeRenderer = new MazeRenderer(bombermanGame.batch, world.getMaze());
    bombRenderer = new BombRenderer(bombermanGame.batch, world.getBomb());
    itemRenderer = new ItemRenderer(bombermanGame.batch, world.getItem());
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

		int dir = bomberman.getCurrentDirection();

		if(dir == bomberman.DIRECTION_UP) {
			bombermanImg = bombermanBackImg;
		} else if (dir == bomberman.DIRECTION_RIGHT) {
			bombermanImg = bombermanRightImg;
		} else if (dir == bomberman.DIRECTION_DOWN) {
			bombermanImg = bombermanFrontImg;
		} else if (dir == bomberman.DIRECTION_LEFT) {
			bombermanImg = bombermanLeftImg;
		}
	}
}
