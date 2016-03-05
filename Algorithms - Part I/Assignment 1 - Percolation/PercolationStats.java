
public class PercolationStats {
  
  private int ncol;
  private int ntimes;
  private double [] threshold; 
  
  /****************************************************************************
   *  perform T independent experiments on an N-by-N grid.   
   ****************************************************************************/
  public PercolationStats(int nr, int ntest)  {
    
    if (nr <= 0 || ntest <= 0) {
      throw new java.lang.IllegalArgumentException("illegal argument");
    }
    
    this.ncol  = nr;
    this.ntimes = ntest;
  
    threshold = new double[ntimes];
    
    for (int itime = 0; itime < ntimes; itime++) {
      
      Percolation perc = new Percolation(ncol);
      int count = 0;
    
      while (!perc.percolates())  {
        int row = StdRandom.uniform(ncol) + 1;
        int col = StdRandom.uniform(ncol) + 1;
      
        if (!perc.isOpen(row, col)) {
          perc.open(row, col);
          count++;
        }
      }
    
      threshold[itime] = (((double) count) / ((double) (ncol * ncol)));
    }
  }
  
  /****************************************************************************
   *  sample mean of percolation threshold.   
   ****************************************************************************/
  public double mean()  {
    return StdStats.mean(threshold);
  }
  
  /****************************************************************************
   *  sample standard deviation of percolation threshold.   
   ****************************************************************************/
  public double stddev() {
    return StdStats.stddev(threshold);
  }

  /****************************************************************************
   *  low  endpoint of 95% confidence interval.   
   ****************************************************************************/
  public double confidenceLo() {
    return (mean() - ((1.96 * stddev()) / Math.sqrt(ntimes)));
  }
  
  /****************************************************************************
   *  high endpoint of 95% confidence interval.   
   ****************************************************************************/
  public double confidenceHi() {
    return (mean() + ((1.96 * stddev()) / Math.sqrt(ntimes)));
  }
  

  /****************************************************************************
   *  test client (described below).   
   ****************************************************************************/
  public static void main(String[] args)  {
    PercolationStats ps = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
    
    StdOut.println("mean                    = " + ps.mean());
    StdOut.println("stddev                  = " + ps.stddev());
    StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
  }
}
