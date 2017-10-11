package mazeSolver;

import maze.Cell;
import maze.Maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static java.lang.Thread.sleep;
import static maze.Maze.NUM_DIR;

/**
 * Implements WallFollowerSolver
 */

public class WallFollowerSolver implements MazeSolver {

	private boolean ifSolved = false;
	private int cellExplored = 1;
	
	@Override
	public void solveMaze(Maze maze) {
		//a stack to store cell visited
		Stack<Cell> history = new Stack<>();

		//Make the initial cell the current cell and mark it as visited
		Cell currentCell = maze.entrance;
		currentCell.isSolverVisited=true;

		//draw the entrance
		maze.drawFtPrt(currentCell);

		//The history is the stack of visited locations
		history.push(currentCell);

		// a ArrayList to store the available neighbours of current cell
		ArrayList<Cell> availableNeigh = new ArrayList<>();

		//While there are unvisited cells
		while(!history.peek().equals(maze.exit)){

			currentCell=history.peek();

			cellExplored++;

			availableNeigh.clear();
			//If the current cell has any neighbours which have not been visited
			for (int i = 0; i < NUM_DIR; i++) {
				if (history.peek().neigh[i] != null) {
					if (!currentCell.wall[i].present) {
						if (!currentCell.neigh[i].isSolverVisited) {
							availableNeigh.add(currentCell.neigh[i]);
						}
					}
				}
			}
			//If there is a valid cell to move to.
			if(!availableNeigh.isEmpty()){

				//choose the first available neighbour of current cell
				currentCell=availableNeigh.get(0);

				//draw the path
				maze.drawFtPrt(currentCell);

				try {
					sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				currentCell.isSolverVisited=true;
				//push the chosen cell to the stack
				history.push(currentCell);


			}else{ //If there are no valid cells to move to.
				//Pop a cell from the stack
				//Make it the current cell
				history.pop();
				cellExplored--;
			}

		}

		ifSolved = true;
        
	} // end of solveMaze()
    
    
	@Override
	public boolean isSolved() {
		return ifSolved;
	} // end if isSolved()
    
    
	@Override
	public int cellsExplored() {
		return cellExplored;
	} // end of cellsExplored()

} // end of class WallFollowerSolver
