package com.mygdx.game;

public class Maze {
	private String[] MAP = new String [] {
	            "###################",
	            "#...XXXXXXXXXXX...#",
	            "#.#X#X#X#X#X#X#X#.#",
	            "#.XXXXXXXXXXXXXXX.#",
	            "#X#X#X#X#X#X#X#X#X#",
	            "#XXXXXXXXXXXXXXXXX#",
	            "#X#X#X#X#X#X#X#X#X#",
	            "#XXXXXXXXXXXXXXXXX#",
	            "#X#X#X#X#X#X#X#X#X#",
	            "#.XXXXXXXXXXXXXXX.#",
	            "#.#X#X#X#X#X#X#X#.#",
	            "#...XXXXXXXXXXX...#",
	            "###################"    
	};
	
	private int height;
	private int width;
	
	public Maze() {
		height = MAP.length;
		width = MAP[0].length();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public boolean hasWallAt(int r, int c) {
		return MAP[r].charAt(c) == '#';
	}
	
	public boolean hasBoxAt(int r, int c) {
		return MAP[r].charAt(c) == 'X';
	}
	
}
