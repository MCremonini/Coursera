
public class PointSET {

  private SET<Point2D> set;
  
  public PointSET() {
    set = new SET<Point2D>();
  }
  
  public boolean isEmpty() {
    return size() == 0;
  }
  
  public int size() {
    return set.size();
  }
  
  public void insert(Point2D p) {
    if (p == null) {
      throw new java.lang.NullPointerException("");
    }
    set.add(p);
  }
  
  public boolean contains(Point2D p) {
    if (p == null) {
      throw new java.lang.NullPointerException("");
    }
    return set.contains(p);
  }
  
  public void draw() {
    for (Point2D p : set) {
      p.draw();
    }
  }
  
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) {
      throw new java.lang.NullPointerException("");
    }
    Stack<Point2D> stack = new Stack<Point2D>();
      
    for (Point2D p : set) {
      if (rect.contains(p)) {
        stack.push(p);
      }
    }
      
    return stack;
  }
  
  public Point2D nearest(Point2D p) {
    if (p == null) {
      throw new java.lang.NullPointerException("");
    }
    if (size() == 0) {
      return null;
    }
      
    Point2D neighbor = null;
      
    for (Point2D point : set) {
      if (neighbor == null) {
        neighbor = point;
      }
          
      if (point.distanceSquaredTo(p) < neighbor.distanceSquaredTo(p)) {
        neighbor = point;
      }
    }
      
    return neighbor;
  }

}
