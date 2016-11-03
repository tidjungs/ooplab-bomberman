package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class StatusBarRenderer {
    private static final int PLAYER1_STATUS_POSITION = 50;
    private static final int PLAYER2_STATUS_POSITION = 250;
    private static final int PLAYER3_STATUS_POSITION = 450;
    private static final int PLAYER4_STATUS_POSITION = 650;


    private SpriteBatch batch;
    private BitmapFont font;
    private World world;

    private String status1;
    private String status2;
    private String status3;
    private String status4;

    public StatusBarRenderer(SpriteBatch batch, World world) {
        this.batch = batch;
        this.world = world;
        font = new BitmapFont();

    }
    
    public void render() {
        status1  = world.getBomberman().isAlive() ? "alive" : "dead";
        status2  = world.getBomberman2().isAlive() ? "alive" : "dead";
        status3  = world.getBomberman3().isAlive() ? "alive" : "dead";
        status4  = world.getBomberman4().isAlive() ? "alive" : "dead";

        batch.begin();
        font.draw(batch, "Player 1" , PLAYER1_STATUS_POSITION, 90);
        font.draw(batch, "Status: " + status1 , PLAYER1_STATUS_POSITION, 70);
        font.draw(batch, "Bomb: " + world.getBomberman().getBombLimit() , PLAYER1_STATUS_POSITION, 50);
        font.draw(batch, "Flame: " + world.getBomberman().getBombArea() , PLAYER1_STATUS_POSITION, 30);
        font.draw(batch, "Ghost Walk: " + world.getBomberman().getGhostWalkTimeLeft() , PLAYER1_STATUS_POSITION, 10);

        font.draw(batch, "Player 2" , PLAYER2_STATUS_POSITION, 90);
        font.draw(batch, "Status: " + status2 , PLAYER2_STATUS_POSITION, 70);
        font.draw(batch, "Bomb: " + world.getBomberman2().getBombLimit() , PLAYER2_STATUS_POSITION, 50);
        font.draw(batch, "Flame: " + world.getBomberman2().getBombArea() , PLAYER2_STATUS_POSITION, 30);

        font.draw(batch, "Player 3" , PLAYER3_STATUS_POSITION, 90);
        font.draw(batch, "Status: " + status3 , PLAYER3_STATUS_POSITION, 70);
        font.draw(batch, "Bomb: " + world.getBomberman3().getBombLimit() , PLAYER3_STATUS_POSITION, 50);
        font.draw(batch, "Flame: " + world.getBomberman3().getBombArea() , PLAYER3_STATUS_POSITION, 30);

        font.draw(batch, "Player 4" , PLAYER4_STATUS_POSITION, 90);
        font.draw(batch, "Status: " + status4 , PLAYER4_STATUS_POSITION, 70);
        font.draw(batch, "Bomb: " + world.getBomberman4().getBombLimit() , PLAYER4_STATUS_POSITION, 50);
        font.draw(batch, "Flame: " + world.getBomberman4().getBombArea() , PLAYER4_STATUS_POSITION, 30);
        batch.end();
    }
}
