
/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (row, col).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param row The row of current point
     * @param col The col of current point
     * @return If a path through (row, col) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int row, int col) {

        if (row >= maze.getNRows() || col >= maze.getNCols() || row < 0 || col < 0){
            return false;
        }
        else if (!maze.getColor(row,col).equals(GridColors.BACKGROUND)) {
            return false;
        }
        else if (row == maze.getNRows() - 1 && col == maze.getNCols() - 1){
            maze.recolor(row, col, GridColors.PATH);
            return true;
        }

        else{
            maze.recolor(row,col,GridColors.PATH);
            try {
                if (findMazePath(row - 1, col) || findMazePath(row + 1, col) || findMazePath(row, col - 1) || findMazePath(row, col + 1)) {
                    return true;
                } else {
                    maze.recolor(row, col, GridColors.TEMPORARY);
                    return false;
                }
            }
            catch (Exception e) {}
        }

        return false;

    }

    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }

    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
    }
}

