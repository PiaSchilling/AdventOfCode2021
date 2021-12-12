package Puzzle9;

public class SpecialInt {

    private int value;
    private int steppedOver = 0;
    private boolean blocked = false;

    public SpecialInt(int value){
        this.value = value;
    }

    public void incrementSteppedOver(){
        steppedOver++;

        if(steppedOver == 2){
            blocked = true;
        }
    }

    public int getValue() {
        return value;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public int getSteppedOver() {
        return steppedOver;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
