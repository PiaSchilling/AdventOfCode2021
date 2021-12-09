package Puzzle5;

/**
 * create a VerticalLine when x1 == x2
 */
public class VerticalLine extends Line{

    private final Coordinate coordinate1;
    private final Coordinate coordinate2;

    //both coordinates have the same x value  -> this is the level/x-achsenabschnitt of the line
    private final int xLevel;

    public VerticalLine(Coordinate coordinate1, Coordinate coordinate2) {
        super(coordinate1, coordinate2);

        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;

        this.xLevel = coordinate1.getX();
    }

    /**
     * Beginning means in this case which coordinate has the bigger y value so which coordinate is further down on the board
     * @return the coordinate which marks the beginning of the line
     */
    @Override
    public Coordinate getBeginningCoordinate() {
        if(coordinate1.getY() < coordinate2.getY()){
            return coordinate1;
        }
        return coordinate2;
    }

    /**
     * get the absolute length of the line
     * @return the lenght of the line
     */
    @Override
    public int getLineLength() {
        return Math.abs(coordinate1.getY()-coordinate2.getY());
    }

    /**
     * @return the xLevel
     */
    public int getXLevel(){
        return this.xLevel;
    }
}
