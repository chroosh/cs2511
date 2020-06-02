package overrideStatic;

public class Main extends Interface {
  
  
  //  @Override
  public static int addTwoNumbers(int a, int b) {
    return a + b;
    
  }
  
  // Seems like there is no need to override static methods.
  // You cannot override static methods in java, but you can declare
  // the same method in the child class, this is known as method hiding
  
  // When a compiler is not able to resolve a binding at compile time,
  // the binding must occur at runtime. Static methods can be binded
  // at compile time because they do not require knowledge of instances.
  
  // Non-static methods cannot be binded at compile time because during
  // compile time, the instances of the classes to which they belong to
  // do not exist yet. So they are binded at runtime. The same goes for
  // overriding non-static methods.
  
  // TLDR ceebs, just use method hiding
  
  
  

}
