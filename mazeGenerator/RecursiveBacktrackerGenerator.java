package mazeGenerator;
import maze.Cell;
import maze.Maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static maze.Maze.NUM_DIR;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	@Override
	public void generateMaze(Maze maze) {
        //a stack to store cell visited
        Stack<Cell> history = new Stack<>();

        //Make the initial cell the current cell and mark it as visited
        Cell currentCell = maze.entrance;
        currentCell.isVisited=true;

        //The history is the stack of visited locations
        history.push(currentCell);

        // a ArrayList to store the available neighbours of current cell
        ArrayList<Cell> availableNeigh = new ArrayList<>();

        //While there are unvisited cells
       while(!history.isEmpty()){

            //If the current cell has any neighbours which have not been visited
            for (int i = 0; i < NUM_DIR; i++) {
                if(currentCell.neigh[i]!=null) {
                    if (!currentCell.neigh[i].isVisited) {
                        availableNeigh.add(currentCell.neigh[i]);
                    }
                }
            }
            //If there is a valid cell to move to.
            if(!availableNeigh.isEmpty()){
                //Choose randomly one of the unvisited neighbours
                Random random = new Random();
                //index to store the random index of availableNeigh
                int index = random.nextInt(availableNeigh.size());
                /*System.out.println("the random index of neighbour "+index);
                System.out.println("available number of neighbour "+availableNeigh.size());*/

                //Remove the wall between the current cell and the chosen cell
                for (int i = 0; i < NUM_DIR; i++) {
                    for (int j = 0; j < NUM_DIR; j++) {
                        if (availableNeigh.get(index).wall[i] == currentCell.wall[j] && (availableNeigh.get(index).wall[i]!=null)) {
                            /*System.out.println("equal!");
                            System.out.println(availableNeigh.get(index).wall[i]);
                            System.out.println(currentCell.wall[j]);*/
                            availableNeigh.get(index).wall[i].present=false;
                            break;
                        }
                    }
                }
                //Make the chosen cell the current cell and mark it as visited
                currentCell=availableNeigh.get(index);
                currentCell.isVisited=true;
                //push the chosen cell to the stack
                history.push(currentCell);
                availableNeigh.clear();

            }else{ //If there are no valid cells to move to.
                //Pop a cell from the stack
                //Make it the current cell
                currentCell=history.pop();
            }

       }

    } // end of generateMaze()

} // end of class RecursiveBacktrackerGenerator
