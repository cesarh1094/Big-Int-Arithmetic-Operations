public class TestLongInt {
    public static void main( String[] args ) {

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


        /* Step 2: Additions */

        for ( int x = 0; x < long_ints.length; x++ ) {
            for ( int y = 0; y < long_ints.length; y++ ) {

                System.out.println( "" + symbol[x] + " + " + symbol[y] + "\n" );
                long startTime = System.nanoTime();
                LongInt result = long_ints[x].add( long_ints[y] );
                result.printLongInt();
                long duration = System.nanoTime() - startTime;
                System.out.println( "Run Time: " + duration + " nanoseconds\n" );

            }
        }

        /* Step 2: Subtractions */

        for ( int x = 0; x < long_ints.length; x++ ) {
            for ( int y = 0; y < long_ints.length; y++ ) {

                System.out.println( "" + symbol[x] + " - " + symbol[y] + "\n" );
                long startTime = System.nanoTime();
                LongInt result = long_ints[x].subtract( long_ints[y] );
                result.printLongInt();
                long duration = System.nanoTime() - startTime;
                System.out.println( "Running Time: " + duration + " nanoseconds\n" );

            }
        }

        /* Step 3: Mutiplications */

        for (int x = 0; x < long_ints.length; x++)
            for (int y = 0; y < long_ints.length; y++) {

                System.out.println("" + symbol[x] + " * " + symbol[y] + "\n");
                long startTime = System.nanoTime();
                LongInt result = long_ints[x].multiply(long_ints[y]);
                result.printLongInt();
                long duration = System.nanoTime() - startTime;
                System.out.println("Running Time: " + duration + " nanoseconds\n");

            }

        /* Step 2: Power */

        for (int x = 0; x < long_ints.length; x++) {

            System.out.println("" + symbol[x] + " ^ " + 5 + "\n");
            long startTime_5 = System.nanoTime();
            LongInt power_5 = long_ints[x].power(5);
            power_5.printLongInt();
            long duration_5 = System.nanoTime() - startTime_5;
            System.out.println("Running Time: " + duration_5 + " nanoseconds\n");

            System.out.println("" + symbol[x] + " ^ " + 10 + "\n");
            long startTime_10 = System.nanoTime();
            LongInt power_10 = long_ints[x].power(10);
            power_10.printLongInt();
            long duration_10 = System.nanoTime() - startTime_10;
            System.out.println("Running Time: " + duration_10 + " nanoseconds\n");

            System.out.println("" + symbol[x] + " ^ " + 20 + "\n");
            long startTime_20 = System.nanoTime();
            LongInt power_20 = long_ints[x].power(20);
            power_20.printLongInt();
            long duration_20 = System.nanoTime() - startTime_20;
            System.out.println("Running Time: " + duration_20 + " nanoseconds\n");

        }

        /* Step 2: Arithmetic Sequnces */

        System.out.println("J = B + C \n");
        long startTime_j = System.nanoTime();
        LongInt j = b.add(c);
        j.printLongInt();
        long duration_j = System.nanoTime() - startTime_j;
        System.out.println("Running Time: " + duration_j + " nanoseconds\n");

        System.out.println("K = C + D \n");
        long startTime_k = System.nanoTime();
        LongInt k = c.add(d);
        k.printLongInt();
        long duration_k = System.nanoTime() - startTime_k;
        System.out.println("Running Time: " + duration_k + " nanoseconds\n");

        System.out.println("L = I + I \n");
        long startTime_l = System.nanoTime();
        LongInt l = i.add(i);
        l.printLongInt();
        long duration_l = System.nanoTime() - startTime_l;
        System.out.println("Running Time: " + duration_l + " nanoseconds\n");

        System.out.println("M = A + I \n");
        long startTime_m = System.nanoTime();
        LongInt m = a.add(i);
        m.printLongInt();
        long duration_m = System.nanoTime() - startTime_m;
        System.out.println("Running Time: " + duration_m + " nanoseconds\n");

        System.out.println("N = B + K \n");
        long startTime_n = System.nanoTime();
        LongInt n = b.add(k);
        n.printLongInt();
        long duration_n = System.nanoTime() - startTime_n;
        System.out.println("Running Time: " + duration_n + " nanoseconds\n");

        System.out.println("O = A - D \n");
        long startTime_o = System.nanoTime();
        LongInt o = a.subtract(d);
        o.printLongInt();
        long duration_o = System.nanoTime() - startTime_o;
        System.out.println("Running Time: " + duration_o + " nanoseconds\n");

        System.out.println("P = C - D \n");
        long startTime_p = System.nanoTime();
        LongInt p = c.subtract(d);
        p.printLongInt();
        long duration_p = System.nanoTime() - startTime_p;
        System.out.println("Running Time: " + duration_p + " nanoseconds\n");

        System.out.println("Q = D - C \n");
        long startTime_q = System.nanoTime();
        LongInt q = d.subtract(c);
        q.printLongInt();
        long duration_q = System.nanoTime() - startTime_q;
        System.out.println("Running Time: " + duration_q + " nanoseconds\n");

        System.out.println("R = L - L \n");
        long startTime_r = System.nanoTime();
        LongInt r = l.subtract(l);
        r.printLongInt();
        long duration_r = System.nanoTime() - startTime_r;
        System.out.println("Running Time: " + duration_r + " nanoseconds\n");

        System.out.println("S = P - O \n");
        long startTime_s = System.nanoTime();
        LongInt s = p.subtract(o);
        s.printLongInt();
        long duration_s = System.nanoTime() - startTime_s;
        System.out.println("Running Time: " + duration_s + " nanoseconds\n");

        System.out.println("T = N - Q \n");
        long startTime_t = System.nanoTime();
        LongInt t = n.subtract(q);
        t.printLongInt();
        long duration_t = System.nanoTime() - startTime_t;
        System.out.println("Running Time: " + duration_t + " nanoseconds\n");

        System.out.println("U = A * D \n");
        long startTime_u = System.nanoTime();
        LongInt u = a.multiply(d);
        u.printLongInt();
        long duration_u = System.nanoTime() - startTime_u;
        System.out.println("Running Time: " + duration_u + " nanoseconds\n");

        System.out.println("V = B * C \n");
        long startTime_v = System.nanoTime();
        LongInt v = b.multiply(c);
        v.printLongInt();
        long duration_v = System.nanoTime() - startTime_v;
        System.out.println("Running Time: " + duration_v + " nanoseconds\n");

        System.out.println("W = D * D \n");
        long startTime_w = System.nanoTime();
        LongInt w = d.multiply(d);
        w.printLongInt();
        long duration_w = System.nanoTime() - startTime_w;
        System.out.println("Running Time: " + duration_w + " nanoseconds\n");

        System.out.println("X = O * I \n");
        long startTime_x = System.nanoTime();
        LongInt x = o.multiply(i);
        x.printLongInt();
        long duration_x = System.nanoTime() - startTime_x;
        System.out.println("Running Time: " + duration_x + " nanoseconds\n");

        System.out.println("Y = J * P \n");
        long startTime_y = System.nanoTime();
        LongInt y = j.multiply(p);
        y.printLongInt();
        long duration_y = System.nanoTime() - startTime_y;
        System.out.println("Running Time: " + duration_y + " nanoseconds\n");

        System.out.println("Z = M * N \n");
        long startTime_z = System.nanoTime();
        LongInt z = m.multiply(n);
        z.printLongInt();
        long duration_z = System.nanoTime() - startTime_z;
        System.out.println("Running Time: " + duration_z + " nanoseconds\n");
    }
}
