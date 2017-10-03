import maze.HexMaze;
import maze.Maze;
import maze.NormalMaze;
import mazeGenerator.MazeGenerator;
import mazeGenerator.ModifiedPrimsGenerator;
import mazeGenerator.RecursiveBacktrackerGenerator;

import java.util.ArrayList;
import java.util.List;

public class tester {

    public static void main(String[] args) {

        Maze maze=new NormalMaze();
        //Maze maze = new HexMaze();
        List<int[]> tunnelList = new ArrayList<>();

        maze.initMaze(10,10,0,0,9,0,tunnelList);

        MazeGenerator mazeGenerator = new ModifiedPrimsGenerator();
        mazeGenerator.generateMaze(maze);

        maze.draw();
        System.out.println(maze.isPerfect());

    }
}
