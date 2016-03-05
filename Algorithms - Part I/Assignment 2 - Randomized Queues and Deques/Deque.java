import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {

  private Node first = null;
  private Node last  = null;
  private int count  = 0;
  
  private class Node {
    private Node next = null;
    private Node prev = null;
    private Item data = null;
    
    public Node(Item item, Node next, Node prev) {
      this.data = item;
      this.next = next;
      this.prev = prev;
    }
  }
  
  
  public Deque() {
    
  }
  
  public boolean isEmpty() {
    //return (data == null);
    return (count == 0);
  }
  
  public int size() {
    return count;
  }
  
  public void addFirst(Item item) {
    
    if (item == null) {
      throw new java.lang.NullPointerException("");
    }
    
    Node newItem = new Node(item, null, first);
    
    if (first != null) {
      first.next = newItem;
    }
    
    if (last == null) {
      last = newItem;
    }
    
    first = newItem;
    
    count++;
  }
  
  public void addLast(Item item) {
    
    if (item == null) {
      throw new java.lang.NullPointerException("");
    }
    
    Node newItem = new Node(item, last, null);
    
    if (last != null) {
     last.prev = newItem;
    }
    
    if (first == null) {
      first = newItem;
    }
    
    last = newItem;
    
    count++;
  }
  
  
  public Item removeFirst() {
    
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("");
    }
    
    Item ret = null;
    
    if (first != null) {
      ret = first.data;
      
      if (first == last) {
        first = null;
        last = null;
      }
      else {
        first = first.prev;
        if (first != null) {
          first.next = null;
        }
      }
      
      count--;
    }
    
    return ret;
  }
  
  public Item removeLast() {
    
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("");
    }
    
    Item ret = null;
    
    if (last != null) {
      ret = last.data;
      
      if (first == last) {
        first = null;
        last = null;
      }
      else {
        last = last.next;
        if (last != null) {
          last.prev = null;
        }
      }
      
      count--;
    }
    
    return ret;
  }
  
  // return an iterator over items in order from front to end
  public Iterator<Item> iterator() {
    return new ListIterator();
  }


  private class ListIterator implements Iterator<Item> {
    private Node cursor;

    public ListIterator() {
      cursor = Deque.this.first;
    }

    public boolean hasNext() {
      return (cursor != null);
    }

    public Item next() {
        if (this.hasNext()) {
          Item ret = cursor.data;
          
          cursor = cursor.prev;
          
          return ret;
        }
        throw new java.util.NoSuchElementException();
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException();
    }
  }


  
  public static void main(String[] args) {
    testAddRemove();
    testAddRemoveAll();
    testAddFrontRemoveEnd();
    testAddEndRemoveFront();
  }

  private static void testAddEndRemoveFront() {
    Deque<Integer> d = new Deque<Integer>();

    d.addLast(10);
    d.addLast(20);
    d.addLast(30);
    d.addLast(40);
    d.addLast(50);
    assert (d.size() == 5);

    assert (d.removeLast() == 50);

    assert (d.removeLast() == 40);
    
    assert (d.removeFirst() == 10);
    
    assert (d.removeLast() == 30);
    
    assert (d.size() == 1);

  }
  
  
  private static void testAddFrontRemoveEnd() {
    Deque<Integer> d = new Deque<Integer>();

    d.addFirst(10);
    d.addFirst(20);
    d.addFirst(30);
    d.addFirst(40);
    d.addFirst(50);
    assert (d.size() == 5);

    assert (d.removeLast() == 10);

    assert (d.removeLast() == 20);
    
    assert (d.removeFirst() == 50);
    
    assert (d.removeLast() == 30);
    
    assert (d.size() == 1);

  }
  
  
  private static void testAddRemove() {
    Deque<Integer> d = new Deque<Integer>();
    d.addFirst(10);
    d.addFirst(20);
    d.addFirst(30);
    d.addLast(100);
    d.addLast(101);
    d.addLast(102);

    int size = d.size();
    assert (size == 6);
    System.out.println(d.size());

    Iterator<Integer> i = d.iterator();
    while (i.hasNext()) {
        System.out.print(i.next() + " ");
    }
    System.out.println("");

    int removed = d.removeFirst();
    assert (removed == 30);
    
    removed = d.removeLast(); 
    assert (removed == 102);

    size = d.size();
    assert (size == 4);
    System.out.println(d.size());

    i = d.iterator();
    while (i.hasNext()) {
        System.out.print(i.next() + " ");
    }
    System.out.println("");
    
    
    removed = d.removeFirst();
    assert (removed == 20);
    
    removed = d.removeLast(); 
    assert (removed == 101);

    size = d.size();
    assert (size == 2);
    System.out.println(d.size());

    i = d.iterator();
    while (i.hasNext()) {
        System.out.print(i.next() + " ");
    }
    System.out.println("");
  }

  private static void testAddRemoveAll() {
    Deque<Integer> d = new Deque<Integer>();

    d.addFirst(10);
    d.addLast(20);
    int size = d.size();
    assert (size == 2);

    int removed = d.removeFirst(); 
    assert (removed == 10);
    
    removed = d.removeLast(); 
    assert (removed == 20);
    size = d.size();
    assert (size == 0);

    d.addFirst(30);
    d.addLast(50);
    size = d.size();
    assert (size == 2);

    removed = d.removeFirst(); 
    assert (removed == 30);
    
    removed = d.removeLast(); 
    assert (removed == 50);
    size = d.size();
    assert (size == 0);
    
    Iterator<Integer> i = d.iterator();
    while (i.hasNext()) {
      System.out.print(i.next() + " ");
    }
    System.out.println("");
  }
}
