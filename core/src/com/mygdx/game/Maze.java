package com.mygdx.game;

public class Maze {
	private String[] MAP = new String [] {
	            "###################",
	            "#...XXXXXXXXXXX...#",
	            "#.#X#X#X#X#X#X#X#.#",
	            "#.XXXXXXXXXXXXXXX.#",
	            "#X#X#X#X#.#X#X#X#X#",
	            "#XXXXXXXX.XXXXXXXX#",
	            "#X#X#X#X#.#X#X#X#X#",
	            "#XXXXXXXX.XXXXXXXX#",
	            "#X#X#X#X#.#X#X#X#X#",
	            "#.XXXXXXXXXXXXXXX.#",
	            "#.#X#X#X#X#X#X#X#.#",
	            "#...XXXXXXXXXXX...#",
	            "###################"    
	};
	// private String[] MAP = new String [] {
	//             "###################",
	//             "#.................#",
	//             "#.#.#.#.#.#.#.#.#.#",
	//             "#.................#",
	//             "#.#.#.#.#.#.#.#.#.#",
	//             "#.................#",
	//             "#.#.#.#.#.#.#.#.#.#",
	//             "#.................#",
	//             "#.#.#.#.#.#.#.#.#.#",
	//             "#.................#",
	//             "#.#.#.#.#.#.#.#.#.#",
	//             "#.................#",
	//             "###################"    
	// };
	
	private boolean [][] Box;
	
	private int height;
	private int width;
	
	public Maze() {
		height = MAP.length;
		width = MAP[0].length();
		inintialBox();
	}
	
	private void inintialBox() {
		Box = new boolean [height][width];
		for(int r=0; r < height; r++) {
			for(int c=0; c < width; c++) {
				if(MAP[r].charAt(c) == 'X') {
					Box[r][c] = true;
				}
			}
		}
	}

	// private boolean canBoxSpawn(int r, int c) {
	// 	return !hasWallAt(r, c) && 
	// }

	// private boolean bombermanSpawnPoint(int r, int c) {
	// 	for(int i)
	// }
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public boolean hasWallAt(int r, int c) {
		return MAP[r].charAt(c) == '#';
	}

	public boolean hasPortalAt(int r, int c) {
		return MAP[r].charAt(c) == 'P';
	}
	
	public boolean hasBoxAt(int r, int c) {
		return Box[r][c];
	}
	
	public void explodeBox(int r, int c) {
		if(hasBoxAt(r, c)) {
			Box[r][c] = false;
		}
	}
	
}
