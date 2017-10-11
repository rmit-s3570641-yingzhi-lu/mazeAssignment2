import maze.HexMaze;
import maze.Maze;
import maze.NormalMaze;
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

        Maze maze=new NormalMaze();
        //Maze maze = new HexMaze();
        List<int[]> tunnelList = new ArrayList<>();

        maze.initMaze(10,10,0,0,9,2,tunnelList);

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
