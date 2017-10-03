package mazeGenerator;

import java.util.*;

import maze.Maze;
import maze.Cell;

public class GrowingTreeGenerator implements MazeGenerator {
    // Growing tree maze generator. As it is very general, here we implement as "usually pick the most recent cell, but occasionally pick a random cell"

    /**
     * parameter of particular strategy
     */
    private Random random = new Random();
    private double threshold = 0.1;
    private double randomPickNumber = 0;
    private double totalPickNUmber = 0;

    /**
     * pick random cell from set Z
     * @param Z a set store Cells
     * @return cell be randomly chosen
     */
    private Cell randomPickCell(ArrayList<Cell> Z) {
        int indexOfB = random.nextInt(Z.size());
        Cell b = Z.get(indexOfB);
        randomPickNumber++;
        totalPickNUmber++;
        return b;
    }

    /**
     * pick the most recent cell
     * @param Z a set store Cells
     * @return cell at the end index of set Z
     */
    private Cell recentPickCell(ArrayList<Cell> Z) {
        totalPickNUmber++;
        return Z.get(Z.size() - 1);

    }

    @Override
    public void generateMaze(Maze maze) {

        // a ArrayList to store the available neighbours of current cell
        ArrayList<Cell> availableNeigh = new ArrayList<>();

        //Pick a random starting cell and add it to set Z
        Cell randomCell = maze.map[random.nextInt(maze.sizeR)][random.nextInt(maze.sizeC)];
        ArrayList<Cell> Z = new ArrayList<>();
        // put in the random cell
        randomCell.isVisited = true;
        Z.add(randomCell);
        System.out.println("The random Cell location is : " + randomCell.r + " " + randomCell.c);

        while (!Z.isEmpty()) {
            //Using a particular strategy  select a cell b from Z
            Cell b;
            //System.out.println(randomPickNumber / totalPickNUmber);
            if (totalPickNUmber == 0) {
                b = recentPickCell(Z);
            } else if ((randomPickNumber / totalPickNUmber) > threshold) {
                b = recentPickCell(Z);
               // System.out.println("pick recent");
            } else {
                b = randomPickCell(Z);
               // System.out.println("pick random");
            }


            //store the unvisited neighbours of cell b
            for (int i = 0; i < 6; i++) {
                if (b.neigh[i] != null) {
                    if (!b.neigh[i].isVisited) {
                        availableNeigh.add(b.neigh[i]);
                    }
                }
            }
            //If cell b has unvisited neighbouring cells
            if (!availableNeigh.isEmpty()) {

                //randomly select a neighbour
                int index = random.nextInt(availableNeigh.size());
                Cell randomNeighbour = availableNeigh.get(index);

                //carve a path
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (randomNeighbour.wall[i] == b.wall[j] && (randomNeighbour.wall[i] != null)) {
                            randomNeighbour.wall[i].present = false;
                            break;
                        }
                    }
                }

                //add the selected neighbour to set Z
                Z.add(randomNeighbour);
                randomNeighbour.isVisited = true;
                availableNeigh.clear();

            } else {
                //If b has no unvisited neighbours, remove it from Z
                Z.remove(b);
                //System.out.println("b deleted!");
            }
            //System.out.println(Z.size());
        }

    }

}
