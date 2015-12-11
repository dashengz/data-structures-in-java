import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

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
      LinkedList<Double> tmp = new LinkedList<>();
      for (int sodaSize : sodaSizes) {
        if (i - sodaSize >= 0)
          tmp.add(minCosts[i - sodaSize] + minCosts[sodaSize]);
      }
      minCosts[i] = Collections.min(tmp);
    }

    // For debug
    // System.out.println(Arrays.toString(minCosts));
    // for (int i = minCosts.length - 100; i < minCosts.length; i++) System.out.print(minCosts[i] + ",");

    return minCosts[n];
  }

  public static int maximumSodaNumber(int[] sodaSizes, double[] costs, double cost) {
    double maxCost = costs[0];
    int maxSize = sodaSizes[0];
    for (int i = 0; i < costs.length; i++) {
      if (costs[i] > maxCost) {
        maxCost = costs[i];
        maxSize = sodaSizes[i];
      }
    }
    // System.out.println(maxCost + " " + maxSize);

    int guess = (int) (cost / maxCost + 1) * maxSize;

    // For debug
    // System.out.println(guess);

    // minimumSodaCost()
    double[] minCosts = new double[guess+1];
    minCosts[0] = 0;
    for (int i = 0; i < sodaSizes.length; i++) {
      minCosts[sodaSizes[i]] = costs[i];
    }
    for (int i = 2; i <= guess; i++) {
      LinkedList<Double> tmp = new LinkedList<>();
      for (int sodaSize : sodaSizes) {
        if (i - sodaSize >= 0)
          tmp.add(minCosts[i - sodaSize] + minCosts[sodaSize]);
      }
      minCosts[i] = Collections.min(tmp);
    }

    // go back from the end of the array and find the first one that's no larger than cost
    int maxSoda = 0;
    for (int i = minCosts.length - 1; i >= 0; i--) {
      if (minCosts[i] <= cost) {
        maxSoda = i;
        break;
      }
    }

    return maxSoda;
  }

  public static int[] minimalSodaCostCombinations(int[] sodaSizes, double[] costs, int n) {

    return null; 
  }

  public static void main(String[] args) {
    int[] sodaSizes = new int[] { 1, 6, 12, 25, 36 };
    double[] costs = new double[] { 0.8, 4, 7.5, 14, 20 };
    System.out.println(minimalSodaCost(sodaSizes, costs, 100));
    System.out.println(maximumSodaNumber(sodaSizes, costs, 52.8));
    System.out.println(Arrays.toString(minimalSodaCostCombinations(sodaSizes, costs, 105)));
  }
}
