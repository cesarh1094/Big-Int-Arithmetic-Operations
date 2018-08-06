public interface LongIntList<P extends Position> {
    void insertFirst( int value );

    void insertLast( int value );

    P first();

    P last();

    boolean isFirst( P p );

    boolean isLast( P p );

    P before( P p );

    P after( P p );

    boolean isEmpty();

    int size();
}
