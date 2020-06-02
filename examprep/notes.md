# Week 2
## Polymorphism
Objects that can pass more than one "IS-A" test are considered to be polymorpohic.
(You can use the same function semantics for multiple classes - this isn't even my final form)

```java
Shape s1 = new Rectangle();
s1 = new Circle();
```

Here, the object s1 is a rectangle, and can also be a circle.

Functions and variables can be "polymorphic" too.
ie - calling the same function from two different objects.

```java
s1.getArea()
```
will work if s1 is either a Rectangle or a Circle.


## Access modifiers
* Public: same class, same package, subclass, everyone
* Protected: same class, same package, **subclass**, but not everyone
* Default: same class, same package
* Private: same class only

## Relationships
* Association shows two classes linked to each other
* Aggregation (hollow diamond) - can also exist on its own
* Composition (solid diamond) - cannot exist on its own

# Week 3
## Abstract Classes
Classes which cannot be instantiated, but serves as a placeholder for factoring out common behaviour in subclasses.

Abstract classes ***may*** define (but not implement) abstract methods.
Abstract classes are a way to implement polymorphism.

```java
import java.lang.Math.*;

public abstract class Shape {
    public double getArea();
    public double getPerimeter();
}

public class Circle extends Shape {
    private double r;
    public double getArea() {
        return Math.PI * radius. * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Rectangle extends Shape {
    private double x;
    private double y;

    public double getArea() {
        return x * y;
    }

    public double getPerimeter() {
        return 2 * (x + y);
    }
}
```

## Interfaces
Interfaces defines a collection of abstract methods. (No implementation). Where classes **extend** other abstract classes, classes must **implement** interfaces.

**An interface describes a common set of shared behaviours behaviours without a default,** as opposed to an abstract class which can provide a default. Because there is no default, there is also nothing to "override"

A class can also implement multiple interfaces but only extend one class.

```java
public interface GraphicObject {
    // No implementation provided
    public void drawShape();
}

// Can implement multiple interface
public class Shape implements GraphicObject, ... {
    // ...

    public void drawShape() {
        // Implementation provided here
    }
}

public class Circle extends Shape {
    // ..https://www.youtube.com/watch?v=sWl0UAevNIw.
    // no implementation of drawShape here because all shapes can use the same drawing algorithm.
}
```

## Design Smells
Symptoms which hint to you that your design is shit.

* **Rigidity**: hard to change code, changing one thing can change everything (Ceebs changing)
* **Fragility**: tendency of one change to break code in many different places (Ceebs fixing)
* **Immobility**: design is hard to reuse, even though it :pears useful (Ceebs reusing)
* **Viscosity**: 
    * Software Viscosity: changes are easier to implement through hacks (Everyone make an MVP)
    * Environment Viscosity: dev environment is slow and inefficient (Wtf?)
* **Opacity**: code is hard to understand, unclear, not expressive (Shitty variable names)
* **Needless Complexity**: Create shit that you don't currently need (What I do)
* **Needless Repetition**: Repeated structures could be unified under a single abstraction

## Characteristics of Good Design
Loose coupling and high cohesion allow for software that is:
* Extensible
* Reusable
* Maintainable
* Understandable
* Testable

**Coupling** is the degree of interdependence between components/classes. High coupling means that one part of code is too dependant on another part and changing one part causes changes in other parts.

**Cohesion** the degree to which all elements of a component/class/module work together as a functional unit, allowing for easier maintainability and reusability. (How well does code work together)

## Design Prinicples

### SOLID:
* **Single Responsiblity**: Each class should only have one responsibility
* **Open-closed**: Entities should be open for extension, closed for modification
* **Liskov Substitution Principle**: Objects should be replaceable with subtype instances without altering correctness of the program
* **Interface segregation principle**: Many client-specific interfaces are better than one general purpose interface
* **Dependency inversion principle**: High level modules should not depend on low level modules. Both should depend on abstractions

### Law of Demeter (Principle of Least Knowledge):
* Classes should interact with the least amount of other classes as possible.
* Don't call functions of friends of friends of friends of friends - this.

```java
o.get(name).get(thing).remove(node)
```

* Rule 1: Methods in objects can call other methods in the same object and:
    * Rule 2: methods of parameters passed in to the object
    * Rule 3: methods of objects instantiated within the original object
    * Rule 4: methods of obejcts that are components of the original object

### Well Designed Inheritance (Likov Substitution Principle)
LSP states that subtypes mut be substitutable for their base types. This means that the methods for parent classes must work correctly for all their child classes for LSP to be true.
   
> A great example illustrating LSP (given by Uncle Bob in a podcast I heard recently) was how sometimes something that sounds right in natural language doesn't quite work in code.

> In mathematics, a Square is a Rectangle. Indeed it is a specialization of a rectangle. The "is a" makes you want to model this with inheritance. However if in code you made Square derive from Rectangle, then a Square should be usable anywhere you expect a Rectangle. This makes for some strange behavior.

> Imagine you had SetWidth and SetHeight methods on your Rectangle base class; this seems perfectly logical. However if your Rectangle reference pointed to a Square, then SetWidth and SetHeight doesn't make sense because setting one would change the other to match it. In this case Square fails the Liskov Substitution Test with Rectangle and the abstraction of having Square inherit from Rectangle is a bad one.

```java
Board board = new Board3D();

// The function board.getUnits(x, y) does not make sense in a 3D sense.
artillery unit = board.getUnits(8, 4);
```

### Avoiding inheritence
Favour composition and delegation over inheritance
* Delegation - delegate functionality to another class
* Composition - reuse behaviour using one or more classes with composition

## Method Overriding
* Argument list should be exactly the same as overriden method
* Access level cannot be more restrictive than overriden method
* Return types of overriden methods shoudl be the same or a subtype

```java
// Lecture Example

public class AnimalShelter {

    public Animal getAnimalForAdoption() {
        return null;
    }

    public void putAnimal(Animal someAnimal) {}
}

public class CatShelter extends AnimalShelter {

    @Override
    public Cat getAnimalForAdoption() {
        return new Cat();
    }

    // this is not actually an override, because the argument list is different
    public void putAnimal(Object someAnimal) {
        // do something
    }
}
```

* Methods declared final cannot be overriden

## Refactoring
Changing your code to make it easier to understand and cheaper to modify without changing external behaviour. Helps you find bugs, make program faster and conform to design principles.

You should always refactor, and then introduce new features.

## Code Smells
This is different to design smells (ridigity, fragility, immobility, etc.) Design smells are problems with the way codebase is designed. Code smells are faults with how designs are implemented.

But there are a lot of overlaps...

* **Duplicated Code**: lol
* **Long Method**: flashbacks to 1511
* **Large Class**: Shows up as too many instance variables
* **Long Parameter list**: If you're taking in too many parameters, you code probably has too many responsibilities/obligations.
* **Divergent Change**: When one class one class is commonly changed in different ways for different reasons. (When Mac and I had that fight lmao)
* **Shotgun Surgery**: Opposite to divergent change, when you have to make a lot of little changes to a lot of different changes.

### Fundamental Refactoring Techniques
1. Extracting new methods from existing methods 
2. Renaming variables
3. Moving methods
4. Replacing temporary variables with queries.
5. Replacing conditional logic with polymorphism (like below, note you will have to change the code that uses these classes to instantiate the individual classes. How you determine which type of "bird" to instantiate will still be ...?

```java
public class Bird {

    // this is bad
    double getSpeed() {
        switch(type) {
            case EUROPEAN:
                return getBaseSpeed();
            case AFRICAN:
                return getBaseSpeed * 2;
            case NORWEGIAN_BLUE:
                return getBaseSpeed * 3;
        }
    }
}
```

```java
public abstract class Bird {
    public double getSpeed();
}

// this is better
public Euroean extends Bird {
    public double getSpeed() {
        return getBaseSpeed;
    }
}

public African extends Bird {
    public double getSpeed() {
        return getBaseSpeed * 2;
    }
}

public NorwegianBlue extends Bird {
    public double getSpeed() {
        return getBaseSpeed * 3;
    }
}
```

# Week 4
## Design Principles (continued)

### Encapsulate varying code
Identify aspects of your code that varies and encapsulate (separate) it from your code.
* Pull out methods that vary across like-classes
* Introduce new classes to look after this behaviour

### Program to an interface (a super-type), not an implementation
Exploit polymorphism by programming to a super-type so the runtime object is not locked. This was a problem we faced in the major project because
```java
// this is bad
Dog d = new Dog();
d.bark();

// this is better
Animal a = new Dog();
a.makeSound();
```

This was a problem we faced in the major project where runtime would first aim to identify(capture) the Entity at a given x, y coordinate - and then identify(capture) the specific subclass of the Entity(Sword) at the given x, y.

```java
// Sword.java
/**
    * Method to pickup sword for the player 
    * @param dungeon
    * @param x
    * @param y
    */
public static void pickupSword(Dungeon dungeon, int x, int y) {
    Sword s = new Sword(dungeon, -1, -1);
    if (dungeon.getCell(x, y).containsAnInstanceOf(s)) {
        Entity e = dungeon.getCell(x, y).getEntity(s);
        dungeon.removeEntity(e);
        
        // pickup sword
        for (Sword sword : dungeon.getSwords()) {
            if (sword.getX() == x && sword.getY() == y) {
                dungeon.getPlayer().setSword(sword);
                dungeon.removeSimpleEntityVisual(sword);


            }
        }
    }
}
```

This was because Entities were stored as a \<list> at given x, y coordinate (Cell) - 

```java
// Cell.java
public class Cell {
// x and y coordinates of the cell 
private int x;
private int y;

// list of entities the cell contains
private List<Entity> content;

// ...
}
```

but stored as the specific subclass in the Player class, where they are used.
```java
// Player.java
public class Player extends DynamicEntity {

private static int noTreasure, enemiesLeft;
private Key currKey;
private Sword equippedSword;
private Potion potion;

// ...
}
```

**Solution** This problem could be easily solved by programming to the Entity interface (super-type) in Player.java so that the pickupSword() does not need to retrieve both the Entity and the specific subclass. There would also be no need to store a list of specific subclasses in Dungeon.java (not shown)

```java
// Player.java
public class Player extends DynamicEntity {

private static int noTreasure, enemiesLeft;
private Entity currKey;
private Entity equippedSword;
private Entity potion;

// ...
}
```

### Favour composition over inheritance
Instead of inheriting behaviour from superclasses, subclasses can delegate their behaviour to a composition of the right behaviour objects.


## Design Patterns

### Strategy Pattern (Behavioural)
**Encapsulating a family of algorithms and make them interchangeable, allowing you to change behaviour at runtime. Allows the algorithm vary independently from the context class using it. (C++ STL)**

The A* pathfinding algorithm used in the major project propagates a path via "neighbour" evaluation, so it is possible to use the pre-filtering of possible neighbours (to all 8 tiles allowing for diagonals or only 4 tiles allowing for non-diagonal movement only) as a family of algorithms to be interchanged.

Implementing two algorithms for neighbour filtering and assigning them to a variable in Enemy.java allows the behaviour of the enemy to change at any time. Implementing via an interface also allows for extensibility of more algorithms.

```java
// Enemy.java
public class Enemy extends DynamicEntity {
private static Dungeon dungeon;
private static MoveStrategy movementStrategy;
```  

```java
// MoveStrategy.java
public interface MoveStrategy {
/**
* Gets the list of neighbour cells from a specific cell in the dungeon
* @param dungeon the dungeon containing the cell
* @param cell the cell to be checked
* @return returns a list of neighbouring cells 
*/
public List<Cell> getNeighbours(Dungeon dungeon, Cell cell);
}

// EasyMoveStrategy.java
public class EasyMoveStrategy implements MoveStrategy {

/**
* Simplified movement strategy for easier levels
*/
@Override
public List<Cell> getNeighbours(Dungeon dungeon, Cell cell) {
    List<Cell> retCells = new ArrayList<>();
    List<Integer> listX = Arrays.asList(0, 1, 0, -1);
    List<Integer> listY = Arrays.asList(-1, 0, 1, 0);
        
        
    for (int i = 0; i < listX.size(); i++) {
        int newX = cell.getX()+listX.get(i);
        int newY = cell.getY()+listY.get(i);
        
        if (newY > 0 && newY < dungeon.getHeight()-1 && newX > 0 && newX < dungeon.getWidth()-1) {
            if (DynamicEntity.movementCheck(dungeon, newX, newY, true) == 3) {
                retCells.add(dungeon.getCell(newX, newY));
            }
        }
    }
    
    return retCells;

}
}

// HardMoveStrategy.java
public class HardMoveStrategy implements MoveStrategy {

/**
* Hard move strategy for more challenging levels
*/
@Override
public List<Cell> getNeighbours(Dungeon dungeon, Cell cell) {

        
    List<Cell> retCells = new ArrayList<>();
    List<Integer> listX = Arrays.asList(0, 1, 1, 1, 0, -1, -1, -1);
    List<Integer> listY = Arrays.asList(-1, -1, 0, 1, 1, 1, 0, -1);
    
    
    for (int i = 0; i < listX.size(); i++) {
        int newX = cell.getX()+listX.get(i);
        int newY = cell.getY()+listY.get(i);
        

        if (newY > 0 && newY < dungeon.getHeight()-1 && newX > 0 && newX < dungeon.getWidth()-1) {
    
            if (DynamicEntity.movementCheck(dungeon, newX, newY, true) == 3) {
                retCells.add(dungeon.getCell(newX, newY));
            }
        }
    }
    return retCells;

}
}
```

### State Pattern (Behavioural)

A finite-state machine is an abstract machine that can be in exactly one of a finite number of states at any given time, and can change from one state to another (transition) in response to some external inputs.

It is defined by:
* A list of its states 
* The conditions for each transition
* Its initial state

Example given from major project.
```java
// Door.java
public class Door extends StaticEntity {

private DoorState state;
private int id;

/***
* Door constructor for door entity objects 
* @param dungeon the dungeon for it to be contained in
* @param x the coordinate of the object
* @param y the y coordinate of the object
* @param id the unique identifier of the door 
*/
public Door(Dungeon dungeon, int x, int y, int id) {
    super(dungeon, x, y);
    this.id = id;
    // all door start off as closed (initial state)
    this.state = new ClosedDoorState();
}

public DoorState getState() {
    return state;
}

public void setState(DoorState state) {
    this.state = state;
}

public void lock() {
    state.lock(this);
}

public void unlock() {
    state.unlock(this);
}
}

// DoorState.java
public interface DoorState {
// List of states
void unlock(Door d);
void lock(Door d);
}

// OpenDoorState.java
public class OpenDoorState implements DoorState {

@Override
public void unlock(Door d) {
}

@Override
public void lock(Door d) {
d.setState(new ClosedDoorState());
}
}

// ClosedDoorState.java
public class ClosedDoorState implements DoorState {

@Override
public void unlock(Door d) {
d.setState((new OpenDoorState()));
}

@Override
public void lock(Door d) {
}
}

// Snippet from DynamicEntity.java, containing conditions for transition
for (Door door: dungeon.getDoors()) {
if (door.getX() == x && door.getY() == y && dungeon.getPlayer().getKey() != null) {
if (dungeon.getPlayer().getKey().getID() == door.getID()) {
    door.unlock();
    type = 3;
} else {
    door.lock();
}  
}
}

```

# Week 5
## Design Patterns (Continued)

### Observer Pattern (Behavioural)
Implement distributed event handling systems. A subject (observable) maintains a list of dependents (observers). This symbolises a one-to-many relationship, where the observers are automatically notified of any state changes in the subject, usually by calling one of their methods.

Observers aim to:
* Define a one-to-many dependancy between objects without making them tightly coupled (dependant)
* Automatically notify/update and open-ended number of observers when the subject changes state.
* Be able to dynamically add and remove observers.

It is the responsibility of:
* A subject to maintain a list of observers and call update() to notify them of state changes.
* Observers to register/unregister themselves on a subject and update their stated when notified.

Aim for loose coupling of subjects and observers.
It is also possible to have multiple observers for multiple subjects.

```java
// Basic Observer Template

public class Subject {
private int num = 0;

List<Observer> listObservers = new ArrayList<Observer>();

public void attach(Observer o) {
    listObservers.add(o);
}

public void detach(Observer o) {
    listObservers.remove(o);
}

public void notify() {
    for (Observer o : listObservers) {
        o.update(this);
    }
}

public void setNum(int newNum) {
    num = newNum;
    // An event occurs, need to tell everyone yeet
    notify();
}
}

public class A extends Subject {}
public class B extends Subject {}

public class Observer {
private int num = 0;
public void update(...) {
    // Push-style
    // relevant update data is passed in arguments in update()
}

public void update(Subject s) {
    // Pull-style
    // retrieve relevant information from Subject s
    if (s instanceof A) {
        updateA((A)s);
    } else if (s instanceof B) {
        updateB((B)s);
    }
}

public void updateA(A a) {
    int num = num+=a.getNum();
}
public void updateB(B b) {
    int num = num-=a.getNum();
}
}

public class Client {
A a = new A();
B b = new B();

Observer observerA = new Observer();
Observer observerB = new Observer();

a.setNum(3); 
// This will call notify(), which will push the subject to the observers, requiring the observer to pull the changes in updateA() and updateB()

}
```
### Composite Design (Structural)
Objects are designed as a composition of one-or-more similar objects that exhibit similar functionality. This aims to manipulate a single instance of an object just as we would manipulate a group of them.

It takes advantage of polymorpishm - IE allowing one function semantic to invoke different sets of behaviour depending on composite/leaf classes.

It is defined by:
* An interface that defines common methods to both composites and leaves
* Leaf (extend interface) that contain the lowest level of operation
* Composite (extend interface) that perform operationgs on it's children, which can be composites or leafs
* If required, composite objects can also collect return values

Uniformity
* Include all child methods in the interface, both composites and leaves must implement them
* Leaf and composite objects are treated the same
* Type safety is loosened (polymorphism is increased) because the same methods can be used for both leaf and composite.

Type Safety
* Child methods are only defined in composite classes
* Type constraints are enforced, meaning that client needs to treat composite and leaf differently, and child methods on a leaf.

Example from major project: (Implemented for uniformity)
```java
// Goal.java
public interface Goal {
// Common methods implemented by both composite and leaf
public boolean checkGoal();
public String getDesc();
}

// GoalAnd.java (Composite)
public class GoalAnd implements Goal {

// Children are stored here
private List<Goal> subgoals;

public GoalAnd() {
    subgoals = new ArrayList<>();
}

public void addSubgoal(Goal goal) {
    this.subgoals.add(goal);
}

public boolean checkGoal() {
    for (Goal g : subgoals) {
        if (!g.checkGoal()) {
            return false;
        }
    }
    return true;
}

public String getDesc() {
    String ret = "";
    Iterator<Goal> it = subgoals.iterator();
    while (it.hasNext()) {
        Goal g = it.next();
        ret+=g.getDesc();
        if (it.hasNext()) {
            ret+="and\n";
        }
    }
    
    return ret;
}
}

// GoalBoulders.java (Leaf)
public class GoalBoulders implements Goal {

private Dungeon dungeon;
private String desc = "Move all the boulders onto a switch\n";

public GoalBoulders(Dungeon d) {
this.dungeon= d;
}

public boolean checkGoal() {
if (dungeon.getSwitchCount() == dungeon.getActivatedSwitches()) {
    return true;
}
else  {
    return false;
}
}

public String getDesc() {
return this.desc;
}
}
```

# Week 6
## Design by Contract
At the design time, responsibilities are clearly assigned to different software elements, which are clearly documented and enforced during development using unit testing.

Documentation should define a specification/contract that addresses:
* **Pre-condition**: what does the contract expect
* **Post-condition**: what does the contract guarantee
* **Invariant**: what does the contract maintain

Declarative, with no details of implementation. Precise, formal and verifiable.

### Pre-conditions
Condition or predicate that must be true prior to the execution of the software element. Violation of pre-condition may caused undefined behaviour. Are sometimes tested using guards or assertions.

Design by contract allows the developer to assume that the preconditions are satisfied for a software element, so redundant error checking code (assertions) are not needed.

### Post-conditions
Condition or predicate that must be true after the execution of the software element. In many cases, it is what the software element needs to deliver. Also sometimes tested using assertions.

Design by contracts assures that post-conditions specified are delivered. A lot of post-conditions are actually the pre-conditions of other software elements. This is how design by contract tries to guarantee safety.

### Class Invariant
Class invariant constrains the state stored in the object. They are established during **construction** and constantly maintained between calls to public methods.

Methods must make sure that class invariants are satisfied/preserved. Code within methods can break invariants as long as they are restored before the method concludes.

## Exceptions
An event which occurs during execution that disrupts normal flow of the programs instructions, typically occurs when an error occurs.

When an error occurs, an exception object is created and given to the runtime (thrown). Runtime will search the call stack for a method with code to **handle/catch** the exception.

* Checked Exception(IOException, SQLException, etc)
* Error (VirtualMachineError, OutOfMemoryError, etc)
* Runtime exception (ArrayIndexOutOfBoundsExceptions, etc)

Error and runtime exceptions are unchecked.

Try/catch statements can be used to **try** to run a piece of code, and **catch** an error/exception if one is thrown. The developer must specify what kind of error/exception to catch.

```java
public void foo() {
    try {
        List<Integer> list =  Arrays.asList(0, 1, 2, 3, 4);
        for (int i = 0; i < list.size()+1; i++) {
            System.out.println(list.get(i));
        }
    } catch (IndexOutOfBoundsException e) {
        // Error found yeet
    }
}
```

### User defined exceptions
All exceptions must be a child of Throwable. Checked exceptions need to extend the Exception class and a unchecked exception (runtime exception) need to extend the RuntimeException class.

## Assertions
Statement that allows you to test your assumptions. Do not them for:
* Argument checking in public methods
* To do any work that your applications requires for correct operation(?)

Evaluation assertions should also not result in any side effects.

# Week 7
## Generics
Generics enable types (classes and interfaces) to be **parameters** when defining classes, interfaces and methods.

Benefits:
* Allows the removal of casting and offers stronger type checks at compile time.
* Allows impementation of generic algorithms, that work on collections of different types, that can be customised, and are type-safe. (C++ STL)
* Adds stability because more bugs are detectable at compile time.

```java
// The use of <String> here is a generic.
List<String> list = new ArrayList<String>();
list.add("hello");
String s = list.get(0);
```

### Generic Types (Classes / Interfaces)
A generic type is a generic class or interface that is parameterised over types. This allows for the definition of collections that can contain any arbitrary type.

Element, Key, Number, Type, Value, etc.

```java
public class Box<T> {
private T t;

public void set(T t) {
    this.t = t;
}

public T get() {
    return this.t;
}
}
```

Generic classes can also have multiple type parameters.

```java
public interface Pair<K, V> {
public K getKey();
public V getValue();
}

public class OrderedPair<K, V> implements Pair<K, V> {
private K key;
private V value;

public OrderedPair(K key, V value) {
    this.key = key;
    this.value = value;
}

public K getKey() {return this.key;}
public V getValue() {return this.value;}
}

public class Client {
Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Pepperoni", 3);
// ...
}
```
### Generic Methods
Methods that introduce their own type parameters.
```java
public class foo {
public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2>) {
    return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
}
}

public class Client {
//...
boolean same = Util.<String, Integer>compare(p1, p2);
}
```

### Bounded Type Parameters
There may be time where you would want to restrict the types that can be used as arguments.
```java
// Bounded type in class
public class NaturalNumber<T extends Integer> {}

// Bounded type in method
public <U extends Number> void inspect(U u) {}
```

### Multiple Bounds
```java
<T extends B1 & B2 & B3>
```

### Generic Classes and Subtyping
You can subtype a generic class or interface by extending or implementing it.
For example:
* ArrayList\<E> implements List\<E>
* List\<E> extends Collection\<E>

Here, a PayloadList<E, P> is an extension of the List<E> interface.
```java
public interface PayloadList<E, P> extends List<E> {
    void setPayload(int index, P val) {
        // ... 
    }
}
```

### Wildcards
Use of (?) as a wildcard to represent an unknown type. It can be used as the type of parameter, field, local variable or even as a return type.

You can specify upper and lower bounds for wildcards, but you cannot specify both.

```java
// Recall - Type extends Integer
public class Class<T extends Integer> {
    // use T here
}
```
```java
// Upper bounded wildcard:
public class Class<? extends Foo> {}

// List of unknowns which extend number
public double sumOfList(List<? extends Number> list) {
    double s = 0.0;
    for (Number n : list) {
        s += n.getValue();
    }       
    return s;
}
```

```java
// Unbounded wildcard:
public class Class<?> {}

// List of unknown objects (unbounded)
public void printList<List<?> list> {
    for (Object o : list) {
        // Do something
    }
}
```

```java
// Lower bounded wildcard:
public class Class<? super Foo> {}

// List of unknown objects which super number
public void addNumbers(List<? super Integer> list) {
    for (int i = 0l i <= 10; i++) {
        list.add(i);
    }
}
```

## Collections
Collections framework is a unified architecture for representing and manipulating collections, which is simply an object that groups multiple elements into a single unit.

~ C++ STL

They contain the following:
* **Interfaces**: allow collections to be manipulated independently of the details of their representation
* **Implementations**: concrete implementations of the collection interfaces
* **Algorithms**: methods that perform useful computations, sorting, searching on objects that implement collection interfaces. These algorithms are supposed to be polymorphic - ie can be used on anything.

So like... C++ STL.

Collection interface can be implemented, it contains methods that perform basic operations:
```java
int size();
boolean isEmpty();
boolean contains(Object element);
boolean add(E element);
boolean remove(Object element);
Iterator<E> iterator();
```

## Iterator Pattern (Behavioural)
Aims to provide a way to access elements of an aggregate object sequentially without exposing its underlying representation. This is because exposing representation breaks encapsulation.

A separate Iterator object looks after the access and traversal of the aggregate object. The Iterator object is usually implemented as an inner class of an aggregate class, this allows the iterator to access the internal data structures of the aggregate.

```java
public class ArrayListSet<E> implements Set<E> {
    private ArrayList<E> elements;

    public Iterator<E> iterator() {
        Iterator <E> it = new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < elements.size() 
                && elements.get(currentIndex) != null;
            }

            @Override
            public E next() {
                return elements.get(currentIndex++);
            }
        };
        return it;
    }
}

public class Client {
    Set<E> s = new ArrayListSet<E>();
    s.add(1);
    s.add(3);
    s.add(5);

    // No need to know internal representation 
    Iterator it = s.iterator();
    while (it.hasNext()) {
        Object o = it.next();
    }
}
```

## Decorator Pattern (Structural)
Aim is to attach additional responsibilities to an object dynamically (at runtime), instead of creating sub-classes.

While inheritance extends behaviours at compile time, additional functionality is bound to all the instances of that class for their life time. Decorators prefers composition over inheritance for its implementation.

Structure:
* **Client**: calls the component interface
* **Component (interface)**: shared interface for individual Components and Decorators
* **Component1 (class)**: defines objects that get *decorated*, these are the base items, like leaf in composite
* **Decorator (interface)**: shared interface for all Decorators to implement
* **Decorator1, Decorator2, etc (classes)**: Implement additional functionality, and maintains a reference to the underlying object that was decorated (the decorator acts as a wrapper), forwards requests to underlying objects

Given that the decorator has the same supertype that it decorates, we can pass around a decorated object in place of the original (wrapped) object.

The structure of the Decorator Pattern is very similar to the Composite Pattern, except instead of adding objects to composite classes, Decorator creates a new object which wraps around the old one. Both patterns make use of polymorphism to recursively call identically named functions in different contexts with different functions.

The Decorator pattern has base components and decorators. Objects start out as base objects, and then they are wrapped around with decorators, which can then be wrapped around with more decorators, and so on, etc. This is possible because of the sharing of common interfaces that components and decorators implement.

Example code:
```java
public abstract class Car {
    public abstract String getName();
    public abstract int cost();
}

public class Commodore extends Car {
    private String name;
    private int cost;

    public Commodore() {
        this.name = "Commodore";
    }

    public String getName() {
        return this.name;
    }

    public int cost() {
        return 30000;
    }
}

public abstract class ExtrasDecorator extends Car {
    public abstract String getName();
    public abstract int cost();
}

public class LeatherSeats extends ExtrasDecorator() {
    Car car;

    public LeatherSeats(Car car) {
        // re-assign car
        this.car = car;
    }

    public String getDescription() {
        return car.getDescription() + ", Leather Seats";
    }

    public int cost() {
        return 1000 + car.cost();
    }
}

public class SatNav extends ExtrasDecorator() {
    Car car;
    
    public SatNav(Car car) {
        // re-assign car
        this.car = car;
    }

    public String getDescription() {
        return car.getDescription() + ", Sat Nav";
    }

    public int cost() {
        return 2000 + car.cost();
    }
}


public class Client {
    Car c = new Commodore();
    // adds leather seats to Commodore
    c = new LeatherSeats(c);
    // adds SatNav to Commodore
    c = new SatNav(c);
    System.out.println(c.getDescription());
    System.out.println(c.cost());
}
```

## Adapter Pattern (Structural)
Aims to convert the interface of a class into another interface expected by the client. This is often used to make existing classes to work with a client class without modifying its internal representation.

The **adapter class** by mapping/joining the functionality of two different types/interfaces. The **adapter pattern** offers a wrapper around an existing useful class for the client to use, and does not offer any new/additional functionality.

Structure:
* The adapter contains an instance of the class it wraps
* The adapter makes calls to the instance of the wrapped object

```java
public interface LightningPhone {
    public void recharge();
    public void useLightning();
}

public interface MicroUsbPhone {
    public void recharge();
    public void useMicroUsb();
}

public class Iphone implements LightningPhone {}
public class Android implements MicroUsbPhone {}

public class LightningToMicroUsbAdapter implements MicroUsbPhone {
    private final LightningPhone lightningPhone;

    public LightningToMicroUsbAdapter(LightningPhone lightningPhone) {
        this.lightningPhone = lightningPhone;
    }

    @Override
    public void useMicroUsb() {
        // Adapts the behaviour here
        System.out.println("MicroUsb connected");
        lightningPhone.useLightning();
    }

    @Override
    public void recharge() {
        lightningPhone.recharge();
    }
}

public class Client {
    static void rechargeMicroUsbPhone(MicroUsbPhone phone) {
        phone.useMicroUsb();
        phone.recharge();
    }

    static void rechargeLightningPhone(LightningPhone phone) {
        phone.useLightning();
        phone.recharge();
    }

    public static void main() {
        Android android = new Android();
        Iphone iphone = new Iphone();
        
        rechargeMicroUsbPhone(android);
        rechargeLightningPhone(iphone);

        // This works because LightningToMicroUsbAdapter is an implementation of MicroUsbPhone
        rechargeMicroUsbPhone(new LightningToMicroUsbAdapter(iphone));
    }
}
```

# Week 8
## Template Pattern (Behavioural)
Template method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure, by defining the skeleton of a behaviour (by implememting the invariant parts and deferring variant parts to subclasses)

This is similar to the implementation of Enemy movement strategy in the major project. The invariant parts of the A* algorithm were implemented within Enemy.java, and variant parts (neighbour filtering) was implemented in subclasses.

```java

public abstract class CaffeineBeverageWithHook {
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    // These two methods may be overriden
    public abstract void brew();
    public abstract void addCondiments();

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    boolean customerWantsCondiments() {
        return true;
    }
}


```



