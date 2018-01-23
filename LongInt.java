public class LongInt {
    
    // DO NOT CHANGE OR REMOVE THIS LINE (UNTIL STEP 3)
    // This will give a warning about raw types, but you can ignore it for this project
    //private final LongIntList list = new SLLLongIntList();
    
    // USE THIS LINE IN STEP 3
    private final LongIntList list = new ArrayLongIntList();
    
    private boolean isNegative;

    private int digitCount;
	    
	public LongInt() {

        digitCount = 0;
        this.isNegative = false;

	}

    public LongInt(LongIntList l, int digitCount) {

        Position iterator = l.first();

        while ( iterator != null ){

            list.insertLast(iterator.getValue());
            iterator = l.after(iterator);

        }

        this.digitCount = digitCount;
        isNegative = false;

    }

    public LongInt(boolean isNeg) {

        isNegative = isNeg;

    }
		
    public LongInt(String s) {

        /* negative number */

        if( s.charAt(0) == '-' ) {

            isNegative = true;

            s = s.substring(1);

            digitCount = s.length();

            if ( digitCount % 8 == 0 ) {

                for( int i = s.length(); i>=0; i-=8) {

                    if( i > 8 ) {
                    
                        list.insertLast( Integer.parseInt(s.substring(i-8, i)) );

                    }
                    if ( i == 8 ) {

                        list.insertLast( Integer.parseInt(s.substring(0, i)) );

                    }

                }

            }

            else if ( digitCount < 8) {

                list.insertFirst( Integer.parseInt(s) );

            }

            else {

                for (int i= s.length(); i>=0; i-=8) {
 
                    if( i >= 8 ) {

                        list.insertLast( Integer.parseInt(s.substring(i-8, i)) );

                    }
                    if( i <= 8 ) {
                        
                        list.insertLast( Integer.parseInt(s.substring(0, i)) );

                    }

                }

            }

        }

        /* positive number */

        else {

            isNegative = false;

            digitCount = s.length();

            if ( digitCount % 8 == 0 ) {

                for( int i = s.length(); i>=0; i-=8) {

                    if ( i > 8 ) {
                    
                        list.insertLast( Integer.parseInt(s.substring(i-8, i)) );

                    }

                    if ( i == 8 ) {

                        list.insertLast(Integer.parseInt(s.substring(0, i)));

                    }

                }

            }

            else if ( digitCount < 8) {

                list.insertFirst( Integer.parseInt(s) );

            }

            else {

                for ( int i = s.length(); i>=0; i-=8) {
 
                    if( i >= 8 ) {

                        list.insertLast( Integer.parseInt(s.substring(i-8, i)) );

                    }

                    if( i <= 8 ) {
                        
                        list.insertLast( Integer.parseInt(s.substring(0, i)) );

                    }

                }

            }
            
        }

    }
	    
    
    public void output() {

        /* prints LongInt starting from the end of the list */

        Position iterator = this.list.last();

        if ( iterator == null ) {

            System.out.println("LongInt is empty");

        }

        else {

            if ( this.getSign() ) {

                System.out.print("-");

            }
                
            while ( iterator != null ) {

                if ( iterator != this.list.last() ) {

                    if ( LongIntUtils.digits(iterator.getValue()) < 8 ){

                        for (int i=0; i<8-LongIntUtils.digits(iterator.getValue()); i++){

                            System.out.print(0);

                        }
                    }
                }

                System.out.print(iterator.getValue());

                iterator = this.list.before(iterator);
            }

        }

        System.out.println("\n");                       // line break

    }
	
    public boolean getSign() {

        /* isNegative is true if LongInt is negative or false if positive*/

        return isNegative;

    }

    private void setSign(boolean sign) {

        /* sets LongInt isNegative to sign */

        isNegative = sign;

    }

    public int getDigitCount() {

        /* return the number of digits in the calling LongInt */

        Position iterator = this.list.first();
        int count = 0;                                                 // stores Digit Count

        while ( iterator != null ) {

            if ( ( LongIntUtils.digits(iterator.getValue()) < 8 ) && ( this.list.isLast(iterator) == false ) ) {

                count = count + ( 8 - LongIntUtils.digits(iterator.getValue()) );

            }

            count = count + LongIntUtils.digits(iterator.getValue());
            iterator =  this.list.after(iterator);

        }

        return count;

    }
    
    public boolean equalTo(LongInt i) {

        Position iterator_this = this.list.first();

        Position iterator_i = i.list.first();


        /* check if both LongInt have the same digit count and same sign otherwise they're not equal */

        if ( ( this.getDigitCount() == i.getDigitCount() ) && ( this.isNegative == i.isNegative ) ) {

            while( iterator_this != null ) {

                if( iterator_this.getValue() != iterator_i.getValue() ) {

                    return false;

                }
                    
                iterator_this = this.list.after(iterator_this);

                iterator_i = i.list.after(iterator_i);

            }

            return true;
        }

        return false;

    }
    
    public boolean lessThan(LongInt i) {

        Position iterator_this = this.list.last();

        Position iterator_i = i.list.last();

        /* make sure they aren't equal to each other */

        if( this.equalTo(i) ) {

            return false;

        }

        /* both LongInts are positive */

        if( ( this.isNegative == false ) && ( i.isNegative == false) ) {

            if( this.getDigitCount() == i.getDigitCount() ) {

                  while ( iterator_this!= null ) {

                        if ( iterator_this.getValue() > iterator_i.getValue() )

                            return false;

                        iterator_this = this.list.before(iterator_this);

                        iterator_i = i.list.before(iterator_i);

                  }

                  return true;

            }

            else if ( this.getDigitCount() < i.getDigitCount() )
            
                return true;
            
            else
            
                return false;
            
        }

        /* both LongInts are negative */

        if ( ( this.isNegative == true ) && ( i.isNegative == true) ) {

            if( this.getDigitCount() == i.getDigitCount() ) {

                  while ( iterator_this!= null ) {

                        if ( iterator_this.getValue() < iterator_i.getValue() ) {
                        
                            return false;

                        }

                        iterator_this = this.list.before(iterator_this);

                        iterator_i = i.list.before(iterator_i);

                  }

                  return true;

            }

            else if ( this.getDigitCount() > i.getDigitCount() ) {
             
               return true;

            }

            else
                return false;

        }

        /* if the calling LongInt is negative and i is positive */

        if ( ( this.isNegative == true ) && ( i.isNegative == false ) ) {

            return true;

        }

        /* if the calling LongInt is positive and i is negative */
 
        if ( ( this.isNegative == true ) && ( i.isNegative == false ) ) {

            return false;

        }

        return false;        

    }

    public boolean greaterThan(LongInt i) {

        Position iterator_this = this.list.last();

        Position iterator_i = i.list.last();

        /* make sure LongInts aren't equal to each other */

        if( this.equalTo(i) ) {

            return false;

        }

        /* both LongInts are positive */

        if( ( this.isNegative == false ) && ( i.isNegative == false) ) {

            if( this.getDigitCount() == i.getDigitCount() ) {

                  while ( iterator_this!= null ) {

                        if ( iterator_this.getValue() < iterator_i.getValue() ) {

                            return false;

                        }

                        iterator_this = this.list.before(iterator_this);

                        iterator_i = i.list.before(iterator_i);

                  }

                  return true;

            }

            else if ( this.getDigitCount() > i.getDigitCount() )
                return true;
            else
                return false;
            
        }

        /* both LongInts are negative */

       if ( ( this.isNegative == true ) && ( i.isNegative == true ) ) {

            if( this.getDigitCount() == i.getDigitCount() ) {

                  while ( iterator_this!= null ) {

                        if ( iterator_this.getValue() > iterator_i.getValue() )
                            return false;

                        iterator_this = this.list.before(iterator_this);

                        iterator_i = i.list.before(iterator_i);

                  }

                  return true;

            }

            else if ( this.getDigitCount() < i.getDigitCount() )
                return true;
            else
                return false;

        }

        /* if the calling LongInt is negative and i is positive */

        if ( ( this.isNegative == true ) && ( i.isNegative == false ) ) {

            return false;

        }

        /* if the calling LongInt is positive and i is negative */

        if ( ( this.isNegative == false ) && ( i.isNegative == true ) ) {

            return true;
            
        } 

        return false;    
        
    }

    public LongInt add(LongInt i) {

        Position iterator_this = this.list.first();

        Position iterator_i = i.list.first();

        LongInt solution = null;
        
        int overflow = 0;

        int node_sum = 0;

        if ( iterator_this == null ) {

            solution = new LongInt( i.list, i.getDigitCount());
            return solution;

        }

        if ( iterator_i == null ) {

            solution = new LongInt( this.list, this.getDigitCount() );
            return solution;

        }

        /* if the two LongInts have the same node length */

        if ( this.list.size() == i.list.size() ) {

            if ( this.isNegative == i.isNegative ) {

                if ( this.isNegative == true ) {

                    solution = new LongInt(this.isNegative);

                }

                else
                    solution = new LongInt();

                while ( iterator_this != null ) {

                    if ( overflow > 0 ) {

                        node_sum = iterator_this.getValue() + iterator_i.getValue() + overflow;

                        if ( LongIntUtils.digits(node_sum) > 8 ) {

                            solution.list.insertLast(LongIntUtils.underflow(node_sum));

                            overflow = LongIntUtils.overflow(node_sum);

                        }

                        else {

                            solution.list.insertLast(node_sum);

                            overflow = 0;

                        }

                    }

                    else {

                        node_sum = iterator_this.getValue() + iterator_i.getValue();

                        if ( LongIntUtils.digits(node_sum) > 8 ) {

                            solution.list.insertLast(LongIntUtils.underflow(node_sum));

                            overflow = LongIntUtils.overflow(node_sum);

                        }
                        else {

                            solution.list.insertLast(node_sum);

                            overflow = 0;

                        }
                    }

                    iterator_this = this.list.after(iterator_this);
    
                    iterator_i = i.list.after(iterator_i);

                }

                if ( overflow > 0 ) {

                    solution.list.insertLast(overflow);

                }

                return solution;

            }

            
            if ( this.isNegative == false && i.isNegative == true ) {

                LongInt temp = new LongInt(i.list, i.digitCount);

                temp.setSign(false);

                solution = this.subtract(temp);

                return solution;

            }
            
            if ( this.isNegative == true && i.isNegative == false ) {
               
                LongInt temp = new LongInt(this.list, this.digitCount);

                temp.setSign(false);

                solution = i.subtract(temp);

                return solution;           

            }

        }

        /* if the calling LongInt has less digits than i */


        if ( this.list.size() < i.list.size() ) {

            if ( this.isNegative == i.isNegative ) {

                if (this.isNegative == true) {

                    solution = new LongInt(this.isNegative);

                }

                else
                    solution = new LongInt();

                while ( iterator_this != null ) {

                    if ( overflow > 0 ){

                        node_sum = iterator_this.getValue() + iterator_i.getValue() + overflow;

                        if ( LongIntUtils.digits(node_sum) > 8 ) {

                            solution.list.insertLast(LongIntUtils.underflow(node_sum));

                            overflow = LongIntUtils.overflow(node_sum);

                        }

                        else {

                            solution.list.insertLast(node_sum);

                            overflow = 0;

                        }

                    }

                    else if ( overflow == 0 ) {

                        node_sum = iterator_this.getValue() + iterator_i.getValue();

                        if ( LongIntUtils.digits(node_sum) > 8 ) {

                            solution.list.insertLast(LongIntUtils.underflow(node_sum));

                            overflow = LongIntUtils.overflow(node_sum);

                        }

                        else {

                            solution.list.insertLast(node_sum);

                            overflow = 0;

                        }
                    }

                    iterator_this = this.list.after(iterator_this);
            
                    iterator_i = i.list.after(iterator_i);

                }

                while ( iterator_i != null ) {


                    if ( overflow > 0 ) {

                        solution.list.insertLast(iterator_i.getValue() + overflow);

                        overflow = 0;

                    } 

                    else {

                        solution.list.insertLast(iterator_i.getValue());

                    }

                    iterator_i = i.list.after(iterator_i);

                }

                return solution;

            }
            
            if ( this.isNegative == false && i.isNegative == true ) {

                LongInt temp = new LongInt(i.list, i.digitCount);

                temp.setSign(false);

                solution = this.subtract(temp);

                return solution;

            }
            
            if ( this.isNegative == true && i.isNegative == false ) {

                LongInt temp = new LongInt(this.list, this.digitCount);

                temp.setSign(false);

                solution = i.subtract(temp);

                return solution;           

            }

        } 

        /* if the calling LongInt has more digits than i */

        if ( this.list.size() > i.list.size() ) {

            if ( this.isNegative == i.isNegative ) {

                if (this.isNegative == true) {

                    solution = new LongInt(this.isNegative);

                }

                else
                    solution = new LongInt();

                while ( iterator_i != null ) {

                    if ( overflow > 0 ) {

                        node_sum = iterator_this.getValue() + iterator_i.getValue() + overflow;

                        if ( LongIntUtils.digits(node_sum) > 8 ) {

                            solution.list.insertLast(LongIntUtils.underflow(node_sum));

                            overflow = LongIntUtils.overflow(node_sum);

                        }

                        else {

                            solution.list.insertLast(node_sum);

                            overflow = 0;

                        }

                    }

                    else if ( overflow == 0 ) {

                        node_sum = iterator_this.getValue() + iterator_i.getValue();

                        if ( LongIntUtils.digits(node_sum) > 8 ) {

                            solution.list.insertLast(LongIntUtils.underflow(node_sum));

                            overflow = LongIntUtils.overflow(node_sum);

                        }

                        else {

                            solution.list.insertLast(node_sum);

                            overflow = 0;

                        }
                    }

                    iterator_this = this.list.after(iterator_this);
            
                    iterator_i = i.list.after(iterator_i);

                }

                while ( iterator_this != null ) {


                    if ( overflow > 0 ) {

                        solution.list.insertLast(iterator_this.getValue() + overflow);

                        overflow = 0;

                    } 

                    else {

                        solution.list.insertLast(iterator_this.getValue());

                    }

                    iterator_this = this.list.after(iterator_this);

                }

                return solution;

            }


            if ( this.isNegative == false && i.isNegative == true ) {

                LongInt temp = new LongInt(i.list, i.digitCount);

                temp.setSign(false);

                solution = this.subtract(temp);

                return solution;

            }
            
            if ( this.isNegative == true && i.isNegative == false ) {
                
                LongInt temp = new LongInt( this.list, this.digitCount);

                temp.setSign(false);

                solution = i.subtract(temp);

                return solution;           

            }

        }

        return solution;

    }

    public LongInt subtract(LongInt i) {

        Position iterator_this = this.list.first();

        Position iterator_i = i.list.first();

        LongInt solution = new LongInt();

        boolean carry = false;

        int node_sub = 0;


        if ( this.isNegative == false && i.isNegative == false ) {

            if ( this.equalTo(i) ) {

                solution = new LongInt();

                solution.list.insertLast(0);

                return solution;

            }

            if ( this.lessThan(i) ) {

                solution = new LongInt(true);

                while ( iterator_this != null ) {

                    if ( carry ) {

                        if ( ( iterator_i.getValue() < iterator_this.getValue() )  && ( i.list.after(iterator_i) != null ) ) {

                            carry = true;

                            node_sub = ( ( 100000000 + iterator_i.getValue() ) - 1 ) - iterator_this.getValue();

                            solution.list.insertLast(node_sub);

                        }

                        else {

                            carry = false;

                            node_sub = ( iterator_i.getValue() - 1 ) - iterator_this.getValue();

                            solution.list.insertLast(node_sub);

                        }
                    }

                    else {

                        if ( ( iterator_i.getValue() < iterator_this.getValue() )  && ( i.list.after(iterator_i) != null ) ) {

                            carry = true;

                            node_sub = ( 100000000 + iterator_i.getValue() ) - iterator_this.getValue();

                            solution.list.insertLast(node_sub);

                        }

                        else {

                            carry = false;

                            node_sub = iterator_i.getValue() - iterator_this.getValue();

                            if ( (( i.list.isLast(iterator_i) == false ) && ( node_sub > 0 )) ) {

                                solution.list.insertLast(node_sub);
                            
                            }

                            else if ( this.list.size() == i.list.size() && node_sub != 0 )

                                solution.list.insertLast(node_sub);


                        }

                    }

                    iterator_this = this.list.after(iterator_this);

                    iterator_i = i.list.after(iterator_i);

                }

                 while ( iterator_i != null ) {

                    if ( carry ) {

                        solution.list.insertLast(iterator_i.getValue()-1);

                        carry = false;

                    } 

                    else {

                        solution.list.insertLast(iterator_i.getValue());

                    }

                    iterator_i = i.list.after(iterator_i);

                }

                return solution;

            }

            if ( this.greaterThan(i) ) {

                solution = new LongInt();

                while ( iterator_i != null ) {

                    if ( carry ) {

                        if ( ( iterator_this.getValue() < iterator_i.getValue() )  && ( this.list.after(iterator_this) != null ) ) {

                            carry = true;

                            node_sub = ( ( 100000000 + iterator_this.getValue() ) - 1 ) - iterator_i.getValue();

                            solution.list.insertLast(node_sub);

                        }

                        else {

                            carry = false;

                            node_sub = ( iterator_this.getValue() - 1 ) - iterator_i.getValue();

                            solution.list.insertLast(node_sub);

                        }
                    }

                    else {

                        if ( ( iterator_this.getValue() < iterator_i.getValue() )  && ( this.list.after(iterator_this) != null ) ) {

                            carry = true;

                            node_sub = ( 100000000 + iterator_this.getValue() ) - iterator_i.getValue();

                            solution.list.insertLast(node_sub);

                        }

                        else {

                            carry = false;

                            node_sub = iterator_this.getValue() - iterator_i.getValue();

                            if ( ( this.list.isLast(iterator_this) == false ) && ( node_sub > 0 ) ) {

                                solution.list.insertLast(node_sub);
                            }

                            else if ( this.list.size() == i.list.size()  && node_sub != 0 )
                                
                                solution.list.insertLast(node_sub);


                        }

                    }

                    iterator_this = this.list.after(iterator_this);

                    iterator_i = i.list.after(iterator_i);

                }

                 while ( iterator_this != null ) {

                    if ( carry ) {

                        solution.list.insertLast(iterator_this.getValue()-1);

                        carry = false;

                    } 

                    else {

                        solution.list.insertLast(iterator_this.getValue());

                    }

                    iterator_this = this.list.after(iterator_this);

                }

                return solution;

            } 

        }

        if ( this.isNegative == true && i.isNegative == true ) {

            if ( this.equalTo(i) ) {

                solution = new LongInt();

                solution.list.insertLast(0);

                return solution;

            }

            if ( this.greaterThan(i) ) {

                LongInt temp_this = new LongInt(this.list, this.digitCount);

                temp_this.setSign(false);

                LongInt temp_i = new LongInt(i.list, i.digitCount);

                temp_i.setSign(false);

                solution = temp_i.subtract(temp_this);

                solution.setSign(false);

                return solution;

            }

            if ( this.lessThan(i) ) {

                LongInt temp_this = new LongInt(this.list, this.digitCount);

                temp_this.setSign(false);

                LongInt temp_i = new LongInt(i.list, i.digitCount);

                temp_i.setSign(false);

                solution = temp_this.subtract(temp_i);

                solution.setSign(true);

                return solution;

            }

        }

        if ( this.isNegative == false && i.isNegative == true ) {

            LongInt temp = new LongInt(i.list, i.digitCount);

            temp.setSign(false);

            solution = this.add(temp);

            return solution;

        }

        if ( this.isNegative == true && i.isNegative == false ) {

            LongInt temp = new LongInt(this.list, this.digitCount);

            temp.setSign(false);

            solution = i.add(temp);

            solution.setSign(true);

            return solution;

        }

        return solution;

    }
    
    public LongInt multiply(LongInt i) {

        Position iterator_this = this.list.first();
        Position iterator_i = i.list.first();

        LongInt result = new LongInt("0");
        int zeros_this = 0;                     // # of nodes with padded zeros for this
        int zeros_i = 0;                        // # of nodes with padded zeros for i

        while ( iterator_this != null ) {

            while ( iterator_i != null ) {

                result = new LongInt(result.add(karatsuba(iterator_this.getValue(), iterator_i.getValue(), zeros_i)).list, result.add(karatsuba(iterator_this.getValue(), iterator_i.getValue(), zeros_i)).getDigitCount());
                ///result.printEachNode();
                zeros_i = zeros_i + 1;
                iterator_i = i.list.after(iterator_i);

            }

            iterator_this = this.list.after(iterator_this);
            iterator_i = i.list.first();
            zeros_this = zeros_this + 1;
            zeros_i = zeros_this;

        }

        /* if either this or i (not and) result is negative */

        if ( ( this.isNegative == false && i.isNegative == true ) || ( this.isNegative == true && i.isNegative == false ) )
            result.setSign(true);
        else
            result.setSign(false);

        return result;

    }

    public static LongInt karatsuba( int c, int d, int n ) {

        /* performs the karatsuba algorithm for muiplying two 8-digit numbers where n is the number nodes with zeros */

        LongInt result = new LongInt();

        int z1 = LongIntUtils.upperHalf(c) * LongIntUtils.upperHalf(d);
        int z3 = LongIntUtils.lowerHalf(c) * LongIntUtils.lowerHalf(d);

        int z2 = ( ( LongIntUtils.upperHalf(c) + LongIntUtils.lowerHalf(c) ) * ( LongIntUtils.upperHalf(d) + LongIntUtils.lowerHalf(d) ) ) - z1 - z3;
        int overflow = LongIntUtils.overflow(z2);

        int v1 = z1 + LongIntUtils.upperHalf(z2);
        int v2 = ( 10000 * LongIntUtils.lowerHalf(z2) ) +  z3;

        if ( overflow > 0 ) {

            for ( int i = 0; i < n; i++ ) {

                result.list.insertLast(0);

            }

            result.list.insertLast( LongIntUtils.underflow(v2) );
            result.list.insertLast( v1 + overflow + overflow*10000);

        }

        else {

            for ( int i = 0; i < n; i++ ) {

                result.list.insertLast(0);

            }

            if ( LongIntUtils.overflow(v2) > 0 ) {

                result.list.insertLast(LongIntUtils.underflow(v2));
                v1 = v1 + LongIntUtils.overflow(v2);

            }

            else {

                result.list.insertLast(v2);

            }

            

            if ( v1 > 0 ){

                result.list.insertLast(v1);

            }
            
        }

        return result;

    }

    public LongInt power(int p) {


        if ( p == 0 )
            return new LongInt("1");

        if ( p == 1 )
            return this;

        /* if p is even */

        if ( ( p % 2 ) == 0 )
            return this.multiply(this).power(p/2);

        /* if p is odd */

        return this.multiply(this.multiply(this).power( (p-1) / 2));
        
    }

    public void printEachNode() {


        /* print nodes in LongIntList in this format: Node 1: 00000000   Node 2: 00000000 ....... */

        Position node = this.list.first();

        int counter = 1;

        while ( node != null ) {

            System.out.print("Node " + counter++ + ": ");


            if(LongIntUtils.digits(node.getValue())<8) {

                for(int i=0; i<8-LongIntUtils.digits(node.getValue()); i++) {

                    System.out.print(0);

                }
            }

            System.out.print("" + node.getValue() + "\t");

            node = this.list.after(node);

        }

        System.out.println("\n");

    }

    public static void main(String[] args) {

        /* intialization of all Test Cases */

        long startTime_a = System.nanoTime();
        LongInt a = new LongInt("2222");
        long duration_a = System.nanoTime() - startTime_a;
        System.out.println("Initialization of A - Running Time: " + duration_a + " nanoseconds\n");

        long startTime_b = System.nanoTime();
        LongInt b = new LongInt("99999999");
        long duration_b = System.nanoTime() - startTime_b;
        System.out.println("Initialization of B - Running Time: " + duration_b + " nanoseconds\n");

        long startTime_c = System.nanoTime();
        LongInt c = new LongInt("-246813575732");
        long duration_c = System.nanoTime() - startTime_c;
        System.out.println("Initialization of C - Running Time: " + duration_c + " nanoseconds\n");

        long startTime_d = System.nanoTime();
        LongInt d = new LongInt("180270361023456789");
        long duration_d = System.nanoTime() - startTime_d;
        System.out.println("Initialization of D - Running Time: " + duration_d + " nanoseconds\n");

        long startTime_e = System.nanoTime();
        LongInt e = new LongInt("1423550000000010056810000054593452907711568359");
        long duration_e = System.nanoTime() - startTime_e;
        System.out.println("Initialization of E - Running Time: " + duration_e + " nanoseconds\n");

        long startTime_f = System.nanoTime();
        LongInt f = new LongInt("-350003274594847454317890");
        long duration_f = System.nanoTime() - startTime_f;
        System.out.println("Initialization of F - Running Time: " + duration_f + " nanoseconds\n");

        long startTime_g = System.nanoTime();
        LongInt g = new LongInt("29302390234702973402973420937420973420937420937234872349872934872893472893749287423847");
        long duration_g = System.nanoTime() - startTime_g;
        System.out.println("Initialization of G - Running Time: " + duration_g + " nanoseconds\n");

        long startTime_h = System.nanoTime();
        LongInt h = new LongInt("-98534342983742987342987339234098230498203894209928374662342342342356723423423");
        long duration_h = System.nanoTime() - startTime_h;
        System.out.println("Initialization of H - Running Time: " + duration_h + " nanoseconds\n");

        long startTime_i = System.nanoTime();
        LongInt i = new LongInt("8436413168438618351351684694835434894364351846843435168484351684684315384684313846813153843135138413513843813513813138438435153454154515151513141592654543515316848613242587561516511233246174561276521672162416274123076527612");
        long duration_i = System.nanoTime() - startTime_i;
        System.out.println("Initialization of I - Running Time: " + duration_i + " nanoseconds\n");

        LongInt [] long_ints = new LongInt[9];
        long_ints[0] = a;
        long_ints[1] = b;
        long_ints[2] = c;
        long_ints[3] = d;
        long_ints[4] = e;
        long_ints[5] = f;
        long_ints[6] = g;
        long_ints[7] = h;
        long_ints[8] = i;

        char [] symbol = new char[9];
        symbol[0] = 'A';
        symbol[1] = 'B';
        symbol[2] = 'C';
        symbol[3] = 'D';
        symbol[4] = 'E';
        symbol[5] = 'F';
        symbol[6] = 'G';
        symbol[7] = 'H';
        symbol[8] = 'I';


        /* Step 1: Standard output: Test case output(), test case getDigitCount(), test case getSign(), test case printEachNode() */

        for ( int x = 0; x < long_ints.length; x++) {

                System.out.print("" + symbol[x] + " = ");
                long_ints[x].output();
                System.out.println(""+ symbol[x] + " Digit Count: " + long_ints[x].getDigitCount() + "\n");
                System.out.println("Is '" + symbol[x] + "' negative? " + long_ints[x].getSign() + "\n");
                long_ints[x].printEachNode();

        }




        /* Steo 1: LongInUtils test cases */

        int a_int = 2222;
        int b_int = 99999999;

        System.out.println("Overflow A: " + LongIntUtils.overflow(a_int));
        System.out.println("Overflow B: " + LongIntUtils.overflow(b_int));
        System.out.print("\n");

        System.out.println("Underflow A: " + LongIntUtils.underflow(a_int));
        System.out.println("Underflow B: " + LongIntUtils.underflow(b_int));
        System.out.print("\n");
    
        System.out.println("Upperhalf A: " + LongIntUtils.upperHalf(a_int));
        System.out.println("Lowerhalf A: " + LongIntUtils.lowerHalf(a_int));
        System.out.print("\n");

        System.out.println("Upperhalf B: " + LongIntUtils.upperHalf(b_int));
        System.out.println("Lowerhalf B: " + LongIntUtils.lowerHalf(b_int));
        System.out.print("\n");

        System.out.println("Digits A: " + LongIntUtils.digits(a_int));
        System.out.println("Digits B: " + LongIntUtils.digits(b_int));
        System.out.print("\n");



         /* Step 1: LongInt comparisons */


         for ( int x = 0; x < long_ints.length; x++ )
            for ( int y = 0; y < long_ints.length; y++ ) {

                System.out.println("" + symbol[x] + " and " + symbol[y] + "\n");
                System.out.print("Are " + symbol[x] + " and " + symbol[y] + " equal? " + long_ints[x].equalTo(long_ints[y]) + "\t");
                System.out.print("\tIs " + symbol[x] + " less than " + symbol[y] + "? " + long_ints[x].lessThan(long_ints[y]) + "\t");
                System.out.print("\tIs " + symbol[x] + " greater than " + symbol[y] + "? " + long_ints[x].greaterThan(long_ints[y]));
                System.out.println("\n");

            }






        /* Step 2: Additions */


        for ( int x = 0; x < long_ints.length; x++ )
            for ( int y = 0; y < long_ints.length; y++ ) {

                System.out.println("" + symbol[x] + " + " + symbol[y] + "\n");
                long startTime = System.nanoTime();
                LongInt result = long_ints[x].add(long_ints[y]);
                result.output();
                long duration = System.nanoTime() - startTime;
                System.out.println("Run Time: " + duration + " nanoseconds\n");

            }


        /* Step 2: Subtractions */


        for ( int x = 0; x < long_ints.length; x++ )
            for ( int y = 0; y < long_ints.length; y++ ) {

                
                System.out.println("" + symbol[x] + " - " + symbol[y] + "\n");
                long startTime = System.nanoTime();
                LongInt result = long_ints[x].subtract(long_ints[y]);
                result.output();
                long duration = System.nanoTime() - startTime;
                System.out.println("Running Time: " + duration + " nanoseconds\n");

            }





        /* Step 3: Mutiplications */


        for ( int x = 0; x < long_ints.length; x++ )
            for ( int y = 0; y < long_ints.length; y++ ) {

                System.out.println("" + symbol[x] + " * " + symbol[y] + "\n");
                long startTime = System.nanoTime();
                LongInt result = long_ints[x].multiply(long_ints[y]);
                result.output();
                long duration = System.nanoTime() - startTime;
                System.out.println("Running Time: " + duration + " nanoseconds\n");

            }



        /* Step 2: Power */ 

      
        for ( int x = 0; x < long_ints.length; x++){

            System.out.println("" + symbol[x] + " ^ " + 5 + "\n");
            long startTime_5 = System.nanoTime();
            LongInt power_5 = long_ints[x].power(5);
            power_5.output();
            long duration_5 = System.nanoTime() - startTime_5;
            System.out.println("Running Time: " + duration_5 + " nanoseconds\n");

            System.out.println("" + symbol[x] + " ^ " + 10 + "\n");
            long startTime_10 = System.nanoTime();
            LongInt power_10 = long_ints[x].power(10);
            power_10.output();
            long duration_10 = System.nanoTime() - startTime_10;
            System.out.println("Running Time: " + duration_10 + " nanoseconds\n");

            System.out.println("" + symbol[x] + " ^ " + 20 + "\n");
            long startTime_20 = System.nanoTime();
            LongInt power_20 = long_ints[x].power(20);
            power_20.output();
            long duration_20 = System.nanoTime() - startTime_20;
            System.out.println("Running Time: " + duration_20 + " nanoseconds\n");

        }
        



        /* Step 2: Arithmetic Sequnces */


        System.out.println("J = B + C \n");
        long startTime_j = System.nanoTime();
        LongInt j = b.add(c);
        j.output();
        long duration_j = System.nanoTime() - startTime_j;
        System.out.println("Running Time: " + duration_j + " nanoseconds\n");

        System.out.println("K = C + D \n");
        long startTime_k = System.nanoTime();
        LongInt k = c.add(d);
        k.output();
        long duration_k = System.nanoTime() - startTime_k;
        System.out.println("Running Time: " + duration_k + " nanoseconds\n");

        System.out.println("L = I + I \n");
        long startTime_l = System.nanoTime();
        LongInt l = i.add(i);
        l.output();
        long duration_l = System.nanoTime() - startTime_l;
        System.out.println("Running Time: " + duration_l + " nanoseconds\n");

        System.out.println("M = A + I \n");
        long startTime_m = System.nanoTime();
        LongInt m = a.add(i);
        m.output();
        long duration_m = System.nanoTime() - startTime_m;
        System.out.println("Running Time: " + duration_m + " nanoseconds\n");

        System.out.println("N = B + K \n");
        long startTime_n = System.nanoTime();
        LongInt n = b.add(k);
        n.output();
        long duration_n = System.nanoTime() - startTime_n;
        System.out.println("Running Time: " + duration_n + " nanoseconds\n");

        System.out.println("O = A - D \n");
        long startTime_o = System.nanoTime();
        LongInt o = a.subtract(d);
        o.output();
        long duration_o = System.nanoTime() - startTime_o;
        System.out.println("Running Time: " + duration_o + " nanoseconds\n");

        System.out.println("P = C - D \n");
        long startTime_p = System.nanoTime();
        LongInt p = c.subtract(d);
        p.output();
        long duration_p = System.nanoTime() - startTime_p;
        System.out.println("Running Time: " + duration_p + " nanoseconds\n");

        System.out.println("Q = D - C \n");
        long startTime_q = System.nanoTime();
        LongInt q = d.subtract(c);
        q.output();
        long duration_q = System.nanoTime() - startTime_q;
        System.out.println("Running Time: " + duration_q + " nanoseconds\n");

        System.out.println("R = L - L \n");
        long startTime_r = System.nanoTime();
        LongInt r = l.subtract(l);
        r.output();
        long duration_r = System.nanoTime() - startTime_r;
        System.out.println("Running Time: " + duration_r + " nanoseconds\n");

        System.out.println("S = P - O \n");
        long startTime_s = System.nanoTime();
        LongInt s = p.subtract(o);
        s.output();
        long duration_s = System.nanoTime() - startTime_s;
        System.out.println("Running Time: " + duration_s + " nanoseconds\n");

        System.out.println("T = N - Q \n");
        long startTime_t = System.nanoTime();
        LongInt t = n.subtract(q);
        t.output();
        long duration_t = System.nanoTime() - startTime_t;
        System.out.println("Running Time: " + duration_t + " nanoseconds\n");

        System.out.println("U = A * D \n");
        long startTime_u = System.nanoTime();
        LongInt u = a.multiply(d);
        u.output();
        long duration_u = System.nanoTime() - startTime_u;
        System.out.println("Running Time: " + duration_u + " nanoseconds\n");

        System.out.println("V = B * C \n");
        long startTime_v = System.nanoTime();
        LongInt v = b.multiply(c);
        v.output();
        long duration_v = System.nanoTime() - startTime_v;
        System.out.println("Running Time: " + duration_v + " nanoseconds\n");

        System.out.println("W = D * D \n");
        long startTime_w = System.nanoTime();
        LongInt w = d.multiply(d);
        w.output();
        long duration_w = System.nanoTime() - startTime_w;
        System.out.println("Running Time: " + duration_w + " nanoseconds\n");

        System.out.println("X = O * I \n");
        long startTime_x = System.nanoTime();
        LongInt x = o.multiply(i);
        x.output();
        long duration_x = System.nanoTime() - startTime_x;
        System.out.println("Running Time: " + duration_x + " nanoseconds\n");

        System.out.println("Y = J * P \n");
        long startTime_y = System.nanoTime();
        LongInt y = j.multiply(p);
        y.output();
        long duration_y = System.nanoTime() - startTime_y;
        System.out.println("Running Time: " + duration_y + " nanoseconds\n");

        System.out.println("Z = M * N \n");
        long startTime_z = System.nanoTime();
        LongInt z = m.multiply(n);
        z.output();
        long duration_z = System.nanoTime() - startTime_z;
        System.out.println("Running Time: " + duration_z + " nanoseconds\n");


    }
        
}
