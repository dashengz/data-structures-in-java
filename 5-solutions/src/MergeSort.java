import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MergeSort {

  /**
   * Internal method that merges two sorted halves of a subarray (from Weiss
   * Data Structures and Algorithm Analysis in Java)
   * 
   * @param a
   *          an array of Comparable items.
   * @param tmpArray
   *          an array to place the merged result.
   * @param leftPos
   *          the left-most index of the subarray.
   * @param rightPos
   *          the index of the start of the second half.
   * @param rightEnd
   *          the right-most index of the subarray.
   */
  private static void merge(Integer[] a, Integer[] tmpArray, int leftPos, int rightPos, int rightEnd) {
    int leftEnd = rightPos - 1;
    int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;

    // Main loop
    while (leftPos <= leftEnd && rightPos <= rightEnd) {
      if (a[leftPos] <= a[rightPos]) {
        tmpArray[tmpPos++] = a[leftPos++];
      } else {
        tmpArray[tmpPos++] = a[rightPos++];
      }
    }

    while (leftPos <= leftEnd) { // Copy rest of first half
      tmpArray[tmpPos++] = a[leftPos++];
    }

    while (rightPos <= rightEnd) { // Copy rest of right half
      tmpArray[tmpPos++] = a[rightPos++];
    }

    // Copy tmpArray back
    for (int i = 0; i < numElements; i++, rightEnd--) {
      a[rightEnd] = tmpArray[rightEnd];
    }
  }

  /**
   * Merge Sort algorithm. This is the Merge Sort algorithm from from Weiss,
   * Data Structures and Algorithm Analysis in Java, as presented in class.
   * 
   * @param a
   *          an array of Comparable items.
   */
  public static void mergeSort(Integer[] a) {
    Integer[] tmpArray = new Integer[a.length];
    mergeSort(a, tmpArray, 0, a.length - 1);
  }

  /**
   * Internal method that makes recursive calls. This is part of the MergeSort
   * algorithm from from Weiss, Data Structures and Algorithm Analysis in Java,
   * as presented in class.
   * 
   * @param a
   *          an array of Comparable items.
   * @param tmpArray
   *          an array to place the merged result.
   * @param left
   *          the left-most index of the subarray.
   * @param right
   *          the right-most index of the subarray.
   */
  private static void mergeSort(Integer[] a, Integer[] tmpArray, int left, int right) {
    if (left < right) {
      int center = (left + right) / 2;
      mergeSort(a, tmpArray, left, center);
      mergeSort(a, tmpArray, center + 1, right);
      merge(a, tmpArray, left, center + 1, right);
    }
  }

  /**
   * Problem 5: Iterative Bottom-up Merge Sort
   */
  public static void mergeSortB(Integer[] inputArray) {
    int width = 1;

    Integer[] tempArray = new Integer[inputArray.length];

    while (width <= inputArray.length) {
      width *= 2;
      for (int i = 0; i < inputArray.length; i += width) {
        merge(inputArray, tempArray, i, Math.min(i + width / 2, inputArray.length - 1),
            Math.min(i + width - 1, inputArray.length - 1));
      }
    }
    return;
  }

  /**
   * Problem 6: Merge Sort for Lists, Without Side Effects
   */
  public static List<Integer> sortList(List<Integer> inputList) {
    if (inputList.size() == 1) {
      return inputList;
    } else {
      int splitPoint = inputList.size() / 2;

      List<Integer> leftList = inputList.subList(0, splitPoint);
      List<Integer> rightList = inputList.subList(splitPoint, inputList.size());

      return mergeLists(sortList(leftList), sortList(rightList));
    }
  }

  /**
   * New merge method that merges two lists and returns a new list. Use this
   * method to implement sortList.
   */
  public static List<Integer> mergeLists(List<Integer> left, List<Integer> right) {
    // List<Integer> result = new LinkedList<Integer>();
    // Iterator<Integer> leftIte = left.iterator();
    // Iterator<Integer> rightIte = right.iterator();
    //
    // if (left == null || left.size() == 0) {
    // return right;
    // }
    // if (right == null || right.size() == 0) {
    // return left;
    // }
    //
    // Integer leftVal;
    // Integer rightVal;
    //
    // leftVal = leftIte.hasNext() ? leftIte.next() : null;
    // rightVal = rightIte.hasNext() ? rightIte.next() : null;
    //
    // while (leftVal != null && rightVal != null) {
    // if (leftVal < rightVal) {
    // result.add(leftVal);
    // leftVal = leftIte.hasNext() ? leftIte.next() : null;
    // } else {
    // result.add(rightVal);
    // rightVal = rightIte.hasNext() ? rightIte.next() : null;
    // }
    // }
    //
    // if (leftVal != null) {
    // result.add(leftVal);
    // while (leftIte.hasNext()) {
    // result.add(leftIte.next());
    // }
    // }
    //
    // if (rightVal != null) {
    // result.add(rightVal);
    // while (rightIte.hasNext()) {
    // result.add(rightIte.next());
    // }
    // }
    //
    // return result;

    Integer[] arrayBoth = Stream.concat(left.stream(), right.stream()).toArray(Integer[]::new);
    Integer[] temp = new Integer[arrayBoth.length];
    merge(arrayBoth, temp, 0, left.size(), arrayBoth.length - 1);
    return Arrays.asList(arrayBoth);
  }

  public static void main(String[] args) {
    // Weiss sort
    Integer[] a = { 1, 4, 9, 131, 0, 2, 7, 19, 245, 18 };
    MergeSort.mergeSortB(a);
    System.out.println(Arrays.toString(a)); // Should be [0, 1, 2, 4, 7, 9,
    // 18,
    // 19, 131, 245]

    Integer[] b = { 1, 4, 9, 131, 0, 2, 7, 19, 245, 18 };
    List<Integer> list = Arrays.asList(b);
    System.out.println(MergeSort.sortList(list));
    System.out.println(list); // original list not modified
  }
}
