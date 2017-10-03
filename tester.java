import maze.HexMaze;
import maze.Maze;
import maze.NormalMaze;
import mazeGenerator.GrowingTreeGenerator;
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

        maze.initMaze(20,20,0,0,19,19,tunnelList);

        //MazeGenerator mazeGenerator = new ModifiedPrimsGenerator();
        //MazeGenerator mazeGenerator= new RecursiveBacktrackerGenerator();
        MazeGenerator mazeGenerator = new GrowingTreeGenerator();
        mazeGenerator.generateMaze(maze);

        maze.draw();
        System.out.println(maze.isPerfect());

    }
}
