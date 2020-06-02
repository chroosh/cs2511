package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {

		// private variables
		private static final int BOARDSIZE = 10;
		private BooleanProperty[][] board;

    public GameOfLife() {
        // TODO At the start all cells are dead
				this.board = new SimpleBooleanProperty[10][10];
				for (int x = 0; x < BOARDSIZE; x++) {
					for (int y = 0; y < BOARDSIZE; y++) {
						board[x][y] = new SimpleBooleanProperty(false);

					}
				}
    }

    public void ensureAlive(int x, int y) {
        // TODO Set the cell at (x,y) as alive
				//
				this.board[x][y].set(true);
    }

    public void ensureDead(int x, int y) {
        // TODO Set the cell at (x,y) as dead
				//
				this.board[x][y].set(false);
    }

    public boolean isAlive(int x, int y) {
        // TODO Return true if the cell is alive
				return board[x][y].get();
    }

		public int neighbours(int x, int y) {
    	int res = 0;
    	for (int i = -1; i <= 1; i++) {
    		for (int j = -1; j <= 1; j++) {
    			if (i == 0 && j == 0) continue; // Don't check itself
    			
    			// check edges:
    			int n = x + i;
    			int m = y + j;
    			
    			if (n > BOARDSIZE - 1) {
    				n = 0;
    			}
    			
    			if (n < 0) {
    				n = BOARDSIZE - 1;
    			}
    			
    			if (m > BOARDSIZE - 1) {
    				m = 0;
    			}
    			
    			if (m < 0) {
    				m = BOARDSIZE - 1;
    			}
    			
    			if (board[n][m].get()) res++;
    			
    		}
    	}
    	
    	return res;
    }



		public void tick() {
        // TODO Transition the game to the next generation.
    	BooleanProperty[][] newboard = new SimpleBooleanProperty[BOARDSIZE][BOARDSIZE];
    	//Initialise Temp Board
    	for (int x = 0; x < BOARDSIZE; x++) {
    		for (int y = 0; y < BOARDSIZE; y++) {
    			newboard[x][y] = new SimpleBooleanProperty();
    		}
    	}
    	
    	
    	for (int x = 0; x < BOARDSIZE; x++) {
    		for (int y = 0; y < BOARDSIZE; y++) {
    			int nn = neighbours(x,y);
    			newboard[x][y].set(cellNext(x,y,nn));
    		}
    	}
    	
    	for (int x = 0; x < BOARDSIZE; x++) {
    		for (int y = 0; y < BOARDSIZE; y++) {
    			board[x][y].set(newboard[x][y].get());
    		}
    	}
    	
    }
    
    
    public boolean cellNext(int x, int y, int neighbours) {
    	if (this.board[x][y].get()) {
    		if (neighbours < 2)
    			return false;
    		else if (neighbours == 2 || neighbours == 3)
    			return true;
    		else
    			return false;
    	} else if (neighbours == 3) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public BooleanProperty cellProperty(int x, int y) {
    	return board[x][y];
    }
    

}
