public class ArrayLongIntList implements LongIntList<ArrayEntry> {
    private ArrayEntry[] entries;

    /**
     * Constructor
     */
    ArrayLongIntList() {
        this.entries = new ArrayEntry[1];
    }

    /**
     * Inserts an integer value at the beginning of entries[]
     *
     * @param value int
     */
    public void insertFirst( int value ) {
        if ( null == this.first() ) {
            this.entries[0] = new ArrayEntry( value, 0 );

            return;
        }

        // Create new array of ArrayEntry type
        ArrayEntry[] newEntries = new ArrayEntry[this.size() + 1];

        // Set value at first index of array
        newEntries[0] = new ArrayEntry( value, 0 );

        for ( ArrayEntry entry : this.entries ) {
            newEntries[entry.getIndex() + 1] = entry;
            newEntries[entry.getIndex() + 1].setIndex( entry.getIndex() + 1 );
        }

        this.entries = newEntries;
    }

    /**
     * Inserts an integer value at the end of entries[]
     *
     * @param value int
     */
    public void insertLast( int value ) {
        if ( null == this.first() ) {
            this.entries[0] = new ArrayEntry( value, 0 );

            return;
        }

        // Create new array of ArrayEntry type
        ArrayEntry[] newEntries = new ArrayEntry[this.size() + 1];

        // Set value at last index of array
        newEntries[newEntries.length - 1] = new ArrayEntry( value, newEntries.length - 1 );

        for ( ArrayEntry entry : this.entries ) {
            newEntries[entry.getIndex()] = entry;
            newEntries[entry.getIndex()].setIndex( entry.getIndex() );
        }

        this.entries = newEntries;
    }

    /**
     * Get the value at the first index of the 'entries' array
     *
     * @return int
     */
    public ArrayEntry first() {
        return this.entries[0];
    }

    /**
     * Get the value at the last index of the 'entries' array
     *
     * @return int
     */
    public ArrayEntry last() {
        return this.entries[this.entries.length - 1];
    }

    /**
     * Check if p's index is that the beginning of the array
     *
     * @param p ArrayEntry
     * @return int
     */
    public boolean isFirst( ArrayEntry p ) {
        return 0 == p.getIndex();
    }

    /**
     * Check if p's index is that the end of the array
     *
     * @param p ArrayEntry
     * @return int
     */
    public boolean isLast( ArrayEntry p ) {
        return p.getIndex() == ( this.entries.length - 1 );
    }

    /**
     * Get the previous value in the 'entries' array
     *
     * @param p ArrayEntry
     * @return int
     */
    public ArrayEntry before( ArrayEntry p ) {
        return ( p.getIndex() > 0 ) ? this.entries[p.getIndex() - 1] : null;
    }

    /**
     * Get the next value in entries[]
     *
     * @param p ArrayEntry
     * @return int
     */
    public ArrayEntry after( ArrayEntry p ) {
        return ( p.getIndex() < ( this.entries.length - 1 ) ) ? this.entries[p.getIndex() + 1] : null;
    }

    /**
     * Check if entries[] is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return ( this.entries.length == 0 ) || this.isDeepEmpty();
    }

    /**
     * Deep check of all values in entries[]
     *
     * @return boolean
     */
    private boolean isDeepEmpty() {
        for ( ArrayEntry entry : this.entries ) {
            if ( entry != null ) {
                return false;
            }
        }

        return true;
    }

    /**
     * Get size of entries[]
     *
     * @return int
     */
    public int size() {
        return this.entries.length;
    }

    public static void main( String args[] ) {
        ArrayLongIntList list = new ArrayLongIntList();

        list.insertFirst( 1 );
        list.insertLast( 10 );
        list.insertFirst( 5 );

        int size = list.size();
        System.out.println( "First entry in the list: " + list.first().getValue() );
        System.out.println( "Last entry in the list: " + list.last().getValue() );

        ArrayEntry iterator = list.first();

        while ( iterator != null ) {
            System.out.println( "Entry #" + iterator.getIndex() + " in the list: " + iterator.getValue() );
            iterator = list.after( iterator );
        }
    }
}