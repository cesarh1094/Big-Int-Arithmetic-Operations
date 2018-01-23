public class ArrayLongIntList implements LongIntList<ArrayEntry> {

    private int n;
    
    private ArrayEntry [] entries;
    
    public ArrayLongIntList() {

        n = 1;
        entries = new ArrayEntry[1];
        
    }
    
    public void insertFirst(int value) {

        if ( this.first() == null ) {
  
            this.entries[0] = new ArrayEntry(value, 0);

        }

        else {

            ArrayLongIntList newarray = new ArrayLongIntList();
            newarray.entries = new ArrayEntry [this.size()+1];

            newarray.entries[0] = new ArrayEntry(value, 0);

            for (int i = 1; i < newarray.size(); i++) {

                newarray.entries[i] = this.entries[i-1];
                newarray.entries[i].setIndex(i);

            }

            this.entries = newarray.entries;

        }
    }
    
    public void insertLast(int value) {

        if ( this.first() == null ) {

            this.entries[0] = new ArrayEntry(value, 0);

        }

        else {

            ArrayLongIntList newarray = new ArrayLongIntList();
            newarray.entries = new ArrayEntry [this.size()+1];

            newarray.entries[newarray.size()-1] = new ArrayEntry(value, newarray.size()-1);

            for (int i=0; i < newarray.size()-1; i++) {

                newarray.entries[i] = this.entries[i];
                newarray.entries[i].setIndex(i);

            }

            this.entries = newarray.entries;

        }
        
    }
    
    public ArrayEntry first() {

        return entries[0];

    }

    public ArrayEntry last() {

        return entries[entries.length-1];

    }

    public boolean isFirst(ArrayEntry p) {

        if ( p.getIndex() == 0 ) 

            return true;
        
        return false;
    }

    public boolean isLast(ArrayEntry p) {

        if ( p.getIndex() == entries.length-1 ) 
            
            return true;
        
        return false;

    }

    public ArrayEntry before(ArrayEntry p) {

        if ( p.getIndex() > 0 )

            return entries[p.getIndex()-1];

        else

            return null;

    }

    public ArrayEntry after(ArrayEntry p) {

        if ( p.getIndex() < entries.length-1 )
            
            return entries[p.getIndex()+1];

        else 

            return null;
    }

    public boolean isEmpty() {

        if ( entries.length == 0 )

            return true;

        else 

            return false;

    }

    public int size() {

        return entries.length;

    }

   public static void main(String args[]) {

    ArrayLongIntList list = new ArrayLongIntList();
    
    list.insertFirst(1);
    list.insertLast(10);
    list.insertFirst(5);
    
    int size;
    size = list.size();
    System.out.println(list.first().getValue());
    System.out.println(list.last().getValue());

    ArrayEntry iterator = list.first();

    while ( iterator != null ) {

        System.out.println(iterator.getValue());
        iterator = list.after(iterator);

    }    

    }
    
}


