package Puzzle9;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, steppedOver);
    }
}
