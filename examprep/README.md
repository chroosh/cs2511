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
    // ...
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

This was because Entities were stored as a <list> at given x, y coordinate (Cell) - 

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
### Composite Design (Behavioural)
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





