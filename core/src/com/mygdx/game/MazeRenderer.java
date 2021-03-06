package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MazeRenderer {
	private Maze maze;
    private SpriteBatch batch;
    private Texture wallImage;
    private Texture glassImage;
    private Texture boxImage;
    private Texture portalImage;

    public MazeRenderer(SpriteBatch batch, Maze maze) {
        this.maze = maze;
        this.batch = batch;
        
        wallImage = new Texture("SolidBlock.png");
        glassImage = new Texture("glass.png");
        boxImage = new Texture("ExplodableBlock.png");
        portalImage = new Texture("Portal.png");
    }
    
    public void render() {
        batch.begin();
        
        for(int r=0; r < maze.getHeight(); r++) {
        	
        	for(int c=0; c < maze.getWidth(); c++) {
        		
        		int x = c * WorldRenderer.BLOCK_SIZE;
        		int y = BombermanGame.HEIGHT - (r * WorldRenderer.BLOCK_SIZE) - WorldRenderer.BLOCK_SIZE;
        		
        		if (maze.hasWallAt(r, c)) {
                    batch.draw(wallImage, x, y);
        		} else if (maze.hasBoxAt(r, c)) {
                    batch.draw(boxImage, x, y);
        		} else if (maze.hasFirstPortalAt(r, c) || maze.hasSecondPortalAt(r, c)) {
                    batch.draw(glassImage, x, y);
                    batch.draw(portalImage, x, y);
                } else {
                    batch.draw(glassImage, x, y);
        		}
        		
        		
        	}
        	
        }
        
        batch.end();
    }
}
