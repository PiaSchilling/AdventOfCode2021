package Puzzle5;

public class DiagonalLine extends Line{

    private final Coordinate coordinate1;
    private final Coordinate coordinate2;

    //1 for positive -1 for negative gradient
    private int gradient;


    public DiagonalLine(Coordinate coordinate1, Coordinate coordinate2) {
        super(coordinate1, coordinate2);

        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;

        computeGradient();
    }

    @Override
    public int getLineLength() {
        //makes no sense here
        return 0;
    }

    /**
     * the coordinate which is "higher" (has a smaller y value) is considered as beginning
     * @return the beginningCoordinate
     */
    @Override
    public Coordinate getBeginningCoordinate() {
        if(coordinate1.getY()< coordinate2.getY()){
            return coordinate1;
        }
        return coordinate2;
    }

    /**
     * get bigger y value
     * @return  bigger y value
     */
    public Coordinate getEndCoordinate(){
        if(coordinate1.getY() > coordinate2.getY()){
            return coordinate1;
        }
        return coordinate2;
    }

    /**
     * compute the gradient of the diagonal line
     */
    private void computeGradient(){
        int m = (coordinate2.getY() - coordinate1.getY())/(coordinate2.getX()-coordinate1.getX());
        if(m > 0){
            this.gradient = 1;
        }else {
            this.gradient = -1;
        }
    }

    public int getGradient() {
        return gradient;
    }
}
