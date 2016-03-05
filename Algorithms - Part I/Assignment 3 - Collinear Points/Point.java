import java.util.Comparator;


public class Point implements Comparable<Point> {
  
  // compare points by slope
  public final Comparator<Point> SLOPE_ORDER = new ComparatorPoint();


  private class ComparatorPoint implements Comparator<Point> {
    public int compare(Point o1, Point o2) {
      if (o1 == null && o2 == null) {
        throw new java.lang.NullPointerException("");
      }
      double slope1 = slopeTo(o1);
      double slope2 = slopeTo(o2);
      return Double.compare(slope1, slope2);
    }
  }
  
  private final int x;                              // x coordinate
  private final int y;                              // y coordinate


  // create the point (x, y)
  public Point(int x, int y) {
    /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
  }

  
  // plot this point to standard drawing
  public void draw() {
    /* DO NOT MODIFY */
    StdDraw.point(x, y);
  }

  
  // draw line between this point and that point to standard drawing
  public void drawTo(Point that) {
    /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  
  // slope between this point and that point
  public double slopeTo(Point that) {
    if (that == null) {
      throw new java.lang.NullPointerException("");
    }
    if (that.x == x) {
      if (that.y == y)
        return Double.NEGATIVE_INFINITY;
      
      return Double.POSITIVE_INFINITY;
    }
    
    if (that.y == y) {
      return 0.0;
    }
    
    return (((double) (that.y - y)) / ((double) (that.x - x)));
  }

  
  // is this point lexicographically smaller than that one?
  // comparing y-coordinates and breaking ties by x-coordinates
  public int compareTo(Point that) {
    if (that == null) {
      throw new java.lang.NullPointerException("");
    }
    int diff = y - that.y;
    if (diff == 0) {
      diff = x - that.x;
    }
    
    return diff;
  }

  
  // return string representation of this point
  public String toString() {
    /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }

  
  // unit test
  public static void main(String[] args) {
    /* YOUR CODE HERE */
  }
}