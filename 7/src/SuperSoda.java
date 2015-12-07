import java.util.Arrays;

public class SuperSoda {

  public static double minimalSodaCost(int[] sodaSizes, double[] costs, int n) {
    return 0; 
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
    // System.out.println(Arrays.toString(minimalSodaCostCombinations(sodaSizes, costs, 105)));
  }
}
