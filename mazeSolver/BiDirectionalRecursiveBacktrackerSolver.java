package mazeSolver;

import maze.Cell;
import maze.Maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static java.lang.Thread.sleep;
import static maze.Maze.NUM_DIR;

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {

    private boolean ifSolved = false;

    @Override
    public void solveMaze(Maze maze) {

        //a stack to store the cell visited
        Stack<Cell> historyFromEntrance = new Stack<>();
        Stack<Cell> historyFromExit = new Stack<>();

        //Make the current cell and mark it as visited
        Cell currentCellFromEntrance = maze.entrance;
        Cell currentCellFromExit = maze.exit;

        currentCellFromEntrance.isVisitedFromEntrance = true;
        maze.drawFtPrt(currentCellFromEntrance);

        currentCellFromExit.isVisitedFromExit = true;
        maze.drawFtPrt(currentCellFromExit);

        //The history is the stack of visited locations
        historyFromEntrance.push(currentCellFromEntrance);
        historyFromExit.push(currentCellFromExit);

        // a ArrayList to store the available neighbours of current cell from Entrance
        ArrayList<Cell> availableNeighFromEntrance = new ArrayList<>();
        // a ArrayList to store the available neighbours of current cell from Exit
        ArrayList<Cell> availableNeighFromExit = new ArrayList<>();

        while (!(currentCellFromEntrance.isVisitedFromExit || currentCellFromExit.isVisitedFromEntrance)) {
            availableNeighFromEntrance.clear();
            currentCellFromEntrance = historyFromEntrance.peek();
            //System.out.println("查找领居前单元格的行为 " + (currentCellFromEntrance.r + 1) + " " + "列为 " + (currentCellFromEntrance.c + 1));
            //initially randomly choose an adjacent unvisited cell
            for (int i = 0; i < NUM_DIR; i++) {
                if (historyFromEntrance.peek().neigh[i] != null) {
                    if (!currentCellFromEntrance.wall[i].present) {
                        if (!currentCellFromEntrance.neigh[i].isVisitedFromEntrance) {
                            availableNeighFromEntrance.add(currentCellFromEntrance.neigh[i]);
                        }
                    }
                }
            }

            //If there is a valid cell to move to.
            if (!availableNeighFromEntrance.isEmpty()) {
                //Choose randomly one of the unvisited neighbours
                Random random = new Random();
                //index to store the random index of availableNeigh
                int index = random.nextInt(availableNeighFromEntrance.size());

                currentCellFromEntrance = availableNeighFromEntrance.get(index);

                //draw the path
                maze.drawFtPrt(currentCellFromEntrance);
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //System.out.println("前进后单元格的行为 " + (currentCellFromEntrance.r + 1) + " " + "列为 " + (currentCellFromEntrance.c + 1));

                currentCellFromEntrance.isVisitedFromEntrance = true;
                //push the chosen cell to the stack
                historyFromEntrance.push(currentCellFromEntrance);

            } else {
                // /If there are no valid cells to move to.
                //Pop a cell from the stack
                //Make it the current cell
                historyFromEntrance.pop();
                //System.out.println("POP后倒退到单元格的行为 " + (currentCellFromEntrance.r + 1) + " " + "列为 " + (currentCellFromEntrance.c + 1));

            }


            //From the exit
            availableNeighFromExit.clear();
            currentCellFromExit = historyFromExit.peek();
            //System.out.println("查找领居前单元格的行为 " + (currentCellFromExit.r + 1) + " " + "列为 " + (currentCellFromExit.c + 1));
            //initially randomly choose an adjacent unvisited cell
            for (int i = 0; i < NUM_DIR; i++) {
                if (historyFromExit.peek().neigh[i] != null) {
                    if (!currentCellFromExit.wall[i].present) {
                        if (!currentCellFromExit.neigh[i].isVisitedFromExit) {
                            availableNeighFromExit.add(currentCellFromExit.neigh[i]);
                        }
                    }
                }
            }

            //If there is a valid cell to move to.
            if (!availableNeighFromExit.isEmpty()) {
                //Choose randomly one of the unvisited neighbours
                Random random = new Random();
                //index to store the random index of availableNeigh
                int index = random.nextInt(availableNeighFromExit.size());

                currentCellFromExit = availableNeighFromExit.get(index);

                //draw the path
                maze.drawFtPrt(currentCellFromExit);

                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //System.out.println("前进后单元格的行为 " + (currentCellFromExit.r + 1) + " " + "列为 " + (currentCellFromExit.c + 1));

                currentCellFromExit.isVisitedFromExit = true;
                //push the chosen cell to the stack
                historyFromExit.push(currentCellFromExit);

            } else {
                // /If there are no valid cells to move to.
                //Pop a cell from the stack
                //Make it the current cell
                historyFromExit.pop();
                //System.out.println("POP后倒退到单元格的行为 " + (currentCellFromExit.r + 1) + " " + "列为 " + (currentCellFromExit.c + 1));

            }
        }

        ifSolved = true;
        System.out.println(ifSolved);

    } // end of solveMaze()

    @Override
    public boolean isSolved() {
        // TODO Auto-generated method stub
        return ifSolved;
    } // end if isSolved()


    @Override
    public int cellsExplored() {
        // TODO Auto-generated method stub
        return 0;
    } // end of cellsExplored()

} // end of class BiDirectionalRecursiveBackTrackerSolver
