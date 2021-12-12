package Puzzle9;

public class Coordinate {

    private int row;
    private int col;

    private int steppedOver = 0;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setSteppedOver() {
        this.steppedOver++;
    }

    public int getSteppedOver() {
        return steppedOver;
    }
}
