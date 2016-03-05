public class Percolation {

  private WeightedQuickUnionUF data;
  private int[] data2;
  private int count = 0;
  private int ncol = 0;
  private boolean[] open;


  /****************************************************************************
   *  public Percolation(int nitems).   
   ****************************************************************************/
  public Percolation(int nitems) {
    if (nitems <= 0) {
      throw new IllegalArgumentException("illegal grid size");
    }
  
    ncol = nitems;
    count = nitems * nitems + 2;
    data = new WeightedQuickUnionUF(count);
    data2 = new int[count - 1];
    open = new boolean[count];
  
    for (int i = 0; i < count; i++)    {
      open[i] = false;
    }
    
    for (int i = 0; i < count - 1; i++)    {
      data2[i] = i;
    }
    
    open[0] = true;
    open[count - 1] = true;
  }

  private boolean isConnected(int s1, int s2)  {
    return data.connected(s1, s2);
  }
  
  private boolean isConnected2(int s1, int s2)  {
    return (root2(s1) == root2(s2));
  }

  private int root2(int site)  {
    int pos = site;
    while (pos != data2[pos]) {
      pos = data2[pos];
    }
    return pos;
  }
  
  private void union(int s1, int s2) {
    data.union(s1, s2);
  }
  
  private void union2(int s1, int s2) {
    int d1 = 0;
    int d2 = 0;
    
    int r1 = s1;
    while (r1 != data2[r1]) {
      d1++;
      r1 = data2[r1];
    }
    
    int r2 = s2;
    while (r2 != data2[r2]) {
      d2++;
      r2 = data2[r2];
    }
      
    if (r1 == r2) {
      return;
    }
    
    if (d2 > d1) {
      data2[r1] = r2;
    }  else {
      data2[r2] = r1;
    }
  }

  private int getPos(int row, int col) {
    return ((row - 1) * ncol + (col - 1)) + 1;
  }
  
  private void checkRange(int row, int col) {
    if (row <= 0 || row > ncol || col <= 0 || col > ncol) {
      throw new java.lang.IndexOutOfBoundsException("index not valid");
    }
  }

  /****************************************************************************
   *  public void open(int row, int col).   
   ****************************************************************************/
  public void open(int row, int col)   {
    checkRange(row, col);
    
    if (!isOpen(row, col)) {
      int curr = getPos(row, col);
      open[curr] = true;
      

      // la prima riga la connetto alla cella 0
      // l'ultima riga la connetto alla cella count-1
      if (row == 1) {
        union(curr, 0);
        union2(curr, 0);
      }

      if (row >= ncol) {
        union(curr, count - 1);
      }
      
      
      // top site
      int icol = col;
      int irow = row - 1;
      int neighboor = getPos(irow, icol);
      if (irow >= 1 && open[neighboor]) {
        union(curr, neighboor);
        union2(curr, neighboor);
      }

      // bottom site
      irow = row + 1;
      neighboor = getPos(irow, icol);
      if (irow <= ncol && open[neighboor]) {
        union(curr, neighboor);
        union2(curr, neighboor);
      }

      //left site
      icol = col - 1;
      irow = row;
      neighboor = getPos(irow, icol);
      if (icol >= 1 && open[neighboor]) {
        union(curr, neighboor);
        union2(curr, neighboor);
      }

      // right site
      icol = col + 1;
      neighboor = getPos(irow, icol);
      if (icol <= ncol && open[neighboor]) {
        union(curr, neighboor);
        union2(curr, neighboor);
      }
    }
  }


  // is site (row i, column j) open?
  public boolean isOpen(int row, int col)    {
    checkRange(row, col);
    return open[getPos(row, col)];
  }


  /****************************************************************************
   *  public boolean isFull(int row, int col).   
   ****************************************************************************/
  public boolean isFull(int row, int col)    {
    checkRange(row, col);
    if (isOpen(row, col)) {
      int pos = getPos(row, col);

      return isConnected2(0, pos);
    }
    
    return false;
  }


  /****************************************************************************
   *  public boolean percolates().   
   ****************************************************************************/
  public boolean percolates() {

    return isConnected(0, count - 1);
  }

}
