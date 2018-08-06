public class SLLLongIntList implements LongIntList<SLLNode> {

    private SLLNode head;
    private SLLNode tail;

    private int size; // new class member for indication of size of SLLLongIntList

    public SLLLongIntList() {
        this.size = 0;
    }

    public void insertFirst( int value ) {
        SLLNode node = new SLLNode( value );

        if ( null == this.head ) {
            this.head = node;
            this.tail = head;
        } else {
            SLLNode h = this.head;
            this.head = node;

            this.head.setNext( h );
        }

        this.size++;
    }

    public void insertLast( int value ) {
        SLLNode node = new SLLNode( value );

        if ( null == this.head ) {
            this.head = node;
            this.tail = this.head;
        } else {
            SLLNode t = this.tail;
            this.tail = node;

            t.setNext( this.tail );
        }

        this.size++;
    }

    public SLLNode first() {
        return this.head;
    }

    public SLLNode last() {
        return this.tail;
    }

    public boolean isFirst( SLLNode p ) {
        return this.head == p;
    }

    public boolean isLast( SLLNode p ) {
        return this.tail == p;
    }

    public SLLNode before( SLLNode p ) {
        if ( null == this.head ) {
            return null;
        }

        SLLNode iter = this.head;

        while ( iter.getNext() != null ) {
            if ( iter.getNext() == p ) {
                return iter;
            }

            iter = iter.getNext();
        }

        return null;
    }

    public SLLNode after( SLLNode p ) {
        if ( null == this.head ) {
            return null;
        }

        SLLNode iter = this.head;

        while ( iter.getNext() != null ) {
            if ( iter == p ) {
                return iter.getNext();
            }

            iter = iter.getNext();
        }

        return null;
    }

    public boolean isEmpty() {
        return null == this.head;
    }

    public int size() {
        return this.size;
    }
}

