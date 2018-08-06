public class LongInt {

    // DO NOT CHANGE OR REMOVE THIS LINE (UNTIL STEP 3)
    // This will give a warning about raw types, but you can ignore it for this project
//    private final LongIntList list = new SLLLongIntList();

    // USE THIS LINE IN STEP 3
    private final LongIntList<ArrayEntry> list = new ArrayLongIntList();

    private boolean isNegative;

    private int digitCount;

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
     * @param l          LongIntList<ArrayEntry>
     * @param digitCount int
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

    /**
     * Main constructor for creating a LongInt by passing in a String of digits
     *
     * @param s String
     */
    private LongInt( String s ) {

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
     * Prints LongInt starting from the end of the list
     */
    private void printLongInt() {
        ArrayEntry iterator = this.list.last();

        if ( iterator == null ) {
            System.out.println( "LongInt is empty" );
            return;
        }

        if ( this.isNegative() ) {
            System.out.print( "-" );
        }

        while ( iterator != null ) {
            if ( ( iterator != this.list.last() ) && ( LongIntUtils.digits( iterator.getValue() ) < 8 ) ) {
                for ( int i = 0; i < 8 - LongIntUtils.digits( iterator.getValue() ); i++ ) {
                    System.out.print( 0 );
                }
            }

            System.out.print( iterator.getValue() );
            iterator = this.list.before( iterator );
        }

        System.out.println( "\n" ); // line break
    }

    /**
     * Getter of LongInt's negative value
     *
     * @return boolean
     */
    private boolean isNegative() {
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
    private int getDigitCount() {
        return this.digitCount;
    }

    /**
     * Flat and 'deep' check for LongInt equality
     *
     * @param i LongInt
     * @return boolean
     */
    private boolean equalTo( LongInt i ) {
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
    private boolean lessThan( LongInt i ) {
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
    private boolean greaterThan( LongInt i ) {
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
    private LongInt add( LongInt i ) {
        LongInt solution;

        // Operations on empty list just returns
        if ( this.list.first() == null ) {
            solution = new LongInt( i.list, i.getDigitCount() );

            return solution;
        }

        // Operations on empty list just returns
        if ( i.list.first() == null ) {
            solution = new LongInt( this.list, this.getDigitCount() );

            return solution;
        }

        int nodeSum, overflow = 0;

        // Perform subtraction if either LongInts are negative
        if ( this.isNegative() != i.isNegative() ) {
            LongInt tmp = this.isNegative() ? new LongInt( this.list, this.getDigitCount() ) : new LongInt( i.list, i.getDigitCount() );
            solution = this.isNegative() ? i.subtract( tmp ) : this.subtract( tmp );

            return solution;
        }

        // Set outer and inner iterators based on size of their lists
        ArrayEntry inner = ( this.list.size() <= i.list.size() ) ? this.list.first() : i.list.first();
        ArrayEntry outer = ( this.list.size() <= i.list.size() ) ? i.list.first() : this.list.first();

        solution = this.isNegative() ? new LongInt( this.isNegative() ) : new LongInt();

        while ( inner != null ) {
            nodeSum = overflow > 0 ? inner.getValue() + outer.getValue() + overflow : inner.getValue() + outer.getValue();

            overflow = LongIntUtils.digits( nodeSum ) > 8 ? LongIntUtils.overflow( nodeSum ) : 0;

            solution.list.insertLast( LongIntUtils.underflow( nodeSum ) );

            inner = ( this.list.size() <= i.list.size() ) ? this.list.after( inner ) : i.list.after( inner );
            outer = ( this.list.size() <= i.list.size() ) ? i.list.after( outer ) : this.list.after( outer );
        }

        if ( this.list.size() == i.list.size() && overflow > 0 ) {
            solution.list.insertLast( overflow );

            return solution;
        }

        // Append the rest of the outer iterator
        while ( outer != null ) {
            boolean hasOverflow = ( overflow > 0 );
            int outerVale = hasOverflow ? outer.getValue() + overflow : outer.getValue();

            solution.list.insertLast( outerVale );

            if ( hasOverflow ) {
                overflow = 0;
            }

//            if ( overflow > 0 ) {
//                solution.list.insertLast( outer.getValue() + overflow );
//                overflow = 0;
//            } else {
//                solution.list.insertLast( outer.getValue() );
//            }

            outer = i.list.after( outer );
        }

        return solution;
    }

    /**
     * Perform subtraction of two LongInts
     *
     * @param i LongInt
     * @return solution
     */
    private LongInt subtract( LongInt i ) {

        ArrayEntry iterator_this = this.list.first();
        ArrayEntry iterator_i = i.list.first();

        LongInt solution = new LongInt();
        boolean carry = false;

        int nodeDiff = 0;

        // Perform addition if either LongInts are negative
        if ( this.isNegative() != i.isNegative() ) {
            LongInt tmp = this.isNegative() ? new LongInt( this.list, this.getDigitCount() ) : new LongInt( i.list, i.getDigitCount() );
            solution = this.isNegative() ? i.add( tmp ) : this.add( tmp );

            if ( this.isNegative() ) {
                solution.setToNegative( true );
            }

            return solution;
        }

        if ( this.isNegative == false && i.isNegative == false ) {

            if ( this.equalTo( i ) ) {

                solution = new LongInt();

                solution.list.insertLast( 0 );

                return solution;

            }

            if ( this.lessThan( i ) ) {

                solution = new LongInt( true );

                while ( iterator_this != null ) {

                    if ( carry ) {

                        if ( ( iterator_i.getValue() < iterator_this.getValue() ) && ( i.list.after( iterator_i ) != null ) ) {

                            carry = true;

                            nodeDiff = ( ( 100000000 + iterator_i.getValue() ) - 1 ) - iterator_this.getValue();

                            solution.list.insertLast( nodeDiff );

                        } else {

                            carry = false;

                            nodeDiff = ( iterator_i.getValue() - 1 ) - iterator_this.getValue();

                            solution.list.insertLast( nodeDiff );

                        }
                    } else {

                        if ( ( iterator_i.getValue() < iterator_this.getValue() ) && ( i.list.after( iterator_i ) != null ) ) {

                            carry = true;

                            nodeDiff = ( 100000000 + iterator_i.getValue() ) - iterator_this.getValue();

                            solution.list.insertLast( nodeDiff );

                        } else {

                            carry = false;

                            nodeDiff = iterator_i.getValue() - iterator_this.getValue();

                            if ( ( ( i.list.isLast( iterator_i ) == false ) && ( nodeDiff > 0 ) ) ) {

                                solution.list.insertLast( nodeDiff );

                            } else if ( this.list.size() == i.list.size() && nodeDiff != 0 )

                                solution.list.insertLast( nodeDiff );

                        }

                    }

                    iterator_this = this.list.after( iterator_this );

                    iterator_i = i.list.after( iterator_i );

                }

                while ( iterator_i != null ) {

                    if ( carry ) {

                        solution.list.insertLast( iterator_i.getValue() - 1 );

                        carry = false;

                    } else {

                        solution.list.insertLast( iterator_i.getValue() );

                    }

                    iterator_i = i.list.after( iterator_i );

                }

                return solution;

            }

            if ( this.greaterThan( i ) ) {

                solution = new LongInt();

                while ( iterator_i != null ) {

                    if ( carry ) {

                        if ( ( iterator_this.getValue() < iterator_i.getValue() ) && ( this.list.after( iterator_this ) != null ) ) {

                            carry = true;

                            nodeDiff = ( ( 100000000 + iterator_this.getValue() ) - 1 ) - iterator_i.getValue();

                            solution.list.insertLast( nodeDiff );

                        } else {

                            carry = false;

                            nodeDiff = ( iterator_this.getValue() - 1 ) - iterator_i.getValue();

                            solution.list.insertLast( nodeDiff );

                        }
                    } else {

                        if ( ( iterator_this.getValue() < iterator_i.getValue() ) && ( this.list.after( iterator_this ) != null ) ) {

                            carry = true;

                            nodeDiff = ( 100000000 + iterator_this.getValue() ) - iterator_i.getValue();

                            solution.list.insertLast( nodeDiff );

                        } else {

                            carry = false;

                            nodeDiff = iterator_this.getValue() - iterator_i.getValue();

                            if ( ( this.list.isLast( iterator_this ) == false ) && ( nodeDiff > 0 ) ) {

                                solution.list.insertLast( nodeDiff );
                            } else if ( this.list.size() == i.list.size() && nodeDiff != 0 )

                                solution.list.insertLast( nodeDiff );

                        }

                    }

                    iterator_this = this.list.after( iterator_this );

                    iterator_i = i.list.after( iterator_i );

                }

                while ( iterator_this != null ) {

                    if ( carry ) {

                        solution.list.insertLast( iterator_this.getValue() - 1 );

                        carry = false;

                    } else {

                        solution.list.insertLast( iterator_this.getValue() );

                    }

                    iterator_this = this.list.after( iterator_this );

                }

                return solution;

            }

        }

        if ( this.isNegative == true && i.isNegative == true ) {

            if ( this.equalTo( i ) ) {

                solution = new LongInt();

                solution.list.insertLast( 0 );

                return solution;

            }

            if ( this.greaterThan( i ) ) {

                LongInt temp_this = new LongInt( this.list, this.digitCount );

                temp_this.setToNegative( false );

                LongInt temp_i = new LongInt( i.list, i.digitCount );

                temp_i.setToNegative( false );

                solution = temp_i.subtract( temp_this );

                solution.setToNegative( false );

                return solution;

            }

            if ( this.lessThan( i ) ) {

                LongInt temp_this = new LongInt( this.list, this.digitCount );

                temp_this.setToNegative( false );

                LongInt temp_i = new LongInt( i.list, i.digitCount );

                temp_i.setToNegative( false );

                solution = temp_this.subtract( temp_i );

                solution.setToNegative( true );

                return solution;

            }

        }

        return solution;
    }

    /**
     * @param i LongInt
     * @return LongInt
     */
    private LongInt multiply( LongInt i ) {
        ArrayEntry iteratorThis = this.list.first();
        ArrayEntry iteratorI = i.list.first();

        LongInt result = new LongInt( "0" );

        if ( this.isNegative() != i.isNegative() ) {
            result.setToNegative( true );
        }

        int zerosThis = 0; // # of nodes with padded zeros for this
        int zerosI = 0; // # of nodes with padded zeros for i

        while ( iteratorThis != null ) {
            while ( iteratorI != null ) {
                result = result.add( karatsuba( iteratorThis.getValue(), iteratorI.getValue(), zerosI ) );
                zerosI += 1;
                iteratorI = i.list.after( iteratorI );
            }

            iteratorThis = this.list.after( iteratorThis );
            iteratorI = i.list.first();
            zerosThis += 1;
            zerosI = zerosThis;
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
        LongInt result = new LongInt();

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
    private LongInt power( int p ) {
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
    private void printEachNode() {
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

    public static void main( String[] args ) {

        /* initialization of all Test Cases */

        long startTime_a = System.nanoTime();
        LongInt a = new LongInt( "2222" );
        long duration_a = System.nanoTime() - startTime_a;
        System.out.println( "Initialization of A - Running Time: " + duration_a + " nanoseconds\n" );

        long startTime_b = System.nanoTime();
        LongInt b = new LongInt( "99999999" );
        long duration_b = System.nanoTime() - startTime_b;
        System.out.println( "Initialization of B - Running Time: " + duration_b + " nanoseconds\n" );

        long startTime_c = System.nanoTime();
        LongInt c = new LongInt( "-246813575732" );
        long duration_c = System.nanoTime() - startTime_c;
        System.out.println( "Initialization of C - Running Time: " + duration_c + " nanoseconds\n" );

        long startTime_d = System.nanoTime();
        LongInt d = new LongInt( "180270361023456789" );
        long duration_d = System.nanoTime() - startTime_d;
        System.out.println( "Initialization of D - Running Time: " + duration_d + " nanoseconds\n" );

        long startTime_e = System.nanoTime();
        LongInt e = new LongInt( "1423550000000010056810000054593452907711568359" );
        long duration_e = System.nanoTime() - startTime_e;
        System.out.println( "Initialization of E - Running Time: " + duration_e + " nanoseconds\n" );

        long startTime_f = System.nanoTime();
        LongInt f = new LongInt( "-350003274594847454317890" );
        long duration_f = System.nanoTime() - startTime_f;
        System.out.println( "Initialization of F - Running Time: " + duration_f + " nanoseconds\n" );

        long startTime_g = System.nanoTime();
        LongInt g = new LongInt(
                "29302390234702973402973420937420973420937420937234872349872934872893472893749287423847" );
        long duration_g = System.nanoTime() - startTime_g;
        System.out.println( "Initialization of G - Running Time: " + duration_g + " nanoseconds\n" );

        long startTime_h = System.nanoTime();
        LongInt h = new LongInt( "-98534342983742987342987339234098230498203894209928374662342342342356723423423" );
        long duration_h = System.nanoTime() - startTime_h;
        System.out.println( "Initialization of H - Running Time: " + duration_h + " nanoseconds\n" );

        long startTime_i = System.nanoTime();
        LongInt i = new LongInt(
                "8436413168438618351351684694835434894364351846843435168484351684684315384684313846813153843135138413513843813513813138438435153454154515151513141592654543515316848613242587561516511233246174561276521672162416274123076527612" );
        long duration_i = System.nanoTime() - startTime_i;
        System.out.println( "Initialization of I - Running Time: " + duration_i + " nanoseconds\n" );

        LongInt[] long_ints = new LongInt[9];
        long_ints[0] = a;
        long_ints[1] = b;
        long_ints[2] = c;
        long_ints[3] = d;
        long_ints[4] = e;
        long_ints[5] = f;
        long_ints[6] = g;
        long_ints[7] = h;
        long_ints[8] = i;

        char[] symbol = new char[9];
        symbol[0] = 'A';
        symbol[1] = 'B';
        symbol[2] = 'C';
        symbol[3] = 'D';
        symbol[4] = 'E';
        symbol[5] = 'F';
        symbol[6] = 'G';
        symbol[7] = 'H';
        symbol[8] = 'I';

        /* Step 1: Standard printLongInt: Test case printLongInt(), test case getDigitCount(), test case isNegative(), test case printEachNode() */

        for ( int x = 0; x < long_ints.length; x++ ) {

            System.out.print( "" + symbol[x] + " = " );
            long_ints[x].printLongInt();
            System.out.println( "" + symbol[x] + " Digit Count: " + long_ints[x].getDigitCount() + "\n" );
            System.out.println( "Is '" + symbol[x] + "' negative? " + long_ints[x].isNegative() + "\n" );
            long_ints[x].printEachNode();

        }

        /* Steo 1: LongInUtils test cases */

        int a_int = 2222;
        int b_int = 99999999;

        System.out.println( "Overflow A: " + LongIntUtils.overflow( a_int ) );
        System.out.println( "Overflow B: " + LongIntUtils.overflow( b_int ) );
        System.out.print( "\n" );

        System.out.println( "Underflow A: " + LongIntUtils.underflow( a_int ) );
        System.out.println( "Underflow B: " + LongIntUtils.underflow( b_int ) );
        System.out.print( "\n" );

        System.out.println( "Upperhalf A: " + LongIntUtils.upperHalf( a_int ) );
        System.out.println( "Lowerhalf A: " + LongIntUtils.lowerHalf( a_int ) );
        System.out.print( "\n" );

        System.out.println( "Upperhalf B: " + LongIntUtils.upperHalf( b_int ) );
        System.out.println( "Lowerhalf B: " + LongIntUtils.lowerHalf( b_int ) );
        System.out.print( "\n" );

        System.out.println( "Digits A: " + LongIntUtils.digits( a_int ) );
        System.out.println( "Digits B: " + LongIntUtils.digits( b_int ) );
        System.out.print( "\n" );

        /* Step 1: LongInt comparisons */

        for ( int x = 0; x < long_ints.length; x++ ) {
            for ( int y = 0; y < long_ints.length; y++ ) {

                System.out.println( "" + symbol[x] + " and " + symbol[y] + "\n" );
                System.out.print( "Are " + symbol[x] + " and " + symbol[y] + " equal? "
                        + long_ints[x].equalTo( long_ints[y] ) + "\t" );
                System.out.print( "\tIs " + symbol[x] + " less than " + symbol[y] + "? "
                        + long_ints[x].lessThan( long_ints[y] ) + "\t" );
                System.out.print( "\tIs " + symbol[x] + " greater than " + symbol[y] + "? "
                        + long_ints[x].greaterThan( long_ints[y] ) );
                System.out.println( "\n" );

            }
        }


//        /* Step 2: Additions */
//
//        for (int x = 0; x < long_ints.length; x++)
//            for (int y = 0; y < long_ints.length; y++) {
//
//                System.out.println("" + symbol[x] + " + " + symbol[y] + "\n");
//                long startTime = System.nanoTime();
//                LongInt result = long_ints[x].add(long_ints[y]);
//                result.printLongInt();
//                long duration = System.nanoTime() - startTime;
//                System.out.println("Run Time: " + duration + " nanoseconds\n");
//
//            }

        /* Step 2: Subtractions */

        for ( int x = 0; x < long_ints.length; x++ )
            for ( int y = 0; y < long_ints.length; y++ ) {

                System.out.println( "" + symbol[x] + " - " + symbol[y] + "\n" );
                long startTime = System.nanoTime();
                LongInt result = long_ints[x].subtract( long_ints[y] );
                result.printLongInt();
                long duration = System.nanoTime() - startTime;
                System.out.println( "Running Time: " + duration + " nanoseconds\n" );

            }

//        /* Step 3: Mutiplications */
//
//        for (int x = 0; x < long_ints.length; x++)
//            for (int y = 0; y < long_ints.length; y++) {
//
//                System.out.println("" + symbol[x] + " * " + symbol[y] + "\n");
//                long startTime = System.nanoTime();
//                LongInt result = long_ints[x].multiply(long_ints[y]);
//                result.printLongInt();
//                long duration = System.nanoTime() - startTime;
//                System.out.println("Running Time: " + duration + " nanoseconds\n");
//
//            }
//
//        /* Step 2: Power */
//
//        for (int x = 0; x < long_ints.length; x++) {
//
//            System.out.println("" + symbol[x] + " ^ " + 5 + "\n");
//            long startTime_5 = System.nanoTime();
//            LongInt power_5 = long_ints[x].power(5);
//            power_5.printLongInt();
//            long duration_5 = System.nanoTime() - startTime_5;
//            System.out.println("Running Time: " + duration_5 + " nanoseconds\n");
//
//            System.out.println("" + symbol[x] + " ^ " + 10 + "\n");
//            long startTime_10 = System.nanoTime();
//            LongInt power_10 = long_ints[x].power(10);
//            power_10.printLongInt();
//            long duration_10 = System.nanoTime() - startTime_10;
//            System.out.println("Running Time: " + duration_10 + " nanoseconds\n");
//
//            System.out.println("" + symbol[x] + " ^ " + 20 + "\n");
//            long startTime_20 = System.nanoTime();
//            LongInt power_20 = long_ints[x].power(20);
//            power_20.printLongInt();
//            long duration_20 = System.nanoTime() - startTime_20;
//            System.out.println("Running Time: " + duration_20 + " nanoseconds\n");
//
//        }
//
//        /* Step 2: Arithmetic Sequnces */
//
//        System.out.println("J = B + C \n");
//        long startTime_j = System.nanoTime();
//        LongInt j = b.add(c);
//        j.printLongInt();
//        long duration_j = System.nanoTime() - startTime_j;
//        System.out.println("Running Time: " + duration_j + " nanoseconds\n");
//
//        System.out.println("K = C + D \n");
//        long startTime_k = System.nanoTime();
//        LongInt k = c.add(d);
//        k.printLongInt();
//        long duration_k = System.nanoTime() - startTime_k;
//        System.out.println("Running Time: " + duration_k + " nanoseconds\n");
//
//        System.out.println("L = I + I \n");
//        long startTime_l = System.nanoTime();
//        LongInt l = i.add(i);
//        l.printLongInt();
//        long duration_l = System.nanoTime() - startTime_l;
//        System.out.println("Running Time: " + duration_l + " nanoseconds\n");
//
//        System.out.println("M = A + I \n");
//        long startTime_m = System.nanoTime();
//        LongInt m = a.add(i);
//        m.printLongInt();
//        long duration_m = System.nanoTime() - startTime_m;
//        System.out.println("Running Time: " + duration_m + " nanoseconds\n");
//
//        System.out.println("N = B + K \n");
//        long startTime_n = System.nanoTime();
//        LongInt n = b.add(k);
//        n.printLongInt();
//        long duration_n = System.nanoTime() - startTime_n;
//        System.out.println("Running Time: " + duration_n + " nanoseconds\n");
//
//        System.out.println("O = A - D \n");
//        long startTime_o = System.nanoTime();
//        LongInt o = a.subtract(d);
//        o.printLongInt();
//        long duration_o = System.nanoTime() - startTime_o;
//        System.out.println("Running Time: " + duration_o + " nanoseconds\n");
//
//        System.out.println("P = C - D \n");
//        long startTime_p = System.nanoTime();
//        LongInt p = c.subtract(d);
//        p.printLongInt();
//        long duration_p = System.nanoTime() - startTime_p;
//        System.out.println("Running Time: " + duration_p + " nanoseconds\n");
//
//        System.out.println("Q = D - C \n");
//        long startTime_q = System.nanoTime();
//        LongInt q = d.subtract(c);
//        q.printLongInt();
//        long duration_q = System.nanoTime() - startTime_q;
//        System.out.println("Running Time: " + duration_q + " nanoseconds\n");
//
//        System.out.println("R = L - L \n");
//        long startTime_r = System.nanoTime();
//        LongInt r = l.subtract(l);
//        r.printLongInt();
//        long duration_r = System.nanoTime() - startTime_r;
//        System.out.println("Running Time: " + duration_r + " nanoseconds\n");
//
//        System.out.println("S = P - O \n");
//        long startTime_s = System.nanoTime();
//        LongInt s = p.subtract(o);
//        s.printLongInt();
//        long duration_s = System.nanoTime() - startTime_s;
//        System.out.println("Running Time: " + duration_s + " nanoseconds\n");
//
//        System.out.println("T = N - Q \n");
//        long startTime_t = System.nanoTime();
//        LongInt t = n.subtract(q);
//        t.printLongInt();
//        long duration_t = System.nanoTime() - startTime_t;
//        System.out.println("Running Time: " + duration_t + " nanoseconds\n");
//
//        System.out.println("U = A * D \n");
//        long startTime_u = System.nanoTime();
//        LongInt u = a.multiply(d);
//        u.printLongInt();
//        long duration_u = System.nanoTime() - startTime_u;
//        System.out.println("Running Time: " + duration_u + " nanoseconds\n");
//
//        System.out.println("V = B * C \n");
//        long startTime_v = System.nanoTime();
//        LongInt v = b.multiply(c);
//        v.printLongInt();
//        long duration_v = System.nanoTime() - startTime_v;
//        System.out.println("Running Time: " + duration_v + " nanoseconds\n");
//
//        System.out.println("W = D * D \n");
//        long startTime_w = System.nanoTime();
//        LongInt w = d.multiply(d);
//        w.printLongInt();
//        long duration_w = System.nanoTime() - startTime_w;
//        System.out.println("Running Time: " + duration_w + " nanoseconds\n");
//
//        System.out.println("X = O * I \n");
//        long startTime_x = System.nanoTime();
//        LongInt x = o.multiply(i);
//        x.printLongInt();
//        long duration_x = System.nanoTime() - startTime_x;
//        System.out.println("Running Time: " + duration_x + " nanoseconds\n");
//
//        System.out.println("Y = J * P \n");
//        long startTime_y = System.nanoTime();
//        LongInt y = j.multiply(p);
//        y.printLongInt();
//        long duration_y = System.nanoTime() - startTime_y;
//        System.out.println("Running Time: " + duration_y + " nanoseconds\n");
//
//        System.out.println("Z = M * N \n");
//        long startTime_z = System.nanoTime();
//        LongInt z = m.multiply(n);
//        z.printLongInt();
//        long duration_z = System.nanoTime() - startTime_z;
//        System.out.println("Running Time: " + duration_z + " nanoseconds\n");
    }
}
