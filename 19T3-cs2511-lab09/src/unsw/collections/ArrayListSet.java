/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E e) {
      // TODO Implement me
      this.elements.add(e);
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return elements.contains(e);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
      // TODO Implement me 
      for (E e : elements) {
        if (!other.contains(e)) {
          return false;
        }
      }
      
      return true;
    }

    @Override
    public Iterator<E> iterator() {
      // TODO Implement me

      Iterator<E> it = new Iterator<E>() {
        private int currentIndex = 0;
        
        @Override
        public boolean hasNext() {
          return currentIndex < elements.size() && elements.get(currentIndex) != null;
        }
        
        @Override
        public E next() {
          return elements.get(currentIndex++);
        }
        
      };
      return it;
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
      // TODO Implement me
      // in either one
      Set<E> ret = new ArrayListSet<>();
      for (E e : elements) {
        ret.add(e);
      }
      
      for (E e : other) {
        if (!ret.contains(e)) {
          ret.add(e);
        }
      }
      return ret;

    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
      // TODO Implement me
      // in both
      Set<E> ret = new ArrayListSet<>();
      for (E e : elements) {
        if (other.contains(e)) {
          ret.add(e);
        }
      }
      return ret;
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @Override
    public boolean equals(Object other) {
      // TODO Implement me
      if (other instanceof ArrayListSet) {
        Set<E> set = (Set<E>)other;
        
        if (set.size() != elements.size()) {
          return false;
        }
        
        
        for (E e : set) {
          if (!elements.contains(e)) {
            return false;
          }
        }
        
        for (E e : elements) {
          if (!set.contains(e)) {
            return false;
          }
        }
        
      }

      return false;
    }

}
