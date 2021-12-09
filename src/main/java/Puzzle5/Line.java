package Puzzle5;

public abstract class Line {

    private final Coordinate coordinate1;
    private final Coordinate coordinate2;

    public Line(Coordinate coordinate1, Coordinate coordinate2){
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
    }

    public Coordinate getCoordinate1() {
        return coordinate1;
    }

    public Coordinate getCoordinate2() {
        return coordinate2;
    }

    public abstract int getLineLength();

    @Override
    public String toString() {
        return "Line{" +
                "coordinate1=" + coordinate1.getX() + "," + coordinate1.getY() +
                ", coordinate2=" + coordinate2.getX() + "," + coordinate2.getY() +
                '}';
    }

    public abstract Coordinate getBeginningCoordinate();
}
