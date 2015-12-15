import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    return minimalCosts;
  }

  public static int maximumSodaNumber(int[] sodaSizes, double[] costs, double cost) {
    // strategy is to start with an arbitrary maximum size assumption, then keep
    // increasing until we reach the cost specified

    // initialize and set cost of 0 soda sizes to 0
    // we can't buy anything lower than the smallest sodaSize, so we set cost to
    // 0 for those
    int initialN = 8;
    double[] minimalCosts = new double[initialN];
    int smallestSize = sodaSizes[0];
    for (int i = 0; i < smallestSize; i++) {
      minimalCosts[i] = 0;
    }

    // the index to start counting at so that we don't doublecount
    int startIndex = 1;

    // so that people don't kill our CPUs
    int maxSodas = 10000;

    while (minimalCosts.length < maxSodas) {
      // standard DP algorithm for this problem
      for (int i = startIndex; i < minimalCosts.length; i++) {
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

        // once we find a cost that exceeds the given cost, return the number of
        // sodas we can buy which is the previous count
        if (minimalCosts[i] > cost) {
          return i - 1;
        }
      }

      // increment array size and start again, this time at a new point
      initialN *= 2;
      startIndex = minimalCosts.length;
      double[] newMinimalCosts = Arrays.copyOf(minimalCosts, initialN);
      minimalCosts = newMinimalCosts;
    }

    // if we can't find the answer, return a nonsensical answer
    return Integer.MIN_VALUE;
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

  public static int[] maximumSodaNumberCombinations(int[] sodaSizes, double[] costs, double cost) {
    int smallestSize = sodaSizes[0];
    int initialN = smallestSize;

    // set initial combination to all 0
    int[][] combinations = new int[initialN][sodaSizes.length];
    double[] minimalCosts = new double[initialN];

    for (int i = 0; i < smallestSize; i++) {
      minimalCosts[i] = 0;
    }
    for (int i = 0; i < smallestSize; i++) {
      for (int j = 0; j < sodaSizes.length; j++) {
        combinations[i][j] = 0;
      }
    }

    // the index to start counting at so that we don't doublecount
    int startIndex = 1;

    // so that people don't kill our CPUs
    int maxSodas = 10000;

    while (minimalCosts.length < maxSodas) {
      // standard DP algorithm for this problem
      for (int i = startIndex; i < minimalCosts.length; i++) {
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

        // once we find a cost that exceeds the given cost, return the number of
        // sodas we can buy which is the previous count
        if (minimalCosts[i] > cost) {
          return combinations[i - 1];
        } else {
          // increment combination chosen too
          combinations[i] = Arrays.copyOf(combinations[i - sodaSizes[minIndex]], sodaSizes.length);
          combinations[i][minIndex]++;
        }
      }

      // increment array size and start again, this time at a new point
      initialN *= 2;
      startIndex = minimalCosts.length;
      double[] newMinimalCosts = Arrays.copyOf(minimalCosts, initialN);
      minimalCosts = newMinimalCosts;

      int[][] newCombinations = new int[initialN][sodaSizes.length];
      for (int i = 0; i < initialN; i++) {
        if (i < initialN / 2) {
          newCombinations[i] = combinations[i];
        }
      }
      combinations = newCombinations;
    }

    // if we can't find the answer, return a nonsensical answer
    return null;
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
    System.out.println(minimalSodaCost(sodaSizes, costs, 107));
    // System.out.println(Arrays.toString(minimalSodaCostCombinations(sodaSizes,
    // costs, 105)));
    System.out.println(maximumSodaNumber(sodaSizes, costs, 59.6));
    // System.out.println(Arrays.toString(maximumSodaNumberCombinations(sodaSizes,
    // costs, 59.6)));

    try {
      double[] minimalCosts = minimalSodaCosts(sodaSizes, costs, 100);
      File file = new File("minimalcosts.txt");
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      for (double cost : minimalCosts) {
        bw.write(Double.toString(cost));
        bw.write("\n");
      }
      bw.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
