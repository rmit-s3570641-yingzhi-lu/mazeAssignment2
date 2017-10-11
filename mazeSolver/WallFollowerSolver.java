package mazeSolver;

import maze.*;

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
	//to draw the shorted path
	ArrayList<Cell> path = new ArrayList<>();
	
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
		path.add(currentCell);

		// a ArrayList to store the available neighbours of current cell
		ArrayList<Cell> availableNeigh = new ArrayList<>();

		//While there are unvisited cells
		while(!history.peek().equals(maze.exit)){

			currentCell=history.peek();

			cellExplored++;

			availableNeigh.clear();

			// if this cell has tunnel, add tunnel to the exit
			if (maze instanceof TunnelMaze && currentCell.tunnelTo != null) {
				currentCell = currentCell.tunnelTo;
				currentCell.isSolverVisited=true;
				maze.drawFtPrt(currentCell);
				path.add(currentCell);
				//System.out.println("传送后单元格为"+(currentCell.r+1) + " "+ (currentCell.c+1));
			}

			//If the current cell has any neighbours which have not been visited
			for (int i = 0; i < NUM_DIR; i++) {
				if (currentCell.neigh[i] != null) {
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
				path.add(currentCell);

			}else{ //If there are no valid cells to move to.
				//Pop a cell from the stack
				//Make it the current cell
				history.pop();
				path.remove(currentCell);
				cellExplored--;
			}

		}

		ifSolved = true;
		System.out.println("Green path is the shortest path!");
		//draw the shorted path with red dot
		if (maze instanceof NormalMaze || maze instanceof TunnelMaze) {
			for (Cell cell : path) {
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledCircle(cell.c + 0.5, cell.r + 0.5, 0.15);
			}
		}else if(maze instanceof HexMaze){

			for (Cell cell : path) {
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledCircle(cell.r % 2 * 0.5 + cell.c - (cell.r + 1) / 2 + 0.5, cell.r + 0.5, 0.15);
			}
		}
        
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
