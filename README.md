# Big-Int-Arithmetic-Operations
Arithmetic Operations on Big Integers

- Comparison Operators
    - [equalTo](#equalto)
    - lessThan
    - greaterThan
    
- Arithmetic Operators
    - add
    - subtract
    - multiply
    - power

## Comparison Operators
##### equalTo
Sample usage:
```java
LongInt a = new LongInt("2222");
LongInt b = new LongInt("9999999");

System.out.println(a.equalTo(b)); // prints false
System.out.println(a.equalTo(a)); // prints true

```