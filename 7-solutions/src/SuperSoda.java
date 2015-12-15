import java.util.ArrayList;
import java.util.Arrays;

public class SuperSoda {

  public static double minimalSodaCost(int[] sodaSizes, double[] costs, int n) {
    return minimalSodaCosts(sodaSizes, costs, n)[n];
  }

  public static double[] minimalSodaCosts(int[] sodaSizes, double[] costs, int n) {
    // initialize and set cost of 0 soda sizes to 0
    // we can't buy anything lower than the smallest sodaSize, so we set cost to
    // 0 for those
    double[] minimalCosts = new double[n + 1];
    int smallestSize = sodaSizes[0];
    for (int i = 0; i < smallestSize; i++) {
      minimalCosts[i] = 0;
    }

    // iterate up the minimalCosts table
    for (int i = 1; i < minimalCosts.length; i++) {

      // the cost for the latest element is the minimum of the possible choices
      double[] candidates = new double[costs.length];
      for (int j = 0; j < candidates.length; j++) {
        if (i - sodaSizes[j] >= 0) {
          candidates[j] = minimalCosts[i - sodaSizes[j]] + costs[j];
        } else {
          candidates[j] = Double.MAX_VALUE;
        }
      }
      int minIndex = minIndex(candidates);
      minimalCosts[i] = minimalCosts[i - sodaSizes[minIndex]] + costs[minIndex];
    }

    System.out.println(Arrays.toString(minimalCosts));

    return minimalCosts;
  }

  public static int maximumSodaNumber(int[] sodaSizes, double[] costs, double cost) {
    // strategy is to start with an arbitrary maximum size assumption, then keep
    // increasing until we reach the cost specified

    // initialize and set cost of 0 soda sizes to 0
    // we can't buy anything lower than the smallest sodaSize, so we set cost to
    // 0 for those
    ArrayList<Double> minimalCosts = new ArrayList<>();
    int smallestSize = sodaSizes[0];
    for (int i = 0; i < smallestSize; i++) {
      minimalCosts.add(0.0);
    }
    int i = 0;

    while (true) {
      // standard DP algorithm for this problem
      double[] candidates = new double[costs.length];
      for (int j = 0; j < candidates.length; j++) {
        if (i - sodaSizes[j] >= 0) {
          candidates[j] = minimalCosts.get(i - sodaSizes[j]) + costs[j];
        } else {
          candidates[j] = Double.MAX_VALUE;
        }
      }
      int minIndex = minIndex(candidates);
      minimalCosts.set(i, minimalCosts.get(i - sodaSizes[minIndex]) + costs[minIndex]);

      // once we find a cost that exceeds the given cost, return the number of
      // sodas we can buy which is the previous count
      if (minimalCosts.get(i) > cost) {
        return i - 1;
      }
      i++;
    }
  }

  public static int[] minimalSodaCostCombinations(int[] sodaSizes, double[] costs, int n) {
    // set initial combination to all 0
    int[][] combinations = new int[n + 1][sodaSizes.length];

    // initialize and set cost of 0 soda sizes to 0
    // we can't buy anything lower than the smallest sodaSize, so we set cost to
    // 0 for those
    double[] minimalCosts = new double[n + 1];
    int smallestSize = sodaSizes[0];
    for (int i = 0; i < smallestSize; i++) {
      minimalCosts[i] = 0;
    }

    for (int i = 0; i < smallestSize; i++) {
      for (int j = 0; j < sodaSizes.length; j++) {
        combinations[i][j] = 0;
      }
    }

    // iterate up the minimalCosts table
    for (int i = 1; i < minimalCosts.length; i++) {

      // the cost for the latest element is the minimum of the possible choices
      double[] candidates = new double[costs.length];
      for (int j = 0; j < candidates.length; j++) {
        if (i - sodaSizes[j] >= 0) {
          candidates[j] = minimalCosts[i - sodaSizes[j]] + costs[j];
        } else {
          candidates[j] = Double.MAX_VALUE;
        }
      }
      int minIndex = minIndex(candidates);
      minimalCosts[i] = minimalCosts[i - sodaSizes[minIndex]] + costs[minIndex];
      // increment combination chosen too
      combinations[i] = Arrays.copyOf(combinations[i - sodaSizes[minIndex]], sodaSizes.length);
      combinations[i][minIndex]++;
    }

    return combinations[n];
  }

  private static int minIndex(double[] array) {
    double min = Double.MAX_VALUE;
    int minIndex = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  public static void main(String[] args) {
    int[] sodaSizes = new int[] { 1, 6, 12, 25, 36 };
    double[] costs = new double[] { 0.8, 4, 7.5, 14, 20 };
    // System.out.println(minimalSodaCost(sodaSizes, costs, 100));
    System.out.println(maximumSodaNumber(sodaSizes, costs, 7.6));
  }
}
