import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;



public class Fast {
    
  public static void main(String[] args) {

    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.setPenRadius(.01);

    In in = new In(args[0]);
    int numPoints = in.readInt();
    
    Point[] origPoints = new Point[numPoints];
    Point[] points = new Point[numPoints];
    Map<String, ArrayList<Point>> data = new HashMap<>();
    
    for (int i = 0; i < numPoints; i++) {
      int x = in.readInt();
      int y = in.readInt();
      origPoints[i] = new Point(x, y);
      points[i] = new Point(x, y);
      origPoints[i].draw();
    }

    double slope = 0;
    for (int p = 0; p < numPoints; p++) {
      
      sort(points, origPoints[p].SLOPE_ORDER);      
      // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p      
      ArrayList<Point> collinearPoints = new ArrayList<Point>();  
      collinearPoints.clear();
      for (int q = 1; q < numPoints; q++) {
          
        if (collinearPoints.isEmpty()) {
          collinearPoints.add(points[q]);
        } 
        else if (origPoints[p].slopeTo(points[q - 1]) == origPoints[p].slopeTo(points[q])) {
          slope = origPoints[p].slopeTo(points[q - 1]);
          collinearPoints.add(points[q]);
        }
        else if (collinearPoints.size() > 2) {
          
          collinearPoints.add(origPoints[p]);
          Collections.sort(collinearPoints);
          
          String strSlope = Double.toString(slope) + "_" + Collections.min(collinearPoints).toString();
          
          if (data.get(strSlope) == null) {

            for (int i = 0; i < collinearPoints.size() - 1; i++) {
              StdOut.print(collinearPoints.get(i));
              StdOut.print(" -> ");
            }
          
            StdOut.println(Collections.max(collinearPoints));
          
            Collections.min(collinearPoints).drawTo(Collections.max(collinearPoints));
          
            data.put(strSlope, collinearPoints);
          }

          collinearPoints.clear();
          collinearPoints.add(points[q]);
          continue;
        } 
        else {
          collinearPoints.clear();
          collinearPoints.add(points[q]);
        }
      }
      
      if (collinearPoints.size() > 2) {
        collinearPoints.add(origPoints[p]);
        Collections.sort(collinearPoints);
          
        String strSlope = Double.toString(slope) + "_" + Collections.min(collinearPoints).toString();
          
        if (data.get(strSlope) == null) {

          for (int i = 0; i < collinearPoints.size() - 1; i++) {
            StdOut.print(collinearPoints.get(i));
            StdOut.print(" -> ");
          }
          
          StdOut.println(Collections.max(collinearPoints));

          Collections.min(collinearPoints).drawTo(Collections.max(collinearPoints));
          
          data.put(strSlope, collinearPoints);
        }
        collinearPoints.clear();
      } 
    }
  }

  
  // stably merge a[lo..m] with a[m+1..hi] using aux[lo..hi]
  private static void merge(Point[] a, Point[] aux, int lo, int m, int hi, Comparator<Point> comparator) {
    // copy to aux[]
    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }
    
    // merge back to a[]
    int i = lo, j = m+1;
    for (int k = lo; k <= hi; k++) {
      if      (i > m)                a[k] = aux[j++];
      else if (j > hi)               a[k] = aux[i++];
      else if (less(comparator, aux[j], aux[i])) a[k] = aux[j++];
      else                           a[k] = aux[i++];
    }
  }

  
  
  // bottom-up mergesort
  
  private static void sort(Point[] a, Comparator<Point> comparator) {
  
    int N = a.length;
    
    Point[] aux = new Point[N];
    
    for (int n = 1; n < N; n = n+n) {
    
      for (int i = 0; i < N-n; i += n+n) {
      
        int lo = i;
        int m  = i+n-1;
        int hi = Math.min(i+n+n-1, N-1);
        merge(a, aux, lo, m, hi, comparator);
      }
    }
  }


  // is v < w ?
  private static boolean less(Comparator<Point> comparator, Point v, Point w) {
    return comparator.compare(v, w) < 0;
  }
}
