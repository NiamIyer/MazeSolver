/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */

    public ArrayList<MazeCell> getSolution() {
        // Initialize new stack and array
        Stack<MazeCell> cells = new Stack<MazeCell>();
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        // Stores end cell
        MazeCell end = maze.getEndCell();
        // While the cell isn't the beginning
        while (end != maze.getStartCell()) {
            // Add the cell
            cells.push(end);
            // Look at the parent cell next
            end = end.getParent();
        }
        // Add the moves to the ArrayList and return the ArrayList
        int i = 0;
        int length = cells.size();
        while (i < length) {
            solution.add(cells.pop());
            i++;
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Create Stack
        Stack<MazeCell> cells = new Stack<MazeCell>();
        // Save start cell and end cell
        MazeCell currentCell = maze.getStartCell();
        MazeCell end = maze.getEndCell();
        // Push start cell to the stack and mark the cell as explored
        cells.push(currentCell);
        currentCell.setExplored(true);
        // While the stack isn't empty
        while (!cells.isEmpty()) {
            // Set current cell to the top of the stack
            currentCell = cells.pop();
            // If it is the end return the solution found in getSolution
            if (currentCell.equals(end)) {
                return getSolution();
            }
            // Checks North, East, South, West
            if (maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol())) {
                // Stores neighbor cell to set its parent and set explored, adds to stack
                MazeCell neighbor = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                neighbor.setParent(currentCell);
                neighbor.setExplored(true);
                cells.push(neighbor);
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1)) {
                MazeCell neighbor = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                neighbor.setParent(currentCell);
                neighbor.setExplored(true);
                cells.push(neighbor);
            }
            if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol())) {
                MazeCell neighbor = maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                neighbor.setParent(currentCell);
                neighbor.setExplored(true);
                cells.push(neighbor);
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1)) {
                MazeCell neighbor = maze.getCell(currentCell.getRow(), currentCell.getCol() -1);
                neighbor.setParent(currentCell);
                neighbor.setExplored(true);
                cells.push(neighbor);
            }
        }
        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Creates Queue to store MazeCells
        Queue<MazeCell> cells = new LinkedList<MazeCell>();
        // Stores beginning and end cells
        MazeCell currentCell = maze.getStartCell();
        MazeCell end = maze.getEndCell();
        // Adds current cell (start) to queue
        cells.add(currentCell);
        // Sets the start cell as explored
        currentCell.setExplored(true);
        // While the queue isn't empty
        while (!cells.isEmpty()) {
            // Sets current cell to first in queue
            currentCell = cells.poll();
            // If it is the end, return the getSolution solution
            if (currentCell.equals(end)) {
                return getSolution();
            }
            // Checks North, East, South, West
            if (maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol())) {
                // Saves neighbor to set the parent and set explored
                MazeCell neighbor = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                neighbor.setParent(currentCell);
                neighbor.setExplored(true);
                // Adds the neighbor to the queue
                cells.add(neighbor);
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1)) {
                MazeCell neighbor = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                neighbor.setParent(currentCell);
                neighbor.setExplored(true);
                cells.add(neighbor);
            }
            if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol())) {
                MazeCell neighbor = maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                neighbor.setParent(currentCell);
                neighbor.setExplored(true);
                cells.add(neighbor);
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1)) {
                MazeCell neighbor = maze.getCell(currentCell.getRow(), currentCell.getCol() - 1);
                neighbor.setParent(currentCell);
                neighbor.setExplored(true);
                cells.add(neighbor);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
