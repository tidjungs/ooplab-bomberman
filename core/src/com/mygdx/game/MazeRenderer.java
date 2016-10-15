package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MazeRenderer {
	private Maze maze;
    private SpriteBatch batch;
    private Texture wallImage;

    public MazeRenderer(SpriteBatch batch, Maze maze) {
        this.maze = maze;
        this.batch = batch;
        
        wallImage = new Texture("wall.png");
    }
    
    public void render() {
        batch.begin();
        
        for(int r=0; r < maze.getHeight(); r++) {
        	
        	for(int c=0; c < maze.getWidth(); c++) {
        		
        		int x = c * 40;
        		int y = BombermanGame.HEIGHT - (r * 40) - 40;
        		
        		if (maze.hasWallAt(r, c)) {
                    batch.draw(wallImage, x, y);
        		}
        		
        	}
        	
        }
        
        batch.end();
    }
}
