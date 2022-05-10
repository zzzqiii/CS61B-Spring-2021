package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: ZUO Zhangqi
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }


    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * 重点在于找到tile能够移动的位置，用findNestStep方法。如果下一步移动的位置非空，那么要加分
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        board.setViewingPerspective(side);
        for (int i = 0; i < board.size(); i ++){
            int merged = -1;
            for (int j = board.size() - 2; j >= 0; j -= 1){
                if (board.tile(i, j) != null) {
                    Tile t = board.tile(i, j);
                    int nextStep = findNestStep(t, j + 1, j, merged, i);
                    if (nextStep != j){
                        if (board.tile(i, nextStep) != null) {
                            merged = nextStep;
                            score += (t.value() * 2);
                        }
                        board.move(i, nextStep, t);
                        changed = true;
                    }
                }
            }
        }
        board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** find the next step to move *
     * t表示往上推的格子
     * line表示往上移动方块时的下界，从方块的上面一个格子到上边界是它可以移动的范围
     * nextStep记录可以移动的位置，初始值为方块最开始所在的格子
     * merged表示由合并得到的格子
     * col 表示方块所在的列，注意不能用t.col()表示，因为这样返回的是原来视角的值
     * 如果方块已经在最上方，即line为board size，那么不用移动直接返回
     * 否则遍历上方格子
     * 如果碰到空格，那么将nextStep更新为空格，继续以空格为下界找能够继续移动的地方
     * 如果不是空格，且格子的value和方块的value相等，且格子不是由合并得到的，那么可以直接合并
     * 如果这两种情况都没有碰到那么返回初始的nextStep
     */
    private int findNestStep(Tile t, int line, int nextStep, int merged, int col) {
        if (line == board.size())   return nextStep;
        for (int r = line; r < board.size(); r ++) {
            Tile t1 = board.tile(col, r);
            if (t1 == null) {
                return findNestStep(t, r + 1, r, merged, col);
            }
            else if (t1.value() == t.value() && r != merged){
                return r;
            }
        }
        return nextStep;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        int s = b.size();
        for (int i = 0; i < s; i = i + 1){
            for (int j = 0; j < s; j = j + 1){
                if (b.tile(i, j) == null)
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        int MAX_PIECE = 2048, s = b.size();
        for (int i = 0; i < s; i ++){
            for (int j = 0; j < s; j ++){
                if (b.tile(i, j) != null && b.tile(i, j).value() == MAX_PIECE)
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        if (emptySpaceExists(b))
            return  true;
        int s = b.size();
        for (int i = 0; i < s; i ++){
            for (int j = 0; j < s; j ++){
                int nextI = i + 1, nextJ = j + 1;
                if (b.tile(i, j) != null && nextI < s && b.tile(nextI, j) != null && b.tile(i, j).value() == b.tile(nextI, j).value())
                    return true;
                if (b.tile(i, j) != null && nextJ < s && b.tile(i, nextJ) != null && b.tile(i, j).value() == b.tile(i, nextJ).value())
                    return true;
            }
        }

        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
