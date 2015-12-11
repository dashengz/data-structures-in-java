import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SuperSoda {

  public static double minimalSodaCost(int[] sodaSizes, double[] costs, int n) {
    // Caching results
    double[] minCosts = new double[n+1];

    // minCosts[0]
    minCosts[0] = 0;
    // minCosts[1,6,12,25,36]
    for (int i = 0; i < sodaSizes.length; i++) {
      minCosts[sodaSizes[i]] = costs[i];
    }

    // For debug
    // System.out.println(Arrays.toString(minCosts));

    // Updating minCosts[], starting from minCosts[2]
    for (int i = 2; i <= n; i++) {
      ArrayList<Double> tmp = new ArrayList<>();
      for (int sodaSize : sodaSizes) {
        if (i - sodaSize >= 0)
          tmp.add(minCosts[i - sodaSize] + minCosts[sodaSize]);
      }
      minCosts[i] = Collections.min(tmp);
    }

    // For debug
    // System.out.println(Arrays.toString(minCosts));

    return minCosts[n];
  }

  public static int maximumSodaNumber(int[] sodaSizes, double[] costs, double cost) {

    return 0;
  }

  public static int[] minimalSodaCostCombinations(int[] sodaSizes, double[] costs, int n) {

    return null; 
  }

  public static void main(String[] args) {
    int[] sodaSizes = new int[] { 1, 6, 12, 25, 36 };
    double[] costs = new double[] { 0.8, 4, 7.5, 14, 20 };
    System.out.println(minimalSodaCost(sodaSizes, costs, 100));
    System.out.println(maximumSodaNumber(sodaSizes, costs, 56.0));
    System.out.println(Arrays.toString(minimalSodaCostCombinations(sodaSizes, costs, 105)));
  }
}
