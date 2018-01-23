public class SLLLongIntList implements LongIntList<SLLNode> {

    private SLLNode head;
	private SLLNode tail;

    private int size; // new class member for indication of size of SLLLongIntList

    
    public SLLLongIntList() {

        size = 0;
        head = null;
        tail = head;

    }

    public void insertFirst(int value) {

        SLLNode node = new SLLNode(value);

        /* if List is empty */

        if ( head == null ) {
            head = node;
            tail = head;
        }

        /* if not empty set new node to head */

        else {
            SLLNode h = head;
            head = node;
            head.setNext(h);
        } 

        /* increase size by 1 */

        size++;

    }
    
    public void insertLast(int value) {

        SLLNode node = new SLLNode(value);

        /* if List if empty */

        if ( head == null ){
            head = node;
            tail = head;
        }

        /* if not empty set new node to tail */

        else {
            SLLNode t = tail;
            tail = node;
            t.setNext(tail); 
        }

        /* increase size by 1 */

        size++;
        
    }
    
    public SLLNode first() {

        /*return null or head pointer */

        if ( head == null )
            return null;
        else
            return head;

    }

    public SLLNode last() {

        /* return null or tail pointer */

        if ( head == null && tail == null)
            return null;
        else
            return tail;
    }

    public boolean isFirst(SLLNode p) {

        /* compares p to head pointer */

        if ( p == head )
            return true;
        else 
            return false;
    }

    public boolean isLast(SLLNode p) {

        /* compares p to tail pointer */

        if ( p == tail )
            return true;
        else
            return false;
    }

    public SLLNode before(SLLNode p) {

        /* if list empty return null */
        
        if ( head == null )
            return null;

        SLLNode iter = head; 

        /* iterate throught list until you're at one position before p */       

        while ( iter.getNext() !=  null){

            if ( iter.getNext() == p ){
                return iter;
            }

            iter = iter.getNext();

        }

        /* return null is p is not in SLLLongIntList */

        return null;
    }

    public SLLNode after(SLLNode p) {

        /* if list is empty return null */

        if ( head == null )
            return null;

        SLLNode iter = head;

        /* iterate through list until you are at position p */

        while ( iter.getNext() != null ){

            if ( iter == p ) {
                return iter.getNext();
            }

            iter = iter.getNext();

        }  

        /* return null if p is not in SLLLongIntList */

        return null;
    }

    public boolean isEmpty() {

        /* return true if head is not null */

        if ( head == null )
            return false;
        else 
            return true;

    }

    public int size() {

        /* returns int size value */

        return size;

    }

}
