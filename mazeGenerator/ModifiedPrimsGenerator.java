package mazeGenerator;

import maze.Cell;
import maze.Maze;

import java.util.*;

import static maze.Maze.NUM_DIR;

public class ModifiedPrimsGenerator implements MazeGenerator {
    @Override
    public void generateMaze(Maze maze) {
        Random random = new Random();

        //Pick a random starting cell and add it to set Z
        Cell randomCell = maze.map[random.nextInt(maze.sizeR-1)][random.nextInt(maze.sizeC-1)];
        ArrayList<Cell> Z = new ArrayList<>();
        // put in the random cell
        randomCell.isVisited = true;
        Z.add(randomCell);
        //System.out.println("The random Cell location is : "+randomCell.r + " " + randomCell.c);

        //a define of available neighbour of c ,choose randomly from it
        ArrayList<Cell> tempAvailable = new ArrayList<>();

        //create a frontier set to store all neighbours
        ArrayList<Cell> F = new ArrayList<>();

        //firstly store the random cell
        for (int i = 0; i < NUM_DIR; i++) {
            if (randomCell.neigh[i] != null) {
                F.add(randomCell.neigh[i]);
            }
        }
        //System.out.println("The size of current F (random cell's neighbours) : " + F.size());

        while (F.size() != 0) {
            //Randomly select a cell c from the frontier set and remove it from F
            int index_from_F= random.nextInt(F.size());
            //System.out.println("The random index from current F is : " + index_from_F);

            Cell cellFromF_neighbours = F.get(index_from_F);
            F.remove(cellFromF_neighbours);
            cellFromF_neighbours.isVisited = true;
            //System.out.println("The available Cell c location is : "+cellFromF_neighbours.r + " " + cellFromF_neighbours.c);


            //Randomly select a cell b that is in Z and adjacent to the cell c

            //find the available neighbour of current cellFromF_neighbours
            for (int i = 0; i < NUM_DIR; i++) {
                if (cellFromF_neighbours.neigh[i] != null) {
                    if (cellFromF_neighbours.neigh[i].isVisited) {
                        //availableNeighbour.put(i,cellFromF_neighbours.neigh[i]);
                        tempAvailable.add(cellFromF_neighbours.neigh[i]);
                    }
                }
            }

            //System.out.println("The size of available Neighbours is : "+tempAvailable.size());
            int index_b = random.nextInt(tempAvailable.size());
            Cell cellFromAvailableNeighbours = tempAvailable.get(index_b);
            //System.out.println("The index of random available index is :"+index_b);
            //System.out.println("The available Cell b location is : "+cellFromAvailableNeighbours.r + " " + cellFromAvailableNeighbours.c);


            //Carve a path between c and b , remove the wall
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < NUM_DIR; j++) {
                    if (cellFromF_neighbours.wall[i] == cellFromAvailableNeighbours.wall[j] && (cellFromF_neighbours.wall[i] != null)) {
                        //System.out.println(cellFromF_neighbours.wall[i]);
                        //System.out.println(cellFromAvailableNeighbours.wall[j]);
                        cellFromF_neighbours.wall[i].present = false;
                        break;
                    }
                }
            }

            //Add cell c to the set Z
            Z.add(cellFromF_neighbours);
            //System.out.println("The size of current Z " + Z.size());

            tempAvailable.clear();

            //Add the neighbours of cell c to the frontier set F
            for (int i = 0; i < NUM_DIR; i++) {
                if (cellFromF_neighbours.neigh[i] != null) {
                    if (!cellFromF_neighbours.neigh[i].isVisited &&(!F.contains(cellFromF_neighbours.neigh[i]))) {
                        F.add(cellFromF_neighbours.neigh[i]);
                    }
                }
            }
            //System.out.println("The size of current F " + F.size());
            //System.out.println("=================");
        }

    } // end of generateMaze()

} // end of class ModifiedPrimsGenerator
