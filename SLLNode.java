public class SLLNode extends Position {
    private SLLNode next;

    public SLLNode( int value ) {
        super( value );
    }

    public SLLNode getNext() {
        return next;
    }

    public void setNext( SLLNode next ) {
        this.next = next;
    }
}
