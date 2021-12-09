package Puzzle5;

/**
 * create a horizontal line when y1 == y2
 */
public class HorizontalLine extends Line{

    private final Coordinate coordinate1;
    private final Coordinate coordinate2;

    //both coordinate of the line have the same y value -> this is the level/y-achsenabschnitt of the line
    private final int yLevel;

    public HorizontalLine(Coordinate coordinate1, Coordinate coordinate2) {
        super(coordinate1, coordinate2);

        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;

        this.yLevel = coordinate1.getY();
    }

    @Override
    public int getLineLength() {
        return Math.abs(coordinate1.getX()-coordinate2.getX());
    }

    /**
     * Beginning means in this case which xValue is smaller
     * @return the coordinate which marks the beginning of the line
     */
    @Override
    public Coordinate getBeginningCoordinate() {
        if(coordinate1.getX() < coordinate2.getX()){
            return coordinate1;
        }
        return coordinate2;
    }

    public int getYLevel(){
        return this.yLevel;
    }
}
