package Puzzle4;

public class BingoDigit {

    private final int value;
    private boolean marked;

    public BingoDigit(int value){
        this.value = value;
        this.marked =false;
    }

    public int getValue() {
        return value;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(){
        this.marked = true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
