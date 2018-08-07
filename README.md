# Big-Int-Arithmetic-Operations
Arithmetic Operations on Big Integers

- [Comparison Operators](#comparison-operators)
    - [equalTo()](#equalto)
    - [lessThan()](#lessthan)
    - [greaterThan()](#greaterthan)
    
- Arithmetic Operators
    - add
    - subtract
    - multiply
    - power

### Comparison Operators
##### equalTo
Sample usage:
```java
LongInt a = new LongInt( "2222" );
LongInt b = new LongInt( "9999999" );

System.out.println( a.equalTo( b ) ); // prints false
System.out.println( a.equalTo( a ) ); // prints true
```

##### lessThan
Sample usage:
```java
LongInt a = new LongInt( "2222" );
LongInt b = new LongInt( "9999999" );

System.out.println( a.lessThan( b ) ); // prints true
System.out.println( a.lessThan( a ) ); // prints false
System.out.println( b.lessThan( a ) ); // prints false
```

##### greaterThan
Sample usage:
```java
LongInt a = new LongInt( "2222" );
LongInt b = new LongInt( "9999999" );

System.out.println( a.greaterThan( b ) ); // prints false
System.out.println( a.greaterThan( a ) ); // prints false
System.out.println( b.greaterThan( a ) ); // prints true
```
