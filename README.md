# Long Int - Arithmetic Operations
Arithmetic Operations on Long Integers
###Table of Contents
- [Background Information](#background-information)
- [Comparison Operators](#comparison-operators)
   - [equalTo()](#equalto-longint-i-)
   - [lessThan()](#lessthan-longint-i-)
   - [greaterThan()](#greaterthan-longint-i)
- Arithmetic Operators
   - add
   - subtract
   - multiply
   - power
   
#### Background Information
Problem: `int` primitive data type has it's limitations. According to the Java spec:

> By default, the int data type is a `32-bit` signed two's complement integer, which has a minimum value of -2<sup>31</sup> and a maximum value of 2<sup>(31-1)</sup>. In Java SE 8 and later, you can use the int data type to represent an unsigned `32-bit` integer, which has a minimum value of 0 and a maximum value of 2<sup>(32-1)</sup>. Use the Integer class to use int data type as an unsigned integer.

Solution: implement LongInt using ```array ( [] )``` primitive data type. Each LongInt has a `list` that is an array of integers where each value at every index can have **at most** 8 digits of a parsed ``String`` passed into the constructor
For example:

```java
LongInt a = new LongInt( "1238729332432432497" );
// String "1238729332432432497" gets parsed and stored into a's array of `int`s
// a's array of `int`s looks like this:
// [ 32432497, 87293324, 123 ]
```

#### Comparison Operators
##### `equalTo( LongInt i )`
Sample usage:
```java
LongInt a = new LongInt( "2222" );
LongInt b = new LongInt( "9999999" );

System.out.println( a.equalTo( b ) ); // prints false
System.out.println( a.equalTo( a ) ); // prints true
```

##### `lessThan( LongInt i )`
Sample usage:
```java
LongInt a = new LongInt( "2222" );
LongInt b = new LongInt( "9999999" );

System.out.println( a.lessThan( b ) ); // prints true
System.out.println( a.lessThan( a ) ); // prints false
System.out.println( b.lessThan( a ) ); // prints false
```

##### `greaterThan( LongInt i)`
Sample usage:
```java
LongInt a = new LongInt( "2222" );
LongInt b = new LongInt( "9999999" );

System.out.println( a.greaterThan( b ) ); // prints false
System.out.println( a.greaterThan( a ) ); // prints false
System.out.println( b.greaterThan( a ) ); // prints true
```
