package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class StatusBarRenderer {
    private static final int PLAYER1_POSITION = 50;
    private static final int PLAYER2_POSITION = 250;
    private static final int PLAYER3_POSITION = 450;
    private static final int PLAYER4_POSITION = 650;

    private static final int NAME_POSITION = BombermanGame.HEIGHT - 540;
    private static final int STATUS_POSITION = BombermanGame.HEIGHT - 560;
    private static final int BOMB_POSITION = BombermanGame.HEIGHT - 580;
    private static final int FLAME_POSITION = BombermanGame.HEIGHT - 600;
    private static final int GHOSTWLAK_POSITION = BombermanGame.HEIGHT - 620;

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
        font.draw(batch, "Player 1" , PLAYER1_POSITION, NAME_POSITION);
        font.draw(batch, "Status: " + status1 , PLAYER1_POSITION, STATUS_POSITION);
        font.draw(batch, "Bomb: " + world.getBomberman().getBombLimit() , PLAYER1_POSITION, BOMB_POSITION);
        font.draw(batch, "Flame: " + world.getBomberman().getBombArea() , PLAYER1_POSITION, FLAME_POSITION);
        font.draw(batch, "Ghost Walk: " + world.getBomberman().getGhostWalkTimeLeft() , PLAYER1_POSITION, GHOSTWLAK_POSITION);

        font.draw(batch, "Player 2" , PLAYER2_POSITION, NAME_POSITION);
        font.draw(batch, "Status: " + status2 , PLAYER2_POSITION, STATUS_POSITION);
        font.draw(batch, "Bomb: " + world.getBomberman2().getBombLimit() , PLAYER2_POSITION, BOMB_POSITION);
        font.draw(batch, "Flame: " + world.getBomberman2().getBombArea() , PLAYER2_POSITION, FLAME_POSITION);
        font.draw(batch, "Ghost Walk: " + world.getBomberman2().getGhostWalkTimeLeft() , PLAYER2_POSITION, GHOSTWLAK_POSITION);

        font.draw(batch, "Player 3" , PLAYER3_POSITION, NAME_POSITION);
        font.draw(batch, "Status: " + status3 , PLAYER3_POSITION, STATUS_POSITION);
        font.draw(batch, "Bomb: " + world.getBomberman3().getBombLimit() , PLAYER3_POSITION, BOMB_POSITION);
        font.draw(batch, "Flame: " + world.getBomberman3().getBombArea() , PLAYER3_POSITION, FLAME_POSITION);
        font.draw(batch, "Ghost Walk: " + world.getBomberman3().getGhostWalkTimeLeft() , PLAYER3_POSITION, GHOSTWLAK_POSITION);

        font.draw(batch, "Player 4" , PLAYER4_POSITION, NAME_POSITION);
        font.draw(batch, "Status: " + status4 , PLAYER4_POSITION, STATUS_POSITION);
        font.draw(batch, "Bomb: " + world.getBomberman4().getBombLimit() , PLAYER4_POSITION, BOMB_POSITION);
        font.draw(batch, "Flame: " + world.getBomberman4().getBombArea() , PLAYER4_POSITION, FLAME_POSITION);
        font.draw(batch, "Ghost Walk: " + world.getBomberman4().getGhostWalkTimeLeft() , PLAYER4_POSITION, GHOSTWLAK_POSITION);
        batch.end();
    }
}
