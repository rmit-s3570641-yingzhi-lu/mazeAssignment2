package mazeSolver;

import maze.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

import static java.lang.Thread.sleep;
import static maze.Maze.NUM_DIR;

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {

    private boolean ifSolved = false;
    private int cellExplored = 1;
    //to draw the shorted path
    ArrayList<Cell> path = new ArrayList<>();

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
        path.add(currentCellFromEntrance);

        currentCellFromExit.isVisitedFromExit = true;
        maze.drawFtPrt(currentCellFromExit);
        path.add(currentCellFromExit);

        //The history is the stack of visited locations
        historyFromEntrance.push(currentCellFromEntrance);
        historyFromExit.push(currentCellFromExit);

        // a ArrayList to store the available neighbours of current cell from Entrance
        ArrayList<Cell> availableNeighFromEntrance = new ArrayList<>();
        // a ArrayList to store the available neighbours of current cell from Exit
        ArrayList<Cell> availableNeighFromExit = new ArrayList<>();

        while (!(currentCellFromEntrance.isVisitedFromExit || currentCellFromExit.isVisitedFromEntrance)) {

            cellExplored++;

            availableNeighFromEntrance.clear();
            currentCellFromEntrance = historyFromEntrance.peek();
            //System.out.println("查找领居前单元格的行为 " + (currentCellFromEntrance.r + 1) + " " + "列为 " + (currentCellFromEntrance.c + 1));

            // if this cell has tunnel, add tunnel to the exit
            if (maze instanceof TunnelMaze && currentCellFromEntrance.tunnelTo != null) {
                currentCellFromEntrance = currentCellFromEntrance.tunnelTo;
                currentCellFromEntrance.isVisitedFromEntrance = true;
                maze.drawFtPrt(currentCellFromEntrance);
                path.add(currentCellFromEntrance);
                //System.out.println("传送后单元格为"+(currentCell.r+1) + " "+ (currentCell.c+1));
            }

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
                path.add(currentCellFromEntrance);

            } else {
                // /If there are no valid cells to move to.
                //Pop a cell from the stack
                //Make it the current cell
                historyFromEntrance.pop();
                path.remove(currentCellFromEntrance);
                cellExplored--;
                //System.out.println("POP后倒退到单元格的行为 " + (currentCellFromEntrance.r + 1) + " " + "列为 " + (currentCellFromEntrance.c + 1));

            }


            /*
            From the exit to search the path
             */
            availableNeighFromExit.clear();
            currentCellFromExit = historyFromExit.peek();
            //System.out.println("查找领居前单元格的行为 " + (currentCellFromExit.r + 1) + " " + "列为 " + (currentCellFromExit.c + 1));

            // if this cell has tunnel, add tunnel to the exit
            if (maze instanceof TunnelMaze && currentCellFromExit.tunnelTo != null) {
                currentCellFromExit = currentCellFromExit.tunnelTo;
                currentCellFromExit.isVisitedFromExit = true;
                maze.drawFtPrt(currentCellFromExit);
                path.add(currentCellFromExit);
                //System.out.println("传送后单元格为"+(currentCell.r+1) + " "+ (currentCell.c+1));
            }

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
                path.add(currentCellFromExit);

            } else {
                // /If there are no valid cells to move to.
                //Pop a cell from the stack
                //Make it the current cell
                historyFromExit.pop();
                path.remove(currentCellFromExit);
                cellExplored--;
                //System.out.println("POP后倒退到单元格的行为 " + (currentCellFromExit.r + 1) + " " + "列为 " + (currentCellFromExit.c + 1));

            }
        }

        ifSolved = true;

        System.out.println("Orange path is the shorted path!");
        //draw the shorted path with red dot
        if (maze instanceof NormalMaze || maze instanceof TunnelMaze) {
            for (Cell cell : path) {
                StdDraw.setPenColor(StdDraw.ORANGE);
                StdDraw.filledCircle(cell.c + 0.5, cell.r + 0.5, 0.15);
            }
        }else if(maze instanceof HexMaze){

            for (Cell cell : path) {
                StdDraw.setPenColor(StdDraw.ORANGE);
                StdDraw.filledCircle(cell.r % 2 * 0.5 + cell.c - (cell.r + 1) / 2 + 0.5, cell.r + 0.5, 0.15);
            }
        }


    } // end of solveMaze()

    @Override
    public boolean isSolved() {
        // TODO Auto-generated method stub
        return ifSolved;
    } // end if isSolved()


    @Override
    public int cellsExplored() {
        return cellExplored;
    } // end of cellsExplored()

} // end of class BiDirectionalRecursiveBackTrackerSolver
