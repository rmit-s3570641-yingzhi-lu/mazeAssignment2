package mazeSolver;

import maze.Cell;
import maze.Maze;
import maze.StdDraw;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static maze.Maze.NUM_DIR;

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {

 /*   private Cell currentFromStart, currentFromEnd;
    private Stack<Cell> historyFromStart = new Stack<>();
    private Stack<Cell> historyFromEnd = new Stack<>();*/

    @Override
    public void solveMaze(Maze maze) {



        //a stack to store the cell visited
        Stack<Cell> history = new Stack<>();

        //Make the initial cell the current cell and mark it as visited
        Cell currentCell = maze.entrance;
        currentCell.isSolverVisited = true;
        maze.drawFtPrt(currentCell);

        //The history is the stack of visited locations
        history.push(currentCell);

        // a ArrayList to store the available neighbours of current cell
        ArrayList<Cell> availableNeigh = new ArrayList<>();

        while (!currentCell.equals(maze.exit)) {
            //initially randomly choose an adjacent unvisited cell
            for (int i = 0; i < NUM_DIR; i++) {
                Cell next = currentCell.neigh[i];
                if (next != null && !next.isSolverVisited && !currentCell.wall[i].present) {
                            availableNeigh.add(currentCell.neigh[i]);
                }
            }

            //If there is a valid cell to move to.
            if (!availableNeigh.isEmpty()) {
                //Choose randomly one of the unvisited neighbours
                Random random = new Random();
                //index to store the random index of availableNeigh
                int index = random.nextInt(availableNeigh.size());

                //draw the path
                maze.drawFtPrt(availableNeigh.get(index));

                currentCell = availableNeigh.get(index);
                currentCell.isSolverVisited = true;
                //push the chosen cell to the stack
                history.push(currentCell);
                availableNeigh.clear();
            } else {
                // /If there are no valid cells to move to.
                //Pop a cell from the stack
                //Make it the current cell
                //set a pen to draw white circle
                currentCell = history.pop();


            }


        }

/*        for (Cell cell:history
                ) {
            maze.drawFtPrt(cell);
        }*/

       /* currentFromStart = maze.entrance;
        currentFromStart.isSolverVisited = true;
        maze.drawFtPrt(currentFromStart);

        //put the history into the stack (start)
        historyFromStart.push(currentFromStart);


        currentFromEnd = maze.exit;
        currentFromEnd.isSolverVisited = true;
        maze.drawFtPrt(currentFromEnd);

        //put the history into the stack (End)
        historyFromEnd.push(currentFromEnd);

        int i=0;
        while(!currentFromStart.equals(maze.exit)){
            currentFromStart=checkNeighbours(maze,currentFromStart,historyFromStart);
            i++;
            System.out.println("current cell is "+currentFromStart + "    "  +i);
        }

        *//*Cell currentCell1=checkNeighbours(maze,currentFromStart,historyFromStart);
        Cell currentCell2=checkNeighbours(maze,currentFromEnd,historyFromEnd);

        while (!checkNeighbours(maze, currentCell1, historyFromStart).equals(checkNeighbours(maze, currentCell2, historyFromEnd))) {
            currentCell1=  checkNeighbours(maze, currentCell1, historyFromStart);
            currentCell2= checkNeighbours(maze, currentCell2, historyFromEnd);
        }*/


    } // end of solveMaze()

  /*  private Cell checkNeighbours(Maze maze, Cell currentCell, Stack<Cell> currentHistory) {
        // a ArrayList to store the available neighbours of current cell
        ArrayList<Cell> availableNeigh = new ArrayList<>();

        //initially randomly choose an adjacent unvisited cell
        for (int i = 0; i < 6; i++) {
            if (currentCell.neigh[i] != null) {
                if (!currentCell.neigh[i].isSolverVisited) {
                    if (!currentCell.wall[i].present) {
                        availableNeigh.add(currentCell.neigh[i]);

                    }
                }
            }
        }

        //If there is a valid cell to move to.
        if (!availableNeigh.isEmpty()) {
            //Choose randomly one of the unvisited neighbours
            Random random = new Random();
            //index to store the random index of availableNeigh
            int index = random.nextInt(availableNeigh.size());

            //draw the path
            maze.drawFtPrt(availableNeigh.get(index));

            currentCell = availableNeigh.get(index);
            currentCell.isSolverVisited = true;
            //push the chosen cell to the stack
            currentHistory.push(currentCell);
            availableNeigh.clear();

        } else {
            // /If there are no valid cells to move to.
            //Pop a cell from the stack
            //Make it the current cell
            currentCell = currentHistory.pop();

        }

        return currentCell;
    }

*/
    @Override
    public boolean isSolved() {
        // TODO Auto-generated method stub
        return false;
    } // end if isSolved()


    @Override
    public int cellsExplored() {
        // TODO Auto-generated method stub
        return 0;
    } // end of cellsExplored()

} // end of class BiDirectionalRecursiveBackTrackerSolver
