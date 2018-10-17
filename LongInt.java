public class LongInt {

    private final ArrayLongIntList list = new ArrayLongIntList();
    private boolean isNegative;
    private int digitCount;

    /**
     * Main constructor for creating a LongInt by passing in a String of digits
     *
     * @param s String
     */
    public LongInt( String s ) {
        this.isNegative = ( '-' == s.charAt( 0 ) );
        s = this.isNegative ? s.substring( 1 ) : s;

        this.digitCount = s.length();

        if ( this.digitCount < 8 ) {
            this.list.insertFirst( Integer.parseInt( s ) );

            return;
        }

        for ( int i = s.length(); i > 0; i -= 8 ) {
            String node = ( i >= 8 ) ? s.substring( i - 8, i ) : s.substring( 0, i );

            this.list.insertLast( Integer.parseInt( node ) );
        }
    }

    /**
     * Default constructor
     */
    private LongInt() {
        this.digitCount = 0;
        this.isNegative = false;
    }

    /**
     * Constructor that allows you to set whether the LongInt is positive or negative
     *
     * @param isNeg {boolean}
     */
    private LongInt( boolean isNeg ) {
        this();
        this.isNegative = isNeg;
    }

    /**
     * Constructor for 'deep' cloning of a LongInt
     *
     * @param l          The list of nodes that are to be iterated ane duplicated
     * @param digitCount The number of digits of the LongInt that's being duplicated
     */
    private LongInt( LongIntList<ArrayEntry> l, int digitCount ) {
        ArrayEntry iterator = l.first();

        while ( iterator != null ) {
            this.list.insertLast( iterator.getValue() );
            iterator = l.after( iterator );
        }

        this.digitCount = digitCount;
        this.isNegative = false;
    }

    public LongInt duplicate( LongInt i ) {

        String duplicate = i.stringify();

        return new LongInt( duplicate );
    }

    public String stringify() {

        if ( 0 == this.list.size() ) {
            return "0";
        }

        String longIntString = this.isNegative() ? "-" : "";

        ArrayLongIntList list = this.list;
        ArrayEntry node = list.last();


        while ( null != node ) {
            int nodeValue = node.getValue();

            if ( ( this.list.size() - 1 ) != node.getIndex() ) {
                int numberOfZeros = 8 - LongIntUtils.digits( nodeValue );

                for ( int i = 0; i < numberOfZeros; i++ ) {
                    longIntString += "0";
                }
            }

            longIntString += "" + node.getValue();
            node = list.before( node );
        }

        return longIntString;
    }

    public void print() {
        System.out.println( this.stringify() );
    }

    /**
     * Getter of LongInt's negative value
     *
     * @return boolean
     */
    public boolean isNegative() {
        return this.isNegative;
    }

    /**
     * Setter of LongInt's negative value
     *
     * @param sign boolean
     */
    private void setToNegative( boolean sign ) {
        this.isNegative = sign;
    }

    /**
     * Getter of LongInt's digit count
     *
     * @return int
     */
    public int getDigitCount() {
        return this.digitCount;
    }

    /**
     * Flat and 'deep' check for LongInt equality
     *
     * @param i LongInt
     * @return boolean
     */
    public boolean equalTo( LongInt i ) {

        // Compare signs
        if ( this.isNegative() != i.isNegative() ) {
            return false;
        }

        // Compare the number of digits
        if ( this.getDigitCount() != i.getDigitCount() ) {
            return false;
        }

        ArrayEntry iteratorThis = this.list.first();
        ArrayEntry iteratorI = i.list.first();

        if ( iteratorI.getValue() != iteratorThis.getValue() ) {
            return false;
        }

        while ( iteratorThis != null ) {
            if ( iteratorThis.getValue() != iteratorI.getValue() ) {
                return false;
            }

            iteratorThis = this.list.after( iteratorThis );
            iteratorI = i.list.after( iteratorI );
        }

        return true;
    }

    /**
     * Check if 'LongInt this < LongInt i' is true
     *
     * @param i LongInt
     * @return boolean
     */
    public boolean lessThan( LongInt i ) {

        // Compare signs
        if ( this.isNegative() != i.isNegative() ) {
            return this.isNegative();
        }

        // Compare the number of digits
        if ( this.getDigitCount() != i.getDigitCount() ) {
            return this.isNegative() ? !( this.getDigitCount() < i.getDigitCount() ) : !( this.getDigitCount() > i.getDigitCount() );
        }

        ArrayEntry iteratorThis = this.list.last();
        ArrayEntry iteratorI = i.list.last();

        // Do a deep comparison of all the nodes in their respective list[]
        while ( iteratorThis != null ) {
            if ( iteratorThis.getValue() > iteratorI.getValue() && !this.isNegative() ) {
                return false;
            }

            if ( iteratorThis.getValue() < iteratorI.getValue() && this.isNegative() ) {
                return false;
            }

            iteratorThis = this.list.before( iteratorThis );
            iteratorI = i.list.before( iteratorI );
        }

        return ( this.list.first().getValue() != i.list.first().getValue() );
    }

    /**
     * Checks if 'LongInt this > LongInt i' is true
     *
     * @param i LongInt
     * @return boolean
     */
    public boolean greaterThan( LongInt i ) {

        // Compare signs
        if ( this.isNegative() != i.isNegative() ) {
            return !this.isNegative();
        }

        // Compare the number of digits
        if ( this.getDigitCount() != i.getDigitCount() ) {
            return this.isNegative() ? this.getDigitCount() < i.getDigitCount() : this.getDigitCount() > i.getDigitCount();
        }

        ArrayEntry iteratorThis = this.list.last();
        ArrayEntry iteratorI = i.list.last();

        // Do a deep comparison of all the nodes in their respective list[]
        while ( iteratorThis != null ) {
            if ( iteratorThis.getValue() < iteratorI.getValue() && !this.isNegative() ) {
                return false;
            }

            if ( iteratorThis.getValue() > iteratorI.getValue() && this.isNegative() ) {
                return false;
            }

            iteratorThis = this.list.before( iteratorThis );
            iteratorI = i.list.before( iteratorI );
        }

        return ( this.list.first().getValue() != i.list.first().getValue() );
    }

    /**
     * Perform addition of two LongInts
     *
     * @param i LongInt
     * @return solution LongInt
     */
    public LongInt add( LongInt i ) {

        // Operations on empty list just returns
        if ( null == this.list.first() ) {
            return new LongInt( i.list, i.getDigitCount() );
        }

        // Operations on empty list just returns
        if ( null == i.list.first() ) {
            return new LongInt( this.list, this.getDigitCount() );
        }

        // Perform subtraction if 'this' or 'i' is negative
        if ( this.isNegative() != i.isNegative() ) {
            LongInt tmp = this.isNegative() ? new LongInt( this.list, this.getDigitCount() ) : new LongInt( i.list, i.getDigitCount() );

            return this.isNegative() ? i.subtract( tmp ) : this.subtract( tmp );
        }

        LongInt solution;

        int nodeSum, overflow = 0;

        ArrayLongIntList innerList = ( this.list.size() <= i.list.size() ) ? this.list : i.list;
        ArrayLongIntList outerList = ( this.list.size() <= i.list.size() ) ? i.list : this.list;

        solution = this.isNegative() ? new LongInt( this.isNegative() ) : new LongInt();

        ArrayEntry outerListNode = outerList.first();
        ArrayEntry innerListNode = innerList.first();

        while ( null != innerListNode ) {
            nodeSum = ( overflow > 0 ) ? innerListNode.getValue() + outerListNode.getValue() + overflow : innerListNode.getValue() + outerListNode.getValue();

            overflow = LongIntUtils.digits( nodeSum ) > 8 ? LongIntUtils.overflow( nodeSum ) : 0;

            solution.list.insertLast( LongIntUtils.underflow( nodeSum ) );

            innerListNode = innerList.after( innerListNode );
            outerListNode = outerList.after( outerListNode );
        }

        if ( this.list.size() == i.list.size() && overflow > 0 ) {
            solution.list.insertLast( overflow );

            return solution;
        }

        // Append the rest of the outer iterator
        while ( null != outerListNode ) {
            boolean hasOverflow = ( overflow > 0 );
            int outerValue = hasOverflow ? outerListNode.getValue() + overflow : outerListNode.getValue();

            solution.list.insertLast( outerValue );

            if ( hasOverflow ) {
                overflow = 0;
            }

            outerListNode = outerList.after( outerListNode );
        }

        solution.recalculateDigitCount();

        return solution;
    }

    /**
     * Perform subtraction of two LongInts
     *
     * @param i LongInt
     * @return solution
     */
    public LongInt subtract( LongInt i ) {

        LongInt solution = new LongInt();

        // Perform addition if either LongInts are negative
        if ( this.isNegative() != i.isNegative() ) {
            LongInt tmp = this.isNegative() ? new LongInt( this.list, this.getDigitCount() ) : new LongInt( i.list, i.getDigitCount() );
            solution = this.isNegative() ? i.add( tmp ) : this.add( tmp );

            if ( this.isNegative() ) {
                solution.setToNegative( true );
            }

            return solution;
        }

        if ( this.equalTo( i ) ) {
            return new LongInt( "0" );
        }

        if ( this.isNegative() ) {
            LongInt temp_this = new LongInt( this.list, this.getDigitCount() );
            LongInt temp_i = new LongInt( i.list, i.getDigitCount() );

            solution = ( this.greaterThan( i ) ) ? temp_this.subtract( temp_i ) : temp_i.subtract( temp_this );

            if ( this.greaterThan( i ) ) {
                solution.setToNegative( false );
            }

            if ( this.lessThan( i ) ) {
                solution.setToNegative( true );
            }

            return solution;
        }

        int nodeDiff;
        boolean carry = false;

        boolean isCallingLongIntGreater = this.greaterThan( i );

        ArrayLongIntList outerList = isCallingLongIntGreater ? this.list : i.list;
        ArrayLongIntList innerList = isCallingLongIntGreater ? i.list : this.list;

        ArrayEntry outerNode = outerList.first();
        ArrayEntry innerNode = innerList.first();

        while ( innerNode != null ) {

            if ( carry ) {
                if ( ( outerNode.getValue() < innerNode.getValue() ) && ( outerList.after( innerNode ) != null ) ) {
                    nodeDiff = ( ( 100000000 + outerNode.getValue() ) - 1 ) - innerNode.getValue();

                    solution.list.insertLast( nodeDiff );
                } else {
                    carry = false;
                    nodeDiff = ( outerNode.getValue() - 1 ) - innerNode.getValue();

                    solution.list.insertLast( nodeDiff );
                }
            } else {
                if ( ( outerNode.getValue() < innerNode.getValue() ) && ( outerList.after( outerNode ) != null ) ) {
                    carry = true;
                    nodeDiff = ( 100000000 + outerNode.getValue() ) - innerNode.getValue();

                    solution.list.insertLast( nodeDiff );
                } else {
                    nodeDiff = outerNode.getValue() - innerNode.getValue();

                    if ( !outerList.isLast( outerNode ) && ( nodeDiff > 0 ) ) {

                        solution.list.insertLast( nodeDiff );

                    } else if ( outerList.size() == innerList.size() && nodeDiff != 0 ) {
                        solution.list.insertLast( nodeDiff );
                    }
                }
            }

            outerNode = outerList.after( outerNode );
            innerNode = innerList.after( innerNode );
        }

        while ( outerNode != null ) {

            int nodeValue = carry ? outerNode.getValue() - 1 : outerNode.getValue();

            if ( carry ) {
                carry = false;
            }

            solution.list.insertLast( nodeValue );
            outerNode = outerList.after( outerNode );
        }

        solution.recalculateDigitCount();

        return solution;
    }

    /**
     * Perform multiplication utilizing the karatsuba method and additions
     *
     * @param i The LongInt that is to be multiplied to calling LongInt
     * @return The product of two LongInts
     */
    public LongInt multiply( LongInt i ) {

        LongInt result = new LongInt( "0" );

        int zerosThis = 0; // # of nodes with padded zeros for this
        int zerosI = 0; // # of nodes with padded zeros for i

        boolean isThisGreaterThanI = this.greaterThan( i );

        ArrayLongIntList outerList = isThisGreaterThanI ? this.list : i.list;
        ArrayLongIntList innerList = isThisGreaterThanI ? i.list : this.list;

        ArrayEntry outerNode = outerList.first();
        ArrayEntry innerNode = innerList.first();

        while ( outerNode != null ) {
            while ( innerNode != null ) {
                result = result.add( karatsuba( outerNode.getValue(), innerNode.getValue(), zerosI ) );
                zerosI += 1;
                innerNode = innerList.after( innerNode );
            }

            outerNode = outerList.after( outerNode );
            innerNode = innerList.first();
            zerosThis += 1;
            zerosI = zerosThis;
        }

        if ( this.isNegative() != i.isNegative() ) {
            result.setToNegative( true );
        }

        result.recalculateDigitCount();

        return result;
    }

    /**
     * Recalculate digit count to ensure validity of digitCount class member
     */
    private void recalculateDigitCount() {
        ArrayEntry i = this.list.first();
        int c = 0;

        while ( i != null ) {
            int d = LongIntUtils.digits( i.getValue() );

            if ( d < 8 && i != this.list.last() ) {
                c += 8 - d;
            }

            c += d;
            i = this.list.after( i );
        }

        this.digitCount = c;
    }

    /**
     * Performs the karatsuba algorithm on two integers of the same length (in this use case <= 8 digits)
     * see: https://en.wikipedia.org/wiki/Karatsuba_algorithm
     *
     * @param c int
     * @param d int
     * @param n int
     * @return LongInt
     */
    private static LongInt karatsuba( int c, int d, int n ) {
        LongInt result = new LongInt( false );

        // Upper and low halves of 'c'
        int cUpperHalf = LongIntUtils.upperHalf( c );
        int cLowerHalf = LongIntUtils.lowerHalf( c );

        // Upper and low halves of 'd'
        int dUpperHalf = LongIntUtils.upperHalf( d );
        int dLowerHalf = LongIntUtils.lowerHalf( d );

        int z1 = cUpperHalf * dUpperHalf;
        int z3 = cLowerHalf * dLowerHalf;

        int z2 = ( cUpperHalf + cLowerHalf ) * ( dUpperHalf + dLowerHalf ) - z1 - z3;

        int overflow = LongIntUtils.overflow( z2 );

        int v1 = z1 + LongIntUtils.upperHalf( z2 );
        int v2 = ( 10000 * LongIntUtils.lowerHalf( z2 ) ) + z3;

        for ( int i = 0; i < n; i++ ) {
            result.list.insertLast( 0 );
        }

        if ( overflow > 0 ) {
            result.list.insertLast( LongIntUtils.underflow( v2 ) );
            result.list.insertLast( v1 + overflow + overflow * 10000 );
        } else {
            if ( LongIntUtils.overflow( v2 ) > 0 ) {
                result.list.insertLast( LongIntUtils.underflow( v2 ) );
                v1 = v1 + LongIntUtils.overflow( v2 );
            } else {
                result.list.insertLast( v2 );
            }

            if ( v1 > 0 ) {
                result.list.insertLast( v1 );
            }
        }

        return result;
    }

    /**
     * Perform exponentiation of LongInt
     *
     * @param p int
     * @return LongInt
     */
    public LongInt power( int p ) {
        if ( p == 0 ) {
            return new LongInt( "1" );
        }

        if ( p == 1 ) {
            return this;
        }

        if ( ( p % 2 ) == 0 ) {
            return this.multiply( this ).power( p / 2 );
        }

        return this.multiply( this.multiply( this ).power( ( p - 1 ) / 2 ) );
    }

    /**
     * Prints each node of the LongInt
     * ex: Node 1: 00000000   Node 2: 00000000 .......
     */
    public void printNodes() {
        ArrayEntry node = this.list.first();
        int counter = 1;

        while ( node != null ) {
            System.out.print( "Node " + counter++ + ": " );

            if ( LongIntUtils.digits( node.getValue() ) < 8 ) {
                for ( int i = 0; i < 8 - LongIntUtils.digits( node.getValue() ); i++ ) {
                    System.out.print( 0 );
                }
            }

            System.out.print( "" + node.getValue() + "\t" );
            node = this.list.after( node );
        }

        System.out.println( "\n" );
    }
}
