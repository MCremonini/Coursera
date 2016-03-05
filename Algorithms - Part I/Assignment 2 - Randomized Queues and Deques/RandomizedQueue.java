import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item [] data;
  private int count  = 0;
  
  public RandomizedQueue() {

  }
  
  public boolean isEmpty() {
    return (count == 0);
  }
  
  public int size() {
    return count;
  }
  
  public void enqueue(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException("");
    }
    
    checkArray();
    
    data[count] = item;
    count++;
  }
  
  public Item dequeue() {
    
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("");
    }
    
    checkArray();
    
    int pos = StdRandom.uniform(count);
    Item ret = data[pos];
    
    data[pos] = data[count - 1];
    data[count - 1] = null;
    
    count--;
    
    return ret;
  }
  
  private void checkArray() {
    if (data == null) {
      data = (Item[]) new Object[1];
    }
    else if (count >= data.length) {
      Item [] tmp = (Item[]) new Object[data.length * 2];
      for (int i = 0; i < count; i++) {
        tmp[i] = data[i];
      }
      data = tmp;
    }
    else if (count <= (data.length / 4)) {
      Item [] tmp = (Item[]) new Object[data.length / 2];
      for (int i = 0; i < count; i++) {
        tmp[i] = data[i];
      }
      data = tmp;
    }
  }
  
  /*
  private Item dequeue_test(int iitem) {
    
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("");
    }
    
    int pos = iitem;
    Item ret = null;
    
    int i = 0;
    Node curr = first;
    Node prev = null;
    while (curr != null && i != pos) {
      prev = curr;
      curr = curr.next;
      i++;
    }
    
    if (curr != null) {
      ret = curr.data;
      
      if (prev != null) {
        prev.next = curr.next;
      }

      if (curr == first) {
        first = curr.next;
        
        if (first == null)
          last = null;
      }

      if (curr == last) {
        last = prev;
      }
      
      curr = null;
      count--;
    }
    else {
      System.out.println("error ");
    }
    
    return ret;
  }
*/
  public Item sample() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("");
    }
    
    int pos = StdRandom.uniform(count);
    
    return data[pos];
  }
  
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }
  
  private class RandomizedQueueIterator implements Iterator<Item> {
    private int pos;
    private int totalsize;
    private List<Item> itemRandom;

    public RandomizedQueueIterator() {
      
      totalsize = RandomizedQueue.this.size();
      itemRandom = new ArrayList<>();
      
      for (int i = 0; i < RandomizedQueue.this.count; i++) {
        itemRandom.add(RandomizedQueue.this.data[i]);
      }
      
      // shuffling
      for (int i = 0; i < totalsize; i++) {
        int rnd = StdRandom.uniform(i + 1);
        
        Item tmp = itemRandom.get(i);
        itemRandom.set(i, itemRandom.get(rnd));
        itemRandom.set(rnd, tmp);
      }
      
      this.pos = 0;
    }

    public boolean hasNext() {
      return (pos < totalsize);
    }

    public Item next() {
        if (this.hasNext()) {
            return itemRandom.get(pos++);
        }
        throw new java.util.NoSuchElementException();
    }

    public void remove() {
        throw new java.lang.UnsupportedOperationException();
    }
  }
  
  public static void main(String[] args) {
    testRandomCall();
    //testMirato();
    
    testAddRemove();
    testAddRemoveAll();
    testCoursera();
  }
  
  private static void testCoursera() {
    RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
    rq.enqueue(37);
    rq.enqueue(42);
    rq.enqueue(13);
    assert (rq.size() == 3);
    rq.enqueue(43);
    assert (rq.size() == 4);
    rq.enqueue(13);
    rq.enqueue(21);
    rq.enqueue(5);
    assert (rq.size() == 7);
    assert (rq.size() == 7);
    
    assert (rq.sample() != null);
  }
  
/*
  private static void testMirato() {
    RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
    
    int val = 0;
    int rand = 11;
     
    for (int i = 0; i < rand; i++) {
      d.enqueue(d.size() + 1);
    }
    
     
    Iterator<Integer> it = d.iterator();
    while (it.hasNext()) {
      System.out.print(it.next() + " ");
    }
    System.out.println("");
    
    try {
      val = d.dequeue_test(d.size() - 1);
      System.out.println("Deleted: " + val);
    }
    catch (java.util.NoSuchElementException e) {
      System.out.println("catched NoSuchElementException");
    }
    
    try {
      val = d.dequeue_test(3);
      System.out.println("Deleted: " + val);
    }
    catch (java.util.NoSuchElementException e) {
      System.out.println("catched NoSuchElementException");
    }
    
    
    
    rand = 12;
    
    for (int i = 0; i < rand; i++) {
      d.enqueue(d.size() + 1);
    }
    
     
    it = d.iterator();
    while (it.hasNext()) {
      System.out.print(it.next() + " ");
    }
    System.out.println("");
    
    
    
    try {
      val = d.dequeue_test(20);
      System.out.println("Deleted: " + val);
    }
    catch (java.util.NoSuchElementException e) {
      System.out.println("catched NoSuchElementException");
    }

  }
  */
  private static void testRandomCall() {
    RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
    
    int val = 0;
    int rand;
    int counting = 0;
    
    while (counting < 500) {
      rand = StdRandom.uniform(20);
    
      for (int i = 0; i < rand; i++) {
        d.enqueue(d.size() + 1);
      }
    
      System.out.println("Added: " + rand);
     
      Iterator<Integer> it = d.iterator();
      while (it.hasNext()) {
          System.out.print(it.next() + " ");
      }
      System.out.println("");
      
      // assert (d.size() == rand);
      System.out.println("Size: " + d.size());
    
      rand = StdRandom.uniform(20);
    
      for (int i = 0; i < rand; i++) {
        try {
          val = d.dequeue();
          System.out.println("Deleted: " + val + "   Size:" + d.size());
        }
        catch (java.util.NoSuchElementException e) {
          System.out.println("catched NoSuchElementException");
        }
      }
      
      counting++;
    }
  }
 
  private static void testAddRemove() {
    RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
    d.enqueue(10);
    d.enqueue(20);
    d.enqueue(30);
    d.enqueue(100);
    d.enqueue(101);
    d.enqueue(102);

    assert (d.size() == 6);
    System.out.println(d.size());

    Iterator<Integer> i = d.iterator();
    while (i.hasNext()) {
        System.out.print(i.next() + " ");
    }
    System.out.println("");

    int val = d.dequeue();
    System.out.println("Deleted: " + val);
    val = d.dequeue(); 
    System.out.println("Deleted: " + val);
    
    assert (d.size() == 4);
    System.out.println(d.size());

    i = d.iterator();
    while (i.hasNext()) {
        System.out.print(i.next() + " ");
    }
    System.out.println("");
    
    
    d.sample();
    d.sample(); 

    assert (d.size() == 4);
    System.out.println(d.size());

    i = d.iterator();
    while (i.hasNext()) {
        System.out.print(i.next() + " ");
    }
    System.out.println("");
  }

  private static void testAddRemoveAll() {
    RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();

    d.enqueue(10);
    d.enqueue(20);
    assert (d.size() == 2);

    int val = d.dequeue(); 
    System.out.println("Deleted: " + val);
    val = d.dequeue(); 
    System.out.println("Deleted: " + val);
    assert (d.size() == 0);

    d.enqueue(30);
    d.enqueue(50);
    assert (d.size() == 2);

    val = d.dequeue(); 
    System.out.println("Deleted: " + val);
    val = d.dequeue(); 
    System.out.println("Deleted: " + val);
    assert (d.size() == 0);
  }
}
