import maze.HexMaze;
import maze.Maze;
import maze.NormalMaze;
import maze.TunnelMaze;
import mazeGenerator.GrowingTreeGenerator;
import mazeGenerator.MazeGenerator;
import mazeGenerator.ModifiedPrimsGenerator;
import mazeGenerator.RecursiveBacktrackerGenerator;
import mazeSolver.BiDirectionalRecursiveBacktrackerSolver;
import mazeSolver.MazeSolver;
import mazeSolver.WallFollowerSolver;

import java.util.ArrayList;
import java.util.List;

public class tester {

    public static void main(String[] args) {

        //Maze maze=new NormalMaze();
        Maze maze = new HexMaze();
        //Maze maze = new TunnelMaze();
        List<int[]> tunnelList = new ArrayList<>();
        int temp1[]= {2, 2, 10, 10};
        int temp2[]= {13,11,4,2};
        tunnelList.add(temp1);
        tunnelList.add(temp2);

        maze.initMaze(15,15,0,0,14,14,tunnelList);

        //MazeGenerator mazeGenerator = new ModifiedPrimsGenerator();
        MazeGenerator mazeGenerator= new RecursiveBacktrackerGenerator();
        //MazeGenerator mazeGenerator = new GrowingTreeGenerator();
        mazeGenerator.generateMaze(maze);

        maze.draw();

        MazeSolver mazeSolver= new WallFollowerSolver();
        //MazeSolver mazeSolver= new BiDirectionalRecursiveBacktrackerSolver();
        mazeSolver.solveMaze(maze);


        System.out.println("If perfect: "+maze.isPerfect());
        System.out.println("If solved: "+maze.validate());


    }
}
