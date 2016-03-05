


public class Subset {

  public static void main(String[] args) {
    
    int k = Integer.parseInt(args[0]);

    RandomizedQueue<String> data = new RandomizedQueue<String>();
    while (StdIn.hasNextLine() && !StdIn.isEmpty()) {
      data.enqueue(StdIn.readString());
    }

    for (int i = 0; i < k; i++) {
        System.out.println(data.dequeue());
    }
  }

}
