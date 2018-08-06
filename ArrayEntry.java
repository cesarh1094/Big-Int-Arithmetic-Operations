public class ArrayEntry extends Position {
    private int index;

    /**
     * Constructor that sets 'index' member and calls parent class
     *
     * @param value int
     * @param index int
     */
    ArrayEntry( int value, int index ) {
        super( value );
        this.index = index;
    }

    /**
     * Gets value of 'index' class member
     *
     * @return int
     */
    int getIndex() {
        return this.index;
    }

    /**
     * Sets value of 'index' class member
     *
     * @param index int
     */
    void setIndex( int index ) {
        this.index = index;
    }
}
